package com.lxkj.wms.bean;

import java.util.List;

/**
 * Created by kxn on 2020/5/9 0009.
 */
public class PrmCodeListBean extends BaseBean {

    /**
     * message : null
     * errorCode : null
     * result : ["UPMS","VICENTER-WMS"]
     */

    private List<String> result;

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
