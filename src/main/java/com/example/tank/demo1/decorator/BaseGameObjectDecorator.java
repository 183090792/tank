package com.example.tank.demo1.decorator;

import com.example.tank.demo1.GameObject;

import java.awt.*;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/29 14:29
 */
public abstract class BaseGameObjectDecorator extends GameObject {

    GameObject gameObject;
    public BaseGameObjectDecorator(GameObject gameObject){
        this.gameObject=gameObject;
    }

    @Override
    public abstract void paint(Graphics graphics);
}
