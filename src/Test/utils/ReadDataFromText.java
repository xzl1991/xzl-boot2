package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by xzl on 2017/12/6.
 *
 * @author xzl
 * @date 2017/12/6  10:14.
 */
public class ReadDataFromText {
    public  static JSONArray readText(final String filePath,final String split){//分隔符
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
        JSONArray jsonArray = new JSONArray();
        try ( InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(filePath)),"UTF-8");BufferedReader bufReader = new BufferedReader(isr)){
            String temp ;
            while ((temp = bufReader.readLine())!=null){
                //过滤注解
                if(temp.indexOf("#")>-1||temp.indexOf("//")>-1){
                    continue;
                }
                if(temp.indexOf(split)>-1){
                    jsonArray.add(temp.split(split));
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

    public void createDictionary(List list) throws IOException {
        Map<String, String> titles = new LinkedHashMap<String,String>(){{
            put("DIC_TYPE","DIC_TYPE");
            put("DATA_TYPE","DATA_TYPE");
            put("DIC_KEY","DIC_KEY");
            put("DIC_VALUE","DIC_VALUE");
            put("DIC_ORDER","DIC_ORDER");
            put("DESCRIPTION","DESCRIPTION");
        }};
        SXSSFWorkbook wb = null;
        FileOutputStream ouputStream = new FileOutputStream(new File("D:/city.xlsx"));
        try {
            wb = ExcelUtil.mapToExcel(titles , list);
            String filename = "Dictionary" + new Date() + ".xlsx";
//            wb.create
        }
        finally {
            if(wb!=null){
                wb.close();
            }
            if(ouputStream != null){
                ouputStream.flush();
                ouputStream.close();
            }

        }
    }
    public static void main(String args[]) {
        //空格隔开
//        JSONArray jsonArray = readText("C:\\Users\\Administrator\\Desktop\\新建文件夹 (3)\\Data\\Car\\car.txt");
        //=隔开
        JSONArray jsonArray = readText("E:\\国美文件\\系统文件\\省市区编码.txt","=");
        Arrays.asList(jsonArray);
//        JSONArray jsonArray = readByHttp("http://www.qlcoder.com/uploads/passenger.txt","GET");
        System.out.println("===================");
    }
}
