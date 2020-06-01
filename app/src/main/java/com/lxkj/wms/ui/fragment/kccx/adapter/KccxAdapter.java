package com.lxkj.wms.ui.fragment.kccx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.utils.StringUtil;
import com.lxkj.wms.utils.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/4/25 0025.
 */
public class KccxAdapter extends RecyclerView.Adapter<KccxAdapter.ViewHolder> {
    protected Context mContext;
    protected List<SortingRegisterBean.ResultBean.ContentBean> mDatas;
    protected LayoutInflater mInflater;


    public KccxAdapter(Context mContext, List<SortingRegisterBean.ResultBean.ContentBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_kc, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvAwb.setText(mDatas.get(position).getAwb());
        holder.tvBarCode.setText(mDatas.get(position).getBarCode());
        holder.tvGoodsName.setText(mDatas.get(position).getGoodsName());
        if (null != mDatas.get(position).getGoodsType()) {
            switch (mDatas.get(position).getGoodsType()) {
                case "A":
                    holder.tvGoodsType.setText(R.string.goodsTypeA);
                    break;
                case "B":
                    holder.tvGoodsType.setText(R.string.goodsTypeB);
                    break;
                case "C":
                    holder.tvGoodsType.setText(R.string.goodsTypeC);
                    break;
            }
        }

        if (!StringUtil.isEmpty(mDatas.get(position).getInputDate()))
            holder.tvInputDate.setText(TimeUtil.stampToDate(mDatas.get(position).getInputDate(), "yyyy-MM-dd"));
        holder.tvInStockDay.setText(mDatas.get(position).getInStockDay());
        if (!StringUtil.isEmpty(mDatas.get(position).getOutputDate()))
            holder.tvOutputDate.setText(TimeUtil.stampToDate(mDatas.get(position).getOutputDate(), "yyyy-MM-dd"));
        holder.tvProductCode.setText(mDatas.get(position).getProductCode());
        if (null != mDatas.get(position).getStockState()) {
            switch (mDatas.get(position).getStockState()) {
                case "0":
                    holder.tvStockState.setText(mContext.getString(R.string.InStock));
                    break;
                case "1":
                    holder.tvStockState.setText(mContext.getString(R.string.Outbound));
                    break;
            }
        }
        if (!StringUtil.isEmpty(mDatas.get(position).getUpdateDate()))
            holder.tvUpdateDate.setText(TimeUtil.stampToDate(mDatas.get(position).getUpdateDate(), "yyyy-MM-dd HH:mm:ss"));
        holder.tvUpdaterName.setText(mDatas.get(position).getUpdaterName());
        if (null != mDatas.get(position).getWmsWarehouseDetailName())
            holder.tvWmsWarehouseDetailName.setText(mDatas.get(position).getWmsWarehouseDetailName());
        holder.tvWmsWarehouseName.setText(mDatas.get(position).getWmsWarehouseName());

        holder.ivZk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas.get(position).isOpen = !mDatas.get(position).isOpen;
                notifyDataSetChanged();
            }
        });
        if (mDatas.get(position).isOpen) {
            holder.llAwb.setVisibility(View.VISIBLE);
            holder.llGoodsName.setVisibility(View.VISIBLE);
            holder.llInputDate.setVisibility(View.VISIBLE);
            holder.llOutputDate.setVisibility(View.VISIBLE);
            holder.llProductCode.setVisibility(View.VISIBLE);
            holder.llStockState.setVisibility(View.VISIBLE);
            holder.llUpdateDate.setVisibility(View.VISIBLE);
            holder.llUpdaterName.setVisibility(View.VISIBLE);
        } else {
            holder.llAwb.setVisibility(View.GONE);
            holder.llGoodsName.setVisibility(View.GONE);
            holder.llInputDate.setVisibility(View.GONE);
            holder.llOutputDate.setVisibility(View.GONE);
            holder.llProductCode.setVisibility(View.GONE);
            holder.llStockState.setVisibility(View.GONE);
            holder.llUpdateDate.setVisibility(View.GONE);
            holder.llUpdaterName.setVisibility(View.GONE);
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


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBarCode)
        TextView tvBarCode;
        @BindView(R.id.tvGoodsType)
        TextView tvGoodsType;
        @BindView(R.id.tvWmsWarehouseName)
        TextView tvWmsWarehouseName;
        @BindView(R.id.tvWmsWarehouseDetailName)
        TextView tvWmsWarehouseDetailName;
        @BindView(R.id.tvInStockDay)
        TextView tvInStockDay;
        @BindView(R.id.tvAwb)
        TextView tvAwb;
        @BindView(R.id.llAwb)
        LinearLayout llAwb;
        @BindView(R.id.tvGoodsName)
        TextView tvGoodsName;
        @BindView(R.id.llGoodsName)
        LinearLayout llGoodsName;
        @BindView(R.id.tvProductCode)
        TextView tvProductCode;
        @BindView(R.id.llProductCode)
        LinearLayout llProductCode;
        @BindView(R.id.tvStockState)
        TextView tvStockState;
        @BindView(R.id.llStockState)
        LinearLayout llStockState;
        @BindView(R.id.tvInputDate)
        TextView tvInputDate;
        @BindView(R.id.llInputDate)
        LinearLayout llInputDate;
        @BindView(R.id.tvOutputDate)
        TextView tvOutputDate;
        @BindView(R.id.llOutputDate)
        LinearLayout llOutputDate;
        @BindView(R.id.tvUpdaterName)
        TextView tvUpdaterName;
        @BindView(R.id.llUpdaterName)
        LinearLayout llUpdaterName;
        @BindView(R.id.tvUpdateDate)
        TextView tvUpdateDate;
        @BindView(R.id.llUpdateDate)
        LinearLayout llUpdateDate;
        @BindView(R.id.ivZk)
        ImageView ivZk;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


