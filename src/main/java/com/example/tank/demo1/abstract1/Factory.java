package com.example.tank.demo1.abstract1;

import com.example.tank.demo1.Explode;
import com.example.tank.demo1.Tank;
import com.example.tank.demo1.strategy.Fire;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/27 10:20
 */
public abstract class Factory {
    abstract Tank creatTank();
    abstract Fire fire();
    abstract Explode creatExplode();
}
