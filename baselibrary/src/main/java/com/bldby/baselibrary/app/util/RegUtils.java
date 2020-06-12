package com.bldby.baselibrary.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * package name: com.bldby.baselibrary.app.util
 * user: yangqinbo
 * date: 2020/6/12
 * time: 15:35
 * email: 694125155@qq.com
 */
/*
 * 正则验证
 * */
public class RegUtils {
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[1,8,9]))\\d{8}$";
        if (phone == null || phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
