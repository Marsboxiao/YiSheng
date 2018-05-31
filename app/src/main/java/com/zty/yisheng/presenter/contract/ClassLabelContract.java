package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.ClassListBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.LabelListBean;
import com.zty.yisheng.model.bean.UpdataJGBean;

/**
 * Created by 92915 on 2018/4/19.
 */

public interface ClassLabelContract {

    interface View extends BaseContract.BaseView {
        void startClassList(ClassListBean classListBean);
        void againClassList(int code);
        void startLabelList(LabelListBean labelListBean);
        void againLabelList(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getClassListAttribute(String classid);
        void getLabelListAttribute(String labelid);
    }

}
