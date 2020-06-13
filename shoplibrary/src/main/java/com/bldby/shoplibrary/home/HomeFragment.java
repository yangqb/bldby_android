package com.bldby.shoplibrary.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.shoplibrary.adapter.HomeMoreAdapter;
import com.bldby.shoplibrary.adapter.HomeRecommendAdapter;
import com.bldby.shoplibrary.adapter.HomeTodayAdapter;
import com.bldby.shoplibrary.adapter.MainAdapter1;
import com.bumptech.glide.Glide;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.addresspick.AddressPickerUtil;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.adapter.HomeListAdapter;
import com.bldby.shoplibrary.adapter.HomeSeckilAdapter;
import com.bldby.shoplibrary.bean.News;
import com.bldby.shoplibrary.databinding.FragmentHomeBinding;
import com.bldby.shoplibrary.seach.SeachHeaderView;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Route(path = RouteShopConstants.SHOPMAINFirst)
public class HomeFragment extends Basefragment {

    @Autowired()
    public String key;
    private FragmentHomeBinding binding;
    private ArrayList<String> strings;
    private List<News> newsList;
    private SeachHeaderView seachHeaderView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //绑定布局
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(this);
        seachHeaderView = new SeachHeaderView(getActivity(), getLayoutInflater());
        seachHeaderView.onOptionsSelectListener = new AddressPickerUtil.OnOptionsTextSelectListener() {
            @Override
            public void onOptionsSelect(String options1, String options2, String options3) {
                ToastUtil.show(options1 + options2 + options3);
            }
        };
        binding.headers.addView(seachHeaderView.getView());
        return binding.getRoot();
    }


    @Override
    public void initView() {
        newsList = new ArrayList();
        newsList.add(new News("新闻标题1", R.mipmap.a21_03riyong));
        newsList.add(new News("新闻标题2", R.mipmap.a21_04hufu));
        newsList.add(new News("新闻标题3", R.mipmap.a21_05muying));
        //newsList.add(new News("新闻标题4", R.mipmap.home_shoppiingimg));
        //首页banner方法（）
        initBanner();
        //首页飞机，商家，商城，油站方法（）
        initList();
        //秒杀产品方法();
        initseckill();
        //3D画廊效果方法（）
        initsuperposition();
        //更多商家方法（）
        initmore();
        //今日上新方法（）
        inittoday();
        //为你推荐方法()
        initrecommend();
        binding.nesc.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                int height = binding.homeBanner.getHeight();

                if (i1 > height) {
                    seachHeaderView.backBackgroundAlpha.set(1);
                    seachHeaderView.backButton.set(getResources().getColorStateList(R.color.black));
                } else {
                    float i4 = (float) i1 / height;
                    seachHeaderView.backButton.set(getResources().getColorStateList(R.color.white));
                    seachHeaderView.backBackgroundAlpha.set(i4);
                }
            }
        });

    }

    private void initrecommend() {
        binding.homeRecyehgite.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        HomeRecommendAdapter adapter = new HomeRecommendAdapter(newsList);
        binding.homeRecyehgite.setAdapter(adapter);
    }

    private void inittoday() {
        binding.homeRecyseven.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        HomeTodayAdapter adapter = new HomeTodayAdapter(newsList);
        binding.homeRecyseven.setAdapter(adapter);

    }

    private void initmore() {
        binding.homeRecysix.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        HomeMoreAdapter adapter = new HomeMoreAdapter(newsList);
        binding.homeRecysix.setAdapter(adapter);
    }

    private void initsuperposition() {
        MainAdapter1 mainAdapter = new MainAdapter1(newsList);
        binding.homeRecyfive.setLoop();
        binding.homeRecyfive.setAlphaItem(true);
        binding.homeRecyfive.setGreyItem(true);
        binding.homeRecyfive.setHasFixedSize(true);
        binding.homeRecyfive.setAdapter(mainAdapter);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                mainAdapter.is
                if (!binding.homeRecyfive.isFocused()) {
                    if (newsList == null || newsList.size() <= 0) {
                        return;
                    }
                    if (binding.homeRecyfive.getCoverFlowLayout().getSelectedPos() >= newsList.size() - 1) {
                        binding.homeRecyfive.scrollToPosition(0);
                    } else {
                        binding.homeRecyfive.scrollToPosition(binding.homeRecyfive.getCoverFlowLayout().getSelectedPos() + 1);
                    }
                }
                sendEmptyMessageDelayed(0, 2000);
            }
        };
        handler.sendEmptyMessageDelayed(0, 2000);
        mainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.show("sdfsd");

            }
        });
    }

    private void initseckill() {
        binding.homeRecyfour.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        HomeSeckilAdapter adaptertwo = new HomeSeckilAdapter(newsList);
        binding.homeRecyfour.setAdapter(adaptertwo);
    }

    /*
        不可展示页面
     */
    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
    }

    /**
     * 展示页面方法
     */
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    private void initList() {
        //图片集合
        Integer[] integers = {R.mipmap.home_shoppiingimg, R.mipmap.home_shoppingimg, R.mipmap.home_flayimg, R.mipmap.home_oilimg};
        //适配器设置
        binding.homeRecyone.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //适配器
        HomeListAdapter adapter = new HomeListAdapter(Arrays.asList(integers));
        binding.homeRecyone.setAdapter(adapter);
    }

    private void initBanner() {
        binding.homeBanner.setCanLoop(true)
                .setAutoPlay(true)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                .setIndicatorSliderRadius(BannerUtils.dp2px(2.5f))
                .setIndicatorSliderColor(Color.parseColor("#CCCCCC"), Color.parseColor("#6C6D72"))
                .setHolderCreator(HomeFragment.DataViewHolder::new).setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                //onClickBanner(position);
            }
        }).create(newsList);
        binding.homeBanner.startLoop();
    }

    public class DataViewHolder implements ViewHolder<News> {
        private ImageView mImageView;

        @Override
        public int getLayoutId() {
            return R.layout.item_banner;
        }

        @Override
        public void onBind(View itemView, News data, int position, int size) {
            mImageView = itemView.findViewById(R.id.banner_image);
            Glide.with(getActivity()).load(data.getPic()).into(mImageView);
        }
    }


    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }
}