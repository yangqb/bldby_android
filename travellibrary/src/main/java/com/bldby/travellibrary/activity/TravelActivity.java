package com.bldby.travellibrary.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.constants.RouteTravelConstants;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.travellibrary.R;
import com.bldby.travellibrary.activity.adapter.Distance2Adapter;
import com.bldby.travellibrary.activity.adapter.DistanceAdapter;
import com.bldby.travellibrary.activity.model.TravelModel;
import com.bldby.travellibrary.databinding.ActivityTravelBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouteTravelConstants.TRAVELMAIN, extras = RouteLoginConstants.SHOWCHECKLOGIN)
public class TravelActivity extends BaseActivity {


    private ActivityTravelBinding dataBinding;
    private PopupWindow popupWindow;
    private List<String> dinstance = new ArrayList<>();
    private ArrayList<String> strings;
    private ArrayList<String> strings1;
    private String dinstancenumber;
    private String oilnumbersum;
    private String[] kms;
    private String[] split;
    @Override
    public void bindIngView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        dataBinding.setViewmodel(this);
    }

    @Override
    public void initView() {
        dinstance.add("5km");
        dinstance.add("10km");
        dinstance.add("15km");
        dinstance.add("20km");
        dinstance.add("30km");
        dinstance.add("40km");
        strings = new ArrayList<>();
        strings.add("90#");
        strings.add("92#");
        strings.add("95#");
        strings.add("98#");
        strings.add("101#");
        strings1 = new ArrayList<>();
        strings1.add("-40#");
        strings1.add("-35#");
        strings1.add("-30#");
        strings1.add("-20#");
        strings1.add("-10#");
        strings1.add("0#");
        dataBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //团油距离，油号的点击方法（）
        initonclick();
    }

    private void initonclick() {
          dataBinding.distancerela.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String text1 = (String) dataBinding.distance.getText();
                  view = LayoutInflater.from(TravelActivity.this).inflate(
                          R.layout.item_popup_distance, null);
                  popupWindow = new PopupWindow(view,
                          ViewGroup.LayoutParams.MATCH_PARENT,
                          ViewGroup.LayoutParams.WRAP_CONTENT, true);
                 RecyclerView dinstancerecy = view.findViewById(R.id.dinstancerecy);
                  dinstancerecy.setLayoutManager(new GridLayoutManager(TravelActivity.this, 4));
                  DistanceAdapter dadapter = new DistanceAdapter(dinstance);
                  dinstancerecy.setAdapter(dadapter);
                  dadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                      @Override
                      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                          dadapter.chengtextcolor(position);
                          dadapter.notifyDataSetChanged();
                          String dinstancenumber = dinstance.get(position);
                          dataBinding.distance.setText(dinstancenumber);
                          oilnumbersum = (String)dataBinding.oilnumber.getText();
                        /*  pageNo = 1;
                          myoiladapter.setNewData(null);
                          initwork(dinstancenumber, oilnumbersum, false, pageNo);*/
                          popupWindow.dismiss();
                      }
                  });
                  dadapter.chengtextcolor1(text1);
                  popupWindow.showAsDropDown(dataBinding.distancerela);
              }
          });
          dataBinding.oilnumberrela.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String text = (String)dataBinding.oilnumber.getText();
                  view = LayoutInflater.from(TravelActivity.this).inflate(
                          R.layout.item_popup_oilnumber, null);
                  popupWindow = new PopupWindow(view,
                          ViewGroup.LayoutParams.MATCH_PARENT,
                          ViewGroup.LayoutParams.WRAP_CONTENT, true);
                  RecyclerView oilClass = view.findViewById(R.id.oilClass);
                  RecyclerView oilLevel = view.findViewById(R.id.oilLevel);
                  oilClass.setLayoutManager(new GridLayoutManager(TravelActivity.this, 4));
                  Distance2Adapter distanceAdapter = new Distance2Adapter(strings);
                  oilClass.setAdapter(distanceAdapter);
                  distanceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                      @Override
                      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                          distanceAdapter.chengtextcolor(position);
                          distanceAdapter.notifyDataSetChanged();
                          String oilnumbersum = strings.get(position);
                          dataBinding.oilnumber.setText(oilnumbersum);
                          dinstancenumber = (String)dataBinding.distance.getText();
                        /*  pageNo = 1;
                          myoiladapter.setNewData(null);
                          initwork(dinstancenumber, oilnumbersum, false, pageNo);*/
                          popupWindow.dismiss();
                      }
                  });
                  distanceAdapter.chengtextcolor1(text);
                  distanceAdapter.notifyDataSetChanged();
                  oilLevel.setLayoutManager(new GridLayoutManager(TravelActivity.this, 4));
                  Distance2Adapter distanceAdapter1 = new Distance2Adapter(strings1);
                  oilLevel.setAdapter(distanceAdapter1);
                  distanceAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                      @Override
                      public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                          distanceAdapter1.chengtextcolor(position);
                          distanceAdapter1.notifyDataSetChanged();
                          String oilnumbersum = strings1.get(position);
                         dataBinding.oilnumber.setText(oilnumbersum);
                          dinstancenumber = (String)dataBinding.distance.getText();
                        /*  pageNo = 1;
                          myoiladapter.setNewData(null);
                          initwork(dinstancenumber, oilnumbersum, false, pageNo);*/
                          popupWindow.dismiss();
                      }
                  });
                  distanceAdapter1.chengtextcolor1(text);
                  distanceAdapter1.notifyDataSetChanged();
                  popupWindow.showAsDropDown(dataBinding.oilnumberrela);
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