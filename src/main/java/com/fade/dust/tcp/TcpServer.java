package com.fade.dust.tcp;

import com.fade.dust.DustApplication;
import com.fade.dust.utils.BeanUtil;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TcpServer {
    private static volatile Thread thread;

    public static void start() {
        thread = new Thread(() -> {
            try {
                String serverAddress = "192.168.1.32";
                int serverPort = 20019;
                Socket socket = new Socket(serverAddress, serverPort);
                System.out.println("Tcp Server Started");
                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[17];
                while (!Thread.interrupted()) {
                    int len = inputStream.read(buffer);
                    if (len != 17) {
                        continue;
                    }

                    int eCO2 = ((buffer[2] & 0xFF) << 8) | (buffer[3] & 0xFF);
                    int eCH20 = ((buffer[4] & 0xFF) << 8) | (buffer[5] & 0xFF);
                    int TVOC = ((buffer[6] & 0xFF) << 8) | (buffer[7] & 0xFF);
                    int PM25 = ((buffer[8] & 0xFF) << 8) | (buffer[9] & 0xFF);
                    int PM10 = ((buffer[10] & 0xFF) << 8) | (buffer[11] & 0xFF);
                    double Temperature = ((buffer[12] & 0xFF) + (buffer[13] & 0xFF) * 0.1);
                    double Humidity = ((buffer[14] & 0xFF) + (buffer[15] & 0xFF) * 0.1);

                    //继电器控制
                    tcpclient(PM25);

                    // 添加数据
                    BeanUtil.beanUtil.asyncService.addData(eCO2, eCH20, TVOC, PM25, PM10, Temperature, Humidity);

                    // 清空数组
                    Arrays.fill(buffer, (byte) 0);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    //继电器控制
    private static void tcpclient(double value) throws Exception {
        String ipAddress = "192.168.1.10";
        int portNumber = 20019;
        String hexString1 = "FF0F0000000801FF301D";
        String hexString2 = "FF0F000000080100705D";
        byte[] byteArray1 = DatatypeConverter.parseHexBinary(hexString1);
        byte[] byteArray2 = DatatypeConverter.parseHexBinary(hexString2);
        if(value >= 40){
            try (Socket socket = new Socket(ipAddress, portNumber);
                 OutputStream outputStream = socket.getOutputStream()) {
                outputStream.write(byteArray1);
            }
        }else {
            try (Socket socket = new Socket(ipAddress, portNumber);
                 OutputStream outputStream = socket.getOutputStream()) {
                outputStream.write(byteArray2);
            }
        }
    }

    public static void stop()
    {
        if(thread != null){
            thread.interrupt();
        }
    }
}
