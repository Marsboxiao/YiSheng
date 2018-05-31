package com.zty.yisheng.model.bean;

/**
 * Created by 92915 on 2018/4/26.
 */

public class PushBean {

    /**
     * code : 1
     * data : {"meetngno":"111","meetingpwd":"111","patientid":"111","strX":"111","strY":"111"}
     * message :
     */

    private String code;
    private DataBean data;
    private String message;

    public PushBean(String code,DataBean data,String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

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
         * meetngno : 111
         * meetingpwd : 111
         * patientid : 111
         * strX : 111
         * strY : 111
         */

        private String meetngno;
        private String meetingpwd;
        private String patientid;
        private String strX;
        private String strY;

        public String getMeetngno() {
            return meetngno;
        }

        public void setMeetngno(String meetngno) {
            this.meetngno = meetngno;
        }

        public String getMeetingpwd() {
            return meetingpwd;
        }

        public void setMeetingpwd(String meetingpwd) {
            this.meetingpwd = meetingpwd;
        }

        public String getPatientid() {
            return patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public String getStrX() {
            return strX;
        }

        public void setStrX(String strX) {
            this.strX = strX;
        }

        public String getStrY() {
            return strY;
        }

        public void setStrY(String strY) {
            this.strY = strY;
        }
    }
}
