package com.lxkj.wms.ui.fragment.puton;

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
 * Created by kxn on 2020/5/14 0014.
 */
public class HistoryPutOnDetailFra extends TitleFragment {

    Unbinder unbinder;
    SortingRegisterBean.ResultBean.ContentBean contentBean;
    @BindView(R.id.tvBarCode)
    TextView tvBarCode;
    @BindView(R.id.tvPutOnDate)
    TextView tvPutOnDate;
    @BindView(R.id.tvWmsWarehouseIdName)
    TextView tvWmsWarehouseIdName;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.tvPalletNumber)
    TextView tvPalletNumber;
    @BindView(R.id.tvProductCode)
    TextView tvProductCode;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.tvLength)
    TextView tvLength;
    @BindView(R.id.tvWidth)
    TextView tvWidth;
    @BindView(R.id.tvHeight)
    TextView tvHeight;
    @BindView(R.id.tvWmsWarehouseDetailIdName)
    TextView tvWmsWarehouseDetailIdName;
    @BindView(R.id.tvRemarks)
    TextView tvRemarks;

    @Override
    public String getTitleName() {
        return act.getString(R.string.lscxxq);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_detial_puton_history, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        contentBean = ((SortingRegisterBean.ResultBean.ContentBean) getArguments().getSerializable("bean"));
        if (null != contentBean) {
            tvBarCode.setText(contentBean.getBarCode());
            if (!StringUtil.isEmpty(contentBean.getPutOnDate()))
                tvPutOnDate.setText(TimeUtil.stampToDate(contentBean.getPutOnDate(), "yyyy-MM-dd"));
            tvWmsWarehouseIdName.setText(contentBean.getWmsWarehouseIdName());
            tvWeight.setText(contentBean.getWeight());
            tvPalletNumber.setText(contentBean.getPalletNumber());
            tvProductCode.setText(contentBean.getProductCode());
            tvGoodsName.setText(contentBean.getGoodsName());
            tvLength.setText(contentBean.getLength());
            tvWidth.setText(contentBean.getWidth());
            tvHeight.setText(contentBean.getHeight());
            tvWmsWarehouseDetailIdName.setText(contentBean.getWmsWarehouseDetailIdName());
            tvRemarks.setText(contentBean.getRemarks());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

