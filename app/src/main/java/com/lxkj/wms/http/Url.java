package com.lxkj.wms.http;


/**
 * Created by kxn on 2018/5/17 0017.
 */

public class Url {
    //服务器地址
    public static String IP = "https://218.93.19.166:8080";
    //用户登录
    public static String LOGIN =  "/upmsapi/user/login";
    //获取密码规则
    public static String InitPwdRule = "/upmsapi/user/initPwdRule";
    //获取权限编码列表
    public static String getPrmCodeList = "/upmsapi/prm/getPrmCodeList";
    //登出
    public static String logout = "/upmsapi/user/logout";
    //修改密码
    public static String changepassword =  "/upmsapi/user/changepassword";

    /**
     * 分拣登记
     */
    //根据AWB搜索舱单信息接口
    public static String findManifestByAwb = "/wms/manifest/findManifestByAwb";
    //新增数据接口
    public static String addSortingRegister = "/wms/sortingRegister/addSortingRegister";
    //分页查询数据接口
    public static String findSortingRegisterPage =  "/wms/sortingRegister/findSortingRegisterPage";
    //编辑数据接口
    public static String updateSortingRegister =  "/wms/sortingRegister/updateSortingRegister";
    //删除数据接口
    public static String deleteSortingRegister =  "/wms/sortingRegister/deleteSortingRegister";
    //查询仓库列表接口
    public static String findWarehouseList = "/wms/sortingRegister/findWarehouseList";
    //根据舱单信息ID查询舱单航班信息接口
    public static String findFlightByWmsManifestId = "/wms/manifest/findFlightByWmsManifestId";
    //根据舱单信息ID查询舱单货物信息接口
    public static String findGoodsNameByWmsManifestId =  "/wms/manifest/findGoodsNameByWmsManifestId";
    //根据舱单货物ID查询舱单货物信息接口
    public static String findWmsManifestGoodsByGoodsNameId = "/wms/manifest/findWmsManifestGoodsByGoodsNameId";
    //根据分拣登记ID查询分拣登记明细信息接口
    public static String findWmsSortingRegisterDetailList =  "/wms/sortingRegister/findWmsSortingRegisterDetailList";

    /**
     * 入库单
     */
    //分页查询数据接口
    public static String findBillInputPage =  "/wms/billInput/findBillInputPage";
    //新增数据接口
    public static String addBillInput = "/wms/billInput/addBillInput";
    //查询仓库列表接口
    public static String findWarehouseListBillInput = "/wms/billInput/findWarehouseList";
    //根据条形码查询入库所需相关信息接口
    public static String findInfoByBarCode = "/wms/billInput/findInfoByBarCode";


    /**
     * 出库单
     */
    //分页查询数据接口
    public static String findBillOutputPage =  "/wms/billOutput/findBillOutputPage";
    //新增数据接口
    public static String addBillOutput =  "/wms/billOutput/addBillOutput";
    //查询仓库列表接口
    public static String findWarehouseListBillOnput = "/wms/billOutput/findWarehouseList";
    //根据条形码查询入库所需相关信息接口
    public static String findInfoByBarCodeBillOut =  "/wms/billOutput/findInfoByBarCode";

    /**
     *库存查询
     */
    //分页查询数据接口
    public static String findStockPage = "/wms/stock/findStockPage";
    //查询仓库列表接口
    public static String findWarehouseListStock =  "/wms/stock/findWarehouseList";

    /**
     * 盘点单
     */
    //分页查询数据接口
    public static String findBillStockCheckPage =  "/wms/billStockCheck/findBillStockCheckPage";
    //保存草稿数据接口
    public static String updateBillStockCheckTemp =  "/wms/billStockCheck/updateBillStockCheckTemp";
    //完成盘点接口
    public static String updateBillStockCheckOver =  "/wms/billStockCheck/updateBillStockCheckOver";
    //查询仓库列表接口
    public static String findWarehouseListStockCheck =  "/wms/billStockCheck/findWarehouseList";
    //查询储位列表接口
    public static String findWarehouseDetailList =  "/wms/warehouseDetail/findWarehouseDetailList";
    //查询盘点明细数据接口
    public static String findBillStockCheckDetailList = "/wms/billStockCheck/findBillStockCheckDetailList";

    /**
     * 上架单
     */
    //分页查询数据接口
    public static String findBillPutOnPage = "/wms/billPutOn/findBillPutOnPage";
    //新增数据接口
    public static String addBillPutOn =  "/wms/billPutOn/addBillPutOn";
    //根据条形码查询上架单所需相关信息接口
    public static String findInfoByBarCodeBillPutOn =  "/wms/billPutOn/findInfoByBarCode";
    //上架时输入长、宽、高自动分配储位接口
    public static String findTopByPriority ="/wms/warehouseDetail/findTopByPriority";
    //查询仓库列表接口
    public static String findWarehouseListBillPutOn = "/wms/billPutOn/findWarehouseList";

    /**
     * 下架单
     */
    //分页查询数据接口
    public static String findBillPutOffPage =  "/wms/billPutOff/findBillPutOffPage";
    //根据条形码查询所需相关信息接口
    public static String findInfoByBarCodeBillPutOff =  "wms/billPutOff/findInfoByBarCode";
    //新增数据接口
    public static String addBillPutOff =  "/wms/billPutOff/addBillPutOff";
    //查询仓库列表接口
    public static String findWarehouseListBillPutOff = "/wms/billPutOff/findWarehouseList";

}
