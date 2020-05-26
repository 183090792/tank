package com.example.tank.demo1;

import java.util.Random;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/25 14:17
 */
public enum Dir {
    LEFT,RIGHT,UP,DOWN;
    private static Random random = new Random();

    public static Dir random() {
        return Dir.values()[random.nextInt(Dir.values().length)];
    }
}
