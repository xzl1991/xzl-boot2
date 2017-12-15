package qlcoder;

import com.alibaba.fastjson.JSONArray;
import utils.ExcelUtils;
import utils.HttpUtils;
import utils.ReadDataFromText;

import java.net.HttpURLConnection;

/**
 * Created by xzl on 2017/12/7.
 *
 * @author xzl
 * @date 2017/12/7  14:09.
 */
public class ProxyIp {
    public static void main(String[] args) {
        HttpURLConnection connection =   HttpUtils.getHttpURLConnection("http://www.qlcoder.com/train/proxy",null,null,"get");
        JSONArray jsonArray = ReadDataFromText.readByHttpConnection(connection);
        System.out.println("结果："+jsonArray.toJSONString());
    }
}
