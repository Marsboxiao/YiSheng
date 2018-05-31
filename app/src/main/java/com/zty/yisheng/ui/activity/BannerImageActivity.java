package com.zty.yisheng.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.model.bean.BannerImageBean;
import com.zty.yisheng.presenter.contract.BannerImageContract;
import com.zty.yisheng.presenter.presenter.BannerImagePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 92915 on 2018/3/29.
 */

public class BannerImageActivity extends BaseActivity<BannerImagePresenter> implements BannerImageContract.View {

    @BindView(R.id.bi_image)
    TextView biImage;

    @Override
    public void showError(String msg) {

    }

    @Override
    public void startBanner(BannerImageBean bannerImageBean) {
        biImage.setText(bannerImageBean.getBanner().getBanner1());
    }

    @Override
    public void againBanner(int code) {
        biImage.setText(String.valueOf(code));
    }

    @Override
    protected void initView() {
        mPersenter.getBannerAttribute();
        Log.d("miao", "走过了");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_bannerimage;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(BannerImageActivity.this);
    }

}
