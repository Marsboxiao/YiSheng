package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.ClassListBean;
import com.zty.yisheng.model.bean.LabelListBean;
import com.zty.yisheng.model.bean.UpdataJGBean;
import com.zty.yisheng.presenter.contract.ClassLabelContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/4/19.
 */

public class ClassLabelPresenter extends RxPresenter<ClassLabelContract.View> implements ClassLabelContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public ClassLabelPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getClassListAttribute(String classid) {
        Disposable disposable = mRetrofitHelper.getClassListApproveInfo(classid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<ClassListBean>() {
                            @Override
                            public void accept(ClassListBean classListBean) throws Exception {
                                //成功获取数据
                                if (mView != null && classListBean != null) {
                                    int code=Integer.valueOf(classListBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startClassList(classListBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againClassList(code);
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
    public void getLabelListAttribute(String labelid) {
        Disposable disposable = mRetrofitHelper.getLabelListApproveInfo(labelid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<LabelListBean>() {
                            @Override
                            public void accept(LabelListBean labelListBean) throws Exception {
                                //成功获取数据
                                if (mView != null && labelListBean != null) {
                                    int code=Integer.valueOf(labelListBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startLabelList(labelListBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againLabelList(code);
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
