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

    public List<GameObject> objects = new ArrayList<>();
    public Tank tank ;

    static {
        GAME_MODEL.init();
    }
    private void init(){
        tank = new Tank(200,500,Dir.UP,false,Group.GOOD);
        for (int i = 0; i < 5; i++) {
            objects.add(new Tank(50+i*80,200,Dir.DOWN,true,Group.BAD));
            objects.add(new Wall(10+i*120,400,80,40));


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

//        做碰撞检测
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                ColliderChan colliderChan = new ColliderChan();
                colliderChan.collider(objects.get(i),objects.get(j));
            }
        }
    }

    public void remove(GameObject gameObject){
        objects.remove(gameObject);
    }

}
