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
@AllArgsConstructor
public class Explode {
    private int x , y ;
    private TankFrame tankFrame;
    private int step = 0;
    private boolean isExplode;
    public void paint(Graphics graphics){
        graphics.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step>=ResourceMgr.explodes.length){
            step=0;
            isExplode=false;
            tankFrame.explodes.remove(this);
        }
    }
}
