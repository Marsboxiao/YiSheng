package com.zty.yisheng.model.bean;

import java.util.List;

/**
 * Created by 92915 on 2018/5/1.
 */

public class SysSettingBean {


    /**
     * code : 1
     * data : [{"strkey":"receivetimeout","strvalue":"5"},{"strkey":"startuptimeout","strvalue":"30"},{"strkey":"answerhanguptimeout","strvalue":"10"}]
     * message :
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * strkey : receivetimeout
         * strvalue : 5
         */

        private String strkey;
        private String strvalue;

        public String getStrkey() {
            return strkey;
        }

        public void setStrkey(String strkey) {
            this.strkey = strkey;
        }

        public String getStrvalue() {
            return strvalue;
        }

        public void setStrvalue(String strvalue) {
            this.strvalue = strvalue;
        }
    }
}
