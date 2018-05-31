package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.SDocDataBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface MySetContract {

    interface View extends BaseContract.BaseView {
        void startDocData(DocDataBean docDataBean);
        void againDocData(int code);
        void startSDocData(SDocDataBean sDocDataBean);
        void againSDocData(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View>  {
        void getDocDataAttribute(String docid);
        void getSDocDataAttribute(String docid,String docname,String sex,String birthday,
                                  String mobile,String professional, String hospital,String office);
    }

}
