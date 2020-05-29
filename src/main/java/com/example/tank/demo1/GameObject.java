package com.example.tank.demo1;

import lombok.Data;

import java.awt.*;
@Data
public abstract class GameObject {
    int x,y;
    abstract void paint(Graphics graphics);
}
