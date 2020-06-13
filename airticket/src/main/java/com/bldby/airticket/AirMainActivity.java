package com.bldby.airticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteAirConstants;

@Route(path = RouteAirConstants.MAIN)
public class AirMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_main);
    }
}