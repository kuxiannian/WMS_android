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
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.ui.activity.NaviActivity;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.hwdj.HistoryHdFra;
import com.lxkj.wms.view.SingleChooseDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class ShaiXuanRkFra extends TitleFragment implements NaviActivity.NaviRigthImageListener, View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.etAwb)
    EditText etAwb;
    @BindView(R.id.tvHangBan)
    TextView tvHangBan;
    @BindView(R.id.tvHwpm)
    TextView tvHwpm;
    @BindView(R.id.tvDjjs)
    TextView tvDjjs;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.ivReduce)
    ImageView ivReduce;
    @BindView(R.id.tvCx)
    TextView tvCx;
    private int djNum = 0;

    @Override
    public String getTitleName() {
        return act.getString(R.string.jlsx);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_jlsx_rk, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        tvHangBan.setOnClickListener(this);
        tvHwpm.setOnClickListener(this::onClick);
        ivAdd.setOnClickListener(this);
        ivReduce.setOnClickListener(this);
        tvCx.setOnClickListener(this);
    }

    @Override
    public int rightImg() {
        return R.mipmap.ic_time;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFragment(act, HistoryHdFra.class);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvHangBan:
                List<String> hbList = new ArrayList<>();
                SingleChooseDialog gcqkChooseDialog = new SingleChooseDialog(mContext, hbList, new SingleChooseDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                    }
                });
                gcqkChooseDialog.show();
                break;
            case R.id.tvHwpm:

                break;
            case R.id.ivAdd:
                djNum++;
                tvDjjs.setText(djNum + "");
                break;
            case R.id.ivReduce:
                if (djNum > 0)
                    djNum--;
                tvDjjs.setText(djNum + "");
                break;
            case R.id.tvHwfl:

                break;
            case R.id.tvFjdjdd:

                break;
            case R.id.tvHdxq:
//                new HangDanDetailDialog(mContext).show();
                break;
            case R.id.tvCx:

                break;
        }
    }
}


