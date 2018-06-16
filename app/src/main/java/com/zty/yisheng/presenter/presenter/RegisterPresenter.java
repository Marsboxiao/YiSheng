package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.ForgetBean;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.model.bean.RegisterBean;
import com.zty.yisheng.presenter.contract.RegisterContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/6/16.
 */

public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public RegisterPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getRegisterAttribute(String docphone, String password) {
        Disposable disposable = mRetrofitHelper.getRegisterApproveInfo(docphone,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<RegisterBean>() {
                            @Override
                            public void accept(RegisterBean registerBean) throws Exception {
                                //成功获取数据
                                if (mView != null && registerBean != null) {
                                    int code=Integer.valueOf(registerBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startRegister(registerBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againRegister(code);
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
