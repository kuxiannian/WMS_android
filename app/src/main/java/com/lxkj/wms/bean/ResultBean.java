package com.lxkj.wms.bean;

import java.io.Serializable;

/**
 * Created by kxn on 2019/1/21 0021.
 */
public class ResultBean extends BaseBean implements Serializable {
    public String totalPage;
    public String code;
    public String ordernum;
    public String uid;
    public String isnewuser;// "0首次登录 1老用户",
    public String isregister;//"0未注册 1已注册"

}
