package com.example.tank.demo1.netty.message;

import com.example.tank.demo1.Bullet;
import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import com.example.tank.demo1.TankFrame;

import java.io.*;
import java.util.UUID;

public class BulletNewMsg implements Msg {
    private int x,y;

    private Dir dir;
    private Group group;private boolean live = true;
    UUID playerId;
    private UUID id = UUID.randomUUID();

    public BulletNewMsg(UUID playerId, int x, int y, Dir dir, Group group){
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
//        this.tf = tf;
//
//        rect.x = this.x;
//        rect.y = this.y;
//        rect.width = WIDTH;
//        rect.height = HEIGHT;
    }
    public BulletNewMsg(){}

    @Override
    public void handle() {
        if (this.playerId.equals(TankFrame.TANK_FRAME.getMainTank().getId()))
            return;

        Bullet bullet = new Bullet(playerId, x, y, dir,live, TankFrame.TANK_FRAME,group);
        bullet.setId(this.id);
        TankFrame.TANK_FRAME.bullets.add(bullet);
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
            dos.writeLong(playerId.getMostSignificantBits());
            dos.writeLong(playerId.getLeastSignificantBits());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(baos!=null){
                    baos.close();
                }
                if(dos!=null){
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            x = dis.readInt();
            y = dis.readInt();
            dir = Dir.values()[dis.readInt()];
            group = Group.values()[dis.readInt()];
            playerId = new UUID(dis.readLong(),dis.readLong());
            id = new UUID(dis.readLong(),dis.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public MsgType getMsgType() {
        return MsgType.BulletNew;
    }
}
