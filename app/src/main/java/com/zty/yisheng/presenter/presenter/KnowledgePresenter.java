package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.BannerImageBean;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.UpdataJGBean;
import com.zty.yisheng.presenter.contract.BannerImageContract;
import com.zty.yisheng.presenter.contract.KnowledgeContract;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class KnowledgePresenter extends RxPresenter<KnowledgeContract.View> implements KnowledgeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public KnowledgePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getClassAttribute() {
        Disposable disposable = mRetrofitHelper.getClassApproveInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<ClassBean>() {
                            @Override
                            public void accept(ClassBean classBean) throws Exception {
                                //成功获取数据
                                if (mView != null && classBean != null) {
                                    int code=Integer.valueOf(classBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startClass(classBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againClass(code);
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
    public void getLabelAttribute() {
        Disposable disposable = mRetrofitHelper.getLabelApproveInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<LabelBean>() {
                            @Override
                            public void accept(LabelBean labelBean) throws Exception {
                                //成功获取数据
                                if (mView != null && labelBean != null) {
                                    int code=Integer.valueOf(labelBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startLabel(labelBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againLabel(code);
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
