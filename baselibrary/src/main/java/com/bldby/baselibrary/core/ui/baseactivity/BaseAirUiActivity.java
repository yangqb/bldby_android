package com.bldby.baselibrary.core.ui.baseactivity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bldby.baselibrary.R;
import com.bldby.baselibrary.core.util.StringUtils;
import com.bldby.baselibrary.databinding.ActivityBaseAirUiBinding;

/**
 * package name: com.bldby.baselibrary.core.ui.baseactivity
 * user: yangqinbo
 * date: 2020/6/16
 * time: 18:43
 * email: 694125155@qq.com
 */
public abstract class BaseAirUiActivity extends BaseActivity {
    /**
     * 代替Context
     */
    protected Context mContext;
    private ActivityBaseAirUiBinding viewDataBinding;

    @Override
    public void bindIngView() {
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_air_ui);
        viewDataBinding.setViewModel(this);
        viewDataBinding.llContent.addView(initContentView(getLayoutInflater(), viewDataBinding.llContent));
        mContext = BaseAirUiActivity.this;
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

    public void onClickBack(View v) {
        finish();
    }

    public void onClickRightBtn(View view) {

    }

    //容器
    protected abstract View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup);


    /**
     * 创建一个titlebar
     */
    protected void initTitle(String depCityName, String arrCityName) {
        if (!StringUtils.isEmpty(depCityName)) {
            viewDataBinding.depCityName.setText(depCityName);
        } else {
            viewDataBinding.depCityName.setText("");
        }

        if (!StringUtils.isEmpty(arrCityName)) {
            viewDataBinding.arrCityName.setText(arrCityName);
        } else {
            viewDataBinding.arrCityName.setText("");
        }
    }

    /*
     * 右侧图标是否展示
     * */
    protected void setVisibleRightImg(boolean visible) {
        if (visible) {
            viewDataBinding.rightButton.setVisibility(View.VISIBLE);
        } else {
            viewDataBinding.rightButton.setVisibility(View.INVISIBLE);
        }

    }

    /*
     * 设置中间图片
     * */
    protected void setCenterImg(int resId) {
        viewDataBinding.centerImg.setBackgroundResource(resId);
    }
}
