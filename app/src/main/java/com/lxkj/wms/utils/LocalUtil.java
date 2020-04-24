package com.lxkj.wms.utils;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kxn on 2019/12/10 0010.
 */
public class LocalUtil {


    /**
     * 设置字符串中多个不同关键字的颜色（颜色统一, 无点击事件）
     *
     * @param content 目标字符串
     * @param keyStrs 关键字集合
     * @param color   单一的颜色值
     * @return
     */
    public static SpannableString setSpanColorStr(String content, List<String> keyStrs, final String color) {
        SpannableString spannableString = new SpannableString(content);
        for (int i = 0; i < keyStrs.size(); i++) {
            String keyStr = keyStrs.get(i);
            if (content.contains(keyStr)) {
                int startNew = 0;
                int startOld = 0;
                String temp = content;
                while (temp.contains(keyStr)) {
                    spannableString.setSpan(
                            new ClickableSpan() {
                                @Override
                                public void updateDrawState(TextPaint ds) {
                                    super.updateDrawState(ds);
                                    ds.setColor(Color.parseColor(color));
                                    ds.setUnderlineText(false);
                                }

                                @Override
                                public void onClick(View widget) {
                                }
                            }, startOld + temp.indexOf(keyStr),
                            startOld + temp.indexOf(keyStr) + keyStr.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    startNew = temp.indexOf(keyStr) + keyStr.length();
                    startOld += startNew;
                    temp = temp.substring(startNew);
                }
            }
        }
        return spannableString;
    }
    public static SpannableString matcherSearchText(String text, String keyword, String color) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
//            ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 单独设置点击事件
            ClickableSpan clickableSpanOne = new ClickableSpan() {
                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.parseColor(color));
                    ds.setUnderlineText(false);
                }

                @Override
                public void onClick(View view) {
//                    ToastUtil.show("onclick");
                }
            };
            ss.setSpan(clickableSpanOne, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }



}
