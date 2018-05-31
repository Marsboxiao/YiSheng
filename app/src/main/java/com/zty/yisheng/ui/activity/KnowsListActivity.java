package com.zty.yisheng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseActivity;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.ClassListBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.LabelListBean;
import com.zty.yisheng.presenter.contract.ClassLabelContract;
import com.zty.yisheng.presenter.presenter.ClassLabelPresenter;
import com.zty.yisheng.ui.adapter.ClasssAdapter;
import com.zty.yisheng.ui.adapter.LabelsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 92915 on 2018/4/19.
 */

public class KnowsListActivity extends BaseActivity<ClassLabelPresenter> implements ClassLabelContract.View {


    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.knows_recyclerview)
    RecyclerView mRecyclerView;
    private ClasssAdapter classsAdapter;
    private LabelsAdapter labelsAdapter;

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void initView() {
        setSupportActionBar(commonToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_iv_back);
        commonText.setText("知识列表");
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String id = intent.getStringExtra("id");
        if (type.equals("class")) {
            mPersenter.getClassListAttribute(id);
        }
        if (type.equals("label")) {
            mPersenter.getLabelListAttribute(id);
        }
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
        return R.layout.activity_knows;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(KnowsListActivity.this);
    }

    @Override
    public void startClassList(ClassListBean classListBean) {
        final List<ClassListBean.DataBean> classsList = new ArrayList<>();
        for (ClassListBean.DataBean bean : classListBean.getData()) {
            classsList.add(bean);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        classsAdapter = new ClasssAdapter(this, classsList);
        mRecyclerView.setAdapter(classsAdapter);
        classsAdapter.setOnItemClickListener(new ClasssAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(KnowsListActivity.this,KnowcContentActivity.class);
                intent.putExtra("id", classsList.get(position).getKnowledgeid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void againClassList(int code) {
        ToastUtil.show(KnowsListActivity.this, "获取不到数据", Toast.LENGTH_SHORT);
    }

    @Override
    public void startLabelList(LabelListBean labelListBean) {
        final List<LabelListBean.DataBean> labelsList = new ArrayList<>();
        for (LabelListBean.DataBean bean : labelListBean.getData()) {
            labelsList.add(bean);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        labelsAdapter = new LabelsAdapter(this, labelsList);
        mRecyclerView.setAdapter(labelsAdapter);
        labelsAdapter.setOnItemClickListener(new LabelsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(KnowsListActivity.this,KnowcContentActivity.class);
                intent.putExtra("id", labelsList.get(position).getKnowledgeid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void againLabelList(int code) {
        ToastUtil.show(KnowsListActivity.this, "获取不到数据", Toast.LENGTH_SHORT);
    }

}
