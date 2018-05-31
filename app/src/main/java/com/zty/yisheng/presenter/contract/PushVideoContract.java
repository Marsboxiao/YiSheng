package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.SDocIsanswerBean;
import com.zty.yisheng.model.bean.SDocOpenAppBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface PushVideoContract {

    interface View extends BaseContract.BaseView {
        void startOpenApp(SDocOpenAppBean sDocOpenAppBean);
        void againOpenApp(int code);
        void startState(SDocIsanswerBean sDocIsanswerBean);
        void againState(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getOpenAppAttribute(String docid);
        void getStateAttribute(String docid, String isanswer);
    }

}
