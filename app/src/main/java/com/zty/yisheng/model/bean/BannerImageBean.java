package com.zty.yisheng.model.bean;

/**
 * Created by MarsBoxiao on 2017/10/20.
 */

public class BannerImageBean {


    /**
     * banner : {"banner1":"http://www.suiuxing.com/uploadimg/LbnKXG14kK.png","banner2":"http://www.suiuxing
     * .com/uploadimg/p3STK6ZRt1.png","banner3":"http://www.suiuxing.com/uploadimg/MtaJ5nTw7W.png",
     * "banner4":"http://www.suiuxing.com/uploadimg/oxeD6XyKys.png","bannername1":"2","bannername2":"2",
     * "bannername3":"2","bannername4":"2","bannertype1":"https://www.baidu.com/","bannertype2":"https://www.baidu
     * .com/","bannertype3":"https://www.baidu.com/","bannertype4":"https://www.baidu.com/","id":1}
     * code : 1
     * desc : 获取成功
     */

    private BannerBean banner;
    private int code;
    private String desc;

    public BannerBean getBanner() {
        return banner;
    }

    public void setBanner(BannerBean banner) {
        this.banner = banner;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class BannerBean {
        /**
         * banner1 : http://www.suiuxing.com/uploadimg/LbnKXG14kK.png
         * banner2 : http://www.suiuxing.com/uploadimg/p3STK6ZRt1.png
         * banner3 : http://www.suiuxing.com/uploadimg/MtaJ5nTw7W.png
         * banner4 : http://www.suiuxing.com/uploadimg/oxeD6XyKys.png
         * bannername1 : 2
         * bannername2 : 2
         * bannername3 : 2
         * bannername4 : 2
         * bannertype1 : https://www.baidu.com/
         * bannertype2 : https://www.baidu.com/
         * bannertype3 : https://www.baidu.com/
         * bannertype4 : https://www.baidu.com/
         * id : 1
         */

        private String banner1;
        private String banner2;
        private String banner3;
        private String banner4;
        private String bannername1;
        private String bannername2;
        private String bannername3;
        private String bannername4;
        private String bannertype1;
        private String bannertype2;
        private String bannertype3;
        private String bannertype4;
        private int id;

        public String getBanner1() {
            return banner1;
        }

        public void setBanner1(String banner1) {
            this.banner1 = banner1;
        }

        public String getBanner2() {
            return banner2;
        }

        public void setBanner2(String banner2) {
            this.banner2 = banner2;
        }

        public String getBanner3() {
            return banner3;
        }

        public void setBanner3(String banner3) {
            this.banner3 = banner3;
        }

        public String getBanner4() {
            return banner4;
        }

        public void setBanner4(String banner4) {
            this.banner4 = banner4;
        }

        public String getBannername1() {
            return bannername1;
        }

        public void setBannername1(String bannername1) {
            this.bannername1 = bannername1;
        }

        public String getBannername2() {
            return bannername2;
        }

        public void setBannername2(String bannername2) {
            this.bannername2 = bannername2;
        }

        public String getBannername3() {
            return bannername3;
        }

        public void setBannername3(String bannername3) {
            this.bannername3 = bannername3;
        }

        public String getBannername4() {
            return bannername4;
        }

        public void setBannername4(String bannername4) {
            this.bannername4 = bannername4;
        }

        public String getBannertype1() {
            return bannertype1;
        }

        public void setBannertype1(String bannertype1) {
            this.bannertype1 = bannertype1;
        }

        public String getBannertype2() {
            return bannertype2;
        }

        public void setBannertype2(String bannertype2) {
            this.bannertype2 = bannertype2;
        }

        public String getBannertype3() {
            return bannertype3;
        }

        public void setBannertype3(String bannertype3) {
            this.bannertype3 = bannertype3;
        }

        public String getBannertype4() {
            return bannertype4;
        }

        public void setBannertype4(String bannertype4) {
            this.bannertype4 = bannertype4;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
