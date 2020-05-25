package com.example.tank;

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
    private static final int SPEED = 10;
    private static final int WIDTH = 30, HEIGHT = 30;
    private int x,y;

    private Dir dir;
    private boolean emission;


    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillOval(x,y,WIDTH,HEIGHT);
        graphics.setColor(color);
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
    }
}
