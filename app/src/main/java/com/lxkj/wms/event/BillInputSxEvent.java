package com.lxkj.wms.event;

/**
 * Created by kxn on 2020/5/13 0013.
 */
public class BillInputSxEvent {
    public String barCode;//条形码
    public String inputDateStart;//入库开始日期
    public String inputDateEnd;//入库结束日期
    public String wmsWarehouseId;//入库仓库
    public String palletNumber;//托盘号
    public String weight;//	重量
    public String goodsType;//	货物分类
    public String goodsName;//	货物品名（包括包装、体积或尺寸）
    public String updaterName;//	操作员姓名
    public String updateDateStart;//	操作开始时间
    public String updateDateEnd;//	操作结束时间

    public BillInputSxEvent(String barCode, String inputDateStart, String inputDateEnd, String wmsWarehouseId, String palletNumber, String weight, String goodsType, String goodsName, String updaterName, String updateDateStart, String updateDateEnd) {
        this.barCode = barCode;
        this.inputDateStart = inputDateStart;
        this.inputDateEnd = inputDateEnd;
        this.wmsWarehouseId = wmsWarehouseId;
        this.palletNumber = palletNumber;
        this.weight = weight;
        this.goodsType = goodsType;
        this.goodsName = goodsName;
        this.updaterName = updaterName;
        this.updateDateStart = updateDateStart;
        this.updateDateEnd = updateDateEnd;
    }
}
