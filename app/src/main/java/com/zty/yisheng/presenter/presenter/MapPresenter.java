package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.UpdataXYBean;
import com.zty.yisheng.presenter.contract.KnowledgeContract;
import com.zty.yisheng.presenter.contract.MapContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class MapPresenter extends RxPresenter<MapContract.View> implements MapContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MapPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getUpdataXYAttribute(String docid, String x, String y) {
        Disposable disposable = mRetrofitHelper.getUpdataXYApproveInfo(docid,x,y)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<UpdataXYBean>() {
                            @Override
                            public void accept(UpdataXYBean updataXYBean) throws Exception {
                                //成功获取数据
                                if (mView != null && updataXYBean != null) {
                                    int code=Integer.valueOf(updataXYBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startMap(updataXYBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againMap(code);
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
