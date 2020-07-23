package com.lxkj.wms.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lxkj.wms.R;
import com.lxkj.wms.adapter.HomeAdapter;
import com.lxkj.wms.bean.HomeItemBean;
import com.lxkj.wms.ui.fragment.TitleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kxn on 2020/4/22 0022.
 */
public class MoreItemFra extends TitleFragment {
    @BindView(R.id.gv)
    GridView gv;
    Unbinder unbinder;
    List<HomeItemBean> items;
    HomeAdapter adapter;
    @Override
    public String getTitleName() {
        return mContext.getString(R.string.gdgn);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fra_more_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        items = new ArrayList<>();
        items.add(new HomeItemBean(getResources().getString(R.string.sjzy),R.mipmap.ic_sjzy));
        items.add(new HomeItemBean(getResources().getString(R.string.xjzy),R.mipmap.ic_xjzy));
        adapter = new HomeAdapter(mContext,items);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
