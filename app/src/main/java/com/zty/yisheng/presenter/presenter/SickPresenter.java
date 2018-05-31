package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.model.bean.SickMessageBean;
import com.zty.yisheng.presenter.contract.MyContract;
import com.zty.yisheng.presenter.contract.SickContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class SickPresenter extends RxPresenter<SickContract.View> implements SickContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public SickPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getSickAttribute(String sickid) {
        Disposable disposable = mRetrofitHelper.getSickMessageApproveInfo(sickid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SickMessageBean>() {
                            @Override
                            public void accept(SickMessageBean sickMessageBean) throws Exception {
                                //成功获取数据
                                if (mView != null && sickMessageBean != null) {
                                    int code=Integer.valueOf(sickMessageBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startSick(sickMessageBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againSick(code);
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
