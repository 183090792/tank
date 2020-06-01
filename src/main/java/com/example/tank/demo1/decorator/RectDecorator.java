package com.example.tank.demo1.decorator;

import com.example.tank.demo1.GameObject;

import java.awt.*;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/29 15:00
 */
public class RectDecorator extends BaseGameObjectDecorator {

    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics graphics) {
        this.x = gameObject.getX();
        this.y = gameObject.getY();

        gameObject.paint(graphics);

        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(super.gameObject.getX(), super.gameObject.getY(), 100, 140);
        graphics.setColor(c);
    }
}
