package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.model.bean.UpdataXYBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface MapContract {

    interface View extends BaseContract.BaseView {
        void startMap(UpdataXYBean updataXYBean);
        void againMap(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getUpdataXYAttribute(String docid,String x,String y);
    }

}
