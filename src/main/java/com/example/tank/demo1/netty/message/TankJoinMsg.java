package com.example.tank.demo1.netty.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TankJoinMsg implements Msg {
    public int x = 0,y=0;
    public boolean living = true;
//    public int oldX, oldY;

    //    public static final int SPEED = 1;
//    public Dir dir ;
//    public boolean moving = false;
//    public Group group = Group.BAD;
//    public Rectangle rectangle = new Rectangle();
//    public Fire fire ;
    public static void main(String[] args) {
        System.out.println(Byte.SIZE);
    }

}
