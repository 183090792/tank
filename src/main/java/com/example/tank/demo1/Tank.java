package com.example.tank.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

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
    private static final int SPEED = 10;
    private boolean moving = false;
    private TankFrame tankFrame = null;

    public void paint(Graphics graphics) {
//        Color color = graphics.getColor();
        switch (dir){
            case RIGHT:
                graphics.drawImage(ResourceMgr.goodTankR,x,y,null);
                break;
            case LEFT:
                graphics.drawImage(ResourceMgr.goodTankL,x,y,null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.goodTankD,x,y,null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.goodTankU,x,y,null);
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
    }

    public void fire(){
        tankFrame.bullets.add(new Bullet(x,y,dir,true,tankFrame)) ;
    }
}
