package com.example.tank.demo1.cor;

import com.example.tank.demo1.Bullet;
import com.example.tank.demo1.GameObject;
import com.example.tank.demo1.Tank;
import com.example.tank.demo1.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;
            if(bullet.getRectangle().intersects(wall.getRectangle())){
                bullet.setLiving(false);
                wall.setLiving(false);
                return false;
            }
        }else if(o1 instanceof Wall && o2 instanceof Bullet){
            return collider(o2,o1);
        }
        return true;
    }
}
