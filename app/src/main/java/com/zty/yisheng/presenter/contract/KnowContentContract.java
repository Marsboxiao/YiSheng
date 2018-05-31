package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.KnowledgeBean;
import com.zty.yisheng.model.bean.LabelBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface KnowContentContract {

    interface View extends BaseContract.BaseView {
        void startKnowledge(KnowledgeBean classBean);
        void againKnowledge(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getKnowledgeAttribute(String knowledgeid);
    }

}
