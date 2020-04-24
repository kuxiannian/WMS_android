package com.lxkj.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxkj.wms.R;
import com.lxkj.wms.bean.HomeItemBean;
import com.lxkj.wms.utils.DisplayUtil;
import com.lxkj.wms.utils.ScreenUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2020/4/22 0022.
 * 首页功能模块适配器
 */
public class HomeAdapter extends BaseAdapter {
    Context context;
    List<HomeItemBean> itemBeans;

    public HomeAdapter(Context context, List<HomeItemBean> itemBeans) {
        this.context = context;
        this.itemBeans = itemBeans;
    }

    @Override
    public int getCount() {
        return itemBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return itemBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home, null);
            viewHolder = new ViewHolder(view);
            ViewGroup.LayoutParams layoutParams = viewHolder.llItem.getLayoutParams();
            layoutParams.height = (ScreenUtil.getScreenWidth(context) - DisplayUtil.dip2px(context,30))/2;
            viewHolder.llItem.setLayoutParams(layoutParams);
            view.setTag(viewHolder);
        } else
            viewHolder = ((ViewHolder) view.getTag());

        viewHolder.tvName.setText(itemBeans.get(position).name);
        viewHolder.ivIcon.setImageResource(itemBeans.get(position).icon);
        switch (itemBeans.get(position).name) {
            case "货物登记":
                viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.color_hwdj));
                break;
            case "入库收货":
                viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.color_rksh));
                break;
            case "出库拣货":
                viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.color_ckjh));
                break;
            case "库存查询":
                viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.color_kccx));
                break;
            case "库存盘点":
                viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.color_kcpd));
                break;
            case "更多功能":
                viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.color_gdgn));
                break;
            case "上架作业":
                viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.color_sjzy));
                break;
            case "下架作业":
                viewHolder.tvName.setTextColor(context.getResources().getColor(R.color.color_xjzy));
                break;
        }



        return view;
    }


    class ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.llItem)
        LinearLayout llItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
