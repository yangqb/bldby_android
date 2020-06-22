package com.bldby.baselibrary.app;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetworkConfig {
    private static volatile NetworkConfig singleton = null;

    public static final String BASE_URL = "http://182.92.177.234/"; //正式环境
    public static final String TICKET_BASE_URL = "http://182.92.177.234:8087/"; //机票正式

    public static final String DEV_BASE_URL = "http://219.234.82.84:10001/"; //测试地址 商品详情/商品搜索等


//    public static final String DEV_TICKET_BASE_URL = "http://8.129.218.83:8087/"; //机票测试

    //public static final String DEV_BASE_URL = "http://8.129.218.83:8088/"; //老的测试地址
//    public static final String DEV_BASE_URL = "http://219.234.82.84:10003/"; //测试地址 商品详情/商品搜索等
//    public static final String DEV_LOGIN_BASE_URL = "http://219.234.82.84:10005/";//登录注册测试 用户登录/注册,获取用户信息等


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

    public static final int DEFAULT_MILLISECONDS = 10 * 1000;

    public void init(Application context) {
        OkGo.getInstance().init(context)                       //必须调用初始化
                .setOkHttpClient(defaultOkHttpClient())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);
        mContext = context;
    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (NetworkConfig.getInstance().getIsDev()) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        //全局的读取超时时间
        builder.readTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

//        SPCookieStore：使用SharedPreferences保持cookie，如果cookie不过期，则一直有效
//        DBCookieStore：使用数据库保持cookie，如果cookie不过期，则一直有效
//        MemoryCookieStore：使用内存保持cookie，app退出后，cookie消失
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(GlobalUtil.getApplication())));
//        HttpsUtils.SSLParams sslParams1;
//        try {//方法三：使用预埋证书，校验服务端证书（自签名证书）
//            sslParams1 = HttpsUtils.getSslSocketFactory(CoreUtils
//                    .getApplicationContext().getAssets().open("srca.cer"));
//            LogUtil.d("ssl", "only yooli https");
//        } catch (IOException e) {
//            e.printStackTrace();
//            sslParams1 = HttpsUtils.getSslSocketFactory();
//        }
//        //方法三：使用预埋证书，校验服务端证书（自签名证书）
//        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);

        return builder.build();
    }

    public String getBaseUrl() {
        if (getIsDev()) {
            return DEV_BASE_URL;
        }
        return DEV_BASE_URL;
    }

    /**
     * 登录
     *
     * @return
     */
    public String getLoginUrl() {
        if (getIsDev()) {
            return DEV_BASE_URL;
        }
        return DEV_BASE_URL;
    }

    /**
     * 团油
     *
     * @return
     */
    public String getTicketBaseUrl() {
        if (getIsDev()) {
            return DEV_BASE_URL;
        }
        return DEV_BASE_URL;
    }

    /**
     * 机票地址
     *
     * @return
     */
    public String getAirBaseUrl() {
        if (getIsDev()) {
            return DEV_BASE_URL;
        }
        return DEV_BASE_URL;
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
