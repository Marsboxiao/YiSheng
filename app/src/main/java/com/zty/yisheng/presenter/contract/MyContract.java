package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface MyContract {

    interface View extends BaseContract.BaseView {
        void startDocImage(DocImageBean docImageBean);
        void againDocImage(int code);
        void startDocData(DocDataBean docDataBean);
        void againDocData(int code);
        void startDocSpeciality(DocSpecialityBean docSpecialityBean);
        void againDocSpeciality(int code);
        void startDocTime(DocTimeBean docTimeBean);
        void againDocTime(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>  {
        void getDocImageAttribute(String docid);
        void getDocDataAttribute(String docid);
        void getDocSpecialityAttribute(String docid);
        void getDocTimeAttribute(String docid);
    }

}
