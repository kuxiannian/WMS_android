package com.lxkj.wms.ui.fragment.ckjh;

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
 * Created by kxn on 2020/4/25 0025.
 */
public class HistoryCkDetailFra extends TitleFragment {
    @BindView(R.id.tvbarCode)
    TextView tvbarCode;
    @BindView(R.id.tvoutputDate)
    TextView tvoutputDate;
    @BindView(R.id.tvladingNumber)
    TextView tvladingNumber;
    @BindView(R.id.tvconsignor)
    TextView tvconsignor;
    @BindView(R.id.tvconsignorPhone)
    TextView tvconsignorPhone;
    @BindView(R.id.tvGoodsType)
    TextView tvGoodsType;
    @BindView(R.id.tvwmsWarehouseName)
    TextView tvwmsWarehouseName;
    @BindView(R.id.tvproductCode)
    TextView tvproductCode;
    @BindView(R.id.tvgoodsName)
    TextView tvgoodsName;
    @BindView(R.id.tvsuspicion)
    TextView tvsuspicion;
    @BindView(R.id.tvsuspicionProblem)
    TextView tvsuspicionProblem;
    Unbinder unbinder;
    SortingRegisterBean.ResultBean.ContentBean contentBean;
    @BindView(R.id.tvremarks)
    TextView tvremarks;

    @Override
    public String getTitleName() {
        return act.getString(R.string.lscxxq);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_detial_ck_history, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        contentBean = ((SortingRegisterBean.ResultBean.ContentBean) getArguments().getSerializable("bean"));
        if (null != contentBean) {
            tvbarCode.setText(contentBean.getBarCode());
            tvladingNumber.setText(contentBean.getLadingNumber());
            tvconsignor.setText(contentBean.getConsignor());
            tvconsignorPhone.setText(contentBean.getConsignorPhone());
            tvGoodsType.setText(contentBean.getGoodsType());
            tvwmsWarehouseName.setText(contentBean.getWmsWarehouseName());
            tvproductCode.setText(contentBean.getProductCode());
            tvgoodsName.setText(contentBean.getGoodsName());
            tvsuspicion.setText(contentBean.getSuspicion());
            if (!StringUtil.isEmpty(contentBean.getSuspicionProblem()))
                tvsuspicionProblem.setText(contentBean.getSuspicionProblem());
            if (!StringUtil.isEmpty(contentBean.getRemarks()))
                tvremarks.setText(contentBean.getRemarks());

            if (null != contentBean.getSuspicion()) {
                switch (contentBean.getSuspicion()) {
                    case "1":
                        tvsuspicion.setText(mContext.getString(R.string.suspicionY));
                        break;
                    default:
                        tvsuspicion.setText(mContext.getString(R.string.suspicionN));
                        break;
                }
            }

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
            tvoutputDate.setText(TimeUtil.stampToDate(contentBean.getOutputDate(), "yyyy-MM-dd"));

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

