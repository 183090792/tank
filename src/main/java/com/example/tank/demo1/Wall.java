package com.example.tank.demo1;

import lombok.Data;

import java.awt.*;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/29 12:22
 */
@Data
public class Wall extends GameObject {
    int w, h;
    public Rectangle rectangle;
    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rectangle = new Rectangle(x, y, w, h);
    }
    @Override
    public void paint(Graphics g) {
        if(!living) {
            GameModel.getInstance().remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);
    }
}
