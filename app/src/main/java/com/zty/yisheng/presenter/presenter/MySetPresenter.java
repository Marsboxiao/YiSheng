package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.SDocDataBean;
import com.zty.yisheng.presenter.contract.MyContract;
import com.zty.yisheng.presenter.contract.MySetContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class MySetPresenter extends RxPresenter<MySetContract.View> implements MySetContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MySetPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
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
    public void getSDocDataAttribute(String docid, String docname, String sex, String birthday, String mobile, String professional, String hospital, String office) {
        Disposable disposable = mRetrofitHelper.getSDocDataApproveInfo(docid, docname, sex, birthday, mobile, professional, hospital, office)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SDocDataBean>() {
                            @Override
                            public void accept(SDocDataBean sDocDataBean) throws Exception {
                                //成功获取数据
                                if (mView != null && sDocDataBean != null) {
                                    int code=Integer.valueOf(sDocDataBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startSDocData(sDocDataBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againSDocData(code);
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
