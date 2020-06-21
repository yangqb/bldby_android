package com.bldby.baselibrary.core.smart;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bldby.baselibrary.R;
import com.bldby.baselibrary.core.util.StringUtil;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * @author : zlf
 * date    : 2019-04-19
 * github  : https://github.com/mamumu
 * blog    : https://www.jianshu.com/u/281e9668a5a6
 */
public class MRefreshFooter extends LinearLayout implements RefreshFooter {

    private ImageView mImage;
    private Animation mAnim;
    private AnimationDrawable mAnimPull;
    private AnimationDrawable mAnimRefresh;
    private TextView title;

    public MRefreshFooter(Context context) {
        this(context, null, 0);
    }

    public MRefreshFooter(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MRefreshFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.m_refresh_footer, this);
        title = view.findViewById(R.id.bottomTitle);
        mImage = view.findViewById(R.id.iv_refresh_footer);
        mAnim = AnimationUtils.loadAnimation(getContext(), R.anim.anim_round_rotate);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        mAnim.setInterpolator(linearInterpolator);

    }

    /**
     * 没有更多数据了
     */
    public void setNoMoreData() {
    }

    /**
     * 没有网络
     */
    public void setNoNetWork() {
        this.title.setText("网络中断,请重试");
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        if (noMoreData) {
            this.title.setText("没有更多数据了");
        } else {
            this.title.setText("请稍等");

        }
        return noMoreData;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        //控制是否稍微上滑动就刷新
        kernel.getRefreshLayout().setEnableAutoLoadMore(false);
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (mAnim != null && mAnim.hasStarted() && !mAnim.hasEnded()) {
            mAnim.cancel();
            mImage.clearAnimation();
        }
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullUpToLoad:
               /* if (mAnim != null) {
                    mImage.startAnimation(mAnim);
                }*/
                mImage.setImageResource(R.drawable.refresh_00);
                break;
            case Loading:

            case LoadReleased:
                mImage.setImageResource(R.drawable.anim_pull_end);
                mAnimPull = (AnimationDrawable) mImage.getDrawable();
                mAnimPull.start();
                title.setText("让你放心 才是对的");
                break;
            case ReleaseToLoad:
                mImage.setImageResource(R.drawable.anim_pull_refreshing);
                mAnimRefresh = (AnimationDrawable) mImage.getDrawable();
                mAnimRefresh.start();
                title.setText("让你放心 才是对的");
                break;
        }
    }
}
