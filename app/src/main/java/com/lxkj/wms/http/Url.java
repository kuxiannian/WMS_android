package com.lxkj.wms.http;


/**
 * Created by kxn on 2018/5/17 0017.
 */

public class Url {
    //服务器地址
    public static String IP = "https://218.93.19.166:8080";
    //用户登录
    public static String LOGIN = IP + "/upmsapi/user/login";
    //获取密码规则
    public static String InitPwdRule = IP + "/upmsapi/user/initPwdRule";
    //获取权限编码列表
    public static String getPrmCodeList = IP + "/upmsapi/prm/getPrmCodeList";
    //登出
    public static String logout = IP + "/upmsapi/user/logout";
    /**
     * 分拣登记
     */
    //根据AWB搜索舱单信息接口
    public static String findManifestByAwb = IP + "/wms/manifest/findManifestByAwb";
    //新增数据接口
    public static String addSortingRegister = IP + "/wms/sortingRegister/addSortingRegister";
    //分页查询数据接口
    public static String findSortingRegisterPage = IP + "/wms/sortingRegister/findSortingRegisterPage";
    //编辑数据接口
    public static String updateSortingRegister = IP + "/wms/sortingRegister/updateSortingRegister";
    //删除数据接口
    public static String deleteSortingRegister = IP + "/wms/sortingRegister/deleteSortingRegister";
    //查询仓库列表接口
    public static String findWarehouseList = IP + "/wms/sortingRegister/findWarehouseList";
    //根据舱单信息ID查询舱单航班信息接口
    public static String findFlightByWmsManifestId = IP + "/wms/manifest/findFlightByWmsManifestId";
    //根据舱单信息ID查询舱单货物信息接口
    public static String findGoodsNameByWmsManifestId = IP + "/wms/manifest/findGoodsNameByWmsManifestId";
    //根据舱单货物ID查询舱单货物信息接口
    public static String findWmsManifestGoodsByGoodsNameId = IP + "/wms/manifest/findWmsManifestGoodsByGoodsNameId";
    //根据分拣登记ID查询分拣登记明细信息接口
    public static String findWmsSortingRegisterDetailList = IP + "/wms/sortingRegister/findWmsSortingRegisterDetailList";

    /**
     * 入库单
     */
    //分页查询数据接口
    public static String findBillInputPage = IP + "/wms/billInput/findBillInputPage";
    //新增数据接口
    public static String addBillInput = IP + "/wms/billInput/addBillInput";
    //查询仓库列表接口
    public static String findWarehouseListBillInput = IP + "/wms/billInput/findWarehouseList";
    //根据条形码查询入库所需相关信息接口
    public static String findInfoByBarCode = IP + "/wms/billInput/findInfoByBarCode";


    /**
     * 出库单
     */
    //分页查询数据接口
    public static String findBillOutputPage = IP + "/wms/billOutput/findBillOutputPage";
    //新增数据接口
    public static String addBillOutput = IP + "/wms/billOutput/addBillOutput";
    //查询仓库列表接口
    public static String findWarehouseListBillOnput = IP + "/wms/billOutput/findWarehouseList";
    //根据条形码查询入库所需相关信息接口
    public static String findInfoByBarCodeBillOut = IP + "/wms/billOutput/findInfoByBarCode";

    /**
     *库存查询
     */
    //分页查询数据接口
    public static String findStockPage = IP + "/wms/stock/findStockPage";
    //查询仓库列表接口
    public static String findWarehouseListStock = IP + "/wms/stock/findWarehouseList";



}
