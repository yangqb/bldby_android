package com.bldby.airticket.request;

import com.alibaba.fastjson.TypeReference;
import com.bldby.baselibrary.app.network.BaseAir2Request;
import com.bldby.baselibrary.app.network.BaseRequest;
import com.bldby.baselibrary.core.network.ParamsBuilder;
import com.bldby.baselibrary.core.network.RequestLevel;

/**
 * package name: com.bldby.airticket.request
 * user: yangqinbo
 * date: 2020/6/20
 * time: 17:53
 * email: 694125155@qq.com
 */
public class UpdatePassengerRequest extends BaseAir2Request {
    public String userId;
    public String id;
    public String name;
    public String ageType;
    public String cardType;
    public String cardNo;
    public String sex;
    public String birthday;

    @Override
    public String getAPIName() {
        return "updatePassengers";
    }

    @Override
    public ParamsBuilder appendParams(ParamsBuilder builder) {
        return super.appendParams(builder.append("userId", userId).append("name", name).append("ageType", ageType)
                .append("cardType", cardType).append("cardNo", cardNo).append("sex", sex).append("birthday", birthday).append("id", id));
    }

    @Override
    public RequestLevel getRequestLevel() {
        return RequestLevel.JSONBody;
    }

    @Override
    public TypeReference getDatatype() {
        return new TypeReference<Object>() {

        };
    }
}
