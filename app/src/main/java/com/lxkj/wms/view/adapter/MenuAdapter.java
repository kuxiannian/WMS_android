package com.lxkj.wms.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxkj.wms.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kxn on 2019/12/11 0011.
 */
public class MenuAdapter extends BaseAdapter {
    Context context;
    List<String> items;

    public MenuAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tv_menu, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else
            holder = ((ViewHolder) view.getTag());

        if (i == 0)
            holder.tvItem.setBackgroundResource(R.drawable.bg_rect_white_top_10dp);
        else
            holder.tvItem.setBackgroundColor(context.getResources().getColor(R.color.white));


        holder.tvItem.setText(items.get(i));
        return view;
    }


    class ViewHolder {
        @BindView(R.id.tvItem)
        TextView tvItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

