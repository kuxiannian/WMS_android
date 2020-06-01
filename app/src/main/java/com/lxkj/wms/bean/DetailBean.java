package com.lxkj.wms.bean;

/**
 * Created by kxn on 2020/5/16 0016.
 */
public class DetailBean {

    private String barCode;
    private String wmsWarehouseId;
    private String wmsWarehouseDetailId;
    private String version;
    private String id;
    private String deleteFlag;

    public DetailBean(String id, String barCode, String wmsWarehouseId, String wmsWarehouseDetailId) {
        if (null != id)
            this.id = id;
        this.barCode = barCode;
        this.wmsWarehouseId = wmsWarehouseId;
        this.wmsWarehouseDetailId = wmsWarehouseDetailId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
