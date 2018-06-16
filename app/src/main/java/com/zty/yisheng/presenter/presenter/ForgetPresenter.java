package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.CodeBean;
import com.zty.yisheng.model.bean.ForgetBean;
import com.zty.yisheng.model.bean.KnowledgeBean;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.presenter.contract.ForgetContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/6/16.
 */

public class ForgetPresenter extends RxPresenter<ForgetContract.View> implements ForgetContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ForgetPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }


    @Override
    public void getCodeAttribute(String docphone) {
        Disposable disposable = mRetrofitHelper.getCodeApproveInfo(docphone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<CodeBean>() {
                            @Override
                            public void accept(CodeBean codeBean) throws Exception {
                                //成功获取数据
                                if (mView != null && codeBean != null) {
                                    int code=Integer.valueOf(codeBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startCode(codeBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againCode(code);
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
    public void getForgetAttribute(String docphone, String password, String code) {
        Disposable disposable = mRetrofitHelper.getForgetApproveInfo(docphone,password,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<ForgetBean>() {
                            @Override
                            public void accept(ForgetBean forgetBean) throws Exception {
                                //成功获取数据
                                if (mView != null && forgetBean != null) {
                                    int code=Integer.valueOf(forgetBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startForget(forgetBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againForget(code);
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
