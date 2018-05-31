package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.SickMessageBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface SickContract {

    interface View extends BaseContract.BaseView {
        void startSick(SickMessageBean sickMessageBean);
        void againSick(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>  {
        void getSickAttribute(String sickid);
    }

}
