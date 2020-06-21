package com.bldby.baselibrary.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bldby.baselibrary.AppidManifests;
import com.bldby.baselibrary.R;
import com.bldby.baselibrary.app.util.XlogUtil;
import com.bldby.baselibrary.core.errorlog.CrashHandler;
import com.bldby.baselibrary.core.analyze.UMengAnalyze;
import com.bldby.baselibrary.core.smart.MRefreshFooter;
import com.bldby.baselibrary.core.smart.MRefreshHeader;
import com.orhanobut.hawk.Hawk;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

public class BaseApp extends Application {

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (NetworkConfig.getInstance().getIsDev()) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        Hawk.init(this)
                .build();
        GlobalUtil.setApplication(this);
        //存储
        Hawk.init(this).build();

        //okGo初始化
        NetworkConfig.getInstance().init(this);

        //异常处理
        CrashHandler.getInstance().init(this);
        CrashHandler.getInstance().setCallBack(new CrashHandler.CrashHandlerCallBack() {
            @Override
            public void onCatchException(Throwable ex) {
                if (NetworkConfig.getInstance().getIsDev()) {
                    Toast.makeText(BaseApp.this, "未捕获的异常", Toast.LENGTH_LONG).show();
                }
            }
        });
        UMengAnalyze.getInstance().init(this);
//初始化日志器
        XlogUtil.XlogInit();

        //微信登录是否每次授权============
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
        //微信登录是否每次授权===========
        PlatformConfig.setWeixin(AppidManifests.WX_APP_ID, AppidManifests.WX_APP_AppSecret);


        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.F8F8F8, android.R.color.white);//全局设置主题颜色
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new MRefreshHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new MRefreshFooter(context);
            }
        });
    }


}
