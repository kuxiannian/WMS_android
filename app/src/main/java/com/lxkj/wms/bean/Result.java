package com.lxkj.wms.bean;

/**
 * Created by kxn on 2020/5/8 0008.
 */
public class Result {
    public String userId;//用户id
    public String account;//账号
    public String userName;//名字
    public String sessionTimeout;
    public String sessionNumPerUser;
    public int minLength;//密码最小长度
    public int maxLength;//密码最长长度
    public String containsUppercaseLetters;//是否包含大写字母（非0校验, 0不校验）
    public String containsLowercaseLetters;//是否包含小写字母（非0校验, 0不校验）
    public String containsDigitalNumber;//是否包含小写字母（非0校验, 0不校验）
    public String containsSpecialCharacters;//是否包含小写字母（非0校验, 0不校验）
    public String leftTimes;

    public String chargeableWeight;    //计费重量
    public String createDate;    //创建日期
    public String createrId;    //创建人Id
    public String createrName;    //创建人姓名
    public String deleteFlag;    //删除标识符
    public String goodsName;    //货物品名（包括包装、体积或尺寸）
    public String grossWeight;    //毛重
    public String id;    //舱单货物ID
    public String lineNumber;    //行号
    public String number;    //件数
    public String productCode;    //商品代号
    public String rate;    //费率/费用
    public String rateClass;    //运价种类
    public String rcp;    //运价点
    public String total;    //总费用
    public String updateDate;    //更新日期
    public String updaterId;    //更新人Id
    public String updaterName;    //更新人姓名
    public String version;    //版本号
    public String wmsManifestId;    //舱单信息ID

    public String barCod;//	条形码
    public String flightId;//	航班
    public String goodsNameId;//	货物品名（包括包装、体积或尺寸）
    public String wmsSortingRegisterId;//	分拣登记ID

    public String goodsType;//	货物类型
    public String weight;//	重量
    public String wmsWarehouseId;//	入库仓库ID
    public String wmsWarehouseIdName;//	入库仓库名称
    public String suspicion;//	是否有嫌疑
    public String wmsStockId;//	String	库存表ID


    public String barCode;
    public String alNumber;
    public String canNumber;
    public String code;


    public String palletNumber;//托盘号
    public String wmsWarehouseName;//仓库名称

    public String wmsWarehouseDetailId;//下架的储位Id
    public String wmsWarehouseDetailName;//	下架的储位

    public String inputDate;//入库日期
    public String remarks;//备注

    public String registerNumber;

    public String ladingNumber;
    public String outputDate;
    public String consignor;
    public String consignorPhone;
    public String putOnDate;
    public String putOffDate;
}
