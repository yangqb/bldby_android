package com.bldby.airticket.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.airticket.AirConstants;
import com.bldby.airticket.R;
import com.bldby.airticket.databinding.ActivityPlaneCalendarBinding;
import com.bldby.airticket.view.CustomPainter;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.necer.calendar.BaseCalendar;
import com.necer.entity.CalendarDate;
import com.necer.entity.Lunar;
import com.necer.enumeration.MultipleNumModel;
import com.necer.listener.OnCalendarChangedListener;
import com.necer.listener.OnCalendarMultipleChangedListener;
import com.necer.utils.CalendarUtil;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/17
 * time: 17:06
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.SELECTDATE)
public class AirCalendarActivity extends BaseUiActivity {
    private ActivityPlaneCalendarBinding binding;
    public String selectData;

    @Autowired
    public String goDate;
    @Autowired
    public String comeDate;
    @Autowired
    public int searchType;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityPlaneCalendarBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        setRightText(getString(R.string.air_date_select_ok));
        CustomPainter innerPainter = new CustomPainter(binding.miui10Calendar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// HH:mm:ss
        Calendar rightNow = Calendar.getInstance();
        Calendar rightNow2 = Calendar.getInstance();
        rightNow2.add(Calendar.YEAR, 1);
        binding.miui10Calendar.setDateInterval(simpleDateFormat.format(rightNow.getTime()), simpleDateFormat.format(rightNow2.getTime()));//初始化日期选择范围
        //当前时间 加10天
        rightNow.add(Calendar.DAY_OF_YEAR, 2);
        //new SimgpleDateFormat 进行格式化
        //利用Calendar的getTime方法，将时间转化为Date对象
        //利用SimpleDateFormat对象 把Date对象格式化
        if (searchType == 2 || searchType == 3) {

            binding.miui10Calendar.getAllSelectDateList().remove(0);
            binding.miui10Calendar.setMultipleNum(2, MultipleNumModel.FULL_REMOVE_FIRST);
            List<LocalDate> localDateList = new ArrayList<>();
            localDateList.add(new LocalDate(goDate));
            localDateList.add(new LocalDate(comeDate));
            binding.miui10Calendar.getAllSelectDateList().addAll(localDateList); //设置默认选中多个日期
        } else {
            binding.miui10Calendar.setMultipleNum(1, MultipleNumModel.FULL_REMOVE_FIRST);
            binding.miui10Calendar.getAllSelectDateList().remove(0);
            binding.miui10Calendar.getAllSelectDateList().add(new LocalDate(goDate));
        }


        //InnerPainter innerPainter = (InnerPainter) miui10Calendar.getCalendarPainter();
        // innerPainter.setPointList(pointList);

        /*Map<String, String> strMap = new HashMap<>();
        strMap.put("2019-01-25", "");
        strMap.put("2019-01-23", "");
        strMap.put("2019-01-24", "");
        innerPainter.setReplaceLunarStrMap(strMap);*/

        /*Map<String, Integer> colorMap = new HashMap<>();
        colorMap.put("2019-08-25", Color.RED);

        colorMap.put("2019-08-5", Color.parseColor("#000000"));
        innerPainter.setReplaceLunarColorMap(colorMap);*/

/*
        List<String> holidayList = new ArrayList<>();
        holidayList.add("2019-7-20");
        holidayList.add("2019-7-21");
        holidayList.add("2019-7-22");

        List<String> workdayList = new ArrayList<>();
        workdayList.add("2019-7-23");
        workdayList.add("2019-7-24");
        workdayList.add("2019-7-25");*/

        //innerPainter.setLegalHolidayList(holidayList, workdayList);
        binding.miui10Calendar.setCalendarPainter(innerPainter);

        binding.miui10Calendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate) {
                binding.tvResult.setText(year + "年" + month + "月" + "   当前页面选中 " + localDate);
                // Log.d(TAG, "   当前页面选中 " + localDate);

                if (localDate != null) {
                    CalendarDate calendarDate = CalendarUtil.getCalendarDate(localDate);
                    Lunar lunar = calendarDate.lunar;
                    binding.tvData.setText(localDate.toString("yyyy年MM月dd日"));
                    binding.tvDesc.setText(lunar.chineseEra + lunar.animals + "年" + lunar.lunarMonthStr + lunar.lunarDayStr);
                    selectData = localDate.toString();
                } else {
                    binding.tvData.setText("");
                    binding.tvDesc.setText("");
                    selectData = null;
                }
            }
        });

        binding.miui10Calendar.setOnCalendarMultipleChangedListener(new OnCalendarMultipleChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, List<LocalDate> currectSelectList, List<LocalDate> allSelectList) {

                binding.tvResult.setText(year + "年" + month + "月" + " 当前页面选中 " + currectSelectList.size() + "个  总共选中" + allSelectList.size() + "个");
                //Log.d(TAG, year + "年" + month + "月");
                //Log.d(TAG, "当前页面选中：：" + currectSelectList);
                //Log.d(TAG, "全部选中：：" + allSelectList);
               /* if (currectSelectList.size() > 2) {
                    miui10Calendar.getAllSelectDateList().remove(0);
                }*/

                if (allSelectList.size() == 2) {
                    if (allSelectList.get(0).isBefore(allSelectList.get(1))) {
                        selectData = allSelectList.get(0).toString() + "=" + allSelectList.get(1).toString();
                    } else {
                        selectData = allSelectList.get(1).toString() + "=" + allSelectList.get(0).toString();
                    }
                } else if (allSelectList.size() == 1) {
                    selectData = allSelectList.get(0).toString() + "=" + allSelectList.get(0).toString();
                } else {
                    selectData = null;
                }
            }
        });
    }

    @Override
    public void onClickRight(View view) {
        if (selectData == null) {
            ToastUtil.show(R.string.air_date_no_select);
        } else {
            Intent intent = new Intent();
            intent.putExtra(AirConstants.SELECT_DATE, selectData);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}
