package com.lxkj.wms.bean;

/**
 * Created by kxn on 2019/12/5 0005.
 */
public class BaseBean {
    public boolean flag;
    public String errorCode;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
