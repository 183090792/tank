package com.example.tank.demo1.netty.message;

import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import com.example.tank.demo1.Tank;
import com.example.tank.demo1.TankFrame;
import com.example.tank.demo1.netty.Client;
import com.example.tank.demo1.strategy.Fire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.UUID;

/**
 * @author intellif
 */
@Data
public class TankJoinMsg implements Msg {

    public int x,y;
    public Dir dir ;
    public boolean moving = false;
    public Group group = Group.BAD;
    public UUID id = UUID.randomUUID();

    public TankJoinMsg(Tank t) {
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.group = t.getGroup();
        this.id = t.getId();
        this.moving = t.isMoving();
    }
    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }
    public TankJoinMsg() {
    }

    public void parse(byte[] bytes){
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            x = dataInputStream.readInt();
            y=dataInputStream.readInt();
            dir = Dir.values()[dataInputStream.readInt()];
            moving = dataInputStream.readBoolean();
            group = Group.values()[dataInputStream.readInt()];
            id = new UUID(dataInputStream.readLong(),dataInputStream.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] toBytes(){
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                baos.close();
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    public void handle(){
        if(id.equals(TankFrame.TANK_FRAME.tank.id) || TankFrame.TANK_FRAME.findTankByUUID(id) != null){
            Tank tank = new Tank(this);
            TankFrame.TANK_FRAME.tanks.put(tank.getId(),tank);
            Client.CLIENT.send(new TankJoinMsg(TankFrame.TANK_FRAME.tank));
        }
    }
    public MsgType getMsgType(){
        return MsgType.TankJoin;
    }
}
