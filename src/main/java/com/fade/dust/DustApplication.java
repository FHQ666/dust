package com.fade.dust;

import com.fade.dust.tcp.TcpServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DustApplication implements CommandLineRunner {

    public static final Map<String,String> types= new HashMap<String,String>();
    {
        types.put("1", "eCO2");
        types.put("2", "eCH20");
        types.put("3", "TVOC");
        types.put("4", "PM2.5");
        types.put("5", "PM10");
        types.put("6", "温度");
        types.put("7", "湿度");
    };

    //对应pojo数据参数名称
    public static final Map<String,String> typesField= new HashMap<String,String>();
    {
        typesField.put("1", "eCO2");
        typesField.put("2", "eCH20");
        typesField.put("3", "TVOC");
        typesField.put("4", "PM25");
        typesField.put("5", "PM10");
        typesField.put("6", "Temperature");
        typesField.put("7", "Humidity");
    };

    public static final Map<String,String> units= new HashMap<String,String>();
    {
        units.put("1", "ppm");
        units.put("2", "ppb");
        units.put("3", "ppb");
        units.put("4", "μg/m³");
        units.put("5", "μg/m³");
        units.put("6", "°C");
        units.put("7", "%");
    };

    public static void main(String[] args) {
        SpringApplication.run(DustApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner");
        TcpServer.start();
    }
}
