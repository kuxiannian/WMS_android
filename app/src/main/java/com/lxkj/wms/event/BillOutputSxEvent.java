package com.lxkj.wms.event;

/**
 * Created by kxn on 2020/5/13 0013.
 */
public class BillOutputSxEvent {
    public String barCode;//条形码
    public String outputDateStart;//出库开始日期
    public String outputDateEnd;//出库结束日期
    public String ladingNumber;//提货单编号
    public String consignor;//提货人
    public String consignorPhone;//提货人联系方式
    public String goodsType;//货物分类
    public String wmsWarehouseId;//出库仓库
    public String goodsName;//货物品名（包括包装、体积或尺寸）
    public String updaterName;//操作员姓名
    public String updateDateStart;//操作开始时间
    public String updateDateEnd;//操作结束时间

    public BillOutputSxEvent(String barCode, String outputDateStart, String outputDateEnd, String ladingNumber, String consignor, String consignorPhone,
                             String goodsType, String wmsWarehouseId, String goodsName, String updaterName, String updateDateStart, String updateDateEnd) {
        this.barCode = barCode;
        this.outputDateStart = outputDateStart;
        this.outputDateEnd = outputDateEnd;
        this.ladingNumber = ladingNumber;
        this.consignor = consignor;
        this.consignorPhone = consignorPhone;
        this.goodsType = goodsType;
        this.wmsWarehouseId = wmsWarehouseId;
        this.goodsName = goodsName;
        this.updaterName = updaterName;
        this.updateDateStart = updateDateStart;
        this.updateDateEnd = updateDateEnd;
    }
}
