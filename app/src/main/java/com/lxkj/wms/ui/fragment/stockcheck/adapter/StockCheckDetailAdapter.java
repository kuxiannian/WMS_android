package com.lxkj.wms.ui.fragment.stockcheck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.WareHouseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/5/16 0016.
 */
public class StockCheckDetailAdapter extends RecyclerView.Adapter<StockCheckDetailAdapter.ViewHolder> {
    protected Context mContext;
    protected List<WareHouseBean.ResultBean> mDatas;
    protected LayoutInflater mInflater;

    private List<WareHouseBean.ResultBean> wareHouseList;
    private List<WareHouseBean.ResultBean> warehouseDetailList;
    OnDoListener onDoListener;

    public interface OnDoListener {
        void onDelete(int position);

        void onEdit(int positon);
    }

    public StockCheckDetailAdapter(Context mContext, List<WareHouseBean.ResultBean> mDatas) {
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

    public void setOnDeleteListener(OnDoListener onDoListener) {
        this.onDoListener = onDoListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_stockcheck_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvBarCode.setText(mDatas.get(position).getBarCode());
        holder.tvWmsWarehouseId.setText(getWmsWarehouseName(mDatas.get(position).getWmsWarehouseId()));
        holder.tvWmsWarehouseDetailId.setText(getWmsWarehouseDetailName(mDatas.get(position).getWmsWarehouseDetailId()));
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onDoListener)
                    onDoListener.onDelete(position);
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onDoListener)
                    onDoListener.onEdit(position);
            }
        });
    }

    private String getWmsWarehouseName(String wmsWarehouseId) {
        if (null != wareHouseList) {
            for (int i = 0; i < wareHouseList.size(); i++) {
                if (wareHouseList.get(i).getId().equals(wmsWarehouseId))
                    return wareHouseList.get(i).getName();
            }
            return "";
        }
        return "";
    }

    private String getWmsWarehouseDetailName(String wmsWarehouseDetailId) {
        if (null != warehouseDetailList) {
            for (int i = 0; i < warehouseDetailList.size(); i++) {
                if (warehouseDetailList.get(i).getId().equals(wmsWarehouseDetailId))
                    return warehouseDetailList.get(i).getCode();
            }
            return "";
        }
        return "";
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
        @BindView(R.id.ivDelete)
        ImageView ivDelete;
        @BindView(R.id.ivEdit)
        ImageView ivEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}




