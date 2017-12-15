package utils;

import java.math.BigDecimal;

/**
 * Created by ${xzl} on 2017/5/3.
 * 数据类型转换
 */
public class DecimalTrans {
    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }
    public static String getPrettyNumber(Double number) {
        return BigDecimal.valueOf(number)
                .stripTrailingZeros().toPlainString();
    }
}
