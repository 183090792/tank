package com.example.tank.demo1;


import com.example.tank.demo1.netty.Client;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/25 9:48
 */
public class TankFrame extends Frame {
    public static TankFrame TANK_FRAME = new TankFrame();
    Client client ;
    static int WIDTH=800,HEIGHT=600;
    public List<Bullet> bullets = new ArrayList<>();
//    public List<Tank> tanks = new ArrayList<>();
    public Map<UUID,Tank> tanks = new HashMap<>();
    public List<Explode> explodes = new ArrayList<>();
//    Dir dir = Dir.DOWN;
//    private static final int SPEED = 10;
    public Tank tank = new Tank(200,500,Dir.UP,false,this,true,Group.GOOD, UUID.randomUUID());
//    Bullet bullet = new Bullet(300,300,Dir.DOWN,true,this);
//    Explode explode = new Explode(100,100,this,0,false);
    private TankFrame(){
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

    public Tank findTankByUUID(UUID id){
        Tank tank = tanks.get(id);
        return tank;
    }

    /**
     * 双缓冲解决卡顿问题
     * @param graphics
     */
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
//        System.out.println("Graphics 开始就被调用");
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

    @SneakyThrows
    @Override
    public void paint(Graphics graphics){
//        System.out.println("开始就被调用");
        Color color = graphics.getColor();
        graphics.setColor(Color.white);
        graphics.drawString("子弹的数量是："+bullets.size(),10,60);
        graphics.drawString("敌方坦克的数量是："+tanks.size(),10,80);
        graphics.drawString("爆炸的数量是："+explodes.size(),10,100);
        graphics.setColor(color);
        tank.paint(graphics);
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(graphics);
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(graphics);
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        for(Iterator<Bullet> it = bullets.iterator();it.hasNext();){
            Bullet next = it.next();
            if(!next.isLive()){
                it.remove();
//                explode.setExplode(true);
            }
        }

        Collection<Tank> values = tanks.values();
        for (Iterator<Tank> it = values.iterator();it.hasNext();){
            Tank next = it.next();
            if(!next.isLive()){
                it.remove();
                explodes.add(new Explode(next.getX(),next.getY(),this,0,true));
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            if(explodes.get(i).isExplode()){
                explodes.get(i).paint(graphics);
            }
        }

//        explode.paint(graphics);


//        for (Bullet bullet1 : bullets) {
//            if(!bullet1.isLive()){
//                bullets.remove(bullet1);
//            }
//        }
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
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                default:
                    break;
            }
            setMainTankDir();
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

//    private void connectToServer(){
////        client=new Client();
////        client.connect();
////    }
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = TANK_FRAME;
        tankFrame.setVisible(true);
        tankFrame.setSize(WIDTH,HEIGHT);
//        TankFrame tankFrame = new TankFrame();

//        TANK_FRAME.connectToServer();
        Client.CLIENT.connect();
//        for (int i = 0; i < 5; i++) {
//            tankFrame.tanks.add(new Tank(50+i*80,200,Dir.DOWN,true,tankFrame,true,Group.BAD));
//        }
        while (true){
            Thread.sleep(15);
            tankFrame.repaint();
        }
    }


}


