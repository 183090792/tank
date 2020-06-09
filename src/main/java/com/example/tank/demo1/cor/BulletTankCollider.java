package com.example.tank.demo1.cor;

import com.example.tank.demo1.*;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Bullet){
            Tank tank = (Tank) o1;
            Bullet bullet = (Bullet) o2;
            if(tank.getGroup()==bullet.getGroup()) {
                return true;
            }
            if(tank.getRectangle().intersects(bullet.getRectangle())){
                tank.setLiving(false);
                bullet.setLiving(false);
                GameModel.getInstance().objects.add(new Explode(tank.getX(),tank.getY()));
                return false;
            }
        }else if(o1 instanceof Bullet && o2 instanceof Tank){
            return collider(o2,o1);
        }
        return true;
    }
}
