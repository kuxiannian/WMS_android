package com.lxkj.wms.ui.fragment.ckjh;

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
import com.lxkj.wms.event.BillOutputSxEvent;
import com.lxkj.wms.http.BaseCallback;
import com.lxkj.wms.http.OkHttpHelper;
import com.lxkj.wms.http.Url;
import com.lxkj.wms.ui.fragment.TitleFragment;
import com.lxkj.wms.ui.fragment.ckjh.adapter.HistoryCkAdapter;
import com.lxkj.wms.ui.fragment.rksh.ShaiXuanRkFra;
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
 * Created by kxn on 2020/4/25 0025.
 */
public class HistoryCkFra  extends TitleFragment implements NaviRightListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    List<SortingRegisterBean.ResultBean.ContentBean> list;
    HistoryCkAdapter adapter;
    private int page = 0;
    private boolean isMore = true;//是否有更多数据


    private String barCode;//条形码
    private String outputDateStart;//出库开始日期
    private String outputDateEnd;//出库结束日期
    private String ladingNumber;//提货单编号
    private String consignor;//提货人
    private String consignorPhone;//提货人联系方式
    private String goodsType;//货物分类
    private String wmsWarehouseId;//出库仓库
    private String goodsName;//货物品名（包括包装、体积或尺寸）
    private String updaterName;//操作员姓名
    private String updateDateStart;//操作开始时间
    private String updateDateEnd;//操作结束时间


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
        adapter = new HistoryCkAdapter(mContext, list);
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
                findBillOutputPage();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                findBillOutputPage();
                refreshLayout.setNoMoreData(false);
            }
        });
        refreshLayout.autoRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectSx(BillOutputSxEvent billOutputSxEvent) {
        barCode = billOutputSxEvent.barCode;
        outputDateStart = billOutputSxEvent.outputDateStart;
        outputDateEnd = billOutputSxEvent.outputDateEnd;
        wmsWarehouseId = billOutputSxEvent.wmsWarehouseId;
        ladingNumber = billOutputSxEvent.ladingNumber;
        consignorPhone = billOutputSxEvent.consignorPhone;
        consignor = billOutputSxEvent.consignor;
        goodsType = billOutputSxEvent.goodsType;
        goodsName = billOutputSxEvent.goodsName;
        updaterName = billOutputSxEvent.updaterName;
        updateDateStart = billOutputSxEvent.updateDateStart;
        updateDateEnd = billOutputSxEvent.updateDateEnd;
        refreshLayout.autoRefresh();
    }


    /**
     * 分页查询数据接口
     */
    private void findBillOutputPage() {
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        params.put("size", "10");
        if (null != barCode)
            params.put("barCode", barCode);
        if (!StringUtil.isEmpty(outputDateStart))
            params.put("outputDateStart", outputDateStart);
        if (!StringUtil.isEmpty(outputDateEnd))
            params.put("outputDateEnd", outputDateEnd);
        if (!StringUtil.isEmpty(wmsWarehouseId))
            params.put("wmsWarehouseId", wmsWarehouseId);
        if (null != ladingNumber)
            params.put("ladingNumber", ladingNumber);
        if (!StringUtil.isEmpty(consignorPhone))
            params.put("consignorPhone", consignorPhone);
        if (null != consignor)
            params.put("consignor", consignor);
        if (!StringUtil.isEmpty(goodsType))
            params.put("goodsType", goodsType);
        if (!StringUtil.isEmpty(goodsName))
            params.put("goodsName", goodsName);
        if (!StringUtil.isEmpty(updaterName))
            params.put("updaterName", updaterName);
        if (!StringUtil.isEmpty(updateDateStart))
            params.put("updateDateStart", updateDateStart);
        if (!StringUtil.isEmpty(updateDateEnd))
            params.put("updateDateEnd", updateDateEnd);

        OkHttpHelper.getInstance().get_json(mContext, Url.findBillOutputPage, params, new BaseCallback<SortingRegisterBean>() {
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
        ActivitySwitcher.startFragment(act, ShaiXuanRkFra.class);
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
