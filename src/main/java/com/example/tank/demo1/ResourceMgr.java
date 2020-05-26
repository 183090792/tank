package com.example.tank.demo1;

import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/26 8:54
 */
public class ResourceMgr {
    public static BufferedImage goodTankL,goodTankR,goodTankD,goodTankU;
    public static BufferedImage bulletL,bulletR,bulletD,bulletU;
    static {
        try {
            goodTankU = ImageIO.read(new ClassPathResource("static/images/GoodTank1.png").getInputStream());
            goodTankD = ImageUtil.rotateImage(goodTankU,180);
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);

            bulletU = ImageIO.read(new ClassPathResource("static/images/bulletU.png").getInputStream());
            bulletD = ImageUtil.rotateImage(bulletU,180);
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
;