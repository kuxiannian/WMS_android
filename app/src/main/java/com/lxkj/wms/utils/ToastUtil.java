package com.lxkj.wms.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.lxkj.wms.GlobalBeans;
import com.lxkj.wms.R;
import com.lxkj.wms.adapter.ErrorHintAdapter;

import java.util.List;


public final class ToastUtil {

    private ToastUtil() {

    }

    private static Toast mToast;

    public static void show(final String title) {
        showToast(title, Toast.LENGTH_SHORT);
    }

    public static void showLong(final String title) {
        showToast(title, Toast.LENGTH_LONG);
    }

    private static void showToast(final String title, final int dur) {
        if (mToast == null) {
            mToast = Toast.makeText(GlobalBeans.getSelf().getApp(), title, dur);
        }
        mToast.setText(title);
        mToast.show();
    }

    public static void showCustom(Context context, List<String> errorList) {

        Toast toast = new Toast(context);

        //创建一个填充物,用于填充Toast
        LayoutInflater inflater = LayoutInflater.from(context);

        //填充物来自的xml文件,在这个改成一个view
        //实现xml到view的转变哦
        View view = inflater.inflate(R.layout.toast_error, null);

        //不一定需要，找到xml里面的组件，设置组件里面的具体内容
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ErrorHintAdapter adapter = new ErrorHintAdapter(context,errorList);
        recyclerView.setAdapter(adapter);
        //把填充物放进toast
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        //展示toast
        toast.show();
    }


    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
