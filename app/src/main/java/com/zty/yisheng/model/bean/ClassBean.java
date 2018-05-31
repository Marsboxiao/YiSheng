package com.zty.yisheng.model.bean;

import java.util.List;

/**
 * Created by 92915 on 2018/4/11.
 */

public class ClassBean {

    /**
     * code : 1
     * data : [{"classid":"2","classname":"急诊科","imgname":"http://120.91tele.cn:12011/knowledge/jizhenke.png"},{"classid":"4","classname":"心内科","imgname":"http://120.91tele.cn:12011/knowledge/xinneike.png"},{"classid":"5","classname":"呼吸内科","imgname":"http://120.91tele.cn:12011/knowledge/huxineike.png"},{"classid":"6","classname":"神经内科","imgname":"http://120.91tele.cn:12011/knowledge/shenjingneike.png"},{"classid":"7","classname":"消化内科","imgname":"http://120.91tele.cn:12011/knowledge/xiaohuaneike.png"},{"classid":"8","classname":"儿科","imgname":"http://120.91tele.cn:12011/knowledge/erke.png"},{"classid":"9","classname":"妇产科","imgname":"http://120.91tele.cn:12011/knowledge/fuchanke.png"},{"classid":"10","classname":"肿瘤科","imgname":"http://120.91tele.cn:12011/knowledge/zhongliuke.png"},{"classid":"11","classname":"肾内科","imgname":"http://120.91tele.cn:12011/knowledge/shenneike.png"},{"classid":"12","classname":"皮肤科","imgname":"http://120.91tele.cn:12011/knowledge/pifuke.png"},{"classid":"13","classname":"老年科","imgname":"http://120.91tele.cn:12011/knowledge/laonianke.png"},{"classid":"14","classname":"内分泌科","imgname":"http://120.91tele.cn:12011/knowledge/neifenmike.png"},{"classid":"15","classname":"眼科","imgname":"http://120.91tele.cn:12011/knowledge/yanke.png"},{"classid":"16","classname":"耳鼻喉科","imgname":"http://120.91tele.cn:12011/knowledge/erbihouke.png"},{"classid":"17","classname":"精神心理睡眠科","imgname":"http://120.91tele.cn:12011/knowledge/shenjingxinli.png"},{"classid":"18","classname":"创伤外科","imgname":"http://120.91tele.cn:12011/knowledge/chuangshangwaike.png"},{"classid":"19","classname":"肝胆外科","imgname":"http://120.91tele.cn:12011/knowledge/gandanwaike.png"},{"classid":"20","classname":"骨科","imgname":"http://120.91tele.cn:12011/knowledge/guke.png"},{"classid":"21","classname":"神经外科","imgname":"http://120.91tele.cn:12011/knowledge/shenjingwaike.png"},{"classid":"22","classname":"胃肠外科","imgname":"http://120.91tele.cn:12011/knowledge/weichangwaike.png"},{"classid":"23","classname":"胸外科","imgname":"http://120.91tele.cn:12011/knowledge/xiongwaike.png"},{"classid":"24","classname":"心脏外科","imgname":"http://120.91tele.cn:12011/knowledge/xinzangwaike.png"},{"classid":"25","classname":"风湿免疫科","imgname":"http://120.91tele.cn:12011/knowledge/fengshimianyike.png"},{"classid":"26","classname":"重症医学科","imgname":"http://120.91tele.cn:12011/knowledge/zhongzhenyixue.png"}]
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
         * classid : 2
         * classname : 急诊科
         * imgname : http://120.91tele.cn:12011/knowledge/jizhenke.png
         */

        private String classid;
        private String classname;
        private String imgname;

        public String getClassid() {
            return classid;
        }

        public void setClassid(String classid) {
            this.classid = classid;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getImgname() {
            return imgname;
        }

        public void setImgname(String imgname) {
            this.imgname = imgname;
        }
    }
}
