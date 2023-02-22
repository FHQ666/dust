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
        types.put("1","PM2.5");
        types.put("2","温度");
        types.put("3","湿度");
        types.put("4","噪声");
    };
    public static final Map<String,String> units= new HashMap<String,String>();
    {
        units.put("1","微米");
        units.put("2","°C");
        units.put("3","hPa");
        units.put("4","dB");
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
