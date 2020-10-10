package com.whdx.base.language;

import android.content.Context;
import android.content.res.Configuration;

import com.github.jokar.multilanguages.library.MultiLanguage;
import com.whdx.base.R;

import java.util.Locale;

import static com.whdx.base.util.ext.MmkvExtKt.getLanguage;
import static com.whdx.base.util.ext.MmkvExtKt.saveLanguage;

public class LocalLanguageUtil {

    private static final String TAG = "LocalManageUtil";

    /**
     * 获取系统的locale
     *
     * @return Locale对象
     */
//    public static Locale getSystemLocale(Context context) {
//        return SPUtil.getInstance(context).getSystemCurrentLocal();
//    }

    public static String getSelectLanguage(Context context) {
        switch (getLanguage()) {
//            case 0:
//                return context.getString(R.string.language_auto);
            case 1:
                return context.getString(R.string.language_cn);
//            case 2:
//                return context.getString(R.string.language_traditional);
//            case 3:
            default:
                return context.getString(R.string.language_en);
        }
    }

    /**
     * 获取选择的语言设置
     *
     * @param context
     * @return
     */
    public static Locale getSetLanguageLocale(Context context) {

        switch (getLanguage()) {
//            case 0:
//                return getSystemLocale(context);
            case 1:
                return Locale.CHINA;
            case 2:
                return Locale.TAIWAN;
            case 3:
            default:
                return Locale.ENGLISH;
        }
    }


  /*  public static void saveSystemCurrentLanguage(Context context) {
        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(context));
    }*/

    /**
     * 保存系统语言
     * @param context
     */
    /*public static void saveSystemCurrentLanguage(Context context, Configuration newConfig) {

        setSystemCurrentLocal(MultiLanguage.getSystemLocal(newConfig));
    }*/

    public static void saveSelectLanguage(Context context, int select) {
        saveLanguage(select);
        MultiLanguage.setApplicationLanguage(context);
    }
}