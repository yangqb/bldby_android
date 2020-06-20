package com.bldby.airticket.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.AirConstants;
import com.bldby.airticket.callback.OnCitySelectListener;
import com.bldby.airticket.callback.OnLocationListener;
import com.bldby.airticket.databinding.ActivitySelectCountryCodeBinding;
import com.bldby.airticket.model.CityModel;
import com.bldby.airticket.model.CustomPhoneCodeModel;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/20
 * time: 15:56
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.AIRCOUNTRYCODE)
public class SelectPhoneCodeActivity extends BaseUiActivity {
    private ActivitySelectCountryCodeBinding binding;
    private String cnJson;
    private List<CustomPhoneCodeModel> cnStatusLs = new ArrayList<>();
    private List<CityModel> cnAllCitys = new ArrayList<>();
    private List<CityModel> cnHotCitys = new ArrayList<>();
    private CityModel cnCurrentCity;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivitySelectCountryCodeBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        try {
            cnJson = readString(mContext.getAssets().open("area_phone_code.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置城市选择之后的事件监听
        binding.cnCityView.setOnCitySelectListener(new OnCitySelectListener() {
            @Override
            public void onCitySelect(CityModel cityModel) {
                //Toast.makeText(SelectPlaneCityActivity.this, "你点击了：" + cityModel.getCityName() + ":" + cityModel.getExtra().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra(AirConstants.SELECT_COUNTRY_CODE, cityModel);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onSelectCancel() {
                //Toast.makeText(SelectPlaneCityActivity.this, "你取消了城市选择", Toast.LENGTH_SHORT).show();
                //finish();
            }
        });

        //设置点击重新定位之后的事件监听
        binding.cnCityView.setOnLocationListener(new OnLocationListener() {
            @Override
            public void onLocation() {
                //这里模拟定位 两秒后给个随便的定位数据
                binding.cnCityView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.cnCityView.reBindCurrentCity(new CityModel("广州", "中国", "10000001"));
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void loadData() {
//设置所有城市数据
        Type jsonType = new TypeToken<List<CustomPhoneCodeModel>>() {
        }.getType();
        cnStatusLs = new Gson().fromJson(cnJson, jsonType);
        //设置热门城市列表 这都是瞎写的 哈哈哈
        for (int i = 0; i < cnStatusLs.size(); i++) {
            CityModel cityModel = new CityModel(cnStatusLs.get(i).zh, "", cnStatusLs.get(i).code);
            cnAllCitys.add(cityModel);
            /*if (!TextUtils.isEmpty(Constant.mCity)) {
                if (Constant.mCity.equals(statusLs.get(i).city)) {
                    //设置当前城市数据
                    currentCity = cityModel;
                    //绑定数据到视图 需要 所有城市列表 热门城市列表 和 当前城市列表 其中 所有城市列表是必传的 热门城市和当前城市是选填的 不传就不会显示对应的视图
                }
            }*/
        }

        binding.cnCityView.bindData(cnAllCitys, cnHotCitys, cnCurrentCity);
        //设置搜索框的文案提示
        binding.cnCityView.setSearchTips("请输入城市名称或者拼音");
        binding.cnCityView.setShowCityCode(true);
    }

    @Override
    public void initListener() {

    }

    private String readString(InputStream in) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringWriter sw = new StringWriter();
            String line;
            while ((line = br.readLine()) != null) {
                sw.write(line);
            }
            br.close();
            sw.close();
            return sw.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }
}
