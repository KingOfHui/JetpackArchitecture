package com.whdx.base.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * @ClassName ViewUtil
 * @Description
 * @Author dinghui
 * @Date 2020/10/19 0019 9:20
 */
public class ViewUtil {

    public static void addFilter(EditText editText, InputFilter inputFilter){
        InputFilter[] filters = editText.getFilters();
        int size = getSize(filters);
        InputFilter[] newfilters = new InputFilter[size + 1];
        for (int i = 0; i < size; i++) {
            newfilters[i] = filters[i];
        }
        newfilters[size] = inputFilter;
    }


    /**
     * 控制 EditText 能输入几位小数
     * @param DECIMAL_DIGITS 小数位数
     * @param MAX_LENGTH 小数+整数长度
     */
    public static InputFilter get2NumPoint(final int DECIMAL_DIGITS, final int MAX_LENGTH) {
        InputFilter lengthfilter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                // 删除等特殊字符，直接返回
                if (nullFilter(source)) return null;
                String dValue = dest.toString();
                String[] splitArray = dValue.split("\\.");//在点前后分开两段
                if (splitArray.length > 0) {
                    String intValue = splitArray[0];
                    int errorIndex = dValue.indexOf(".");
                    if (errorIndex == -1) {
                        errorIndex = dValue.length();
                    }
                    if (intValue.length() >= MAX_LENGTH - DECIMAL_DIGITS - 1 && dstart <= errorIndex) {
                        if (".".equals(source.toString())) {
                            return null;
                        }
                        return "";
                    }
                }
                if (splitArray.length > 1 && dstart >= splitArray[0].length() + 1) {
                    String dotValue = splitArray[1];
                    int diff = dotValue.length() + 1 - DECIMAL_DIGITS;
                    if (diff > 0) {
                        try {
                            return source.subSequence(start, end - diff);
                        } catch (IndexOutOfBoundsException e) {
                            return source;
                        }
                    }
                }
                if (dest.length() == MAX_LENGTH - 1 && ".".equals(source.toString())) {
                    return "";
                }
                if (dest.length() >= MAX_LENGTH) {
                    return "";
                }
                return null;
            }
        };
        return lengthfilter;
    }

    private static boolean nullFilter(CharSequence source) {
        if ("".equals(source.toString())) {
            return true;
        }
        return false;
    }

    public static int getSize(Object[] filters) {
        if (filters == null) return 0;
        return filters.length;
    }
}
