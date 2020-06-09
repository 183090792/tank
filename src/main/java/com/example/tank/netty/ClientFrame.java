package com.example.tank.netty;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/6/4 17:29
 */
public class ClientFrame extends Frame {
    public static final ClientFrame CLIENT_FRAME = new ClientFrame();
//    TextArea ta = new TextArea();
//    TextField tf = new TextField();
    Client client ;
    public ClientFrame(){
        this.setSize(600,400);
        this.setLocation(100,20);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
//        this.add(ta,BorderLayout.CENTER);
//        this.add(tf,BorderLayout.SOUTH);
//        tf.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                client.send(tf.getText());
//                ta.setText(ta.getText()+System.getProperty("line.separator")+tf.getText());
//                tf.setText("");
//            }
//        });
    }
    private void connectToServer(){
        client=new Client();
        client.connect();
    }

    public void updateText(String msg){
//        this.ta.setText(ta.getText()+System.getProperty("line.separator")+msg);
    }

    public static void main(String[] args) {
        ClientFrame frame = ClientFrame.CLIENT_FRAME;
        frame.setVisible(true);
        frame.connectToServer();
    }

    @Override
    public void paint(Graphics graphics) {
    Color color = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.drawString("子弹的数量是：" + 5, 10, 60);
//        graphics.drawString("敌方坦克的数量是：" + tanks.size(), 10, 80);
//        graphics.drawString("爆炸的数量是：" + explodes.size(), 10, 100);
        graphics.setColor(color);
    }


}
