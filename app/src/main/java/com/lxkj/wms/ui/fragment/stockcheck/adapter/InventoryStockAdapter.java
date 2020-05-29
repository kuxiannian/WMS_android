package com.lxkj.wms.ui.fragment.stockcheck.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.bean.WareHouseBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.ui.fragment.stockcheck.AddStockCheckFra;
import com.lxkj.wms.utils.StringUtil;
import com.lxkj.wms.utils.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/5/16 0016.
 */
public class InventoryStockAdapter extends RecyclerView.Adapter<InventoryStockAdapter.ViewHolder> {
    protected Context mContext;
    protected List<SortingRegisterBean.ResultBean.ContentBean> mDatas;
    protected LayoutInflater mInflater;

    private List<WareHouseBean.ResultBean> wareHouseList;


    public InventoryStockAdapter(Context mContext, List<SortingRegisterBean.ResultBean.ContentBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setWareHouseList(List<WareHouseBean.ResultBean> wareHouseList) {
        this.wareHouseList = wareHouseList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_stockcheck, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (!StringUtil.isEmpty(mDatas.get(position).getStartDate()))
            holder.tvStartDate.setText(TimeUtil.stampToDate(mDatas.get(position).getStartDate(), "yyyy-MM-dd HH:mm:ss"));
        if (!StringUtil.isEmpty(mDatas.get(position).getEndDate()))
            holder.tvEndDate.setText(TimeUtil.stampToDate(mDatas.get(position).getEndDate(), "yyyy-MM-dd HH:mm:ss"));
        holder.tvWmsWarehouseName.setText(getWmsWarehouseName(mDatas.get(position).getWmsWarehouseId()));
        if (null != mDatas.get(position).getState()) {
            switch (mDatas.get(position).getState()) {
                case "1": //盘点中
                    holder.tvState.setText(mContext.getString(R.string.Inventory));
                    break;
                case "2": //盘点结束
                    holder.tvState.setText(mContext.getString(R.string.Einventory));
                    break;
                case "3": //作废
                    holder.tvState.setText(mContext.getString(R.string.Void));
                    break;
            }
        }
        if (!StringUtil.isEmpty(mDatas.get(position).getUpdaterName()))
            holder.tvUpdaterId.setText(getWmsWarehouseName(mDatas.get(position).getUpdaterName()));
        if (!StringUtil.isEmpty(mDatas.get(position).getUpdateDate()))
            holder.tvUpdateDate.setText(TimeUtil.stampToDate(mDatas.get(position).getUpdateDate(), "yyyy-MM-dd"));

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", mDatas.get(position));
                bundle.putString("wmsWarehouseName", holder.tvWmsWarehouseName.getText().toString());
                ActivitySwitcher.startFragment(mContext, AddStockCheckFra.class, bundle);
            }
        });
    }

    private String getWmsWarehouseName(String wmsWarehouseId) {
        if (null != wareHouseList) {
            for (int i = 0; i < wareHouseList.size(); i++) {
                if (wareHouseList.get(i).getId().equals(wmsWarehouseId))
                    return wareHouseList.get(i).getName();
            }

            return "-";
        }

        return "-";
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvWmsWarehouseName)
        TextView tvWmsWarehouseName;
        @BindView(R.id.tvStartDate)
        TextView tvStartDate;
        @BindView(R.id.tvEndDate)
        TextView tvEndDate;
        @BindView(R.id.tvState)
        TextView tvState;
        @BindView(R.id.tvUpdaterId)
        TextView tvUpdaterId;
        @BindView(R.id.tvUpdateDate)
        TextView tvUpdateDate;
        @BindView(R.id.ivEdit)
        ImageView ivEdit;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}




