package com.zty.yisheng.model.bean;

/**
 * Created by 92915 on 2018/4/11.
 */

public class KnowledgeBean {

    /**
     * code : 1
     * data : {"knowledgename":"足踝扭伤急救法","knowledgecontent":"轻度足踝扭伤，应先冷敷患处，２４小时后改用热敷，用绷带缠住足踝，把脚垫高，即可减轻症状。"}
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
         * knowledgename : 足踝扭伤急救法
         * knowledgecontent : 轻度足踝扭伤，应先冷敷患处，２４小时后改用热敷，用绷带缠住足踝，把脚垫高，即可减轻症状。
         */

        private String knowledgename;
        private String knowledgecontent;

        public String getKnowledgename() {
            return knowledgename;
        }

        public void setKnowledgename(String knowledgename) {
            this.knowledgename = knowledgename;
        }

        public String getKnowledgecontent() {
            return knowledgecontent;
        }

        public void setKnowledgecontent(String knowledgecontent) {
            this.knowledgecontent = knowledgecontent;
        }
    }
}
