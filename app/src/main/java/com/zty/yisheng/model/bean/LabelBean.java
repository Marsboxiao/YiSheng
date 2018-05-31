package com.zty.yisheng.model.bean;

import java.util.List;

/**
 * Created by 92915 on 2018/4/11.
 * 常识标签
 */

public class LabelBean {

    /**
     * code : 1
     * data : [{"labelid":"1","labelname":"发热"},{"labelid":"2","labelname":"头晕"},{"labelid":"3","labelname":"昏迷"},{"labelid":"4","labelname":"抽搐"},{"labelid":"5","labelname":"哮喘"},{"labelid":"6","labelname":"咳嗽"},{"labelid":"7","labelname":"呼吸困难"},{"labelid":"8","labelname":"心悸"},{"labelid":"9","labelname":"失眠"},{"labelid":"10","labelname":"头痛"},{"labelid":"11","labelname":"胸痛"},{"labelid":"12","labelname":"腹痛"},{"labelid":"13","labelname":"腰背痛"},{"labelid":"14","labelname":"咯血"},{"labelid":"15","labelname":"吐血"},{"labelid":"16","labelname":"便血"},{"labelid":"17","labelname":"尿血"},{"labelid":"18","labelname":"呕吐"},{"labelid":"19","labelname":"腹泻"},{"labelid":"20","labelname":"中毒"},{"labelid":"21","labelname":"中暑"},{"labelid":"22","labelname":"溺水"},{"labelid":"23","labelname":"烧烫伤"},{"labelid":"24","labelname":"动物咬伤"},{"labelid":"25","labelname":"昆虫咬伤"},{"labelid":"26","labelname":"外伤"},{"labelid":"27","labelname":"其他"}]
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
         * labelid : 1
         * labelname : 发热
         */

        private String labelid;
        private String labelname;

        public String getLabelid() {
            return labelid;
        }

        public void setLabelid(String labelid) {
            this.labelid = labelid;
        }

        public String getLabelname() {
            return labelname;
        }

        public void setLabelname(String labelname) {
            this.labelname = labelname;
        }
    }
}
