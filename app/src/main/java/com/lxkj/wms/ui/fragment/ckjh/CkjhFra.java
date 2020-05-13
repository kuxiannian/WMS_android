package com.lxkj.wms.ui.fragment.ckjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.fksh.AddRkFra;
import com.lxkj.wms.ui.fragment.fksh.HistoryRkFra;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class CkjhFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {
    @BindView(R.id.tvSglr)
    TextView tvSglr;
    Unbinder unbinder;
    private int djNum = 0;

    @Override
    public String getTitleName() {
        return act.getString(R.string.ckjh);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_ckjh, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        tvSglr.setOnClickListener(this);
    }

    @Override
    public int rightImg() {
        return R.mipmap.ic_time;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFragment(act, HistoryCkFra.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSglr:
                ActivitySwitcher.startFragment(act, AddCkFra.class);
                break;
        }
    }
}
