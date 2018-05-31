package com.zty.yisheng.model.bean;

import java.util.List;

/**
 * Created by 92915 on 2018/4/11.
 */

public class ClassListBean {

    /**
     * code : 1
     * data : [{"knowledgeid":"1","knowledgename":"足踝扭伤急救法"},{"knowledgeid":"3","knowledgename":"动脉出血急救法"},{"knowledgeid":"6","knowledgename":"骨折急救法"},{"knowledgeid":"9","knowledgename":"韭菜治误吞金属异物法"},{"knowledgeid":"10","knowledgename":"咯血急救法"},{"knowledgeid":"17","knowledgename":"婴幼儿窒息急救法"},{"knowledgeid":"18","knowledgename":"异物吸入急救法"},{"knowledgeid":"19","knowledgename":"血压升高自救法"},{"knowledgeid":"20","knowledgename":"胸部外伤急救法"},{"knowledgeid":"21","knowledgename":"心脏病发作急救法"},{"knowledgeid":"22","knowledgename":"脱臼急救法"},{"knowledgeid":"23","knowledgename":"头部外伤急救法"}]
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
