package com.zty.yisheng.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 92915 on 2018/4/11.
 */

public class SickMessageBean {

    /**
     * code : 1
     * data : {"name":"~zhdk","gender":"","birthday":"","height":"","weight":"","bloodtype":"","medicalhistory":"","surgeryhistory":"","built-in":"","chronic":"","allergens":""}
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
         * name : ~zhdk
         * gender :
         * birthday :
         * height :
         * weight :
         * bloodtype :
         * medicalhistory :
         * surgeryhistory :
         * built-in :
         * chronic :
         * allergens :
         */

        private String name;
        private String gender;
        private String birthday;
        private String height;
        private String weight;
        private String bloodtype;
        private String medicalhistory;
        private String surgeryhistory;
        @SerializedName("built-in")
        private String builtin;
        private String chronic;
        private String allergens;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getBloodtype() {
            return bloodtype;
        }

        public void setBloodtype(String bloodtype) {
            this.bloodtype = bloodtype;
        }

        public String getMedicalhistory() {
            return medicalhistory;
        }

        public void setMedicalhistory(String medicalhistory) {
            this.medicalhistory = medicalhistory;
        }

        public String getSurgeryhistory() {
            return surgeryhistory;
        }

        public void setSurgeryhistory(String surgeryhistory) {
            this.surgeryhistory = surgeryhistory;
        }

        public String getBuiltin() {
            return builtin;
        }

        public void setBuiltin(String builtin) {
            this.builtin = builtin;
        }

        public String getChronic() {
            return chronic;
        }

        public void setChronic(String chronic) {
            this.chronic = chronic;
        }

        public String getAllergens() {
            return allergens;
        }

        public void setAllergens(String allergens) {
            this.allergens = allergens;
        }
    }
}
