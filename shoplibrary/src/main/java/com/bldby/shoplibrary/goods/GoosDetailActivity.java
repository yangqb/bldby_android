package com.bldby.shoplibrary.goods;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityGoosDetailBinding;

public class GoosDetailActivity extends BaseActivity {

    private ActivityGoosDetailBinding dataBinding;

    @Override
    public void bindIngView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_goos_detail);
        dataBinding.setViewmodel(this);
    }

    @Override
    public void initView() {
//dataBinding.nesc.
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {
        dataBinding.nesc.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                int height = dataBinding.one.getHeight();
                Log.e("TAG", "onScrollChange: " + height + ".." + i1 + ".." + ".." + i3);

                if (i1 > height) {
                    dataBinding.titleBackground.setAlpha(1);
                } else {
                    float i4 = (float) i1 / height;
                    Log.e("TAG", "onScrollChange: " + i4);

                    dataBinding.titleBackground.setAlpha(i4);
                }
                Rect scrollRect = new Rect();
                nestedScrollView.getHitRect(scrollRect);
                //子控件在可视范围内（至少有一个像素在可视范围内）
                if (dataBinding.one.getLocalVisibleRect(scrollRect)) {
                    Log.e("TAG", "onScrollChange: ");
                    dataBinding.titleName.setText("第一个");
                } else if (dataBinding.one2.getLocalVisibleRect(scrollRect)) {
                    ////子控件完全不在可视范围内
                    Log.e("TAG", "onScrollChange:00 ");
                    dataBinding.titleName.setText("第2个");
                } else if (dataBinding.one3.getLocalVisibleRect(scrollRect)) {
                    ////子控件完全不在可视范围内
                    Log.e("TAG", "onScrollChange:00 ");
                    dataBinding.titleName.setText("第3个");
                }


            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }

}