package com.lxkj.wms.ui.fragment.rksh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
 * Created by kxn on 2020/4/25 0025.
 */
public class HistoryRkDetailFra extends TitleFragment {

    Unbinder unbinder;
    SortingRegisterBean.ResultBean.ContentBean contentBean;
    @BindView(R.id.tvBarCode)
    TextView tvBarCode;
    @BindView(R.id.tvInputDate)
    TextView tvInputDate;
    @BindView(R.id.tvWmsWarehouseName)
    TextView tvWmsWarehouseName;
    @BindView(R.id.tvPalletNumber)
    TextView tvPalletNumber;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.tvGoodsType)
    TextView tvGoodsType;
    @BindView(R.id.tvProductCode)
    TextView tvProductCode;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.llFjdjdd)
    LinearLayout llFjdjdd;
    @BindView(R.id.llHwfl)
    LinearLayout llHwfl;
    @BindView(R.id.tvRemarks)
    TextView tvRemarks;

    @Override
    public String getTitleName() {
        return act.getString(R.string.lscxxq);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_detial_rk_history, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        contentBean = ((SortingRegisterBean.ResultBean.ContentBean) getArguments().getSerializable("bean"));
        if (null != contentBean) {
            tvBarCode.setText(contentBean.getBarCode());
            tvGoodsName.setText(contentBean.getGoodsName());
            switch (contentBean.getGoodsType()) {
                case "A":
                    tvGoodsType.setText(R.string.goodsTypeA);
                    break;
                case "B":
                    tvGoodsType.setText(R.string.goodsTypeB);
                    break;
                case "C":
                    tvGoodsType.setText(R.string.goodsTypeC);
                    break;
            }
            if (!StringUtil.isEmpty(contentBean.getRemarks()))
                tvRemarks.setText(contentBean.getRemarks());
            tvInputDate.setText(TimeUtil.stampToDate(contentBean.getInputDate(), "yyyy-MM-dd"));
            tvPalletNumber.setText(contentBean.getPalletNumber());
            tvProductCode.setText(contentBean.getProductCode());
            tvWeight.setText(contentBean.getWeight());
            tvWmsWarehouseName.setText(contentBean.getWmsWarehouseName());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
