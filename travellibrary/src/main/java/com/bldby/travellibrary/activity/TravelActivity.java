package com.bldby.travellibrary.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.app.util.MyPoint;
import com.bldby.baselibrary.constants.RouteLoginConstants;
import com.bldby.baselibrary.constants.RouteTravelConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.UserInfo;
import com.bldby.travellibrary.R;
import com.bldby.travellibrary.activity.adapter.Distance2Adapter;
import com.bldby.travellibrary.activity.adapter.DistanceAdapter;
import com.bldby.travellibrary.activity.adapter.MyOilAdapter;
import com.bldby.travellibrary.activity.model.OilListBean;
import com.bldby.travellibrary.activity.model.TravelModel;
import com.bldby.travellibrary.activity.request.OilStationsUrlRequest;
import com.bldby.travellibrary.databinding.ActivityTravelBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
    private String longitude;
    private String latitude;
    private int pageNo = 1;
    private String token;
    private int pagenum = 20;
    private MyOilAdapter myoiladapter;
    private boolean isLoadMore;

    //private MineInfoModel userInfo;
    @Override
    public void bindIngView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        dataBinding.setViewmodel(this);
    }

    @Override
    public void initView() {
        dataBinding.titleName.setText(R.string.oil_name);
        String userId = AccountManager.getInstance().getUserId();
        String token = AccountManager.getInstance().getToken();
        Log.i("cccccccc", "initView: "+userId+"()"+token);
        dataBinding.oilOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(RouteTravelConstants.TRAVELDETAIL);
               // start(RouteTravelConstants.TRAVELORDER);

            }
        });
        dataBinding.travBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        View mEmptyView = View.inflate(TravelActivity.this, R.layout.view_common_nodata, null);
        ImageView img_empty = (ImageView) mEmptyView.findViewById(R.id.img_empty);
        img_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        //团油距离，油号的点击弹框方法（）
        initonclick();
        //赋值方法
        initoil();
        //smart刷新加载方法（）
        initswipeLayout();

        //String string = SPUtils.getString(TravelActivity.this, Constants.USER_DATA);
        this.token = AccountManager.getInstance().getToken();
     /*   myoiladapter = new MyOilAdapter(null);
        myoiladapter.setEmptyView(mEmptyView);
        dataBinding.oilrecy.setAdapter(myoiladapter);
        myoiladapter.notifyDataSetChanged();*/
    }

    private void initswipeLayout() {

        dataBinding.swipeLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                //延迟3秒关闭
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                }, 3000);

            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                //2，刷新完成关闭，正常情况是请求接口完成关闭
                //3,如果需要在网络请求结束后关闭，则调用
//                smart.finishRefresh();
//                smart.finishLoadMore();
                refreshLayout.finishRefresh();
                refreshLayout.setNoMoreData(true);
            }
        });
    }

    private void initoil() {
        dinstancenumber = (String) dataBinding.distance.getText();
        oilnumbersum = (String) dataBinding.oilnumber.getText();
        if (RouteTravelConstants.mPoint != null) {
            MyPoint myPoint = RouteTravelConstants.mPoint;
            longitude = myPoint.longitude + "";
            latitude = myPoint.latitude + "";
        }
        initwork(dinstancenumber, oilnumbersum, true, pageNo);
        
    }

    private void initwork(String dinstancenumber, String oilnumbersum, boolean isLoadM, int pageNo) {
        kms = dinstancenumber.split("km");
        split = oilnumbersum.split("#");
        dataBinding.oilrecy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        OilStationsUrlRequest oilStationsRequest = new OilStationsUrlRequest("116.28", "39.85", Integer.valueOf(kms[0]), Integer.valueOf(split[0]), pagenum, pageNo);
        /*oilStationsRequest.userId = AccountManager.getInstance().getUserId();
        oilStationsRequest.accessToken = AccountManager.getInstance().getToken();*/
        oilStationsRequest.isShowLoading = true;
        oilStationsRequest.call(new ApiCallBack<List<OilListBean>>() {
            @Override
            public void onAPIResponse(List<OilListBean> response) {
                Log.i("cccccccc", "initView: "+response.get(1).getGasName());
                myoiladapter = new MyOilAdapter(response);
                dataBinding.oilrecy.setAdapter(myoiladapter);
                myoiladapter.notifyDataSetChanged();
             /*   if (response != null && response.size() > 0) {
                    if (isLoadM) {
                        myoiladapter.addData(response);
                    } else {
                        myoiladapter.setNewData(response);
                    }
                    if (!isLoadMore) {
                      //  swipeLayout.finishRefresh();
                        myoiladapter.notifyDataSetChanged();
                    } else {
                        myoiladapter.notifyDataSetChanged();
                        //swipeLayout.finishLoadMore();
                    }
                    myoiladapter.chengtextcolor1(oilnumbersum);
                    myoiladapter.notifyDataSetChanged();
                    myoiladapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            OilListBean oilListBean = myoiladapter.getData().get(position);
                            if (token == null || TextUtils.isEmpty(token)) {
                                *//*Intent intent = new Intent(TravelHomeActivity.this, LoginActivity.class);
                                startActivity(intent);*//*
                            } else {
                              //  TraveDetailActivity.toTraveDetailActivity(TravelHomeActivity.this, oilListBean);
                                *//**
                                 * 是否是会员判断
                                 *//*
                              *//*  if (UserInfoUtils.getUserInfo(TravelHomeActivity.this).getAccountType() != 0) {
                                } else {
                                    View inflate = getLayoutInflater().inflate(R.layout.oil_dialog_item, null);
                                    TextView dilagimagedimiss = inflate.findViewById(R.id.dilagimagedimiss);
                                    TextView dilagimageupdate = inflate.findViewById(R.id.dilagimageupdate);
                                    MyDialog myDialog = new MyDialog(TravelHomeActivity.this, 0, 0, inflate, R.style.DialogTheme);
                                    myDialog.setCancelable(true);
                                    dilagimagedimiss.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            myDialog.dismiss();
                                        }
                                    });
                                    dilagimageupdate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(TravelHomeActivity.this, VipActivity.class);
                                            intent.putExtra(VipActivity.MINE_INFO, userInfo);
                                            startActivity(intent);
                                            myDialog.dismiss();
                                        }
                                    });
                                    myDialog.show();
                                }*//*
                            }
                        }
                    });
                } else {
                    if (!isLoadMore) {
                        //swipeLayout.finishRefresh(true);
                    } else {
                       // swipeLayout.finishLoadMore(true);
                    }
                }*/
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {
                Log.i("cccccccc", "initView111111: "+errorCode);
                if (!isLoadMore) {
                    //swipeLayout.finishRefresh(false);
                } else {
                    //swipeLayout.finishLoadMore(false);
                }
            }
        });
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