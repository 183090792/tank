package com.example.tank.demo1;

import lombok.Data;

import java.awt.*;
@Data
public abstract class GameObject {
    public int x,y;
    boolean living = true;
    public abstract void paint(Graphics graphics);
}
