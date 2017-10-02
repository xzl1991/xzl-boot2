package main.nio.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ${xzl} on 2017/9/28.
 */
public class ServerTest {
    //服务端
    public static void main(String[] args) throws Exception {
        //1.定义一个ServerSocket监听在端口999上
        ServerSocket serverSocket = new ServerSocket(999);
        //2.server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
        Socket socket = serverSocket.accept();//
        //3.跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
        Reader reader = new InputStreamReader(socket.getInputStream());
        Reader reader1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));//InputStreamReader(socket.getInputStream());
        char buff[] = new char[64];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len=reader.read(buff))!=-1){
            //读数据
            sb.append(new String(buff, 0, len));
        }
        System.out.println("客户端数据 : " + sb);
        reader.close();
        socket.close();
        serverSocket.close();
    }
}
