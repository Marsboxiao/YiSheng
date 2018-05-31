package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.BannerImageBean;

/**
 * Created by 92915 on 2018/3/29.
 */

public interface BannerImageContract {

    interface View extends BaseContract.BaseView {
        void startBanner(BannerImageBean bannerImageBean);
        void againBanner(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBannerAttribute();
    }

}
