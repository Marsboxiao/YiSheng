package com.zty.yisheng.model.bean;

/**
 * Created by 92915 on 2018/4/11.
 * xx:xx:xx
 */

public class DocTimeBean {

    /**
     * code : 1
     * data : {"begintime":"2018/1/1 1:01:01","endtime":"2018/1/1 2:02:02"}
     * message :
     */

    private String code;
    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * begintime : 2018/1/1 1:01:01
         * endtime : 2018/1/1 2:02:02
         */

        private String begintime;
        private String endtime;

        public String getBegintime() {
            return begintime;
        }

        public void setBegintime(String begintime) {
            this.begintime = begintime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }
}
