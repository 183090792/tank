package com.example.tank;


import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/25 9:48
 */
public class TankFrame extends Frame {
    static int WIDTH=800,HEIGHT=600;
//    Dir dir = Dir.DOWN;
//    private static final int SPEED = 10;
    Tank tank = new Tank(200,200,Dir.DOWN,false);
    Bullet bullet = new Bullet(300,300,Dir.DOWN,false);
    public TankFrame(){
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 双缓冲解决卡顿问题
     * @param graphics
     */
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage = createImage(WIDTH,HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics graphics){
        tank.paint(graphics);
        bullet.paint(graphics);
    }

    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        /**
         * 按键按下事件
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {

//        TankFrame.x+=10;
//        TankFrame.y+=10;

            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
            repaint();
            System.out.println("keyPressed");
        }

        /**
         * 按键抬起事件
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
            System.out.println("keyReleased");
        }
        public void setMainTankDir(){

            if(!bL && !bR && !bU && !bD){
                tank.setMoving(false);
            }else {
                tank.setMoving(true);
                if(bL){
                    tank.setDir(Dir.LEFT);
                }
                if(bR){
                    tank.setDir(Dir.RIGHT);
                }
                if(bU){
                    tank.setDir(Dir.UP);
                }
                if(bD){
                    tank.setDir(Dir.DOWN);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        while (true){
            Thread.sleep(500);
            tankFrame.repaint();
        }
    }


}


