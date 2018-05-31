package com.zty.yisheng.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.KnowledgeBean;
import com.zty.yisheng.presenter.contract.KnowContentContract;
import com.zty.yisheng.presenter.presenter.KnowContentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 92915 on 2018/4/19.
 */

public class KnowcContentActivity extends BaseActivity<KnowContentPresenter> implements KnowContentContract.View {

    @BindView(R.id.knowc_title)
    TextView knowcTitle;
    @BindView(R.id.knowc_content)
    TextView knowcContent;
    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initView() {
        setSupportActionBar(commonToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_iv_back);
        commonText.setText("详细知识");
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        mPersenter.getKnowledgeAttribute(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //android.R.id.home是Android内置home按钮的id
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_knowc;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(KnowcContentActivity.this);
    }

    @Override
    public void startKnowledge(KnowledgeBean classBean) {
        knowcTitle.setText(classBean.getData().getKnowledgename());
        SpannableStringBuilder span = new SpannableStringBuilder("缩进" + classBean.getData().getKnowledgecontent());
        span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        knowcContent.setText(span);
    }

    @Override
    public void againKnowledge(int code) {
        ToastUtil.show(KnowcContentActivity.this, "获取不到数据", Toast.LENGTH_SHORT);
    }

}
