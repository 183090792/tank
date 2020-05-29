package com.example.tank.demo1.strategy;

import com.example.tank.demo1.Bullet;
import com.example.tank.demo1.Tank;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/27 9:17
 */
public class TankFireOne implements Fire {
    @Override
    public void fire(Tank tank) {
        new Bullet(tank.getX(),tank.getY(),tank.getDir(),true,tank.getGroup());
//        tankFrame.bullets.add(new Bullet(tank.getX(),tank.getY(),tank.getDir(),true,tankFrame,tank.getGroup()));
    }
}
