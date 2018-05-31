package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.BannerImageBean;
import com.zty.yisheng.presenter.contract.BannerImageContract;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class BannerImagePresenter extends RxPresenter<BannerImageContract.View> implements BannerImageContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public BannerImagePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getBannerAttribute() {
//        .doOnSubscribe(new Consumer<Disposable>() {
//            @Override
//            public void accept(Disposable disposable) throws Exception {
//                //成功了怎么处理
//            }
//        })
        Disposable disposable = mRetrofitHelper.getBannerImageApproveInfo()
                .subscribe(
                        new Consumer<BannerImageBean>() {
                            @Override
                            public void accept(BannerImageBean bannerImageBean) throws Exception {
                                //成功获取数据
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
