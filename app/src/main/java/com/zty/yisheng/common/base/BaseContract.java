package com.zty.yisheng.common.base;

/**
 * Created by MarsBoxiao on 2017/4/30.
 * 连接接口基接口
 */

public interface BaseContract {

    interface BasePresenter<T extends BaseView> {
        void attachView(T view);
        void detachView();
    }

    interface BaseView{
        void showError(String msg);
    }

}
