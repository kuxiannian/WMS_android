package com.lxkj.wms.bean;

import java.util.List;

/**
 * Created by kxn on 2020/5/12 0012.
 */
public class SortingRegisterDetailBean extends BaseBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private String barCode;//	条形码
        private String createDate;//	创建日期
        private String createrId;//	创建人Id
        private String createrName;//	创建人姓名
        private String deleteFlag;//	删除标识符
        private String flightId;//	航班
        private String goodsNameId;//	货物品名（包括包装、体积或尺寸）
        private String id;//	分拣登记明细ID
        private String lineNumber;//	行号
        private String updateDate;//	更新日期
        private String updaterId;//	更新人Id
        private String updaterName;//	更新人姓名
        private String version;//	版本号
        private String wmsSortingRegisterId;//	分拣登记ID

        private String code;//	储位编号
        private String columns;//	列
        private String height;//	高
        private String layer;//	层
        private String length;//	长
        private String priority;//	自动分配储位优先级
        private String row;//	排
        private String state;//	状态
        private String type;//	储位类型
        private String volume;//	体积
        private String width;//	宽
        private String wmsWarehouseId;//	仓库ID
        private String wmsWarehouseModel;//	仓库数据

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getColumns() {
            return columns;
        }

        public void setColumns(String columns) {
            this.columns = columns;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getLayer() {
            return layer;
        }

        public void setLayer(String layer) {
            this.layer = layer;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public String getRow() {
            return row;
        }

        public void setRow(String row) {
            this.row = row;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getWmsWarehouseId() {
            return wmsWarehouseId;
        }

        public void setWmsWarehouseId(String wmsWarehouseId) {
            this.wmsWarehouseId = wmsWarehouseId;
        }

        public String getWmsWarehouseModel() {
            return wmsWarehouseModel;
        }

        public void setWmsWarehouseModel(String wmsWarehouseModel) {
            this.wmsWarehouseModel = wmsWarehouseModel;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreaterId() {
            return createrId;
        }

        public void setCreaterId(String createrId) {
            this.createrId = createrId;
        }

        public String getCreaterName() {
            return createrName;
        }

        public void setCreaterName(String createrName) {
            this.createrName = createrName;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getFlightId() {
            return flightId;
        }

        public void setFlightId(String flightId) {
            this.flightId = flightId;
        }

        public String getGoodsNameId() {
            return goodsNameId;
        }

        public void setGoodsNameId(String goodsNameId) {
            this.goodsNameId = goodsNameId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(String lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getUpdaterId() {
            return updaterId;
        }

        public void setUpdaterId(String updaterId) {
            this.updaterId = updaterId;
        }

        public String getUpdaterName() {
            return updaterName;
        }

        public void setUpdaterName(String updaterName) {
            this.updaterName = updaterName;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getWmsSortingRegisterId() {
            return wmsSortingRegisterId;
        }

        public void setWmsSortingRegisterId(String wmsSortingRegisterId) {
            this.wmsSortingRegisterId = wmsSortingRegisterId;
        }
    }
}
