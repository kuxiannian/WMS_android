package com.lxkj.wms.ui.fragment.stockcheck;

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
public class HistoryStockCheckDetailFra extends TitleFragment {

    Unbinder unbinder;
    SortingRegisterBean.ResultBean.ContentBean contentBean;
    @BindView(R.id.tvWmsWarehouseName)
    TextView tvWmsWarehouseName;
    @BindView(R.id.tvStartDate)
    TextView tvStartDate;
    @BindView(R.id.tvEndDate)
    TextView tvEndDate;
    @BindView(R.id.tvState)
    TextView tvState;

    @Override
    public String getTitleName() {
        return act.getString(R.string.lscxxq);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_detial_stockcheck_history, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        contentBean = ((SortingRegisterBean.ResultBean.ContentBean) getArguments().getSerializable("bean"));
        String wmsWarehouseName = getArguments().getString("wmsWarehouseName");
        if (null != contentBean) {
            if (!StringUtil.isEmpty(contentBean.getStartDate()))
                tvStartDate.setText(TimeUtil.stampToDate(contentBean.getStartDate(), "yyyy-MM-dd"));
            if (!StringUtil.isEmpty(contentBean.getStartDate()))
                tvEndDate.setText(TimeUtil.stampToDate(contentBean.getEndDate(), "yyyy-MM-dd"));
            tvWmsWarehouseName.setText(wmsWarehouseName);
            if (null != contentBean.getState()) {
                switch (contentBean.getState()) {
                    case "1": //盘点中
                        tvState.setText(mContext.getString(R.string.Inventory));
                        break;
                    case "2": //盘点结束
                        tvState.setText(mContext.getString(R.string.Einventory));
                        break;
                    case "3": //作废
                        tvState.setText(mContext.getString(R.string.Void));
                        break;
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

