package com.example.tank.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/25 18:04
 */
@AllArgsConstructor
@Data
public class Bullet {
    private static final int SPEED = 2;
    private static final int WIDTH = 30, HEIGHT = 30;
    private int x,y;

    private Dir dir;
    private boolean live = true;
    private TankFrame tankFrame = null;
    private Group group;


    public void paint(Graphics graphics) {
        int a = x + ResourceMgr.goodTankD.getWidth() / 2 - ResourceMgr.bulletD.getWidth() / 2;
        int b = y + ResourceMgr.goodTankD.getHeight()/2;
        switch (dir){
            case RIGHT:
                graphics.drawImage(ResourceMgr.bulletR,x + ResourceMgr.goodTankR.getWidth(),
                        y + ResourceMgr.goodTankR.getHeight()/2-ResourceMgr.bulletR.getHeight()/2 ,null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.bulletL,x - ResourceMgr.bulletD.getWidth() ,
                        y + ResourceMgr.goodTankD.getHeight()/2-ResourceMgr.bulletR.getHeight()/2,null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.bulletD,x+ResourceMgr.goodTankD.getWidth() / 2 - ResourceMgr.bulletD.getWidth() / 2,
                        y + ResourceMgr.goodTankD.getHeight(),null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.bulletU,x + ResourceMgr.goodTankD.getWidth() / 2 - ResourceMgr.bulletD.getWidth() / 2,
                        y - ResourceMgr.goodTankD.getHeight()/2,null);
                break;
        }
//        Color color = graphics.getColor();
//        graphics.setColor(Color.RED);
//        graphics.fillOval(x,y,WIDTH,HEIGHT);
//        graphics.setColor(color);
        move();
    }
    private void move() {
        switch (dir){
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
        }

        if(x<0||y<0||x>tankFrame.getWidth()||y>tankFrame.getHeight()){
            live=false;
            tankFrame.bullets.remove(this);
        }
    }

    public void collideWith(Tank tank) {
        if(group==tank.getGroup()){
            return;
        }
        Rectangle rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle rectangle1 = new Rectangle(tank.getX(), tank.getY(), WIDTH, HEIGHT);
        if(rectangle.intersects(rectangle1)){
//            tankFrame.bullets.remove(this);
            live=false;
            tank.setLive(false);
//            tankFrame.tanks.remove(tank);
//            tankFrame..setLive(false);
        }
//        if(this)
    }


}
