package com.lxkj.wms.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.utils.AppUtil;
import com.lxkj.wms.view.adapter.MenuAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2019/12/11 0011.
 */
public class BottomMenuFra extends DialogFragment implements View.OnClickListener {
    Window window;
    View frView;
    Unbinder unbinder;
    @BindView(R.id.lvItem)
    ListView lvItem;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    MenuAdapter adapter;
    List<String> items;
    OnItemClick onItemClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 去掉默认的标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        frView = inflater.inflate(R.layout.dialog_fra_bottom_menu, null);
        unbinder = ButterKnife.bind(this, frView);
        // 下面这些设置必须在此方法(onStart())中才有效

        adapter = new MenuAdapter(getContext(),items);
        lvItem.setAdapter(adapter);
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemClick.onItemClick(i);
                dismiss();
            }
        });
        tvCancel.setOnClickListener(this::onClick);
        return frView;
    }

    public BottomMenuFra setItems(List<String> items){
        this.items = items;
        return this;
    }


    public BottomMenuFra setOnItemClick(OnItemClick onItemClickListener){
        this.onItemClick = onItemClickListener;
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        window = getDialog().getWindow();
        // 如果不设置这句代码, 那么弹框就会与四边都有一定的距离
        window.setBackgroundDrawableResource(android.R.color.transparent);
        // 设置动画
        window.setWindowAnimations(R.style.ani_bottom);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 如果不设置宽度,那么即使你在布局中设置宽度为 match_parent 也不会起作用
        params.width = AppUtil.getScreenWidth(getContext());
        window.setAttributes(params);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
        }
    }

    public interface OnItemClick{
        void onItemClick(int positon);
    }
}

