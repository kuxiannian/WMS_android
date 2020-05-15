package com.lxkj.wms.event;

/**
 * Created by kxn on 2020/5/15 0015.
 */
public class BillPutOffEvent {
    public String barCode;//	条形码
    public String putOffDateStart;//	下架开始日期
    public String putOffDateEnd;//	下架结束日期
    public String productCode;//	商品代号
    public String goodsName;//货物名称
    public String wmsWarehouseId;//下架的仓库
    public String wmsWarehouseDetailName;//下架的储位
    public String updaterName;//操作员姓名
    public String updateDateStart;//	操作开始时间
    public String updateDateEnd;//	操作结束时间

    public BillPutOffEvent(String barCode, String putOffDateStart, String putOffDateEnd, String productCode, String goodsName, String wmsWarehouseId, String wmsWarehouseDetailName, String updaterName, String updateDateStart, String updateDateEnd) {
        this.barCode = barCode;
        this.putOffDateStart = putOffDateStart;
        this.putOffDateEnd = putOffDateEnd;
        this.productCode = productCode;
        this.goodsName = goodsName;
        this.wmsWarehouseId = wmsWarehouseId;
        this.wmsWarehouseDetailName = wmsWarehouseDetailName;
        this.updaterName = updaterName;
        this.updateDateStart = updateDateStart;
        this.updateDateEnd = updateDateEnd;
    }
}
