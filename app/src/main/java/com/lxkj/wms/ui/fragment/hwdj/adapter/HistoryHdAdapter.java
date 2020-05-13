package com.lxkj.wms.ui.fragment.hwdj.adapter;

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
import com.lxkj.wms.ui.fragment.hwdj.HistoryHdDetialFra;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/4/24 0024.
 */
public class HistoryHdAdapter extends RecyclerView.Adapter<HistoryHdAdapter.ViewHolder> {
    protected Context mContext;
    protected List<SortingRegisterBean.ResultBean.ContentBean> mDatas;
    protected LayoutInflater mInflater;


    private OnItemClickListener onItemClickListener;

    public HistoryHdAdapter(Context mContext, List<SortingRegisterBean.ResultBean.ContentBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_hd_history, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.ivZk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas.get(position).isOpen = !mDatas.get(position).isOpen;
                notifyDataSetChanged();
            }
        });
        holder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean",mDatas.get(position));
                ActivitySwitcher.startFragment(mContext, HistoryHdDetialFra.class, bundle);
            }
        });
        holder.tvAwb.setText(mDatas.get(position).getAwb());
        holder.tvFlightName.setText(mDatas.get(position).getFlightName());
        holder.tvGoodsName.setText(mDatas.get(position).getGoodsName());
        holder.tvRegisterNumber.setText(mDatas.get(position).getRegisterNumber());
        holder.tvWmsWarehouseName.setText(mDatas.get(position).getWmsWarehouseName());
        holder.tvGoodsType.setText(mDatas.get(position).getGoodsType());
        if (mDatas.get(position).isOpen) {
            holder.llFjdjdd.setVisibility(View.VISIBLE);
            holder.llHwfl.setVisibility(View.VISIBLE);
        } else {
            holder.llFjdjdd.setVisibility(View.GONE);
            holder.llHwfl.setVisibility(View.GONE);
        }
        if (mDatas.get(position).isOpen)
            holder.ivZk.setImageResource(R.mipmap.ic_arrow_up_main);
        else
            holder.ivZk.setImageResource(R.mipmap.ic_arrow_down_main);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvAwb)
        TextView tvAwb;
        @BindView(R.id.tvFlightName)
        TextView tvFlightName;
        @BindView(R.id.tvGoodsName)
        TextView tvGoodsName;
        @BindView(R.id.tvRegisterNumber)
        TextView tvRegisterNumber;
        @BindView(R.id.tvWmsWarehouseName)
        TextView tvWmsWarehouseName;
        @BindView(R.id.llFjdjdd)
        LinearLayout llFjdjdd;
        @BindView(R.id.tvGoodsType)
        TextView tvGoodsType;
        @BindView(R.id.llHwfl)
        LinearLayout llHwfl;
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

