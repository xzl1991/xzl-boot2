package qlcoder;

import utils.DateUtils;
import utils.MD5Utils;

import java.util.Date;

/**
 * Created by xzl on 2017/12/8.
 *
 * @author xzl
 * @date 2017/12/8  18:36.
 */
public class GetMdDate {
    public static void main(String[] args) {
        String tar = "7E38890B870934B126F66857ED6B57B9";
        Date date = new Date(0);
        String temp = null;
        for(int i=1;i<20000;i++){
            temp = DateUtils.getDate(date,i);
            if(MD5Utils.getMD5Str(temp).equalsIgnoreCase(tar)){
                System.out.println("明文:" + temp);
                break;
            }

        }
        System.out.println("未找到:" );
    }
}
