package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xzl on 2017/12/6.
 *
 * @author xzl
 * @date 2017/12/6  10:14.
 */
public class ReadDataFromText {
    public  static JSONArray readText(final String filePath){
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
        JSONArray jsonArray = new JSONArray();
        try (BufferedReader bufReader = new BufferedReader(new FileReader(new File(filePath)))){
            String temp ;
            while ((temp = bufReader.readLine())!=null){
                if(temp.indexOf(" ")>-1){
                    jsonArray.add(temp.split(" "));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  jsonArray;
    }

    public static JSONArray readByHttp(String urlPath, String requestMethod){
        JSONArray jsonArray = new JSONArray();
        try {
            URL url = new URL(urlPath);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            // 设置请求方式（GET/POST）
            StringBuilder buffer = new StringBuilder();
            httpUrlConn.setRequestMethod(requestMethod);
            if("get".equalsIgnoreCase(requestMethod)){
                httpUrlConn.connect();
            }
            // 将返回的输入流转换成字符串
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(),
                    "utf-8"));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
                jsonArray.add(str);
            }
            bufferedReader.close();
            httpUrlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  jsonArray;
    }
    public static JSONArray readByHttpConnection(HttpURLConnection httpUrlConn){
        JSONArray jsonArray = new JSONArray();
        try {
            // 将返回的输入流转换成字符串
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(),
                    "utf-8"));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                jsonArray.add(str);
            }
            bufferedReader.close();
            httpUrlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  jsonArray;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
    public static void main(String args[]) {
//        JSONArray jsonArray = readText("C:\\Users\\Administrator\\Desktop\\新建文件夹 (3)\\Data\\Car\\car.txt");
        JSONArray jsonArray = readByHttp("http://www.qlcoder.com/uploads/passenger.txt","GET");
        System.out.println("===================");
    }
}
