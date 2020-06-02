package com.lxkj.wms.ui.fragment.ckjh.adapter;

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
import com.lxkj.wms.ui.fragment.ckjh.HistoryCkDetailFra;
import com.lxkj.wms.utils.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class HistoryCkAdapter extends RecyclerView.Adapter<HistoryCkAdapter.ViewHolder> {
    protected Context mContext;
    protected List<SortingRegisterBean.ResultBean.ContentBean> mDatas;
    protected LayoutInflater mInflater;


    public HistoryCkAdapter(Context mContext, List<SortingRegisterBean.ResultBean.ContentBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_ck_history, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvbarCode.setText(mDatas.get(position).getBarCode());
        holder.tvwmsWarehouseName.setText(mDatas.get(position).getWmsWarehouseName());
        holder.tvladingNumber.setText(mDatas.get(position).getLadingNumber());
        holder.tvconsignor.setText(mDatas.get(position).getConsignor());
        holder.tvconsignorPhone.setText(mDatas.get(position).getConsignorPhone());
        holder.tvgoodsName.setText(mDatas.get(position).getGoodsName());
        holder.tvupdaterName.setText(mDatas.get(position).getUpdaterName());

        switch (mDatas.get(position).getGoodsType()) {
            case "A":
                holder.tvgoodsType.setText(R.string.goodsTypeA);
                break;
            case "B":
                holder.tvgoodsType.setText(R.string.goodsTypeB);
                break;
            case "C":
                holder.tvgoodsType.setText(R.string.goodsTypeC);
                break;
        }
        holder.tvoutputDate.setText(TimeUtil.stampToDate(mDatas.get(position).getOutputDate(), "yyyy-MM-dd"));
        holder.tvupdateDate.setText(TimeUtil.stampToDate(mDatas.get(position).getUpdateDate(), "yyyy-MM-dd HH:mm:ss"));

        holder.ivZk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas.get(position).isOpen = !mDatas.get(position).isOpen;
                notifyDataSetChanged();
            }
        });
        if (mDatas.get(position).isOpen) {
            holder.llconsignor.setVisibility(View.VISIBLE);
            holder.llconsignorPhone.setVisibility(View.VISIBLE);
            holder.llgoodsName.setVisibility(View.VISIBLE);
            holder.llladingNumber.setVisibility(View.VISIBLE);
            holder.llupdateDate.setVisibility(View.VISIBLE);
            holder.llupdaterName.setVisibility(View.VISIBLE);
        } else {
            holder.llconsignor.setVisibility(View.GONE);
            holder.llconsignorPhone.setVisibility(View.GONE);
            holder.llgoodsName.setVisibility(View.GONE);
            holder.llladingNumber.setVisibility(View.GONE);
            holder.llupdateDate.setVisibility(View.GONE);
            holder.llupdaterName.setVisibility(View.GONE);
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
                ActivitySwitcher.startFragment(mContext, HistoryCkDetailFra.class, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvbarCode)
        TextView tvbarCode;
        @BindView(R.id.tvgoodsType)
        TextView tvgoodsType;
        @BindView(R.id.tvoutputDate)
        TextView tvoutputDate;
        @BindView(R.id.tvwmsWarehouseName)
        TextView tvwmsWarehouseName;
        @BindView(R.id.tvladingNumber)
        TextView tvladingNumber;
        @BindView(R.id.llladingNumber)
        LinearLayout llladingNumber;
        @BindView(R.id.tvconsignor)
        TextView tvconsignor;
        @BindView(R.id.llconsignor)
        LinearLayout llconsignor;
        @BindView(R.id.tvconsignorPhone)
        TextView tvconsignorPhone;
        @BindView(R.id.llconsignorPhone)
        LinearLayout llconsignorPhone;
        @BindView(R.id.tvgoodsName)
        TextView tvgoodsName;
        @BindView(R.id.llgoodsName)
        LinearLayout llgoodsName;
        @BindView(R.id.tvupdaterName)
        TextView tvupdaterName;
        @BindView(R.id.llupdaterName)
        LinearLayout llupdaterName;
        @BindView(R.id.tvupdateDate)
        TextView tvupdateDate;
        @BindView(R.id.llupdateDate)
        LinearLayout llupdateDate;
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



