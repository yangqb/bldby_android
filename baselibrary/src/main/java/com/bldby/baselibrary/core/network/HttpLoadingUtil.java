package com.bldby.baselibrary.core.network;

import android.app.AlertDialog;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.widget.LinearLayout;


import com.bldby.baselibrary.R;
import com.bldby.baselibrary.app.GlobalUtil;
import com.bldby.baselibrary.core.util.DeviceUtil;
import com.bldby.baselibrary.databinding.LayoutLoadingViewBinding;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.XPopupCallback;

import java.util.HashMap;

/**
 * Created by bch on 2020/5/15
 */
public class HttpLoadingUtil {
    /**
     * 用到的地方的数量
     */
    private static int usefulCount = 0;
    private static HashMap<String, BaseApiRequest> map = new HashMap<>();
    private static AnimationDrawable mAnimPull;
    private static AlertDialog alertDialog;

    //TODO 加载条 添加退出按钮 ,点击取消所有请求 2020-5-15

    synchronized private static void setLoadingViewShowWithContent(BaseApiRequest baseApiRequest, Boolean show, String content) {
        if (show) {
            usefulCount++;
            if (alertDialog == null) {
                LayoutLoadingViewBinding inflate = LayoutLoadingViewBinding.inflate(GlobalUtil.getCurrentActivity().getLayoutInflater());
                alertDialog = new AlertDialog.Builder(GlobalUtil.getCurrentActivity(), R.style.TransparentDialog)
                        .setView(inflate.getRoot())
                        .setCancelable(false)
                        .create();
                alertDialog.getWindow().setDimAmount(0f);

                alertDialog.show();
                int i = DeviceUtil.dp2px(GlobalUtil.getCurrentActivity(), GlobalUtil.getCurrentActivity().getResources().getDimension(R.dimen.dp_50));
                alertDialog.getWindow().setLayout(i, LinearLayout.LayoutParams.WRAP_CONTENT);
                mAnimPull = (AnimationDrawable) inflate.img.getDrawable();
                mAnimPull.start();

            }


        } else {
            usefulCount--;
            if (usefulCount <= 0 && alertDialog != null) {
                alertDialog.dismiss();
                alertDialog = null;
                mAnimPull = null;

            }


        }


    }

    public static void setLoadingViewShow(BaseApiRequest baseApiRequest, Boolean show) {
        GlobalUtil.getCurrentActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setLoadingViewShowWithContent(baseApiRequest, show, "加载中");
            }
        });
    }

}
