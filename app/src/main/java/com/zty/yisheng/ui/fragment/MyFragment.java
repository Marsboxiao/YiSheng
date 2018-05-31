package com.zty.yisheng.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseFragment;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.model.bean.DocDataBean;
import com.zty.yisheng.model.bean.DocImageBean;
import com.zty.yisheng.model.bean.DocSpecialityBean;
import com.zty.yisheng.model.bean.DocTimeBean;
import com.zty.yisheng.presenter.contract.MyContract;
import com.zty.yisheng.presenter.presenter.MyPresenter;
import com.zty.yisheng.ui.activity.LoginActivity;
import com.zty.yisheng.ui.activity.MySetActivity;
import com.zty.yisheng.ui.activity.TimeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 92915 on 2018/4/16.
 */

public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {

    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.my_image)
    ImageView myImage;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_id)
    TextView myId;
    @BindView(R.id.my_jineng)
    TextView myJineng;
    @BindView(R.id.my_techang)
    TextView myTechang;
    @BindView(R.id.my_time)
    TextView myTime;
    @BindView(R.id.my_out)
    Button myOut;
    @BindView(R.id.my_setting)
    LinearLayout linearLayout;

    private String docid;

    @Override
    public void showError(String msg) {

    }

    public static MyFragment getInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        commonText.setText("我的");
    }

    @Override
    public void onResume() {
        super.onResume();
        docid = SharePreUtil.getPrefString(YiShengApplication.getInstance(), "docid", "1");
        mPersenter.getDocImageAttribute(docid);
        mPersenter.getDocDataAttribute(docid);
        mPersenter.getDocSpecialityAttribute(docid);
        mPersenter.getDocTimeAttribute(docid);
    }

    @Override
    protected void initBaidu() {

    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(MyFragment.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @OnClick(R.id.my_time)
    public void setMyTime(){
        startActivity(new Intent(getContext(), TimeActivity.class));
    }

    @OnClick(R.id.my_setting)
    public void setMySetting(){
        startActivity(new Intent(getContext(), MySetActivity.class));
    }

    @OnClick(R.id.my_out)
    public void setMyOut(){
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "username", "null");
        SharePreUtil.setPrefString(YiShengApplication.getInstance(), "password", "null");
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void startDocImage(DocImageBean docImageBean) {
        Glide.with(getContext()).load(docImageBean.getData()).into(myImage);
    }

    @Override
    public void againDocImage(int code) {

    }

    @Override
    public void startDocData(DocDataBean docDataBean) {
        myName.setText(docDataBean.getData().getDoctor_name());
        myId.setText("账号："+ SharePreUtil.getPrefString(YiShengApplication.getInstance(),"username","111"));
    }

    @Override
    public void againDocData(int code) {

    }

    @Override
    public void startDocSpeciality(DocSpecialityBean docSpecialityBean) {
        myJineng.setText(docSpecialityBean.getData().getSkills());
        myTechang.setText(docSpecialityBean.getData().getSpecialties());
    }

    @Override
    public void againDocSpeciality(int code) {

    }

    @Override
    public void startDocTime(DocTimeBean docTimeBean) {
        myTime.setText(docTimeBean.getData().getBegintime()+"-"+docTimeBean.getData().getEndtime());
    }

    @Override
    public void againDocTime(int code) {

    }

}
