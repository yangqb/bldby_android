package com.bldby.travellibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.app.util.SPUtils;
import com.bldby.baselibrary.constants.RouteTravelConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.util.StringUtils;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.loginlibrary.model.UserInfo;
import com.bldby.travellibrary.R;
import com.bldby.travellibrary.activity.adapter.Distance1Adapter;
import com.bldby.travellibrary.activity.adapter.DistanceGunAdapter;
import com.bldby.travellibrary.activity.adapter.DistanceOilInfoAdapter;
import com.bldby.travellibrary.activity.model.OilListBean;
import com.bldby.travellibrary.activity.model.OilStationsDetailBean;
import com.bldby.travellibrary.activity.request.OilStationsDetailUrlRequest;
import com.bldby.travellibrary.activity.request.OilTimeRequest;
import com.bldby.travellibrary.databinding.ActivityOilDeiltaBinding;
import com.bldby.travellibrary.databinding.ActivityOilOrderBinding;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Route(path = RouteTravelConstants.TRAVELDETAIL)
public class OilDeiltaActivity extends BaseActivity {


    private ActivityOilDeiltaBinding oilDeiltaBinding;
    private List<OilStationsDetailBean> response;

    //油类
    private Distance1Adapter distanceAdapter;
    //油号
    private DistanceOilInfoAdapter distanceAdapter1;

    private DistanceGunAdapter distanceAdapter2;

    private int position;
    private OilListBean oilListBean;

    @Override
    public void bindIngView() {
        String gson = getIntent().getStringExtra("GSON");
        Gson gson1 = new Gson();
        oilListBean = gson1.fromJson(gson, OilListBean.class);
        oilDeiltaBinding = DataBindingUtil.setContentView(this, R.layout.activity_oil_deilta);
        oilDeiltaBinding.setViewmodel(this);
    }

    public String n = "", n1 = "", n2 = "";

    public static void toTraveDetailActivity(Context appCompatActivity, OilListBean oilListBean) {
        String s = new Gson().toJson(oilListBean);
        Intent intent = new Intent(appCompatActivity, OilDeiltaActivity.class);
        intent.putExtra("GSON", s);
        appCompatActivity.startActivity(intent);
    }

    @Override
    public void initView() {
        oilDeiltaBinding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (oilListBean != null) {
            oilDeiltaBinding.submit.setEnabled(false);
            oilDeiltaBinding.name.setText(oilListBean.getGasName());
            Glide.with(this)
                    .load(oilListBean.getGasLogoSmall())
                    .into(oilDeiltaBinding.imageView8);
            oilDeiltaBinding.value.setText("0");
            oilDeiltaBinding.downValue.setText("￥0");
            oilDeiltaBinding.address.setText(oilListBean.getGasAddress());
            oilDeiltaBinding.str.setText(oilListBean.getDistanceStr());

        }

        distanceAdapter = new Distance1Adapter(null);
        oilDeiltaBinding.oilClass.setLayoutManager(new GridLayoutManager(OilDeiltaActivity.this, 3));
        oilDeiltaBinding.oilClass.setAdapter(distanceAdapter);
        distanceAdapter1 = new DistanceOilInfoAdapter(null);
        oilDeiltaBinding.oilLevel.setLayoutManager(new GridLayoutManager(this, 3));
        oilDeiltaBinding.oilLevel.setAdapter(distanceAdapter1);
        distanceAdapter2 = new DistanceGunAdapter(null);
        oilDeiltaBinding.gun.setLayoutManager(new GridLayoutManager(this, 3));
        oilDeiltaBinding.gun.setAdapter(distanceAdapter2);
        OilStationsDetailUrlRequest oilStationsDetailRequest = new OilStationsDetailUrlRequest();
        oilStationsDetailRequest.isShowLoading = true;
        oilStationsDetailRequest.gasIds =oilListBean.getGasId();
        oilStationsDetailRequest.phone = AccountManager.getInstance().getUserPhone();
        oilStationsDetailRequest.userId = AccountManager.getInstance().getUserId();
        oilStationsDetailRequest.accessToken = AccountManager.getInstance().getToken();
        oilStationsDetailRequest.call(new ApiLifeCallBack<List<OilStationsDetailBean>>() {

            @Override
            public void onStart() {
                showloadDialog();
            }

            @Override
            public void onFinsh() {
                goneloadDialog();
            }

            @Override
            public void onAPIResponse(List<OilStationsDetailBean> response) {
                OilDeiltaActivity.this.response = response;
                if (response != null && response.size() > 0) {
                    ArrayList<String> oilPriceArrList = new ArrayList<>();
                    for (OilStationsDetailBean oilPriceList : response) {
                        oilPriceArrList.add(oilPriceList.getOilTypeString());
                    }
                    distanceAdapter.setNewData(oilPriceArrList);
                    n = oilPriceArrList.get(0);
                    distanceAdapter.chengtextcolor(0);
                    distanceAdapter.notifyDataSetChanged();
                    List<OilStationsDetailBean.OilInfoBean> oilInfo = response.get(0).getOilInfo();

                    if (oilInfo != null && oilInfo.size() > 0) {

                        distanceAdapter1.setNewData(oilInfo);
                        n1 = oilInfo.get(0).getOilName();
                        distanceAdapter1.chengtextcolor(0);
                        distanceAdapter1.notifyDataSetChanged();


                        OilStationsDetailBean.OilInfoBean oilInfoBean = oilInfo.get(0);
                        oilDeiltaBinding.value.setText(oilInfoBean.getPriceYfq() + "");
                        double priceYfq = oilInfoBean.getPriceYfq();
                        double priceOfficial = oilInfoBean.getPriceOfficial();
                        double v = priceOfficial - priceYfq;
                        String format = new DecimalFormat("0.00").format(v);
                        oilDeiltaBinding.downValue.setText("￥" + format);

                        List<OilStationsDetailBean.OilInfoBean.GunNosBean> gunNos = oilInfoBean.getGunNos();
                        if (gunNos != null && gunNos.size() > 0) {
                            distanceAdapter2.setNewData(gunNos);
                            distanceAdapter2.chengtextcolor(0);
                            distanceAdapter2.notifyDataSetChanged();
                            n2 = gunNos.get(0).getGunNo();
                            oilDeiltaBinding.submit.setEnabled(true);

                        }
                        Log.e("TAG", "onItemClick: " + n + n1 + n2);

                    }
                }
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });


        distanceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OilDeiltaActivity.this.position = position;
                n = distanceAdapter.getData().get(position);
                distanceAdapter.chengtextcolor(position);
                distanceAdapter.notifyDataSetChanged();
                distanceAdapter2.chengtextcolor(-1);
                distanceAdapter2.notifyDataSetChanged();

                List<OilStationsDetailBean.OilInfoBean> oilInfo = response.get(position).getOilInfo();
                if (oilInfo != null && oilInfo.size() > 0) {
                    distanceAdapter1.setNewData(oilInfo);
                    distanceAdapter1.chengtextcolor(0);
                    distanceAdapter1.notifyDataSetChanged();
                    n1 = distanceAdapter1.getData().get(0).getOilName();
                }

                if (oilInfo != null && oilInfo.size() > 0) {
                    OilStationsDetailBean.OilInfoBean oilInfoBean = oilInfo.get(0);
                    List<OilStationsDetailBean.OilInfoBean.GunNosBean> gunNos = oilInfoBean.getGunNos();
                    if (gunNos != null && gunNos.size() > 0) {
                        distanceAdapter2.setNewData(gunNos);
                        distanceAdapter2.chengtextcolor(0);
                        distanceAdapter2.notifyDataSetChanged();
                        n2 = gunNos.get(0).getGunNo();
                        oilDeiltaBinding.submit.setEnabled(true);
                        oilDeiltaBinding.value.setText(oilInfoBean.getPriceYfq() + "");
                        double priceYfq = oilInfoBean.getPriceYfq();
                        double priceOfficial = oilInfoBean.getPriceOfficial();
                        double v = priceOfficial - priceYfq;
                        String format = new DecimalFormat("0.00").format(v);
                        oilDeiltaBinding.downValue.setText("￥" + format);


                    }
                }

                Log.e("TAG", "onItemClick: " + n + n1 + n2);

            }
        });
        distanceAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!StringUtils.isEmpty(n)) {
                    oilDeiltaBinding.submit.setEnabled(false);

                    n1 = distanceAdapter1.getData().get(position).getOilName();
                    distanceAdapter1.chengtextcolor(position);
                    distanceAdapter1.notifyDataSetChanged();
                    OilStationsDetailBean.OilInfoBean oilInfoBean1 = distanceAdapter1.getData().get(position);

                    oilDeiltaBinding.value.setText(oilInfoBean1.getPriceYfq() + "");
                    double priceYfq = oilInfoBean1.getPriceYfq();
                    double priceOfficial = oilInfoBean1.getPriceOfficial();
                    double v = priceOfficial - priceYfq;
                    oilDeiltaBinding.downValue.setText("￥" + new DecimalFormat("0.00").format(v));

                    List<OilStationsDetailBean.OilInfoBean> oilInfo = response.get(OilDeiltaActivity.this.position).getOilInfo();
                    if (oilInfo != null && oilInfo.size() > 0) {
                        OilStationsDetailBean.OilInfoBean oilInfoBean = oilInfo.get(position);
                        List<OilStationsDetailBean.OilInfoBean.GunNosBean> gunNos = oilInfoBean.getGunNos();
                        if (gunNos != null && gunNos.size() > 0) {
                            distanceAdapter2.setNewData(gunNos);
                            distanceAdapter2.chengtextcolor(0);
                            distanceAdapter2.notifyDataSetChanged();
                            n2 = gunNos.get(0).getGunNo();
                            oilDeiltaBinding.submit.setEnabled(true);

                        }
                    }


                }
            }
        });


        distanceAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!StringUtils.isEmpty(n1)) {
                    n2 = distanceAdapter2.getData().get(position).getGunNo();
                    distanceAdapter2.chengtextcolor(position);
                    distanceAdapter2.notifyDataSetChanged();
                    oilDeiltaBinding.submit.setEnabled(true);

                }
                Log.e("TAG", "init: " + n2 + ".." + n1 + ".." + ".." + n);
            }
        });
        oilDeiltaBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(n1) && !StringUtils.isEmpty(n2)) {
                    OilTimeRequest oilTimeRequest = new OilTimeRequest();
                    oilTimeRequest.phone = AccountManager.getInstance().getUserPhone();
                    oilTimeRequest.platformId = "98647229";
                    oilTimeRequest.app_key = "appm_api_h598647229";
                    oilTimeRequest.timestamp = System.currentTimeMillis() + "";
                    oilTimeRequest.isShowLoading = true;
                    oilTimeRequest.call(new ApiCallBack<String>() {
                        @Override
                        public void onAPIResponse(String response) {
                            String u = "https://open.czb365.com/redirection/todo/?platformType=98647229&authCode=" + response + "&gasId=" + oilListBean.getGasId() + "&gunNo=" + n2;
//                            http://test-mcs.czb365.com/services/v3/begin/getSecretCode?platformId=98647229&phone=13671192850
                            Web1Activity.toWeb1Activity(OilDeiltaActivity.this, u);

                        }

                        @Override
                        public void onAPIError(int errorCode, String errorMsg) {
                            ToastUtil.show("网络错误");

                        }
                    });
                }
//
            }
        });
    }

    //1.百度地图包名
    public static final String BAIDUMAP_PACKAGENAME = "com.baidu.BaiduMap";
    //2.高德地图包名
    public static final String AUTONAVI_PACKAGENAME = "com.autonavi.minimap";
    private List<String> mapList = new ArrayList<>();

    public List<String> appHasMap(Context context) {
        if (isAvilible(context, BAIDUMAP_PACKAGENAME)) {
            mapList.add("百度地图");
        }
        if (isAvilible(context, AUTONAVI_PACKAGENAME)) {
            mapList.add("高德地图");
        }
        return mapList;
    }

    public void onClickedTOMap(View view) {
        if (mapList.size() <= 0) {
            ToastUtil.show("请先安装百度地图或高德地图");
            return;
        } else {
           /* new XPopup.Builder(this)
                    .asCustom(new CustomRefundView(TraveDetailActivity.this)
                            .setData(mapList)
                            .setOnItemClickListener(new CustomRefundView.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    if ("百度地图".equals(mapList.get(position))) {
                                        Intent i1 = new Intent();
                                        i1.setData(Uri.parse("baidumap://map/geocoder?src=andr.baidu.openAPIdemo&address=" + oilListBean.getGasAddress()));
                                        startActivity(i1);
                                    } else if ("高德地图".equals(mapList.get(position))) {
                                        Intent intent_gdmap = new Intent();
                                        intent_gdmap.setAction("android.intent.action.VIEW");
                                        intent_gdmap.setPackage("com.autonavi.minimap");
                                        intent_gdmap.addCategory("android.intent.category.DEFAULT");
                                        intent_gdmap.setData(Uri.parse("androidamap://poi?sourceApplication=com.feitianzhu.huangliwo&keywords=" + oilListBean.getGasAddress() + oilListBean.getGasName() + "&dev=0"));
                                        startActivity(intent_gdmap);
                                    }
                                }
                            }))
                    .show();*/
        }
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}