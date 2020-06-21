package com.bldby.baselibrary.core.ui.baseactivity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bldby.baselibrary.R;
import com.bldby.baselibrary.core.util.StringUtils;
import com.bldby.baselibrary.databinding.ActivityBaseUiBinding;

/**
 * Created by bch on 2020/5/30.
 */
public abstract class BaseUiActivity extends BaseActivity {
    /**
     * 代替Context
     */
    protected Context mContext;
    private ActivityBaseUiBinding viewDataBinding;

    @Override
    public void bindIngView() {
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_ui);
        viewDataBinding.setViewmodel(this);
        viewDataBinding.llContent.addView(initContentView(getLayoutInflater(), viewDataBinding.llContent));
        mContext = BaseUiActivity.this;
        initRightImg(viewDataBinding.rightImg);
    }

    /**
     * 取消顶部颜色交由子页面控制
     *
     * @param color
     */
    public void setTitleBackground(@ColorRes int color) {
        viewDataBinding.titleBackground.setBackgroundResource(color);
    }

    /**
     * 取消顶部的45dp,交由子页面控制
     *
     * @param isShow
     */
    public void setGoneTitleBar(boolean isShow) {
        if (isShow) {
            viewDataBinding.titleBar.setVisibility(View.VISIBLE);
        } else {
            viewDataBinding.titleBar.setVisibility(View.GONE);
        }
    }





    //容器
    protected abstract View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup);


    /**
     * 创建一个titlebar
     */
    protected void initTitle(String title) {
        if (!StringUtils.isEmpty(title)) {
            viewDataBinding.titleName.setText(title);
        } else {
            viewDataBinding.titleName.setText("");
        }
    }
    /*
     * 创建右侧标题
     * */
    protected void setRightText(String title) {
        if (!StringUtils.isEmpty(title)) {
            viewDataBinding.rightText.setText(title);
        } else {
            viewDataBinding.rightText.setText("");
        }
        viewDataBinding.rightText.setVisibility(View.VISIBLE);
        viewDataBinding.rightImg.setVisibility(View.GONE);
    }

    protected void initRightImg(ImageView view) {
//        viewDataBinding.rightText.setVisibility(View.GONE);
        viewDataBinding.rightImg.setVisibility(View.VISIBLE);
        return;
    }

    /**
     * 右上角的点击事件
     *
     * @param view
     */
    public void onClickRight(View view) {

    }

}
