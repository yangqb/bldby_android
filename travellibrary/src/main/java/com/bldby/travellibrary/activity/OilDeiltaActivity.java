package com.bldby.travellibrary.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteTravelConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.travellibrary.R;
import com.bldby.travellibrary.databinding.ActivityOilDeiltaBinding;
import com.bldby.travellibrary.databinding.ActivityOilOrderBinding;
@Route(path = RouteTravelConstants.TRAVELDETAIL)
public class OilDeiltaActivity extends BaseActivity {



    @Override
    public void bindIngView() {
        ActivityOilDeiltaBinding oilDeiltaBinding=DataBindingUtil.setContentView(this,R.layout.activity_oil_deilta);
        oilDeiltaBinding.setViewmodel(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}