package com.example.tank.demo1.netty.message;

import com.example.tank.demo1.Dir;
import com.example.tank.demo1.Group;
import com.example.tank.demo1.Tank;
import com.example.tank.demo1.TankFrame;
import com.example.tank.demo1.netty.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.UUID;

@Data
public class TankStopMsg implements Msg {

    private int x, y;
    public UUID id;
    private Dir dir;

    public TankStopMsg(Tank t) {
        this.id = t.getId();
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
    }

    public TankStopMsg(UUID id, int x, int y,Dir dir) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public TankStopMsg() {
    }

    @Override
    public void handle() {
        if (id.equals(TankFrame.TANK_FRAME.tank.getId())) {
            return;
        }
        Tank tank = TankFrame.TANK_FRAME.findTankByUUID(id);
        if (tank != null) {
            tank.setX(x);
            tank.setY(y);
            tank.setDir(dir);
            tank.setMoving(false);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null) {
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
            id = new UUID(dis.readLong(), dis.readLong());
            x = dis.readInt();
            y = dis.readInt();
            dir = Dir.values()[dis.readInt()];
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStop;
    }

}
