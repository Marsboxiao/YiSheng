package com.zty.yisheng.model.bean;

/**
 * Created by 92915 on 2018/4/11.
 */

public class DocSpecialityBean {

    /**
     * code : 1
     * data : {"skills":"lll","specialties":"jjj"}
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
         * skills : lll
         * specialties : jjj
         */

        private String skills;
        private String specialties;

        public String getSkills() {
            return skills;
        }

        public void setSkills(String skills) {
            this.skills = skills;
        }

        public String getSpecialties() {
            return specialties;
        }

        public void setSpecialties(String specialties) {
            this.specialties = specialties;
        }
    }

}
