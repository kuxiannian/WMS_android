package com.lxkj.wms.utils;

import android.widget.TextView;

import com.lxkj.wms.R;

/**
 * Created by kxn on 2020/5/28 0028.
 */
public class AppViewCanDoUtil {

    public static void setBtnCanDo(boolean isCanDo, TextView textView){
        if (isCanDo){
            textView.setEnabled(true);
            textView.setBackgroundResource(R.drawable.btn_main_rect_5dp);
        }else {
            textView.setEnabled(false);
            textView.setBackgroundResource(R.drawable.btn_unclick_rect_5dp);
        }
    }

    public static void setCanDo(boolean isCanDo, TextView ll){
        if (isCanDo){
            ll.setEnabled(true);
            ll.setBackgroundResource(R.drawable.bg_border_efefef_5dp);
        }else {
            ll.setEnabled(false);
            ll.setBackgroundResource(R.drawable.bg_border_efefef_unused_5dp);
        }

    }
}
