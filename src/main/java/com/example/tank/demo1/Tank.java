package com.example.tank.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/25 14:56
 */
@Data
@AllArgsConstructor
public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 1;
    private boolean moving = false;
    private TankFrame tankFrame = null;
    private boolean live = true;
    private Group group = Group.BAD;

    public void paint(Graphics graphics) {
//        Color color = graphics.getColor();
        switch (dir){
            case RIGHT:
                if(group==Group.BAD){
                    graphics.drawImage(ResourceMgr.badTankR,x,y,null);
                }else {
                    graphics.drawImage(ResourceMgr.goodTankR,x,y,null);
                }
                break;
            case LEFT:
                if(group==Group.BAD){
                    graphics.drawImage(ResourceMgr.badTankL,x,y,null);
                }else {
                    graphics.drawImage(ResourceMgr.goodTankL,x,y,null);
                }
//                graphics.drawImage(ResourceMgr.goodTankL,x,y,null);
                break;
            case DOWN:
                if(group==Group.BAD){
                    graphics.drawImage(ResourceMgr.badTankD,x,y,null);
                }else {
                    graphics.drawImage(ResourceMgr.goodTankD,x,y,null);
                }
//                graphics.drawImage(ResourceMgr.goodTankD,x,y,null);
                break;
            case UP:
                if(group==Group.BAD){
                    graphics.drawImage(ResourceMgr.badTankU,x,y,null);
                }else {
                    graphics.drawImage(ResourceMgr.goodTankU,x,y,null);
                }
//                graphics.drawImage(ResourceMgr.goodTankU,x,y,null);
                break;
        }
//        graphics.drawImage(ResourceMgr.goodTankU,x,y,null);
//        graphics.setColor(Color.YELLOW);
//        graphics.fillRect(x,y,50,50);
//        graphics.setColor(color);
        move();
    }

    private void move() {
        if(!moving){
            return;
        }
        if(group==Group.BAD){
            int random = new Random().nextInt(100);
            System.out.println(random);
            if(random>95){
                fire();
            }

        }
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
        if(group==Group.BAD){
            int random = new Random().nextInt(100);
            System.out.println(random);
            if(random>95){
                fire();
            }
        }
        if(x<0||y<0||x>tankFrame.getWidth()||y>tankFrame.getHeight()){
            live=false;
            tankFrame.tanks.remove(this);
        }
    }

    public void fire(){
        tankFrame.bullets.add(new Bullet(x,y,dir,true,tankFrame,group)) ;
    }
}
