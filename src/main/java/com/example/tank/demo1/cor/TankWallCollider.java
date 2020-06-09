package com.example.tank.demo1.cor;

import com.example.tank.demo1.GameObject;
import com.example.tank.demo1.Tank;
import com.example.tank.demo1.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if(tank.getRectangle().intersects(wall.getRectangle())){
                tank.back();
                return false;
            }
        }else if(o1 instanceof Wall && o2 instanceof Tank){
            return collider(o2,o1);
        }
        return true;
    }
}
