package com.lxkj.wms.ui.fragment.rksh.adapter;

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
import com.lxkj.wms.ui.fragment.rksh.HistoryRkDetailFra;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class HistoryRkAdapter extends RecyclerView.Adapter<HistoryRkAdapter.ViewHolder> {
    protected Context mContext;
    protected List<SortingRegisterBean.ResultBean.ContentBean> mDatas;
    protected LayoutInflater mInflater;



    public HistoryRkAdapter(Context mContext, List<SortingRegisterBean.ResultBean.ContentBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_rk_history, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvBarCode.setText(mDatas.get(position).getBarCode());
        holder.tvGoodsType.setText(mDatas.get(position).getGoodsType());
        holder.tvInputDate.setText(mDatas.get(position).getInputDate());
        holder.tvWmsWarehouseName.setText(mDatas.get(position).getWmsWarehouseName());
        holder.tvPalletNumber.setText(mDatas.get(position).getPalletNumber());
        holder.tvWeight.setText(mDatas.get(position).getWeight());
        holder.tvGoodsName.setText(mDatas.get(position).getGoodsName());

        holder.ivZk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas.get(position).isOpen = !mDatas.get(position).isOpen;
                notifyDataSetChanged();
            }
        });
        if (mDatas.get(position).isOpen) {
            holder.llHwpm.setVisibility(View.VISIBLE);
            holder.llTph.setVisibility(View.VISIBLE);
            holder.llZhongLiang.setVisibility(View.VISIBLE);
        } else {
            holder.llHwpm.setVisibility(View.GONE);
            holder.llTph.setVisibility(View.GONE);
            holder.llZhongLiang.setVisibility(View.GONE);
        }
        if (mDatas.get(position).isOpen)
            holder.ivZk.setImageResource(R.mipmap.ic_arrow_up_main);
        else
            holder.ivZk.setImageResource(R.mipmap.ic_arrow_down_main);
        holder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean",mDatas.get(position));
                ActivitySwitcher.startFragment(mContext, HistoryRkDetailFra.class, bundle);
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
        @BindView(R.id.tvGoodsType)
        TextView tvGoodsType;
        @BindView(R.id.tvInputDate)
        TextView tvInputDate;
        @BindView(R.id.tvWmsWarehouseName)
        TextView tvWmsWarehouseName;
        @BindView(R.id.tvPalletNumber)
        TextView tvPalletNumber;
        @BindView(R.id.llTph)
        LinearLayout llTph;
        @BindView(R.id.tvWeight)
        TextView tvWeight;
        @BindView(R.id.llZhongLiang)
        LinearLayout llZhongLiang;
        @BindView(R.id.tvGoodsName)
        TextView tvGoodsName;
        @BindView(R.id.llHwpm)
        LinearLayout llHwpm;
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


