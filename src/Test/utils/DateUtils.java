package utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xzl on 2017/12/8.
 *
 * @author xzl
 * @date 2017/12/8  17:49.
 */
public class DateUtils {
    private  static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    public static String getDate(Date date, int i){
        return sdf.format(getDayFromToday(date,i));
    }

    /**获取当前为起点第 i 天
     * 负数 当前之前日期
     * @auth xzl
     * */
    public static Date getDayFromToday(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        date = calendar.getTime();
        return date;
    }

    @Test
    public  void test(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        System.out.println("*********");
        System.out.println(getDayFromToday(new Date(0),1));
        System.out.println(sdf.format(getDayFromToday(new Date(),0)));
    }
}
