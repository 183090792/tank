package com.example.tank.demo1;

import com.example.tank.demo1.cor.ColliderChan;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameModel {

    private static final GameModel GAME_MODEL = new GameModel();
    private GameModel(){}
    public static GameModel getInstance(){return GAME_MODEL;}

//    public List<Bullet> bullets = new ArrayList<>();
//    public List<Tank> tanks = new ArrayList<>();
//    public List<Explode> explodes = new ArrayList<>();
    public List<GameObject> objects = new ArrayList<>();
    public Tank tank ;

    static {
        GAME_MODEL.init();
    }
    private void init(){
        tank = new Tank(200,500,Dir.UP,false,true,Group.GOOD);
        for (int i = 0; i < 5; i++) {
            objects.add(new Tank(50+i*80,200,Dir.DOWN,true,true,Group.BAD));
        }
    }

    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.white);
//        graphics.drawString("子弹的数量是：" + bullets.size(), 10, 60);
//        graphics.drawString("敌方坦克的数量是：" + tanks.size(), 10, 80);
//        graphics.drawString("爆炸的数量是：" + explodes.size(), 10, 100);
        graphics.setColor(color);
        tank.paint(graphics);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(graphics);
        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                ColliderChan colliderChan = new ColliderChan();
                colliderChan.collider(objects.get(i),objects.get(j));
            }
        }
//        for (int i = 0; i < objects.size(); i++) {
//            objects.get(i).paint(graphics);
//        }

//        for (int i = 0; i < bullets.size(); i++) {
//            bullets.get(i).paint(graphics);
//        }


//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }
//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ) {
//            Bullet next = it.next();
//            if (!next.isLive()) {
//                it.remove();
////                explode.setExplode(true);
//            }
//        }
//
//        for (Iterator<Tank> it = tanks.iterator(); it.hasNext(); ) {
//            Tank next = it.next();
//            if (!next.isLive()) {
//                it.remove();
//                explodes.add(new Explode(next.getX(), next.getY(), 0, true));
//            }
//        }
//        for (int i = 0; i < explodes.size(); i++) {
//            if (explodes.get(i).isExplode()) {
//                explodes.get(i).paint(graphics);
//            }
//        }
    }

}
