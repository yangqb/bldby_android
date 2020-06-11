package com.feitianzhu.shoplibrary.pagetransformer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.feitianzhu.shoplibrary.R;

public class RotationPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE=0.85f;
    private static final float MIN_ALPHA=0.6f;
    public void setContext(Context context) {
        mContext = context;
    }
    private Context mContext;
    @Override
    public void transformPage(@NonNull View view, float v) {
        float scaleFactor = Math.max(MIN_SCALE,1 - Math.abs(v));
        float scaleAlpha = Math.max(MIN_ALPHA,1 - Math.abs(v));
        ImageView imageTag= (ImageView) view.findViewById(R.id.iv_cover);
        float rotate = 0;
        //position小于等于1的时候，代表page已经位于中心item的最左边，
        //此时设置为最小的缩放率以及最大的旋转度数
        if (v <= -1){
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
            imageTag.setAlpha((float) 2);
        }//position从0变化到-1，page逐渐向左滑动
        else if (v < 0){
            imageTag.setAlpha(Math.abs(v));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }//position从0变化到1，page逐渐向右滑动
        else if (v >=0 && v < 1){
            imageTag.setAlpha(v);
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }//position大于等于1的时候，代表page已经位于中心item的最右边
        else if (v >= 1){
            imageTag.setAlpha((float)1);
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }


    }
}
