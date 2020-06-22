package com.bldby.airticket.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.AirConstants;
import com.bldby.airticket.R;
import com.bldby.airticket.databinding.ActivityAirMainBinding;
import com.bldby.airticket.model.CityModel;
import com.bldby.airticket.model.CustomFightCityInfo;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.util.DateUtil;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.UserInfo;
import com.gyf.immersionbar.ImmersionBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Route(path = RouteAirConstants.MAIN)
public class AirMainActivity extends BaseActivity {
    private ActivityAirMainBinding dataBinding;
    private String goDate = "";
    private String comeDate = "";
    private String tvStartCity = "北京";
    private String tvEndCity = "上海";
    private String depCode = "PEK";
    private String arrCode = "SHA";
    private String depCodeData = "PEK";
    private String arrCodeData = "SHA";
    private int startCityType;
    private int endCityType;
    private int searchType = 0; //0国内1国际2国内往返3国际往返
    private boolean reversal;

    @Override
    public void bindIngView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_air_main);
        dataBinding.setViewModel(this);
    }

    @Override
    public ImmersionBar getOpenImmersionBar() {
        return ImmersionBar.with(this)
                .fitsSystemWindows(false)
                .statusBarDarkFont(true, 0.2f)
                .statusBarColor(R.color.transparent);
    }

    @Override
    public void initView() {
        UserInfo userInfo = AccountManager.getInstance().getUserInfo();
        userInfo.loginInfo.accessToken = "8e504efd9c7e4587ababaa5f4940c332";
        userInfo.loginInfo.userId = "275789";
        AccountManager.getInstance().updataLoginInfo(userInfo);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        goDate = simpleDateFormat.format(date);
        dataBinding.startDate.setText(goDate);
        dataBinding.startWeek.setText(DateUtil.strToWeek("yyyy-MM-dd", goDate));
    }


    public void onClickBtn(View view) {
        if (view.getId() == R.id.reversalCity) {
            if (reversal) {
                dataBinding.arrCityName.setText(tvEndCity);
                dataBinding.depCityName.setText(tvStartCity);
                depCodeData = depCode;
                arrCodeData = arrCode;
            } else {
                dataBinding.depCityName.setText(tvEndCity);
                dataBinding.arrCityName.setText(tvStartCity);
                depCodeData = arrCode;
                arrCodeData = depCode;
            }
            reversal = !reversal;
        } else if (view.getId() == R.id.search) {
            //搜索
            if (TextUtils.isEmpty(arrCode) || TextUtils.isEmpty(depCode)) {
                ToastUtil.show(R.string.air_city_no_select);
                return;
            }
            if (searchType == 1 || searchType == 3) {
                ToastUtil.show(R.string.air_city_select_error);
                return;
            }
            CustomFightCityInfo customFightCityInfo = new CustomFightCityInfo();
            customFightCityInfo.depCityName = dataBinding.depCityName.getText().toString();
            customFightCityInfo.depAirPortCode = depCodeData;
            customFightCityInfo.goDate = goDate;
            customFightCityInfo.arrCityName = dataBinding.arrCityName.getText().toString();
            customFightCityInfo.arrAirPortCode = arrCodeData;
            customFightCityInfo.backDate = comeDate;
            if (searchType == 2 || searchType == 3) {
                startWith(RouteAirConstants.AIRSEARCHFLIGHT2).withInt("searchType", searchType).withSerializable("flightInfo", customFightCityInfo).navigation();
            } else {
                startWith(RouteAirConstants.AIRSEARCHFLIGHT).withInt("searchType", searchType).withSerializable("flightInfo", customFightCityInfo).navigation();
            }
        } else if (view.getId() == R.id.ll_order) {
            //机票订单
              /* token = SPUtils.getString(this, Constant.SP_ACCESS_TOKEN);
                if (token == null || TextUtils.isEmpty(token)) {
                    intent = new Intent(PlaneHomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(PlaneHomeActivity.this, PlaneOrderListActivity.class);
                startActivity(intent);*/
        } else if (view.getId() == R.id.ll_select_check_in) {
            //值机
               /*intent = new Intent(PlaneHomeActivity.this, LazyWebActivity.class);
                intent.putExtra(Constant.URL, "http://touch.qunar.com/flight/seat/?isDistribution=true&theme=pure&distributorKey=8e0e4887-6aee-491a-a829-19bb72b47162&channel=web.batour.demo");
                intent.putExtra(Constant.H5_TITLE, "值机选座");
                startActivity(intent);*/
        }
    }

    /*
     * 选择机票(单程，往返)具体的searchType类型由选择的城市决定
     * */
    public void onClickAirType(View view) {
        if (view.getId() == R.id.btn_domestic) {
            //国内
            dataBinding.btnDomestic.setTextColor(getResources().getColor(R.color.color_333333));
            dataBinding.btnInternational.setTextColor(getResources().getColor(R.color.color_666666));
            dataBinding.btnComeGo.setTextColor(getResources().getColor(R.color.color_666666));
            dataBinding.line1.setBackgroundColor(getResources().getColor(R.color.color_fed228));
            dataBinding.line2.setBackgroundColor(getResources().getColor(R.color.white));
            dataBinding.line3.setBackgroundColor(getResources().getColor(R.color.white));
            dataBinding.llComeDate.setVisibility(View.INVISIBLE);
            searchType = 0;
        } else if (view.getId() == R.id.btn_international) {
            //国际
            dataBinding.btnDomestic.setTextColor(getResources().getColor(R.color.color_666666));
            dataBinding.btnInternational.setTextColor(getResources().getColor(R.color.color_333333));
            dataBinding.btnComeGo.setTextColor(getResources().getColor(R.color.color_666666));
            dataBinding.line1.setBackgroundColor(getResources().getColor(R.color.white));
            dataBinding.line2.setBackgroundColor(getResources().getColor(R.color.color_fed228));
            dataBinding.line3.setBackgroundColor(getResources().getColor(R.color.white));
            dataBinding.llComeDate.setVisibility(View.INVISIBLE);
            searchType = 1;
        } else if (view.getId() == R.id.btn_come_go) {
            //往返
            String[] strings = goDate.split("-");
            Calendar calendar = Calendar.getInstance();//创建一个实例
            calendar.set(Integer.valueOf(strings[0]), Integer.valueOf(strings[1]) - 1, Integer.valueOf(strings[2]));//实例化一个Calendar。 年、月、日、时、分、秒
            calendar.add(Calendar.DAY_OF_YEAR, 2);//给当前日期加上指定天数，这里加答的是2天
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// HH:mm:ss
            if (TextUtils.isEmpty(comeDate)) {
                comeDate = simpleDateFormat.format(calendar.getTime());
                dataBinding.endDate.setText(comeDate);
                dataBinding.endWeek.setText(DateUtil.strToWeek("yyyy-MM-dd", comeDate));
            }
            dataBinding.btnDomestic.setTextColor(getResources().getColor(R.color.color_666666));
            dataBinding.btnInternational.setTextColor(getResources().getColor(R.color.color_666666));
            dataBinding.btnComeGo.setTextColor(getResources().getColor(R.color.color_333333));
            dataBinding.line1.setBackgroundColor(getResources().getColor(R.color.white));
            dataBinding.line2.setBackgroundColor(getResources().getColor(R.color.white));
            dataBinding.line3.setBackgroundColor(getResources().getColor(R.color.color_fed228));
            searchType = 2;
            dataBinding.llComeDate.setVisibility(View.VISIBLE);
        }
    }

    /*
     * 出行城市选择
     * */
    public void onSelectCity(View view) {
        if (view.getId() == R.id.depCityName) {
            start(RouteAirConstants.SELECTCITY, AirConstants.REQUEST_DEP_CITY_CODE);

        } else if (view.getId() == R.id.arrCityName) {
            start(RouteAirConstants.SELECTCITY, AirConstants.REQUEST_ARR_CITY_CODE);

        }
    }

    /*
     * 出行日期选择
     * */
    public void onSelectDate(View view) {
        startWith(RouteAirConstants.SELECTDATE)
                .withString("goDate", goDate)
                .withString("comeDate", comeDate)
                .withInt("searchType", searchType)
                .navigation(AirMainActivity.this, AirConstants.REQUEST_DATE__CODE);
    }

    @Override
    public void start(String url) {
        super.start(url);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AirConstants.REQUEST_DATE__CODE) {
                if (searchType == 2 || searchType == 3) {
                    goDate = data.getStringExtra(AirConstants.SELECT_DATE).split("=")[0];
                    comeDate = data.getStringExtra(AirConstants.SELECT_DATE).split("=")[1];
                    dataBinding.startDate.setText(goDate);
                    dataBinding.startWeek.setText(DateUtil.strToWeek("yyyy-MM-dd", goDate));
                    dataBinding.endDate.setText(comeDate);
                    dataBinding.endWeek.setText(DateUtil.strToWeek("yyyy-MM-dd", comeDate));
                } else {
                    goDate = data.getStringExtra(AirConstants.SELECT_DATE).split("=")[0];
                    dataBinding.startDate.setText(goDate);
                    dataBinding.startWeek.setText(DateUtil.strToWeek("yyyy-MM-dd", goDate));
                }
            } else {
                if (requestCode == AirConstants.REQUEST_ARR_CITY_CODE) {
                    startCityType = data.getIntExtra(SelectPlaneCityActivity.CITY_TYPE, 0);
                    CityModel cityModel = (CityModel) data.getSerializableExtra(SelectPlaneCityActivity.CITY_DATA);
                    tvEndCity = cityModel.getCityName();
                    dataBinding.arrCityName.setText(tvEndCity);
                    arrCode = cityModel.getExtra().toString();
                    arrCodeData = cityModel.getExtra().toString();
                    if (startCityType == 0 && endCityType == 0) { //国内城市
                        if (searchType == 3) {
                            searchType = 2;
                        } else if (searchType == 1) {
                            searchType = 0;
                        }
                    } else {
                        if (searchType == 2) {
                            searchType = 3;
                        } else if (searchType == 0) {
                            searchType = 1;
                        }
                    }
                } else if (requestCode == AirConstants.REQUEST_DEP_CITY_CODE) {
                    endCityType = data.getIntExtra(SelectPlaneCityActivity.CITY_TYPE, 0);
                    CityModel cityModel = (CityModel) data.getSerializableExtra(SelectPlaneCityActivity.CITY_DATA);
                    tvStartCity = cityModel.getCityName();
                    dataBinding.depCityName.setText(tvStartCity);
                    depCode = cityModel.getExtra().toString();
                    depCodeData = cityModel.getExtra().toString();
                    if (startCityType == 0 && endCityType == 0) { //国内城市
                        if (searchType == 3) {
                            searchType = 2;
                        } else if (searchType == 1) {
                            searchType = 0;
                        }
                    } else {
                        if (searchType == 2) {
                            searchType = 3;
                        } else if (searchType == 0) {
                            searchType = 1;
                        }
                    }
                }
                if (searchType == 0) {
                    dataBinding.btnDomestic.setTextColor(getResources().getColor(R.color.color_333333));
                    dataBinding.btnInternational.setTextColor(getResources().getColor(R.color.color_666666));
                    dataBinding.btnComeGo.setTextColor(getResources().getColor(R.color.color_666666));
                    dataBinding.line1.setBackgroundColor(getResources().getColor(R.color.color_fed228));
                    dataBinding.line2.setBackgroundColor(getResources().getColor(R.color.white));
                    dataBinding.line3.setBackgroundColor(getResources().getColor(R.color.white));
                } else if (searchType == 1) {
                    dataBinding.btnDomestic.setTextColor(getResources().getColor(R.color.color_666666));
                    dataBinding.btnInternational.setTextColor(getResources().getColor(R.color.color_333333));
                    dataBinding.btnComeGo.setTextColor(getResources().getColor(R.color.color_666666));
                    dataBinding.line1.setBackgroundColor(getResources().getColor(R.color.white));
                    dataBinding.line2.setBackgroundColor(getResources().getColor(R.color.color_fed228));
                    dataBinding.line3.setBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    dataBinding.btnDomestic.setTextColor(getResources().getColor(R.color.color_666666));
                    dataBinding.btnInternational.setTextColor(getResources().getColor(R.color.color_666666));
                    dataBinding.btnComeGo.setTextColor(getResources().getColor(R.color.color_333333));
                    dataBinding.line1.setBackgroundColor(getResources().getColor(R.color.white));
                    dataBinding.line2.setBackgroundColor(getResources().getColor(R.color.white));
                    dataBinding.line3.setBackgroundColor(getResources().getColor(R.color.color_fed228));
                }
            }
        }
    }
}