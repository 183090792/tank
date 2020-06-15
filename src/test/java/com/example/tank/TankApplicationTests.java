package com.example.tank;

import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import com.example.tank.demo1.netty.TankMsgDecoder;
import com.example.tank.demo1.netty.TankMsgEncoder;
import com.example.tank.demo1.netty.message.MsgType;
import com.example.tank.demo1.netty.message.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TankApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("******************************"+new ClassPathResource("GoodTank1.png").getPath());
        BufferedImage image2 = null;
        try {
            image2 = ImageIO.read(new ClassPathResource("static/images/GoodTank1.png").getInputStream());
////            image2 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("/images/GoodTank1.png"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEncoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.UP, true, Group.BAD, id);
        ch.pipeline().addLast(new TankMsgEncoder());
        ch.writeOutbound(msg);
        ByteBuf buf = ch.readOutbound();
        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankJoin,msgType);
        int length = buf.readInt();
        assertEquals(33,length);
        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Dir dir = Dir.values()[dirOrdinal];
        boolean moving = buf.readBoolean();
        int groupOrdinal = buf.readInt();
        Group g = Group.values()[groupOrdinal];
        UUID uuid = new UUID(buf.readLong(), buf.readLong());

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Dir.UP, dir);
        assertEquals(true, moving);
        assertEquals(Group.BAD, g);
        assertEquals(id, uuid);

    }

    @Test
    void testDecoder() {
        EmbeddedChannel ch = new EmbeddedChannel();


        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.BAD, id);
        ch.pipeline()
                .addLast(new TankMsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        System.out.println("MsgType.TankJoin : "+MsgType.TankJoin.ordinal());
        buf.writeInt(MsgType.TankJoin.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());

        TankJoinMsg msgR = (TankJoinMsg)ch.readInbound();



        assertEquals(5, msgR.x);
        assertEquals(10, msgR.y);
        assertEquals(Dir.DOWN, msgR.dir);
        assertEquals(true, msgR.moving);
        assertEquals(Group.BAD, msgR.group);
        assertEquals(id, msgR.id);
    }

}