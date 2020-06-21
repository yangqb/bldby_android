package com.bldby.baselibrary.core.load;

import android.app.AlertDialog;
import android.graphics.drawable.AnimationDrawable;
import android.widget.LinearLayout;

import com.bldby.baselibrary.R;
import com.bldby.baselibrary.app.GlobalUtil;
import com.bldby.baselibrary.core.util.DeviceUtil;
import com.bldby.baselibrary.databinding.LayoutLoadingViewBinding;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.impl.LoadingPopupView;

/**
 * Created by bch on 2020/5/15
 * 加载进度圈旋转
 */
public class LoadingUtil {
    /**
     * 用到的地方的数量
     */
    private static int usefulCount = 0;
    private static AnimationDrawable mAnimPull;
    private static AlertDialog alertDialog;


    synchronized public static void setLoadingViewShowWithContent(Boolean show, String content) {
        if (show) {
            usefulCount++;
            if (alertDialog == null) {
                LayoutLoadingViewBinding inflate = LayoutLoadingViewBinding.inflate(GlobalUtil.getCurrentActivity().getLayoutInflater());
                alertDialog = new AlertDialog.Builder(GlobalUtil.getCurrentActivity())
                        .setView(inflate.getRoot())
                        .setCancelable(false)
                        .create();
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

//    public static void setLoadingViewShow(Boolean show, String c) {
//        setLoadingViewShowWithContent(show, c);
//    }

    public static void setLoadingViewShow(Boolean show) {
        setLoadingViewShowWithContent(show, "加载中");
    }

}
