package com.example.tank.demo1.cor;

import com.example.tank.demo1.*;

public class TankTankCollider implements Collider {
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;
            if(tank1.getRectangle().intersects(tank2.getRectangle())){
                if(tank1.getGroup()==tank2.getGroup()) {
                    tank1.back();
                    tank2.back();
                    return false;
                }
            }
        }
        return true;
    }
}