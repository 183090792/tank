package com.example.tank.demo1;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * 功能说明：
 * 单例模式
 *
 * @author LYZ
 * @date 2020/5/26 17:22
 */

public class PropertyMgr {





//    第三种 静态内部类保证单例，线程安全基于JVM，JVM每个类只会加载一次
    private PropertyMgr(){}
    private static class PropertyMgrHolder{
        private static final  PropertyMgr propertyMgr = new PropertyMgr();
    }
    public static PropertyMgr getInstance(){
        return PropertyMgrHolder.propertyMgr;
    }

//    第二种单例，利用枚举完成单例  最完美的单例
//    public enum PropertyMgrEnum{
//        INTERFACE ;
//
//        public static void main(String[] args) {
//            PropertyMgrEnum.INTERFACE.hashCode();
//        }
//    }



//    第一种单利
//    private PropertyMgr(){}
//    static Properties properties ;
//    private volatile static PropertyMgr propertyMgr;
//    static {
//        try {
//            properties.load(new ClassPathResource("config").getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static PropertyMgr getInstance(){
//        if(propertyMgr==null){
//            synchronized (PropertyMgr.class){
//                if(propertyMgr==null){
//                    propertyMgr = new PropertyMgr();
//                }
//            }
//        }
//        return propertyMgr;
//    }
}
