package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.SDocTimeBean;
import com.zty.yisheng.presenter.contract.MyContract;
import com.zty.yisheng.presenter.contract.TimeContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class TimePresenter extends RxPresenter<TimeContract.View> implements TimeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public TimePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
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

    @Override
    public void getSDocTimeAttribute(String docid, String begintime, String endtime) {
        Disposable disposable = mRetrofitHelper.getSDocTimeApproveInfo(docid,begintime,endtime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SDocTimeBean>() {
                            @Override
                            public void accept(SDocTimeBean sDocTimeBean) throws Exception {
                                //成功获取数据
                                if (mView != null && sDocTimeBean != null) {
                                    int code=Integer.valueOf(sDocTimeBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startSDocTime(sDocTimeBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againSDocTime(code);
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
