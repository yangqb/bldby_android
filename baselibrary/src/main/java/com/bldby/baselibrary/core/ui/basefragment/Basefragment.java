package com.bldby.baselibrary.core.ui.basefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bldby.baselibrary.R;
import com.bldby.baselibrary.constants.RouteConstants;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.impl.LoadingPopupView;

import java.util.LinkedList;
import java.util.List;

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
public abstract class Basefragment extends SupportFragment implements NavigationCallback {
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
    public void onSupportVisible() {
        super.onSupportVisible();
        performTasksOnResumed();
    }

    private List<Runnable> taskListOnResumed = null;

    /**
     * 前台执行,即,fragment可见的时候执行
     */
    public final void postOnResumed(Runnable task) {
        if (task != null) {
            if (isSupportVisible()) {
                task.run();
            } else {
                if (taskListOnResumed == null) {
                    taskListOnResumed = new LinkedList<Runnable>();
                }
                taskListOnResumed.add(task);
            }
        }
    }

    private void performTasksOnResumed() {
        if (taskListOnResumed != null) {
            for (Runnable task : taskListOnResumed) {
                task.run();
            }
            taskListOnResumed = null;
        }
    }

    public void onBcak(View view) {
        pop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loadingPopup = null;
    }

    public void startTo(String url) {
        // 使用两个参数的navigation方法，可以获取单次跳转的结果
        ARouter.getInstance()
                .build(url)
                .navigation(getActivity(), this);

    }

    /*
     * 需要上个页面回调返回参数的
     * */
    public void startTo(String url, int requestCode) {
        ARouter.getInstance()
                .build(url)
                .navigation(getActivity(), requestCode, this);
    }

    /**
     * 设置传递参数
     *
     * @param url
     * @param bundle
     */
    public void startTo(String url, Bundle bundle) {
        Postcard build = ARouter.getInstance().build(url);
        build.with(bundle);
        build.navigation(getActivity(), this);
    }

    /**
     * 退出所有activity到mainActivity
     */
    public void popToRoot() {
        ARouter.getInstance()
                .build(RouteConstants.APPMAIN)
                .navigation(getActivity(), this);
    }


    /**
     * 自定义参数 需要手动调用 .navigation(this, this)
     * ARouter.getInstance().build("/test/1")
     * .withLong("key1", 666L)
     * .withString("key3", "888")
     * .withSerializable("key4", new Test("Jack", "Rose"))
     * .withObject("map", map)
     * .navigation();
     * 也可直接获取fragment
     * 最后可以跳转时一定要使用navigation(this, this)才可以获取回调
     *
     * @param url
     * @return
     */
    public Postcard startWith(String url) {
        Postcard build = ARouter.getInstance().build(url);
        return build;
    }

//    又返回参数的跳转
//    startActivityForResult();
//    navigation(Context mContext, int requestCode, NavigationCallback callback)

    public Basefragment getFragment(String url) {
        // 获取Fragment
        Basefragment fragment = (Basefragment) ARouter
                .getInstance()
                .build(url)
                .navigation(getActivity(), this);
        return fragment;
    }

    /**
     * 设置传递参数
     *
     * @param url
     * @param bundle
     */
    public Basefragment getFragmentWithBundle(String url, Bundle bundle) {
        Basefragment fragment = (Basefragment) ARouter
                .getInstance()
                .build(url)
                .with(bundle)
                .navigation(getActivity(), this);
        return fragment;
    }


    //跳转成功返回
    @Override
    public void onFound(Postcard postcard) {

    }

    //找不到的时候回调
    @Override
    public void onLost(Postcard postcard) {
        ToastUtil.show(getString(R.string.nofoundView));
    }

    //跳转结束
    @Override
    public void onArrival(Postcard postcard) {

    }

    //被拦截
    @Override
    public void onInterrupt(Postcard postcard) {
        ToastUtil.show("被拦截");
    }
}
