package com.bldby.loginlibrary.util;

import android.content.Context;

import com.bldby.baselibrary.app.Constants;
import com.bldby.baselibrary.app.util.SPUtils;
import com.bldby.loginlibrary.model.UserInfo;
import com.google.gson.Gson;

/**
 * package name: com.bldby.baselibrary.app.util
 * user: yangqinbo
 * date: 2020/6/16
 * time: 11:21
 * email: 694125155@qq.com
 */
public class UserInfoUtils {
    public static UserInfo getUserInfo(Context context) {
        UserInfo userInfo;
        String userString = SPUtils.getString(context, Constants.USER_DATA, "");
        if ("".equals(userString)) {
            userInfo = new UserInfo();
        } else {
            userInfo = new Gson().fromJson(userString, UserInfo.class);
        }
        return userInfo;
    }

    public static void saveUserInfo(Context context, UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        String newUser = new Gson().toJson(userInfo);
        SPUtils.putString(context, Constants.USER_DATA, newUser);
    }
}
