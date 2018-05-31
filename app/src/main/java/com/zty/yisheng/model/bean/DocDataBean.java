package com.zty.yisheng.model.bean;

/**
 * Created by 92915 on 2018/4/11.
 */

public class DocDataBean {


    /**
     * code : 1
     * data : {"doctor_name":"111","gender":"男","birthday":"2018/1/1 1:01:01","mobile":"1234567890","professional":"999","hospital":"888","office":"777"}
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
         * doctor_name : 111
         * gender : 男
         * birthday : 2018/1/1 1:01:01
         * mobile : 1234567890
         * professional : 999
         * hospital : 888
         * office : 777
         */

        private String doctor_name;
        private String gender;
        private String birthday;
        private String mobile;
        private String professional;
        private String hospital;
        private String office;

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProfessional() {
            return professional;
        }

        public void setProfessional(String professional) {
            this.professional = professional;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getOffice() {
            return office;
        }

        public void setOffice(String office) {
            this.office = office;
        }
    }
}
