package com.lxkj.wms.event;

/**
 * Created by kxn on 2020/5/15 0015.
 */
public class StockCheckEvent {
    public String wmsWarehouseId;//仓库
    public String startDateStart;//盘点开始日期开始时间
    public String startDateEnd;//盘点开始日期结束时间
    public String endDateStart;//	盘点结束日期开始时间
    public String endDateEnd;//	盘点结束日期结束时间
    public String state;//状态
    public String updaterName;//	操作员姓名
    public String updateDateStart;//	操作开始时间
    public String updateDateEnd	;//	操作结束时间

    public StockCheckEvent(String wmsWarehouseId, String startDateStart, String startDateEnd, String endDateStart, String endDateEnd, String state, String updaterName, String updateDateStart, String updateDateEnd) {
        this.wmsWarehouseId = wmsWarehouseId;
        this.startDateStart = startDateStart;
        this.startDateEnd = startDateEnd;
        this.endDateStart = endDateStart;
        this.endDateEnd = endDateEnd;
        this.state = state;
        this.updaterName = updaterName;
        this.updateDateStart = updateDateStart;
        this.updateDateEnd = updateDateEnd;
    }
}
