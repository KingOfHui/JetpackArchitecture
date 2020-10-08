package com.whdx.base.util;

import androidx.annotation.IntRange;

import java.util.Locale;

public class MathUtil {

    public static long pow(long value, @IntRange(from = 0) int pow){
        if(pow == 0) return 1;
        long result = 1;
        for (int i = 0; i < pow; i++) {
            result *= value;
        }
        return result;
    }

    /**
     * 小数 value 保留小数点后 precision 位, 低于 precision 位的将被舍弃
     * 如: 1.399 和 1.391 调用 floor(value, 2) 保留两位小数后, 均为 1.39
     * @param value 小数
     * @param precision 要保留的小数位数
     */
    public static double floor(double value, int precision){
        long scale = pow(10, precision);
        return Math.floor(value * scale) / scale;
    }

    public static String doubleToFloorString(double value, int precision){
        return String.format(Locale.getDefault(), "%." + precision + "f", floor(value, precision));
    }

    public static String doubleToFloorStringNonZero(double value, int precision){
        String valueS = doubleToFloorString(value, precision);
        int decimalIndex = valueS.indexOf(".");
        if(decimalIndex == -1) {
            return valueS;
        }

        int endIndex = valueS.length() - 1;
        for (int i = endIndex; i >= decimalIndex; i--) {
            if(valueS.charAt(i) == '0'){
                endIndex = i-1;
            }else if(valueS.charAt(i) == '.'){
                endIndex = i-1;
                break;
            }else {
                break;
            }
        }
        return valueS.substring(0, endIndex + 1);

//        BigDecimal valueBig = new BigDecimal(value).setScale(precision, RoundingMode.FLOOR).stripTrailingZeros();
//        return valueBig.toPlainString();
    }
}
