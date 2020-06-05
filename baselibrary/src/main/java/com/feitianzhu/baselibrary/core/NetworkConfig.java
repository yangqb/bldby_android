package com.feitianzhu.baselibrary.core;

public class NetworkConfig {
    private static volatile NetworkConfig singleton = null;

    private static final String BASE_URL = "http://182.92.177.234/"; //正式环境
    private static final String TICKET_BASE_URL = "http://182.92.177.234:8087/"; //机票正式


    private static final String DEV_BASE_URL = "http://8.129.218.83:8088/"; //测试地址
    private static final String DEV_TICKET_BASE_URL = "http://8.129.218.83:8087/"; //机票测试


    //public static final String TICKET_BASE_URL = "http://192.168.0.7:8087/"; //周工机票本地
    //public static final String BASE_URL = "http://192.168.0.9:8089/";//钟工本地地址
    //public static final String BASE_URL = "http://192.168.0.142:8089/"; //周工本地地址
//    public static final String BASE_URL_JI = "http://192.168.0.198:8087/"; //计测试地址


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

    public String getBaseUrl() {
        if (getIsDev()) {
            return DEV_BASE_URL;
        }
        return BASE_URL;
    }

    public String getTicketBaseUrl() {
        if (getIsDev()) {
            return TICKET_BASE_URL;
        }
        return TICKET_BASE_URL;
    }

    public boolean getIsDev() {
        return true;
    }
}
