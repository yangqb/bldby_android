package com.feitianzhu.travellibrary.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.feitianzhu.baselibrary.constants.RouteLoginConstants;
import com.feitianzhu.baselibrary.constants.RouteTravelConstants;
import com.feitianzhu.baselibrary.core.ui.baseactivity.BaseActivity;
import com.feitianzhu.travellibrary.R;
import com.feitianzhu.travellibrary.activity.model.TravelModel;
import com.feitianzhu.travellibrary.databinding.ActivityTravelBinding;

@Route(path = RouteTravelConstants.TRAVELMAIN, extras = RouteLoginConstants.SHOWCHECKLOGIN)
public class TravelActivity extends BaseActivity {


    private ActivityTravelBinding dataBinding;

    @Override
    public void bindIngView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        dataBinding.setViewmodel(this);
    }

    @Override
    public void initView() {
        dataBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadData() {
        // 创建或者直接返回一个已经存在的ViewModel
        TravelModel travelModel = ViewModelProviders.of(this)
                .get(TravelModel.class);
        if (travelModel.getStartTime() == null) {
            //chronometerViewModel如果没设置过开始时间，那么说明这个新的ViewModel,
            //所以给它设置开始时间
            long startTime = SystemClock.elapsedRealtime();
            travelModel.setStartTime(startTime);
            dataBinding.button.setText(startTime + "");
        } else {
            //否则ViewModel已经在上个Activity的onCreate()方法中创建过了，屏幕旋转以后，
            //ViewModel会被保存,我们直接获取ViewModel里持有的时间
            dataBinding.button.setText(travelModel.getStartTime() + "");
        }
        dataBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travelModel.setStartTime(travelModel.getStartTime() + 1);
            }
        });
    }

    @Override
    public void initListener() {

    }
}