package com.lxkj.wms.ui.fragment.stockcheck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.WareHouseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/5/29 0029.
 */
public class HistoryDetailAdapter extends RecyclerView.Adapter<HistoryDetailAdapter.ViewHolder> {
    protected Context mContext;
    protected List<WareHouseBean.ResultBean> mDatas;
    protected LayoutInflater mInflater;


    private List<WareHouseBean.ResultBean> wareHouseList;
    private List<WareHouseBean.ResultBean> warehouseDetailList;

    public HistoryDetailAdapter(Context mContext, List<WareHouseBean.ResultBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setWareHouseList(List<WareHouseBean.ResultBean> wareHouseList) {
        this.wareHouseList = wareHouseList;
        notifyDataSetChanged();
    }

    public void setWarehouseDetailList(List<WareHouseBean.ResultBean> warehouseDetailList) {
        this.warehouseDetailList = warehouseDetailList;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_stockcheck_detail_history, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvBarCode.setText(mDatas.get(position).getBarCode());
        holder.tvWmsWarehouseId.setText(getWmsWarehouseName(mDatas.get(position).getWmsWarehouseId()));
        holder.tvWmsWarehouseDetailId.setText(getWmsWarehouseDetailName(mDatas.get(position).getWmsWarehouseDetailId()));
        if (null != mDatas.get(position).getWmsWarehouseIdActual())
            holder.tvWmsWarehouseIdActual.setText(getWmsWarehouseName(mDatas.get(position).getWmsWarehouseIdActual()));
        if (null != mDatas.get(position).getWmsWarehouseDetailIdActual())
            holder.tvWmsWarehouseDetailIdActual.setText(getWmsWarehouseDetailName(mDatas.get(position).getWmsWarehouseDetailIdActual()));
        holder.llItem.setBackgroundResource(R.drawable.bg_border_line);

        switch (mDatas.get(position).getDiffFlag()) {//diffFlag: 0:无差异 2：储位有差异 3：遗漏盘点 4：无库存匹配
            case "0"://无差异
                holder.llItem.setBackgroundResource(R.drawable.bg_border_line);
                break;
            case "1"://有差异
                holder.llItem.setBackgroundResource(R.drawable.bg_error);
                break;
            case "3":// 遗漏盘点
                holder.llItem.setBackgroundResource(R.drawable.bg_error);
                holder.tvDiffFlag.setText(mContext.getString(R.string.MI));
                break;
            case "4"://无库存匹配
                holder.llItem.setBackgroundResource(R.drawable.bg_error);
                holder.tvDiffFlag.setText(mContext.getString(R.string.NIM));
                break;
            case "2"://储位有差异
                holder.llWmsWarehouseDetailId.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                holder.tvDiffFlag.setText(mContext.getString(R.string.DS));
                break;
        }

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

    private String getWmsWarehouseDetailName(String wmsWarehouseDetailId) {
        if (null != warehouseDetailList) {
            for (int i = 0; i < warehouseDetailList.size(); i++) {
                if (warehouseDetailList.get(i).getId().equals(wmsWarehouseDetailId))
                    return warehouseDetailList.get(i).getCode();
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
        @BindView(R.id.tvBarCode)
        TextView tvBarCode;
        @BindView(R.id.tvWmsWarehouseId)
        TextView tvWmsWarehouseId;
        @BindView(R.id.tvWmsWarehouseDetailId)
        TextView tvWmsWarehouseDetailId;
        @BindView(R.id.llWmsWarehouseDetailId)
        LinearLayout llWmsWarehouseDetailId;
        @BindView(R.id.tvWmsWarehouseIdActual)
        TextView tvWmsWarehouseIdActual;
        @BindView(R.id.tvWmsWarehouseDetailIdActual)
        TextView tvWmsWarehouseDetailIdActual;
        @BindView(R.id.tvDiffFlag)
        TextView tvDiffFlag;
        @BindView(R.id.llItem)
        LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}




