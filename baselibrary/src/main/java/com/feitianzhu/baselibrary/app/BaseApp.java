package com.feitianzhu.baselibrary.app;

import android.app.Application;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.feitianzhu.baselibrary.core.errorlog.CrashHandler;
import com.orhanobut.hawk.Hawk;

public class BaseApp extends Application {

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
    }
}
