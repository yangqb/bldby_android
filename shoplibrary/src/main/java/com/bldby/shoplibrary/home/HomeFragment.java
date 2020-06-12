package com.bldby.shoplibrary.home;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bldby.shoplibrary.adapter.HomeMoreAdapter;
import com.bldby.shoplibrary.adapter.HomeTodayAdapter;
import com.bldby.shoplibrary.adapter.MainAdapter;
import com.bldby.shoplibrary.adapter.MainAdapter1;
import com.bldby.shoplibrary.bean.Newss;
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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(this);
        SeachHeaderView seachHeaderView = new SeachHeaderView(getActivity());
        seachHeaderView.onOptionsSelectListener = new AddressPickerUtil.OnOptionsTextSelectListener() {
            @Override
            public void onOptionsSelect(String options1, String options2, String options3) {
                ToastUtil.show(options1 + options2 + options3);
            }
        };
        binding.headers.addView(seachHeaderView.getView(getLayoutInflater()));
        return binding.getRoot();
    }


    @Override
    public void initView() {
        newsList = new ArrayList();
        newsList.add(new News("新闻标题1", R.mipmap.home_oilimg));
        newsList.add(new News("新闻标题2", R.mipmap.home_bannerimg));
        newsList.add(new News("新闻标题3", R.mipmap.home_flayimg));
        newsList.add(new News("新闻标题4", R.mipmap.home_shoppiingimg));
        //首页banner方法（）
        initBanner();
        //首页飞机，商家，商城，油站方法（）
        initList();
        //秒杀产品方法();
        initseckill();
        //图片叠加显示方法（）
        initsuperposition();
        //更多商家方法（）
        initmore();
        //今日上新方法（）
        inittoday();
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
        strings = new ArrayList<>();
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591807153325&di=5e2685ad705776197e74e26282d59b14&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F14%2F75%2F01300000164186121366756803686.jpg");
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591807153325&di=aae894d67911c5b8d30c534e3fa8473c&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg");
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591807153324&di=170de1efc18fdee973729871276f66de&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F56%2F12%2F01300000164151121576126282411.jpg");
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591807153324&di=8853ec3bab77fae766c186ee96ca34be&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F64%2F76%2F20300001349415131407760417677.jpg");
        strings.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591807153324&di=438e1cf9af20ad28f224332f87a24565&imgtype=0&src=http%3A%2F%2Fpic3.16pic.com%2F00%2F01%2F11%2F16pic_111395_b.jpg");
        binding.homeRecyfive.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), CarouselLayoutManager.HORIZONTAL, false);
//        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        MainAdapter1 mainAdapter = new MainAdapter1(strings);

        binding.homeRecyfive.setLayoutManager(layoutManager);
        binding.homeRecyfive.setHasFixedSize(true);
        binding.homeRecyfive.setAdapter(mainAdapter);
        binding.homeRecyfive.addOnScrollListener(new CenterScrollListener());
        binding.homeRecyfive.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
//                if (parent.getChildAdapterPosition(view) != 0) {
//                    outRect.left = 800;
//                }
//                if (parent.getChildAdapterPosition(view) != (strings.size() - 1)) {
//                    outRect.right = -800;
//                }
            }

            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);

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
        List<Newss> newsList = new ArrayList();
        newsList.add(new Newss("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3091499400,3624463792&fm=26&gp=0.jpg"));
        newsList.add(new Newss("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2672260965,918157271&fm=26&gp=0.jpg"));
        newsList.add(new Newss("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1181030711,3656844490&fm=26&gp=0.jpg"));
        newsList.add(new Newss("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=358521062,3376388299&fm=26&gp=0.jpg"));
        newsList.add(new Newss("https://t9.baidu.com/it/u=48721713,2194094744&fm=193"));
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

    public class DataViewHolder implements ViewHolder<Newss> {
        private ImageView mImageView;

        @Override
        public int getLayoutId() {
            return R.layout.item_banner;
        }

        @Override
        public void onBind(View itemView, Newss data, int position, int size) {
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