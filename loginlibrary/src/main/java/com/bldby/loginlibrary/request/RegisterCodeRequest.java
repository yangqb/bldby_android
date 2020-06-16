package com.bldby.loginlibrary.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseLoginRequest;
import com.bldby.baselibrary.app.util.MD5Utils;
import com.bldby.baselibrary.core.network.ParamsBuilder;

import java.util.Map;
import java.util.TreeMap;

/**
 * package name: com.bldby.loginlibrary.request
 * user: yangqinbo
 * date: 2020/6/15
 * time: 17:08
 * email: 694125155@qq.com
 */
public class RegisterCodeRequest extends BaseLoginRequest {
    public String phone;
    public String type;

    @Override
    public String getAPIName() {
        return "getSmsCode";
    }

    @Override
    public boolean usePost() {
        return false;
    }

    /*
     * 获取验证码接口需要验签
     * */
    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("appKey", "BLDBYWOMENYIDINGXING888888");
        treeMap.put("timestamp", System.currentTimeMillis() + "");
        treeMap.put("phone", phone);
        treeMap.put("type", type);
        String sign = MD5Utils.getSign(treeMap);
        for (Map.Entry entry : treeMap.entrySet()) {
            builder.append(entry.getKey().toString(), entry.getValue());
        }
        return builder.append("sign", sign);
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference() {
        };
    }
}
