package com.example.tank.demo1.cor;

import com.example.tank.demo1.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChan {
    List<Collider> colliders = new LinkedList<>();
    public ColliderChan(){
        add(new BulletTankCollider());
        add(new BulletWallCollider());
        add(new TankTankCollider());
        add(new TankWallCollider());
    }
    public void add(Collider c) {
        colliders.add(c);
    }

    public boolean collider(GameObject o1,GameObject o2){
        for (int i = 0; i < colliders.size(); i++) {
            if(!colliders.get(i).collider(o1,o2)){
                return false;
            }
        }
        return true;
    }
}
