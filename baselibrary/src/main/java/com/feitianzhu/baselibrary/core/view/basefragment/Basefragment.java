package com.feitianzhu.baselibrary.core.view.basefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.feitianzhu.baselibrary.R;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.impl.LoadingPopupView;

import me.yokeyword.fragmentation.SupportFragment;

//获取数据三种方式
//1.通过Autowired注解表明key   &  需要在onCreate中调用ARouter.getInstance().inject(this);配合使用
//    @Autowired(name = "key1")
//    public long data;
//2.通过Autowired注解 & 将key1作为属性的名称   &  需要在onCreate中调用ARouter.getInstance().inject(this);配合使用
//    @Autowired()
//    public long key1;
//3.通过Bundle获取
//    getIntent().getExtras().getLong("key1")
public abstract class Basefragment extends SupportFragment {
    LoadingPopupView loadingPopup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
        loadData();
    }

    public abstract void initView();

    public abstract void loadData();

    public abstract void initListener();

    protected void showloadDialog(String title) {
        if (loadingPopup == null) {
            loadingPopup = (LoadingPopupView) new XPopup.Builder(getContext())
                    .hasShadowBg(false)
                    .popupAnimation(PopupAnimation.NoAnimation)
                    .dismissOnBackPressed(true)
                    .dismissOnTouchOutside(true)
                    .asLoading()
                    .bindLayout(R.layout.layout_loading_view);

        }
        loadingPopup.show();

    }

    protected void goneloadDialog() {
        if (null != loadingPopup) {
            loadingPopup.delayDismissWith(600, new Runnable() {
                @Override
                public void run() {
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loadingPopup = null;
    }
}
