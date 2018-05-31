package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.presenter.contract.LoginContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/4/22.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public LoginPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getLoginAttribute(String username, String password) {
        Disposable disposable = mRetrofitHelper.getLoginApproveInfo(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<LoginBean>() {
                            @Override
                            public void accept(LoginBean loginBean) throws Exception {
                                //成功获取数据
                                if (mView != null && loginBean != null) {
                                    int code=Integer.valueOf(loginBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startLogin(loginBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againLogin(code);
                                    }
                                }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                //未成功获取数据
                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                //已经执行
                            }
                        });
        addSubscrebe(disposable);
    }

}
