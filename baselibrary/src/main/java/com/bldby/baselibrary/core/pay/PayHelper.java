package com.bldby.baselibrary.core.pay;

import android.app.Activity;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.bldby.baselibrary.core.log.LogUtil;

import java.util.Map;

/**
 * package name: com.bldby.baselibrary.core.util
 * user: yangqinbo
 * date: 2020/6/22
 * time: 12:44
 * email: 694125155@qq.com
 */
public class PayHelper {
    public interface OnPayListener {
        void onSuccess(int code, Object result);

        void onFail(int code, String result);
    }

    public static void aliPay(final Activity mActivity, String orderInfo,
                              final OnPayListener mListener) {

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                final Map<String, String> result = alipay.payV2(orderInfo, true);
                LogUtil.i("map", result.toString());
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        @SuppressWarnings("unchecked")
                        PayResult payResult = new PayResult(result);

                        //对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                        String resultStatus = payResult.getResultStatus();
                        if (TextUtils.equals(resultStatus, "9000")) {
                            mListener.onSuccess(200, resultInfo);
                        } else {
                            mListener.onFail(Integer.valueOf(resultStatus), payResult.getMemo());
                        }
                    }
                });
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
