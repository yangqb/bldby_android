package com.bldby.baselibrary.app;

import android.content.Context;

public class NetworkConfig {
    private static volatile NetworkConfig singleton = null;

    public static final String BASE_URL = "http://182.92.177.234/"; //正式环境
    public static final String TICKET_BASE_URL = "http://182.92.177.234:8087/"; //机票正式

    public static final String DEV_TICKET_BASE_URL = "http://8.129.218.83:8087/"; //机票测试

    //public static final String DEV_BASE_URL = "http://8.129.218.83:8088/"; //老的测试地址
    public static final String DEV_BASE_URL = "http://219.234.82.84:10003/"; //测试地址 商品详情/商品搜索等
    public static final String DEV_LOGIN_BASE_URL = "http://219.234.82.84:10005/";//登录注册测试 用户登录/注册,获取用户信息等


    //public static final String TICKET_BASE_URL = "http://192.168.0.7:8087/"; //周工机票本地
    //public static final String BASE_URL = "http://192.168.0.9:8089/";//钟工本地地址
    //public static final String BASE_URL = "http://192.168.0.142:8089/"; //周工本地地址
//    public static final String BASE_URL_JI = "http://192.168.0.198:8087/"; //计测试地址
    private Context mContext;

    private NetworkConfig() {
    }

    public static NetworkConfig getInstance() {
        if (singleton == null) {
            synchronized (NetworkConfig.class) {
                if (singleton == null) {
                    singleton = new NetworkConfig();
                }
            }
        }
        return singleton;
    }

    public void init(Context context) {
        mContext = context;
    }

    public String getBaseUrl() {
        if (getIsDev()) {
            return DEV_BASE_URL;
        }
        return BASE_URL;
    }

    /**
     * 登录
     *
     * @return
     */
    public String getLoginUrl() {
        if (getIsDev()) {
            return DEV_LOGIN_BASE_URL;
        }
        return DEV_LOGIN_BASE_URL;
    }

    /**
     * 团油
     *
     * @return
     */
    public String getTicketBaseUrl() {
        if (getIsDev()) {
            return DEV_TICKET_BASE_URL;
        }
        return TICKET_BASE_URL;
    }

    /**
     * 机票地址
     *
     * @return
     */
    public String getAirBaseUrl() {
        if (getIsDev()) {
            return DEV_TICKET_BASE_URL;
        }
        return TICKET_BASE_URL;
    }

    /**
     * 返回true时测试网测试
     *
     * @return
     */
    public boolean getIsDev() {
        return true;
    }
}
