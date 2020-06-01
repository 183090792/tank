package com.example.tank.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/26 13:55
 */
@Data
public class Explode extends GameObject {
    private int step = 0;
    private boolean isExplode;
    public Explode(int x,int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void paint(Graphics graphics){
        graphics.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step>=ResourceMgr.explodes.length){
            step=0;
            isExplode=false;
            GameModel.getInstance().objects.remove(this);
        }
    }
}
