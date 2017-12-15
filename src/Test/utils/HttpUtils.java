package utils;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by xzl on 2017/12/7.
 *
 * @author xzl
 * @date 2017/12/7  12:53.
 */
public class HttpUtils {
    public static void getServiceInfo(String urlPath){
        //用域名创建 InetAddress对象
        InetAddress address1 = null;
        try {
            address1 = InetAddress.getByName("www.qlcoder.com");
            //获取的是该网站的ip地址，如果我们所有的请求都通过nginx的，所以这里获取到的其实是nginx服务器的IP地址
            System.out.println(address1.getHostName());//www.wodexiangce.cn
            System.out.println(address1.getCanonicalHostName());//124.237.121.122
            System.out.println(address1.getHostAddress());//124.237.121.122
            System.out.println("===============");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public static HttpURLConnection getHttpURLConnection(String urlPath,String proxyUrl,String proxyPort, String requestMethod){

        // 设置请求方式（GET/POST）
        StringBuilder buffer = new StringBuilder();
        HttpURLConnection httpUrlConn = null;
        try {
            if(!StringUtils.isEmpty(proxyUrl)&&!StringUtils.isEmpty(proxyUrl)){
                System.setProperty("http.proxyHost",proxyUrl);
                System.setProperty("http.proxyPort",proxyPort);
            }else {
                System.setProperty("http.proxyHost","121.201.63.168");
                System.setProperty("http.proxyPort","8080");
            }
            URL url = new URL(urlPath);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod(requestMethod);
            if("get".equalsIgnoreCase(requestMethod)){
                httpUrlConn.connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  httpUrlConn;
    }
    @Test
    public  void test(){
        getServiceInfo("http://www.qlcoder.com/uploads/passenger.txt");
        System.out.println("*****");


    }
}
