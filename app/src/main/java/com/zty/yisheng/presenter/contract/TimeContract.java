package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.SDocTimeBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface TimeContract {

    interface View extends BaseContract.BaseView {
        void startDocTime(DocTimeBean docTimeBean);
        void againDocTime(int code);
        void startSDocTime(SDocTimeBean sDocTimeBean);
        void againSDocTime(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>  {
        void getDocTimeAttribute(String docid);
        void getSDocTimeAttribute(String docid, String begintime, String endtime);
    }

}
