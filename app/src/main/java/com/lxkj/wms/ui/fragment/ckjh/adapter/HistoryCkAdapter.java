package com.lxkj.wms.ui.fragment.ckjh.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lxkj.wms.R;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.ui.fragment.ckjh.HistoryCkDetailFra;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class HistoryCkAdapter extends RecyclerView.Adapter<HistoryCkAdapter.ViewHolder> {
    protected Context mContext;
    protected List mDatas;
    protected LayoutInflater mInflater;


    private OnItemClickListener onItemClickListener;

    public HistoryCkAdapter(Context mContext, List mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_ck_history, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.ivZk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.ivDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivitySwitcher.startFragment(mContext, HistoryCkDetailFra.class, new Bundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
//        return mDatas != null ? mDatas.size() : 0;
    }


    public interface OnItemClickListener {
        void OnItemClick(int position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llTph)
        LinearLayout llTph;
        @BindView(R.id.llZhongLiang)
        LinearLayout llZhongLiang;
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



