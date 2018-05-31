package com.zty.yisheng.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kevin.wraprecyclerview.WrapRecyclerView;
import com.zty.yisheng.R;
import com.zty.yisheng.common.base.BaseFragment;
import com.zty.yisheng.common.support.YiShengApplication;
import com.zty.yisheng.common.utils.SharePreUtil;
import com.zty.yisheng.common.utils.ToastUtil;
import com.zty.yisheng.model.bean.ClassBean;
import com.zty.yisheng.model.bean.LabelBean;
import com.zty.yisheng.model.bean.SysSettingBean;
import com.zty.yisheng.model.bean.UpdataJGBean;
import com.zty.yisheng.presenter.contract.KnowledgeContract;
import com.zty.yisheng.presenter.presenter.KnowledgePresenter;
import com.zty.yisheng.ui.activity.KnowsListActivity;
import com.zty.yisheng.ui.adapter.KnowledgeAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by 92915 on 2018/4/16.
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

    @BindView(R.id.common_text)
    TextView commonText;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.knowledge_recyclerview)
    WrapRecyclerView mRecyclerview;
    private KnowledgeAdapter mAdapter;
    private List<ClassBean.DataBean> classList;
    private List<ClassBean.DataBean> sclassList;

    @Override
    public void showError(String msg) {

    }

    public static KnowledgeFragment getInstance() {
        KnowledgeFragment fragment = new KnowledgeFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        commonText.setText("患者常识");
        mAdapter=new KnowledgeAdapter(getContext());
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(mAdapter);
        mPersenter.getLabelAttribute();
        mPersenter.getClassAttribute();
        mAdapter.setOnItemClickListener(new KnowledgeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                ToastUtil.show(getContext(), "得到的数据"+"+"+classList.get(position).getClassid(), Toast.LENGTH_SHORT);
                Intent intent = new Intent(getContext(), KnowsListActivity.class);
                intent.putExtra("type","class");
                intent.putExtra("id", classList.get(position).getClassid());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initBaidu() {

    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(KnowledgeFragment.this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void startClass(ClassBean classBean) {
        classList = new LinkedList<>();
        for (ClassBean.DataBean bean : classBean.getData()) {
            classList.add(bean);
        }
        sclassList = new LinkedList<>();
        for (int i=0;i<9;i++) {
            sclassList.add(classBean.getData().get(i));
        }
        mAdapter.setItemLists(sclassList);
    }

    @Override
    public void againClass(int code) {
        ToastUtil.show(getContext(), "没有获取到数据", Toast.LENGTH_SHORT);
    }

    @Override
    public void startLabel(LabelBean labelBean) {
        List<LabelBean.DataBean> labelList = new ArrayList<>();
        for (LabelBean.DataBean bean : labelBean.getData()) {
            labelList.add(bean);
        }
        addFooterView(labelList);
    }

    @Override
    public void againLabel(int code) {
        ToastUtil.show(getContext(), "没有获取到数据", Toast.LENGTH_SHORT);
    }

    /**
     * 添加尾部
     */
    private void addFooterView(final List<LabelBean.DataBean> labelList) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_footer, null);
        final TextView footerText = layout.findViewById(R.id.footer_text);
        ImageView footerImage = layout.findViewById(R.id.footer_image);
        TagGroup footerTaggroup = layout.findViewById(R.id.footer_taggroup);
        mRecyclerview.addFooterView(layout);
        List<String> stringList = new ArrayList<>();
        for (LabelBean.DataBean list : labelList) {
            stringList.add(list.getLabelname());
        }
        footerTaggroup.setTags(stringList);
        footerTaggroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
//                ToastUtil.show(getContext(),"得到的数据"+"+"+getTagId(tag,labelList)+"+"+tag,Toast.LENGTH_SHORT);
                Intent intent = new Intent(getContext(), KnowsListActivity.class);
                intent.putExtra("type","label");
                intent.putExtra("id", getTagId(tag, labelList));
                startActivity(intent);
            }
        });
        footerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharePreUtil.getPrefBoolean(YiShengApplication.getInstance(), "klstate", true)) {
                    mAdapter.setItemLists(classList);
                    SharePreUtil.setPrefBoolean(YiShengApplication.getInstance(),"klstate",false);
                    footerText.setText(R.string.know_re_off);
                } else {
                    mAdapter.setItemLists(sclassList);
                    SharePreUtil.setPrefBoolean(YiShengApplication.getInstance(),"klstate",true);
                    footerText.setText(R.string.know_re_on);
                }
            }
        });
    }

    private String getTagId(String tag,List<LabelBean.DataBean> labelList){
        String tagid = "";
        for (LabelBean.DataBean list : labelList) {
            if (tag.equals(list.getLabelname())) {
                tagid = list.getLabelid();
            }
        }
        return tagid;
    }

}
