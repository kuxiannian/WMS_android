package com.lxkj.wms.ui.fragment.puton.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.ui.fragment.puton.HistoryPutOnDetailFra;
import com.lxkj.wms.utils.StringUtil;
import com.lxkj.wms.utils.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/5/14 0014.
 */
public class PutOnAdapter extends RecyclerView.Adapter<PutOnAdapter.ViewHolder> {
    protected Context mContext;
    protected List<SortingRegisterBean.ResultBean.ContentBean> mDatas;
    protected LayoutInflater mInflater;


    public PutOnAdapter(Context mContext, List<SortingRegisterBean.ResultBean.ContentBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_puton_history, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvBarCode.setText(mDatas.get(position).getBarCode());
        holder.tvWmsWarehouseDetailIdName.setText(mDatas.get(position).getWmsWarehouseDetailIdName());
        if (!StringUtil.isEmpty(mDatas.get(position).getPutOnDate()))
            holder.tvPutOnDate.setText(TimeUtil.stampToDate(mDatas.get(position).getPutOnDate(),"yyyy-MM-dd"));
        holder.tvPalletNumber.setText(mDatas.get(position).getPalletNumber());
        holder.tvProductCode.setText(mDatas.get(position).getProductCode());
        holder.tvUpdaterName.setText(mDatas.get(position).getUpdaterName());
        holder.tvUpdateDate.setText(mDatas.get(position).getUpdateDate());

        holder.ivZk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas.get(position).isOpen = !mDatas.get(position).isOpen;
                notifyDataSetChanged();
            }
        });
        if (mDatas.get(position).isOpen) {
            holder.llPalletNumber.setVisibility(View.VISIBLE);
            holder.llProductCode.setVisibility(View.VISIBLE);
            holder.llUpdaterName.setVisibility(View.VISIBLE);
            holder.llUpdateDate.setVisibility(View.VISIBLE);
        } else {
            holder.llPalletNumber.setVisibility(View.GONE);
            holder.llProductCode.setVisibility(View.GONE);
            holder.llUpdaterName.setVisibility(View.GONE);
            holder.llUpdateDate.setVisibility(View.GONE);
        }
        if (mDatas.get(position).isOpen)
            holder.ivZk.setImageResource(R.mipmap.ic_arrow_up_main);
        else
            holder.ivZk.setImageResource(R.mipmap.ic_arrow_down_main);
        holder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", mDatas.get(position));
                ActivitySwitcher.startFragment(mContext, HistoryPutOnDetailFra.class, bundle);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBarCode)
        TextView tvBarCode;
        @BindView(R.id.tvWmsWarehouseDetailIdName)
        TextView tvWmsWarehouseDetailIdName;
        @BindView(R.id.tvPutOnDate)
        TextView tvPutOnDate;
        @BindView(R.id.tvPalletNumber)
        TextView tvPalletNumber;
        @BindView(R.id.llPalletNumber)
        LinearLayout llPalletNumber;
        @BindView(R.id.tvProductCode)
        TextView tvProductCode;
        @BindView(R.id.llProductCode)
        LinearLayout llProductCode;
        @BindView(R.id.tvUpdaterName)
        TextView tvUpdaterName;
        @BindView(R.id.llUpdaterName)
        LinearLayout llUpdaterName;
        @BindView(R.id.tvUpdateDate)
        TextView tvUpdateDate;
        @BindView(R.id.llUpdateDate)
        LinearLayout llUpdateDate;
        @BindView(R.id.ivDetail)
        ImageView ivDetail;
        @BindView(R.id.ivZk)
        ImageView ivZk;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


