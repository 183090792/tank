package com.example.tank.demo1.abstract1;

import com.example.tank.demo1.Explode;
import com.example.tank.demo1.Tank;
import com.example.tank.demo1.strategy.Fire;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/27 10:23
 */
public class TankFactory extends Factory {
    @Override
    Tank creatTank() {
        return null;
    }

    @Override
    Fire fire() {
        return null;
    }

    @Override
    Explode creatExplode() {
        return null;
    }
}
