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
public class Tank {
    private static final int SPEED = 1;
    private int x,y;
    private Dir dir ;
    private boolean moving = false;
    private TankFrame tankFrame = null;
    private boolean live = true;
    private Group group = Group.BAD;
    private Rectangle rectangle = new Rectangle();

    public Tank(int x, int y, Dir dir,boolean moving, TankFrame tankFrame, boolean live, Group group){
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.live=live;
        this.moving=moving;
        this.tankFrame=tankFrame;
        this.group=group;
        this.rectangle.x=this.x;
        this.rectangle.y=this.y;
        if(group==Group.BAD){
            this.rectangle.width=ResourceMgr.badTankD.getWidth();
            this.rectangle.height=ResourceMgr.badTankD.getHeight();
        }
        if(group==Group.GOOD){
            this.rectangle.width=ResourceMgr.goodTankD.getWidth();
            this.rectangle.height=ResourceMgr.goodTankD.getHeight();
        }

    }

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
                dir = Dir.random();
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
            if(random>95){
                fire();
            }
        }
        if(x<0||y<0||x>tankFrame.getWidth()||y>tankFrame.getHeight()){
            live=false;
            tankFrame.tanks.remove(this);
        }
        boundsCheck();
        rectangle.x=x;
        rectangle.y=y;
    }
    public void boundsCheck(){
        if(x<0){
            x=0;
        }
        if(y<0){
            y=0;
        }
        if(group==Group.GOOD){
            if(x>tankFrame.getWidth()-ResourceMgr.goodTankR.getWidth()){
                x=tankFrame.getWidth()-ResourceMgr.goodTankR.getWidth();
            }
            if(y>tankFrame.getHeight()-ResourceMgr.goodTankD.getHeight()){
                y=tankFrame.getHeight()-ResourceMgr.goodTankD.getHeight();
            }
        }
        if(group==Group.BAD){
            if(x>tankFrame.getWidth()-ResourceMgr.badTankR.getWidth()){
                x=tankFrame.getWidth()-ResourceMgr.badTankR.getWidth();
            }
            if(y>tankFrame.getHeight()-ResourceMgr.badTankD.getHeight()){
                y=tankFrame.getHeight()-ResourceMgr.badTankD.getHeight();
            }
        }

    }

    public void fire(){
        tankFrame.bullets.add(new Bullet(x,y,dir,true,tankFrame,group)) ;
        System.out.println(group+"**************");
    }
}
