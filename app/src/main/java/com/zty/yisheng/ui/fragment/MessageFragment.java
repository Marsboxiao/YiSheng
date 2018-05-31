package com.zty.yisheng.ui.fragment;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseFragment;
import com.zty.yisheng.presenter.contract.MessageContract;
import com.zty.yisheng.presenter.presenter.MessagePresenter;

/**
 * Created by 92915 on 2018/4/16.
 */

public class MessageFragment extends BaseFragment<MessagePresenter> implements MessageContract.View {

    @Override
    public void showError(String msg) {

    }

    public static MessageFragment getInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initBaidu() {

    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(MessageFragment.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_message;
    }
}
