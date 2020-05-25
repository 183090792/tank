package com.example.tank;

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

    public void paint(Graphics graphics) {
        graphics.fillRect(x,y,50,50);
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
}
