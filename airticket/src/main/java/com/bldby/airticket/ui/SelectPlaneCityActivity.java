package com.bldby.airticket.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.R;
import com.bldby.airticket.callback.OnCitySelectListener;
import com.bldby.airticket.callback.OnLocationListener;
import com.bldby.airticket.databinding.ActivityCitySelectBinding;
import com.bldby.airticket.model.CityModel;
import com.bldby.airticket.model.CustomCityModel;
import com.bldby.airticket.request.SelectCityRequest;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseAirUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/17
 * time: 12:24
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.SELECTCITY)
public class SelectPlaneCityActivity extends BaseAirUiActivity {
    private ActivityCitySelectBinding binding;
    public static final String CITY_TYPE = "search_type";
    public static final String CITY_DATA = "city_data";
    public int type = 0;//国内0,国际1
    private List<CustomCityModel> cnStatusLs = new ArrayList<>();
    private List<CustomCityModel> interStatusLs = new ArrayList<>();
    private List<CityModel> cnHotCitys = new ArrayList<>();
    private List<CityModel> interHotCitys = new ArrayList<>();
    private List<CityModel> cnAllCitys = new ArrayList<>();
    private List<CityModel> interAllCitys = new ArrayList<>();
    private CityModel cnCurrentCity;
    private CityModel interCurrentCity;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityCitySelectBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {
        initCnCity();
        initInterCity();
    }

    public void initCnCity() {
        SelectCityRequest request = new SelectCityRequest();
        request.flag = 1;
        request.call(new ApiLifeCallBack<List<CustomCityModel>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(List<CustomCityModel> response) {
                cnStatusLs = response;
                for (int i = 0; i < cnStatusLs.size(); i++) {
                    CityModel cityModel = new CityModel(cnStatusLs.get(i).city, cnStatusLs.get(i).country, cnStatusLs.get(i).szm);
                    cnAllCitys.add(cityModel);
                    if ("北京".equals(cnStatusLs.get(i).city) || "上海".equals(cnStatusLs.get(i).city) || "广州".equals(cnStatusLs.get(i).city) || "深圳".equals(cnStatusLs.get(i).city) || "武汉".equals(cnStatusLs.get(i).city)) {
                        cnHotCitys.add(cityModel);
                    }

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
                binding.cnCityView.setShowCityCode(false);
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    public void initInterCity() {
        //设置所有城市数据
        SelectCityRequest request = new SelectCityRequest();
        request.flag = 2;
        request.call(new ApiLifeCallBack<List<CustomCityModel>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinsh() {

            }

            @Override
            public void onAPIResponse(List<CustomCityModel> response) {
                interStatusLs = response;
                for (int i = 0; i < interStatusLs.size(); i++) {
                    CityModel cityModel = new CityModel(interStatusLs.get(i).city, interStatusLs.get(i).country, interStatusLs.get(i).szm);
                    interAllCitys.add(cityModel);

                    if (interStatusLs.get(i).id == 6719 || interStatusLs.get(i).id == 6747 ||
                            interStatusLs.get(i).id == 11963 || interStatusLs.get(i).id == 6757 || (interStatusLs.get(i).id == 6897 || interStatusLs.get(i).id == 6925)) {
                        interHotCitys.add(cityModel);
                    }
            /*if (!TextUtils.isEmpty(Constant.mCity)) {
                if (Constant.mCity.equals(statusLs.get(i).city)) {
                    //设置当前城市数据
                    currentCity = cityModel;
                    //绑定数据到视图 需要 所有城市列表 热门城市列表 和 当前城市列表 其中 所有城市列表是必传的 热门城市和当前城市是选填的 不传就不会显示对应的视图
                }
            }*/
                }

                binding.interCityView.bindData(interAllCitys, interHotCitys, interCurrentCity);
                //设置搜索框的文案提示
                binding.interCityView.setSearchTips("请输入城市名称或者拼音");
                binding.interCityView.setShowCityCode(false);
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    public void onClickSelectCity(View view) {
        switch (view.getId()) {
            case R.id.btn_domestic:
                type = 0;
                binding.cnCityView.setVisibility(View.VISIBLE);
                binding.interCityView.setVisibility(View.GONE);
                binding.btnDomestic.setTextColor(getResources().getColor(R.color.color_333333));
                binding.btnInternational.setTextColor(getResources().getColor(R.color.color_666666));
                binding.line1.setBackgroundColor(getResources().getColor(R.color.color_fed228));
                binding.line2.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.btn_international:
                binding.cnCityView.setVisibility(View.GONE);
                binding.interCityView.setVisibility(View.VISIBLE);
                type = 1;
                binding.btnDomestic.setTextColor(getResources().getColor(R.color.color_666666));
                binding.btnInternational.setTextColor(getResources().getColor(R.color.color_333333));
                binding.line1.setBackgroundColor(getResources().getColor(R.color.white));
                binding.line2.setBackgroundColor(getResources().getColor(R.color.color_fed228));
                break;
        }
    }


    @Override
    public void initListener() {
        //设置国内城市选择之后的事件监听
        binding.cnCityView.setOnCitySelectListener(new OnCitySelectListener() {
            @Override
            public void onCitySelect(CityModel cityModel) {
                if ("中国香港".equals(cityModel.getCountryName()) || "中国澳门".equals(cityModel.getCountryName()) || "中国台湾".equals(cityModel.getCountryName())) {
                    type = 1;
                    ToastUtil.show(R.string.air_city_select_error);
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(CITY_TYPE, type);
                intent.putExtra(CITY_DATA, cityModel);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onSelectCancel() {

            }
        });

        //设置国际城市选择之后的事件监听
        binding.interCityView.setOnCitySelectListener(new OnCitySelectListener() {
            @Override
            public void onCitySelect(CityModel cityModel) {
                /*  if ("中国香港".equals(cityModel.getCountryName()) || "中国澳门".equals(cityModel.getCountryName()) || "中国台湾".equals(cityModel.getCountryName())) {
                    type = 1;
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(CITY_TYPE, type);
                intent.putExtra(CITY_DATA, cityModel);
                setResult(RESULT_OK, intent);
                finish();*/
                ToastUtil.show(R.string.air_city_select_error);
            }

            @Override
            public void onSelectCancel() {

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

        //设置点击重新定位之后的事件监听
        binding.interCityView.setOnLocationListener(new OnLocationListener() {
            @Override
            public void onLocation() {
                //这里模拟定位 两秒后给个随便的定位数据
                binding.interCityView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.interCityView.reBindCurrentCity(new CityModel("广州", "中国", "10000001"));
                    }
                }, 2000);
            }
        });
    }
}
