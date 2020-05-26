package com.example.tank.demo1;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * 功能说明：
 *
 * @author LYZ
 * @date 2020/5/26 17:22
 */
public class PropertyMgr {
    private PropertyMgr(){}
    static Properties properties ;
    private static PropertyMgr propertyMgr;
    static {
        try {
            properties.load(new ClassPathResource("config").getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyMgr getInstance(){
        if(propertyMgr==null){
            propertyMgr = new PropertyMgr();
        }
        return propertyMgr;
    }
}
