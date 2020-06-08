package com.feitianzhu.baselibrary.app.network;

import com.alibaba.fastjson.TypeReference;
import com.feitianzhu.baselibrary.app.NetworkConfig;
import com.feitianzhu.baselibrary.core.network.BaseApiRequest;
import com.feitianzhu.baselibrary.core.util.StringUtils;
import com.feitianzhu.baselibrary.core.util.ToastUtil;
import com.lxj.xpopup.impl.ConfirmPopupView;

/**
 * Created by bch on 2020/5/11
 * 这个base请求基类为通用基类请求
 */
public abstract class BaseRequest extends BaseApiRequest {


    private ConfirmPopupView confirmPopupView;

    /**
     * 设置域名 端口
     *
     * @return
     */
    @Override
    public String getAPIBaseURL() {
        return NetworkConfig.getInstance().getBaseUrl();

    }

    /**
     * 设置base返回基类,进行统一处理
     *
     * @return
     */
    @Override
    public TypeReference getResponseType() {
        return new TypeReference<BaseResponse>() {
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
        super.handleError(errorCode, errorMsg);
        if (errorCode == 100021105) {
//            登录异常被踢
//            if (confirmPopupView == null) {
//                confirmPopupView = new XPopup.Builder(GlobalUtil.getCurrentActivity())
//                        .autoDismiss(false)
//                        .dismissOnTouchOutside(false)
//                        .enableDrag(false)
//                        .asConfirm("", "您的账号已在其他设备登陆，如果这不是您的操作，请及时修改密码并重新登陆。", "重新登录", "找回密码", new OnConfirmListener() {
//                            @Override
//                            public void onConfirm() {
//                                //context.startActivity(new Intent(context, ForgetPasswordActivity.class));
////                                Intent intent = new Intent(GlobalUtil.getCurrentActivity(), ChangeLoginPassword.class);
////                                GlobalUtil.getCurrentActivity().startActivity(intent);
//                            }
//                        }, new OnCancelListener() {
//                            @Override
//                            public void onCancel() {
////                                SPUtils.putString(GlobalUtil.getCurrentActivity(), Constant.SP_PASSWORD, "");
////                                SPUtils.putString(GlobalUtil.getCurrentActivity(), Constant.SP_LOGIN_USERID, "");
////                                SPUtils.putString(GlobalUtil.getCurrentActivity(), Constant.SP_ACCESS_TOKEN, "");
////                                Constant.ACCESS_TOKEN = "";
////                                Constant.LOGIN_USERID = "";
////                                Constant.PHONE = "";
//                                Intent intent = new Intent(GlobalUtil.getCurrentActivity(), LoginActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                GlobalUtil.getCurrentActivity().startActivity(intent);
//                            }
//                        }, false)
//                        .bindLayout(R.layout.layout_dialog_login);
//                confirmPopupView.show();//绑定已有布局
//            }
//            SPUtils.putBoolean(GlobalUtil.getCurrentActivity(), Constant.LOGIN_DIALOG, false);
        } else if (errorCode == 404) {
            //找不到
            ToastUtil.show("404 ,网络连接错误  重新加载");

        } else if (errorCode == kErrorTypeNoNetworkConnect) {
            //网络不可用
            ToastUtil.show("网络未开启,请打开网络");

        } else if (errorCode == kErrorTypeResponseHandleError) {
            //外部数据处理错误
            ToastUtil.show("数据处理错误");

        } else if (errorCode == kErrorTypeResponsePraseError) {
            //json解析错误
            ToastUtil.show("网络正在开小差 重新加载");

        } else if (errorCode == kErrorTypeNoNetworkCancel) {
            ToastUtil.show("取消请求");
        } else if (!StringUtils.isEmpty(errorMsg)) {
            ToastUtil.show(errorMsg);
        } else {
            //未知情况
            ToastUtil.show("网络错误,请重试" +errorCode +errorMsg);
        }
    }
}

