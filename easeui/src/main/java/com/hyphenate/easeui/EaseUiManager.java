package com.hyphenate.easeui;

import android.app.Application;
import android.util.Log;

import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.loginlibrary.AccountManager;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.customer.IMContent;

public class EaseUiManager {
    private static volatile EaseUiManager singleton = null;
    private boolean isLogin = false;

    private EaseUiManager() {
    }

    public static EaseUiManager getInstance() {
        if (singleton == null) {
            synchronized (EaseUiManager.class) {
                if (singleton == null) {
                    singleton = new EaseUiManager();
                }
            }
        }
        return singleton;
    }

    public void init(Application application) {
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
//初始化
        EaseUI.getInstance().init(application, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EMClient.getInstance().setDebugMode(true);
//EaseUI初始化成功之后再去调用注册消息监听的代码
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void login(ApiCallBack apiCallBack) {
//        AccountManager.getInstance().getUserPhone()
        EMClient.getInstance().login(AccountManager.getInstance().getUserId()+ IMContent.getTag(), "123456", new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                //startActivity(new Intent(Customerservice.this,ImActivity.class));
                Log.d("main", "登录聊天服务器成功!");
                isLogin = true;
                apiCallBack.onAPIResponse("");

            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.i("onError", "onError: " + code + message);
                Log.d("main", "登录聊天服务器失败！");
                isLogin = false;

                apiCallBack.onAPIError(code, message);
            }
        });
    }
}
