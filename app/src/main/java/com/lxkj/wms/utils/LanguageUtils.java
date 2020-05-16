package com.lxkj.wms.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by kxn on 2019/11/29 0029.
 */
public class LanguageUtils {

    public static void SetAppLanguage(Context context,String language) {
        switch (language) {
            case "1":
                changeAppLanguage(context, Locale.CHINESE);
                break;
            case "2":
                changeAppLanguage(context, Locale.ENGLISH);
                break;
            default:
                changeAppLanguage(context, Locale.ENGLISH);
                break;
        }
    }

    /**
     * 修改APP语言设置
     *
     * @param locale 如Locale.CHINESE、Locale.ENGLISH等
     */
    public static void changeAppLanguage(Context context, Locale locale) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        //conf.locale = locale;
        conf.setLocale(locale);
        res.updateConfiguration(conf, dm);
    }

    /**
     * 获取当前设置的语言
     *
     * @return 当前的语言，可将结果与Locale.ENGLISH.getLanguage()比较进行判断
     */
    public static String getCurrLanguage(Context context) {
        Locale locale = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale.getLanguage();
    }


}
