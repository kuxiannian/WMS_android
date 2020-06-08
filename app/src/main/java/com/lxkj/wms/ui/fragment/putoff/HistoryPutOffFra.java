package com.lxkj.wms.ui.fragment.putoff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxkj.wms.R;
import com.lxkj.wms.actlink.NaviRightListener;
import com.lxkj.wms.bean.SortingRegisterBean;
import com.lxkj.wms.biz.ActivitySwitcher;
import com.lxkj.wms.event.BillPutOffEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.putoff.adapter.PutOffAdapter;
import com.lxkj.wms.utils.ListUtil;
import com.lxkj.wms.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kxn on 2020/5/15 0015.
 */
public class HistoryPutOffFra extends TitleFragment implements NaviRightListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    List<SortingRegisterBean.ResultBean.ContentBean> list;
    PutOffAdapter adapter;
    private int page = 0;
    private boolean isMore = true;//是否有更多数据

    private String barCode;//	条形码
    private String putOffDateStart;//	下架开始日期
    private String putOffDateEnd;//	下架结束日期
    private String productCode;//	商品代号
    private String goodsName;//货物名称
    private String wmsWarehouseId;//下架的仓库
    private String wmsWarehouseDetailName;//下架的储位
    private String updaterName;//操作员姓名
    private String updateDateStart;//	操作开始时间
    private String updateDateEnd;//	操作结束时间


    @Override
    public String getTitleName() {
        return act.getString(R.string.lscx);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        list = new ArrayList<>();
        adapter = new PutOffAdapter(mContext, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!isMore) {
                    refreshLayout.setNoMoreData(true);
                    return;
                }
                page++;
                findBillPutOffPage();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                findBillPutOffPage();
                refreshLayout.setNoMoreData(false);
            }
        });
        refreshLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectSx(BillPutOffEvent billPutOffEvent) {
        barCode = billPutOffEvent.barCode;
        putOffDateStart = billPutOffEvent.putOffDateStart;
        putOffDateEnd = billPutOffEvent.putOffDateEnd;
        productCode = billPutOffEvent.productCode;
        goodsName = billPutOffEvent.goodsName;
        wmsWarehouseId = billPutOffEvent.wmsWarehouseId;
        wmsWarehouseDetailName = billPutOffEvent.wmsWarehouseDetailName;
        updaterName = billPutOffEvent.updaterName;
        updateDateStart = billPutOffEvent.updateDateStart;
        updateDateEnd = billPutOffEvent.updateDateEnd;
        refreshLayout.autoRefresh();
    }


    /**
     * 分页查询数据接口
     */
    private void findBillPutOffPage() {
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("size", "10");
        if (null != barCode)
            params.put("barCode", barCode);
        if (!StringUtil.isEmpty(putOffDateStart))
            params.put("putOffDateStart", putOffDateStart);
        if (!StringUtil.isEmpty(putOffDateEnd))
            params.put("putOffDateEnd", putOffDateEnd);
        if (!StringUtil.isEmpty(productCode))
            params.put("productCode", productCode);
        if (null != goodsName)
            params.put("goodsName", goodsName);
        if (null != wmsWarehouseId)
            params.put("wmsWarehouseId", wmsWarehouseId);
        if (!StringUtil.isEmpty(wmsWarehouseDetailName))
            params.put("wmsWarehouseDetailName", wmsWarehouseDetailName);
        if (!StringUtil.isEmpty(updaterName))
            params.put("updaterName", updaterName);
        if (!StringUtil.isEmpty(updateDateStart))
            params.put("updateDateStart", updateDateStart);
        if (!StringUtil.isEmpty(updateDateEnd))
            params.put("updateDateEnd", updateDateEnd);

        OkHttpHelper.getInstance().get_json(mContext, Url.findBillPutOffPage, params, new BaseCallback<SortingRegisterBean>() {
            @Override
            public void onBeforeRequest(Request request) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onFailure(Request request, Exception e) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onSuccess(Response response, SortingRegisterBean resultBean) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                if (page == 0) {
                    list.clear();
                    adapter.notifyDataSetChanged();
                }
                if (resultBean.flag) {
                    if (null != resultBean.result) {
                        if (!ListUtil.isEmpty(resultBean.result.getContent())) {
                            isMore = true;
                            list.addAll(resultBean.result.getContent());
                            adapter.notifyDataSetChanged();
                        } else
                            isMore = false;
                    } else {
                        isMore = false;
                    }
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public int rightText() {
        return R.string.sx;
    }

    @Override
    public void onRightClicked(View v) {
        ActivitySwitcher.startFragment(act, ShaiXuanPutOffFra.class);
    }

}


