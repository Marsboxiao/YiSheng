package com.zty.yisheng.common.component;

import android.app.Activity;

import com.zty.yisheng.common.component.YiShengComponent;
import com.zty.yisheng.common.component.module.FragmentModule;
import com.zty.yisheng.common.component.scope.FragmentScope;
import com.zty.yisheng.ui.fragment.CallFragment;
import com.zty.yisheng.ui.fragment.KnowledgeFragment;
import com.zty.yisheng.ui.fragment.MapFragment;
import com.zty.yisheng.ui.fragment.MessageFragment;
import com.zty.yisheng.ui.fragment.MyFragment;
import com.zty.yisheng.ui.fragment.VideoFragment;

import dagger.Component;

/**
 * Created by 92915 on 2018/3/26.
 */

@FragmentScope
@Component(dependencies = YiShengComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(KnowledgeFragment knowledgeFragment);

    void inject(MapFragment mapFragment);

    void inject(MessageFragment messageFragment);

    void inject(MyFragment myFragment);

    void inject(VideoFragment videoFragment);

    void inject(CallFragment callFragment);

}
