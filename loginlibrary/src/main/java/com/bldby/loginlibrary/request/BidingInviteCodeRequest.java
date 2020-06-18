package com.bldby.loginlibrary.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseLoginRequest;
import com.bldby.baselibrary.app.util.MD5Utils;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

import java.util.Map;
import java.util.TreeMap;

/**
 * package name: com.bldby.loginlibrary.request
 * user: yangqinbo
 * date: 2020/6/16
 * time: 10:44
 * email: 694125155@qq.com
 */
public class BidingInviteCodeRequest extends BaseLoginRequest {
    public String parentId;
    public String accessToken;
    public String userId;

    @Override
    public String getAPIName() {
        return "bind/bindParent";
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.POST;
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("appKey", "BLDBYWOMENYIDINGXING888888");
        treeMap.put("timestamp", System.currentTimeMillis() + "");
        treeMap.put("accessToken", accessToken);
        treeMap.put("userId", userId);
        treeMap.put("parentId", parentId);
        String sign = MD5Utils.getSign(treeMap);
        for (Map.Entry entry : treeMap.entrySet()) {
            builder.append(entry.getKey().toString(), entry.getValue());
        }
        return builder.append("sign", sign);
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<Boolean>() {
        };
    }
}
