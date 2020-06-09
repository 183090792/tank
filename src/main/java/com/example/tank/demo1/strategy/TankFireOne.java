package com.example.tank.demo1.strategy;

import com.example.tank.demo1.Bullet;
import com.example.tank.demo1.GameModel;
import com.example.tank.demo1.Tank;
import com.example.tank.demo1.decorator.RectDecorator;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/27 9:17
 */
public class TankFireOne implements Fire {
    @Override
    public void fire(Tank tank) {
        GameModel.getInstance().objects.add(new RectDecorator(new Bullet(tank.getX(),tank.getY(),tank.getDir(),tank.getGroup())));
//        new Bullet(tank.getX(),tank.getY(),tank.getDir(),tank.getGroup());
//        tankFrame.bullets.add(new Bullet(tank.getX(),tank.getY(),tank.getDir(),true,tankFrame,tank.getGroup()));
    }
}
