package com.lxkj.wms.bean;

import java.util.List;

/**
 * Created by kxn on 2020/5/11 0011.
 */
public class WareHouseBean extends BaseBean{
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String address;//仓库地址
        private String category;//仓库类别
        private String city;//仓库所在城市
        private String code;//仓库编号
        private String createDate;//创建日期
        private String createrId;//创建人Id
        private String createrName;//创建人姓名
        private String deleteFlag;//删除标识
        private String id;//仓库ID
        private String name;//仓库名称
        private String state;//状态
        private String type;//仓库货物类型
        private String updateDate;//更新日期
        private String updaterId;//更新人Id
        private String updaterName;//更新人姓名
        private String volume;//仓库体积
        private String version;//版本号code	String	储位编号
        private String columns;//	列
        private String height;//	高
        private String layer;//	层
        private String length;//	长
        private String priority;//	自动分配储位优先级
        private String row;//	排
        private String width;//	宽
        private String wmsWarehouseId;//	仓库ID
        private WmsWarehouseModelBean wmsWarehouseModel;//	仓库数据
        private String barCode;//	条形码
        private String diffFlag;//	差异
        private String lineNumber;//	行号
        private String remarks;//	备注
        private String wmsBillStockCheckId;//	盘点单头部ID
        private String wmsWarehouseDetailId;//	页面填写的储位
        private String wmsWarehouseDetailIdActual;//	系统中所在的储位
        private String wmsWarehouseIdActual;//	系统中所在的仓库

        public WmsWarehouseModelBean getWmsWarehouseModel() {
            return wmsWarehouseModel;
        }

        public void setWmsWarehouseModel(WmsWarehouseModelBean wmsWarehouseModel) {
            this.wmsWarehouseModel = wmsWarehouseModel;
        }

        public static class WmsWarehouseModelBean{
            private String id;//
            private String name;
            private String code;
            private String type;
            private String category;
            private String state;
            private String volume;
            private String address;
            private String city;
            private String createrId;
            private String createrName;
            private String createDate;
            private String updaterId;
            private String updaterName;
            private String updateDate;
            private String deleteFlag;
            private String version;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
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

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
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

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public String getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getDiffFlag() {
            return diffFlag;
        }

        public void setDiffFlag(String diffFlag) {
            this.diffFlag = diffFlag;
        }

        public String getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(String lineNumber) {
            this.lineNumber = lineNumber;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getWmsBillStockCheckId() {
            return wmsBillStockCheckId;
        }

        public void setWmsBillStockCheckId(String wmsBillStockCheckId) {
            this.wmsBillStockCheckId = wmsBillStockCheckId;
        }

        public String getWmsWarehouseDetailId() {
            return wmsWarehouseDetailId;
        }

        public void setWmsWarehouseDetailId(String wmsWarehouseDetailId) {
            this.wmsWarehouseDetailId = wmsWarehouseDetailId;
        }

        public String getWmsWarehouseDetailIdActual() {
            return wmsWarehouseDetailIdActual;
        }

        public void setWmsWarehouseDetailIdActual(String wmsWarehouseDetailIdActual) {
            this.wmsWarehouseDetailIdActual = wmsWarehouseDetailIdActual;
        }

        public String getWmsWarehouseIdActual() {
            return wmsWarehouseIdActual;
        }

        public void setWmsWarehouseIdActual(String wmsWarehouseIdActual) {
            this.wmsWarehouseIdActual = wmsWarehouseIdActual;
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


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
