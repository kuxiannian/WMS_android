package com.lxkj.wms.ui.fragment.putoff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.utils.StringUtil;
import com.lxkj.wms.utils.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/5/15 0015.
 */
public class HistoryPutOffDetailFra extends TitleFragment {

    Unbinder unbinder;
    SortingRegisterBean.ResultBean.ContentBean contentBean;
    @BindView(R.id.tvBarCode)
    TextView tvBarCode;
    @BindView(R.id.tvPutOffDate)
    TextView tvPutOffDate;
    @BindView(R.id.tvProductCode)
    TextView tvProductCode;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.tvWmsWarehouseName)
    TextView tvWmsWarehouseName;
    @BindView(R.id.tvWmsWarehouseDetailName)
    TextView tvWmsWarehouseDetailName;
    @BindView(R.id.tvRemarks)
    TextView tvRemarks;

    @Override
    public String getTitleName() {
        return act.getString(R.string.lscxxq);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_detial_putoff_history, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        contentBean = ((SortingRegisterBean.ResultBean.ContentBean) getArguments().getSerializable("bean"));
        if (null != contentBean) {
            tvBarCode.setText(contentBean.getBarCode());
            if (!StringUtil.isEmpty(contentBean.getPutOffDate()))
                tvPutOffDate.setText(TimeUtil.stampToDate(contentBean.getPutOffDate(), "yyyy-MM-dd"));
            tvProductCode.setText(contentBean.getProductCode());
            tvGoodsName.setText(contentBean.getGoodsName());
            tvWmsWarehouseName.setText(contentBean.getWmsWarehouseName());
            tvWmsWarehouseDetailName.setText(contentBean.getWmsWarehouseDetailName());
            if (!StringUtil.isEmpty(contentBean.getRemarks()))
            tvRemarks.setText(contentBean.getRemarks());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

