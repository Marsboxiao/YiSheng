package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.SysSettingBean;
import com.zty.yisheng.model.bean.UpdataJGBean;
import com.zty.yisheng.presenter.contract.KnowledgeContract;
import com.zty.yisheng.presenter.contract.MainContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 92915 on 2018/3/29.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getgetUpdataJGAttribute(String docid, String jgid) {
        Disposable disposable = mRetrofitHelper.getUpdataJGApproveInfo(docid,jgid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<UpdataJGBean>() {
                            @Override
                            public void accept(UpdataJGBean updataJGBean) throws Exception {
                                //成功获取数据
                                if (mView != null && updataJGBean != null) {
                                    int code=Integer.valueOf(updataJGBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startJGid(updataJGBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againJGid(code);
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
    public void getTimeAttribute() {
        Disposable disposable = mRetrofitHelper.getTimeApproveInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<SysSettingBean>() {
                            @Override
                            public void accept(SysSettingBean sysSettingBean) throws Exception {
                                //成功获取数据
                                if (mView != null && sysSettingBean != null) {
                                    int code=Integer.valueOf(sysSettingBean.getCode());
                                    if (code==1) {
                                        try {
                                            mView.startTime(sysSettingBean);
                                        } catch (Exception e) {

                                        }
                                    } else {
                                        mView.againTime(code);
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
