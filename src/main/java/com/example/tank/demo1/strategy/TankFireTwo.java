package com.example.tank.demo1.strategy;

import com.example.tank.demo1.Bullet;
import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Tank;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/27 9:17
 */
public class TankFireTwo implements Fire {
    @Override
    public void fire(Tank tank) {
        Dir[] values = Dir.values();
        for (Dir dir : values) {
            new Bullet(tank.getX(),tank.getY(),dir,true,tank.getTankFrame(),tank.getGroup());
        }
//        new Bullet(tank.getX(),tank.getY(),tank.getDir(),true,tank.getTankFrame(),tank.getGroup());
//        tankFrame.bullets.add(new Bullet(tank.getX(),tank.getY(),tank.getDir(),true,tankFrame,tank.getGroup()));
    }
}
