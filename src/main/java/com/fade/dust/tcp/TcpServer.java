package com.fade.dust.tcp;

import com.fade.dust.DustApplication;
import com.fade.dust.utils.BeanUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class TcpServer {
    private static volatile Thread thread;

    public static void start(){
        thread = new Thread(() -> {
            try {
                DatagramSocket socket = new DatagramSocket(3000);
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf,buf.length);
                while (!Thread.interrupted()) {
                    socket.receive(packet);
                    int len = packet.getLength();
                    String rs = new String(buf, 0, len);
                    System.out.println("收到了ip为：" + packet.getAddress() + " 端口号为：" + packet.getPort() + "的消息：" + rs);
                    String[] data = rs.split("-");
                    if(data.length == 2 && !data[0].equals("") && !data[1].equals("")){
                        if(DustApplication.types.containsKey(data[0])){
                           BeanUtil.beanUtil.asyncService.addData(data[0],data[1]);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        thread.start();
    }

    public static void stop()
    {
        if(thread != null){
            thread.interrupt();
        }
    }
}
