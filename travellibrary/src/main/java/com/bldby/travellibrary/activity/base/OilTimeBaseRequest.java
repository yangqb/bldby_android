package com.bldby.travellibrary.activity.base;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.travellibrary.activity.request.OilTimeResponse;


/**
 * Created by bch on 2020/5/20
 */
public abstract class OilTimeBaseRequest extends BaseRequest {

    @Override
    public String getAPIBaseURL() {
        return "https://mcs.czb365.com/";
    }


    @Override
    public TypeReference getResponseType() {
        return new TypeReference<OilTimeResponse>() {
        };
    }

    /**
     * 在这里处理错误情况
     * 情况有 网络错误码
     * 服务器业务逻辑错误代码
     *
     * @param errorCode
     * @param errorMsg
     */
    @Override
    public void handleError(int errorCode, String errorMsg) {
//        super.handleError(errorCode, errorMsg);
        if (errorCode == kErrorTypeNoNetworkCancel) {
            ToastUtil.show("取消请求");
        } else {
            ToastUtil.show(errorMsg);
        }
    }

}
