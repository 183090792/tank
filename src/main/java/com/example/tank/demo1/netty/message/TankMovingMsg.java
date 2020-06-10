package com.example.tank.demo1.netty.message;

import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TankMovingMsg implements Msg {

    private int x,y;
    private Dir dir ;
    private boolean moving = false;
    private boolean living = true;
    private Group group = Group.BAD;
    public UUID id = UUID.randomUUID();

    @Override
    public void handle() {

    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public void parse(byte[] bytes) {

    }

    @Override
    public MsgType getMsgType() {
        return null;
    }

//    public int oldX, oldY;

    //    public static final int SPEED = 1;
//    public Dir dir ;
//    public boolean moving = false;
//    public Group group = Group.BAD;
//    public Rectangle rectangle = new Rectangle();
//    public Fire fire ;

}
