package com.example.tank.netty.message;

import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import com.example.tank.demo1.strategy.Fire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TankMsg implements Msg {
    public int x = 0,y=0;
//    public boolean living = true;
//    public int oldX, oldY;

    //    public static final int SPEED = 1;
//    public Dir dir ;
//    public boolean moving = false;
//    public Group group = Group.BAD;
//    public Rectangle rectangle = new Rectangle();
//    public Fire fire ;
    public static void main(String[] args) {
        TankMsg tankMsg = new TankMsg();
        System.out.println(tankMsg);
    }

}
