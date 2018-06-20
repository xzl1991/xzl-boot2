package nio.socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ${xzl} on 2017/9/28.
 */
public class ClientTest {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";  //要连接的服务端IP地址
        int port = 999;   //要连接的服务端对应的监听端口
        //与服务端建立连接
        Socket client = new Socket(host,port);
        // 发送消息
        Writer writer = new OutputStreamWriter(client.getOutputStream());
        writer.write("我是客户端 ---1");
        writer.flush();//写完后要记得flush
        writer.close();
        client.close();
    }
}
