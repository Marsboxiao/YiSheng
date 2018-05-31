package com.zty.yisheng.model.bean;

import java.util.List;

/**
 * Created by 92915 on 2018/4/11.
 */

public class LabelListBean {


    /**
     * code : 1
     * data : [{"knowledgeid":"1","knowledgename":"足踝扭伤急救法"},{"knowledgeid":"26","knowledgename":"突发心脏疾病"}]
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
         * knowledgeid : 1
         * knowledgename : 足踝扭伤急救法
         */

        private String knowledgeid;
        private String knowledgename;

        public String getKnowledgeid() {
            return knowledgeid;
        }

        public void setKnowledgeid(String knowledgeid) {
            this.knowledgeid = knowledgeid;
        }

        public String getKnowledgename() {
            return knowledgename;
        }

        public void setKnowledgename(String knowledgename) {
            this.knowledgename = knowledgename;
        }
    }
}
