package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.BannerImageBean;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.SysSettingBean;
import com.zty.yisheng.model.bean.UpdataJGBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface KnowledgeContract {

    interface View extends BaseContract.BaseView {
        void startClass(ClassBean classBean);
        void againClass(int code);
        void startLabel(LabelBean labelBean);
        void againLabel(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getClassAttribute();
        void getLabelAttribute();

    }

}
