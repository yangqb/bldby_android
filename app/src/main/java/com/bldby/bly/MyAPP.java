package com.bldby.bly;

import com.bldby.baselibrary.app.BaseApp;
import com.hyphenate.easeui.EaseUiManager;


public class MyAPP extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        EaseUiManager.getInstance().init(this);
    }
}
