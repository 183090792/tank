package com.example.tank.demo1;

import com.example.tank.demo1.strategy.Fire;
import com.example.tank.demo1.strategy.TankFireOne;
import com.example.tank.demo1.strategy.TankFireTwo;
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
public class Tank extends GameObject {
    private int oldX,oldY;
    private static final int SPEED = 1;
    private Dir dir ;
    private boolean moving = false;
    private Group group = Group.BAD;
    private Rectangle rectangle = new Rectangle();
    private Fire fire ;


    public Tank(int x, int y, Dir dir,boolean moving,  Group group){
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.moving=moving;
        this.group=group;
        this.rectangle.x=this.x;
        this.rectangle.y=this.y;
        if(group==Group.BAD){
            fire= new TankFireOne();
            this.rectangle.width=ResourceMgr.badTankD.getWidth();
            this.rectangle.height=ResourceMgr.badTankD.getHeight();
        }
        if(group==Group.GOOD){
            fire= new TankFireTwo();
            this.rectangle.width=ResourceMgr.goodTankD.getWidth();
            this.rectangle.height=ResourceMgr.goodTankD.getHeight();
        }

    }

    @Override
    public void paint(Graphics graphics) {
        if(!living) {
            GameModel.getInstance().remove(this);
        }
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
                break;
            case DOWN:
                if(group==Group.BAD){
                    graphics.drawImage(ResourceMgr.badTankD,x,y,null);
                }else {
                    graphics.drawImage(ResourceMgr.goodTankD,x,y,null);
                }
                break;
            case UP:
                if(group==Group.BAD){
                    graphics.drawImage(ResourceMgr.badTankU,x,y,null);
                }else {
                    graphics.drawImage(ResourceMgr.goodTankU,x,y,null);
                }
                break;
        }
//        graphics.drawImage(ResourceMgr.goodTankU,x,y,null);
//        graphics.setColor(Color.YELLOW);
//        graphics.fillRect(x,y,50,50);
//        graphics.setColor(color);
        move();
    }


    private void move() {
        oldX=x;
        oldY=y;
        if(!moving){
            return;
        }
        if(group==Group.BAD){
            int random = new Random().nextInt(100);
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
        if(x<0||y<0||x>TankFrame.WIDTH||y>TankFrame.HEIGHT){
            living=false;
            GameModel.getInstance().objects.remove(this);
        }
        boundsCheck();
        rectangle.x=x;
        rectangle.y=y;
    }

    public void back() {
        x=oldX;
        y=oldY;
    }

    public void boundsCheck(){
        if(x<0){
            x=0;
        }
        if(y<0){
            y=0;
        }
        if(group==Group.GOOD){
            if(x>TankFrame.WIDTH-ResourceMgr.goodTankR.getWidth()){
                x=TankFrame.WIDTH-ResourceMgr.goodTankR.getWidth();
            }
            if(y>TankFrame.HEIGHT-ResourceMgr.goodTankD.getHeight()){
                y=TankFrame.HEIGHT-ResourceMgr.goodTankD.getHeight();
            }
        }
        if(group==Group.BAD){
            if(x>TankFrame.WIDTH-ResourceMgr.badTankR.getWidth()){
                x=TankFrame.WIDTH-ResourceMgr.badTankR.getWidth();
            }
            if(y>TankFrame.HEIGHT-ResourceMgr.badTankD.getHeight()){
                y=TankFrame.HEIGHT-ResourceMgr.badTankD.getHeight();
            }
        }

    }

    public void fire(){
        fire.fire(this);
//        tankFrame.bullets.add(new Bullet(x,y,dir,true,tankFrame,group)) ;
    }
}
