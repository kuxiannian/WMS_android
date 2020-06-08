package com.lxkj.wms.ui.fragment.hwdj;

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
import com.lxkj.wms.biz.EventCenter;
import com.lxkj.wms.event.FjdjSxEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.hwdj.adapter.HistoryHdAdapter;
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
 * Created by kxn on 2020/4/24 0024.
 */
public class HistoryHdFra extends TitleFragment implements NaviRightListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    List<SortingRegisterBean.ResultBean.ContentBean> list;
    HistoryHdAdapter adapter;
    private int page = 0;
    private boolean isMore = true;//是否有更多数据

    private String awb;//	AWB号
    private String flightName;//	航班
    private String goodsName;//	货物品名（包括包装、体积或尺寸）
    private String registerNumber;//	否	登记件数
    private String wmsWarehouseId;//	否	分拣登记地点
    private String goodsType;//	否	货物分类
    private String departureStation;//	否	始发站、第一承运人地址及要求的路线
    private String destinationStation;//	否	目的站
    private String productCode;//	否	商品代号

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
        eventCenter.registEvent(this, EventCenter.EventType.EVT_EDIT);
        eventCenter.registEvent(this, EventCenter.EventType.EVT_DELETE);
        list = new ArrayList<>();
        adapter = new HistoryHdAdapter(mContext, list);
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
                findSortingRegisterPage();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                findSortingRegisterPage();
                refreshLayout.setNoMoreData(false);
            }
        });
        refreshLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectAddress(FjdjSxEvent fjdjSxEvent) {
        awb = fjdjSxEvent.awb;
        flightName = fjdjSxEvent.flightName;
        goodsName = fjdjSxEvent.goodsName;
        registerNumber = fjdjSxEvent.registerNumber;
        wmsWarehouseId = fjdjSxEvent.wmsWarehouseId;
        goodsType = fjdjSxEvent.goodsType;
        departureStation = fjdjSxEvent.departureStation;
        destinationStation = fjdjSxEvent.destinationStation;
        productCode = fjdjSxEvent.productCode;
        refreshLayout.autoRefresh();
    }


    /**
     * 分页查询数据接口
     */
    private void findSortingRegisterPage() {
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("size", "10");
        if (null != awb)
            params.put("awb", awb);
        if (!StringUtil.isEmpty(flightName))
            params.put("flightName", flightName);
        if (!StringUtil.isEmpty(goodsName))
            params.put("goodsName", goodsName);
        if (!StringUtil.isEmpty(registerNumber))
            params.put("registerNumber", registerNumber);
        if (null != wmsWarehouseId)
            params.put("wmsWarehouseId", wmsWarehouseId);
        if (null != goodsType)
            params.put("goodsType", goodsType);
        if (!StringUtil.isEmpty(departureStation))
            params.put("departureStation", departureStation);
        if (!StringUtil.isEmpty(destinationStation))
            params.put("destinationStation", destinationStation);
        if (!StringUtil.isEmpty(productCode))
            params.put("productCode", productCode);

        OkHttpHelper.getInstance().get_json(mContext, Url.findSortingRegisterPage, params, new BaseCallback<SortingRegisterBean>() {
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
        ActivitySwitcher.startFragment(act, ShaiXuanFra.class);
    }

    @Override
    public void onEvent(EventCenter.HcbEvent e) {
        super.onEvent(e);
        switch (e.type) {
            case EVT_DELETE:
            case EVT_EDIT:
                refreshLayout.autoRefresh();
                break;
        }
    }
}
