package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.KnowledgeBean;
import com.zty.yisheng.presenter.contract.KnowledgeContract;
import com.zty.yisheng.presenter.contract.MyContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class MyPresenter extends RxPresenter<MyContract.View> implements MyContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MyPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getDocImageAttribute(String docid) {
        Disposable disposable = mRetrofitHelper.getDocImageApproveInfo(docid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<DocImageBean>() {
                            @Override
                            public void accept(DocImageBean docImageBean) throws Exception {
                                //成功获取数据
                                if (mView != null && docImageBean != null) {
                                    int code=Integer.valueOf(docImageBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startDocImage(docImageBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againDocImage(code);
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
    public void getDocDataAttribute(String docid) {
        Disposable disposable = mRetrofitHelper.getDocDataApproveInfo(docid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<DocDataBean>() {
                            @Override
                            public void accept(DocDataBean docDataBean) throws Exception {
                                //成功获取数据
                                if (mView != null && docDataBean != null) {
                                    int code=Integer.valueOf(docDataBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startDocData(docDataBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againDocData(code);
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
    public void getDocSpecialityAttribute(String docid) {
        Disposable disposable = mRetrofitHelper.getDocSpecialityApproveInfo(docid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<DocSpecialityBean>() {
                            @Override
                            public void accept(DocSpecialityBean docSpecialityBean) throws Exception {
                                //成功获取数据
                                if (mView != null && docSpecialityBean != null) {
                                    int code=Integer.valueOf(docSpecialityBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startDocSpeciality(docSpecialityBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againDocSpeciality(code);
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
    public void getDocTimeAttribute(String docid) {
        Disposable disposable = mRetrofitHelper.getDocTimeApproveInfo(docid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<DocTimeBean>() {
                            @Override
                            public void accept(DocTimeBean docTimeBean) throws Exception {
                                //成功获取数据
                                if (mView != null && docTimeBean != null) {
                                    int code=Integer.valueOf(docTimeBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startDocTime(docTimeBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againDocTime(code);
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
