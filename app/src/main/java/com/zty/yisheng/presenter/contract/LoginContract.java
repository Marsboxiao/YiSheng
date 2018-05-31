package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.model.bean.UpdataJGBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface LoginContract {

    interface View extends BaseContract.BaseView {
        void startLogin(LoginBean loginBean);
        void againLogin(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getLoginAttribute(String username, String password);
    }

}
