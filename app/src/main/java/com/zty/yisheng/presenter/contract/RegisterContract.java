package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.model.bean.RegisterBean;

/**
 * Created by 92915 on 2018/6/16.
 */

public interface RegisterContract {

    interface View extends BaseContract.BaseView {
        void startRegister(RegisterBean registerBean);
        void againRegister(int code);
        void startLogin(LoginBean loginBean);
        void againLogin(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getRegisterAttribute(String docphone,String password);
        void getLoginAttribute(String username, String password);
    }

}
