package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.ClassListBean;
import com.zty.yisheng.model.bean.KnowledgeBean;
import com.zty.yisheng.presenter.contract.KnowContentContract;
import com.zty.yisheng.presenter.contract.KnowledgeContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/4/19.
 */

public class KnowContentPresenter extends RxPresenter<KnowContentContract.View> implements KnowContentContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public KnowContentPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getKnowledgeAttribute(String knowledgeid) {
        Disposable disposable = mRetrofitHelper.getKnowledgeApproveInfo(knowledgeid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<KnowledgeBean>() {
                            @Override
                            public void accept(KnowledgeBean knowledgeBean) throws Exception {
                                //成功获取数据
                                if (mView != null && knowledgeBean != null) {
                                    int code=Integer.valueOf(knowledgeBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startKnowledge(knowledgeBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againKnowledge(code);
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
