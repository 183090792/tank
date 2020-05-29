package com.example.tank;

import com.example.tank.demo1.ResourceMgr;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TankApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("******************************"+new ClassPathResource("GoodTank1.png").getPath());
        BufferedImage image2 = null;
        try {
            image2 = ImageIO.read(new ClassPathResource("static/images/GoodTank1.png").getInputStream());
////            image2 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("/images/GoodTank1.png"));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
