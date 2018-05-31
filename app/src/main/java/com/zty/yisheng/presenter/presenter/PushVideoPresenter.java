package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.LoginBean;
import com.zty.yisheng.model.bean.SDocIsanswerBean;
import com.zty.yisheng.model.bean.SDocOpenAppBean;
import com.zty.yisheng.presenter.contract.MessageContract;
import com.zty.yisheng.presenter.contract.PushVideoContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class PushVideoPresenter extends RxPresenter<PushVideoContract.View> implements PushVideoContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public PushVideoPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getOpenAppAttribute(String docid) {
        Disposable disposable = mRetrofitHelper.getSDocOpenAppApproveInfo(docid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SDocOpenAppBean>() {
                            @Override
                            public void accept(SDocOpenAppBean sDocOpenAppBean) throws Exception {
                                //成功获取数据
                                if (mView != null && sDocOpenAppBean != null) {
                                    int code=Integer.valueOf(sDocOpenAppBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startOpenApp(sDocOpenAppBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againOpenApp(code);
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
    public void getStateAttribute(String docid, String isanswer) {
        Disposable disposable = mRetrofitHelper.getSDocIsanswerApproveInfo(docid,isanswer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SDocIsanswerBean>() {
                            @Override
                            public void accept(SDocIsanswerBean sDocIsanswerBean) throws Exception {
                                //成功获取数据
                                if (mView != null && sDocIsanswerBean != null) {
                                    int code=Integer.valueOf(sDocIsanswerBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startState(sDocIsanswerBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againState(code);
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
