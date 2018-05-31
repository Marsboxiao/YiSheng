package com.zty.yisheng.presenter.presenter;

import com.zty.yisheng.common.base.RxPresenter;
import com.zty.yisheng.model.api.RetrofitHelper;
import com.zty.yisheng.presenter.contract.KnowledgeContract;
import com.zty.yisheng.presenter.contract.MessageContract;

import javax.inject.Inject;

/**
 * Created by 92915 on 2018/3/29.
 */

public class MessagePresenter extends RxPresenter<MessageContract.View> implements MessageContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MessagePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

}
