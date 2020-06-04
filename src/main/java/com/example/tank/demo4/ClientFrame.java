package com.example.tank.demo4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/6/4 17:29
 */
public class ClientFrame extends Frame {
    public static final ClientFrame CLIENT_FRAME = new ClientFrame();
    TextArea ta = new TextArea();
    TextField tf = new TextField();
    Client client ;
    public ClientFrame(){
        this.setSize(600,400);
        this.setLocation(100,20);
        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send(tf.getText());
                ta.setText(ta.getText()+System.getProperty("line.separator")+tf.getText());
                tf.setText("");
            }
        });
    }
    private void connectToServer(){
        client=new Client();
        client.connect();
    }

    public void updateText(String msg){
        this.ta.setText(ta.getText()+System.getProperty("line.separator")+msg);
    }

    public static void main(String[] args) {
        ClientFrame frame = ClientFrame.CLIENT_FRAME;
        frame.setVisible(true);
        frame.connectToServer();
    }

}
