package com.lxkj.wms.ui.fragment.fksh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class AddRkFra extends TitleFragment implements NaviActivity.NaviRigthImageListener {

    @BindView(R.id.etAwb)
    EditText etAwb;
    @BindView(R.id.tvHangBan)
    TextView tvHangBan;
    @BindView(R.id.tvHwpm)
    TextView tvHwpm;
    @BindView(R.id.etJs)
    EditText etJs;
    @BindView(R.id.tvDjjs)
    TextView tvDjjs;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.ivReduce)
    ImageView ivReduce;
    @BindView(R.id.tvFjdjdd)
    TextView tvFjdjdd;
    @BindView(R.id.tvHwfl)
    TextView tvHwfl;
    @BindView(R.id.etHwpm)
    EditText etHwpm;
    @BindView(R.id.tvSave)
    TextView tvSave;
    Unbinder unbinder;

    public String getTitleName() {
        return act.getString(R.string.xzrk);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_add_rk, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public int rightImg() {
        return R.mipmap.ic_time;
    }

    @Override
    public void onRightClicked(View v) {

    }
}
