package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.CodeBean;
import com.zty.yisheng.model.bean.ForgetBean;
import com.zty.yisheng.model.bean.LoginBean;

/**
 * Created by 92915 on 2018/6/16.
 */

public interface ForgetContract {

    interface View extends BaseContract.BaseView {
        void startForget(ForgetBean forgetBean);
        void againForget(int code);
        void startCode(CodeBean codeBean);
        void againCode(int code);
        void startLogin(LoginBean loginBean);
        void againLogin(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getCodeAttribute(String docphone);
        void getForgetAttribute(String docphone,String password,String code);
        void getLoginAttribute(String username, String password);
    }

}
