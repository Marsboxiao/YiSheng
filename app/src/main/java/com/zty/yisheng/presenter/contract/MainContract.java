package com.zty.yisheng.presenter.contract;

import com.zty.yisheng.common.base.BaseContract;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.SysSettingBean;
import com.zty.yisheng.model.bean.UpdataJGBean;

/**
 * Created by 92915 on 2018/4/13.
 */

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void startJGid(UpdataJGBean updataJGBean);
        void againJGid(int code);
        void startTime(SysSettingBean sysSettingBean);
        void againTime(int code);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getgetUpdataJGAttribute(String docid, String jgid);
        void getTimeAttribute();
    }

}
