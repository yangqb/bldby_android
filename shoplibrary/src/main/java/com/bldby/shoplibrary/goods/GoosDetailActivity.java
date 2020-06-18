package com.bldby.shoplibrary.goods;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteConstants;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.share.ShareFragment;
import com.bldby.baselibrary.core.share.ShareMenu;
import com.bldby.baselibrary.core.share.ShareUtils;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.baselibrary.core.util.ShareImageUtils;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.bean.News;
import com.bldby.shoplibrary.databinding.ActivityGoosDetailBinding;
import com.bldby.shoplibrary.goods.adapter.AdapterGoodsDetailEvaluate;
import com.bldby.shoplibrary.goods.adapter.AdapterGoodsDetailGetDiscounts;
import com.bldby.shoplibrary.goods.model.BannerViewHolder;
import com.bldby.shoplibrary.goods.model.ShopDetailModel;
import com.bldby.shoplibrary.home.HomeFragment;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;

import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSDETAIL;

@Route(path = SHOPGOODSDETAIL)
public class GoosDetailActivity extends BaseActivity {

    private ActivityGoosDetailBinding dataBinding;
    public ObservableField<ColorStateList> backButton = new ObservableField<ColorStateList>();
    public ObservableField<Drawable> backBackgroundButton = new ObservableField<Drawable>();
    private AdapterGoodsDetailGetDiscounts detailGetDiscounts;
    private AdapterGoodsDetailEvaluate adapterGoodsDetailEvaluate;
    private ShopDetailModel detailModel;
    private ArrayList<String> newsList;

    @Override
    public void bindIngView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_goos_detail);
        dataBinding.setViewmodel(this);
        detailModel = ViewModelProviders.of(this).get(ShopDetailModel.class);
    }

    @Override
    public void initView() {
        backButton.set(getResources().getColorStateList(R.color.white));
        backBackgroundButton.set(getResources().getDrawable(R.color.goods_detail_back));
        ArrayList<String> strings = new ArrayList<>();
        strings.add("满799减40");
        strings.add("满799减40");
        strings.add("满799减40");
        strings.add("满799减40");
        strings.add("满799减40");
        strings.add("满799减40");
        strings.add("满799减40");
        strings.add("满799减40");
        strings.add("满799减40");
        detailGetDiscounts = new AdapterGoodsDetailGetDiscounts(strings);
        dataBinding.youhui.setAdapter(detailGetDiscounts);
        detailGetDiscounts.notifyDataSetChanged();

        adapterGoodsDetailEvaluate = new AdapterGoodsDetailEvaluate(strings);
        dataBinding.evaluate.setAdapter(adapterGoodsDetailEvaluate);
        //添加Android自带的分割线
        dataBinding.evaluate.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapterGoodsDetailEvaluate.notifyDataSetChanged();
    }

    @Override
    public void loadData() {
        newsList = new ArrayList();

        newsList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592150018281&di=191016011e26f8f035cddb89f08f5e90&imgtype=0&src=http%3A%2F%2Fbos.pgzs.com%2Frbpiczy%2FWallpaper%2F2011%2F12%2F8%2Faa69906a9dc34b8d8fad0e0650a03863-2.jpg");
        newsList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592150018281&di=191016011e26f8f035cddb89f08f5e90&imgtype=0&src=http%3A%2F%2Fbos.pgzs.com%2Frbpiczy%2FWallpaper%2F2011%2F12%2F8%2Faa69906a9dc34b8d8fad0e0650a03863-2.jpg");
        newsList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592150018281&di=191016011e26f8f035cddb89f08f5e90&imgtype=0&src=http%3A%2F%2Fbos.pgzs.com%2Frbpiczy%2FWallpaper%2F2011%2F12%2F8%2Faa69906a9dc34b8d8fad0e0650a03863-2.jpg");
        BannerViewHolder bannerViewHolder = new BannerViewHolder(GoosDetailActivity.this);
        dataBinding.banner.setCanLoop(true)
                .setAutoPlay(true)
                .setIndicatorStyle(IndicatorStyle.CIRCLE)
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                .setIndicatorSliderRadius(BannerUtils.dp2px(2.5f))
                .setIndicatorSliderColor(Color.parseColor("#CCCCCC"), Color.parseColor("#6C6D72"))
                .setHolderCreator(new HolderCreator() {
                    @Override
                    public ViewHolder createViewHolder() {
                        return bannerViewHolder;
                    }
                }).setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                onClickBanner(position);
            }
        }).create(newsList);
        dataBinding.banner.startLoop();

        detailModel.getElapsedTime().observeForever(new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {

            }
        });
        detailModel.getElapsedTime().observe(this, new Observer<Long>() {

            @Override
            public void onChanged(@Nullable Long aLong) {
                Log.e("TAG", "onChanged: " + aLong);
                //设置不同的字号
                String price = "¥ " + aLong + "起";
                Spannable sp = new SpannableString(price);
                sp.setSpan(new AbsoluteSizeSpan(12, true), 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                sp.setSpan(new AbsoluteSizeSpan(16, true), 2, price.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                sp.setSpan(new AbsoluteSizeSpan(12, true), price.length() - 1, price.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                dataBinding.price.setText(sp);

            }
        });
    }

    public void onClickBanner(int pos) {
        ImagePreview
                .getInstance()
                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                .setContext(this)
                .setEnableDragClose(true) //下拉图片关闭
                // 设置从第几张开始看（索引从0开始）
                .setIndex(pos)
                .setShowErrorToast(true)//加载失败提示
                //=================================================================================================
                // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                // 1：第一步生成的imageInfo List
                //.setImageInfoList(imageInfoList)

                // 2：直接传url List
                .setImageList(newsList)

                // 3：只有一张图片的情况，可以直接传入这张图片的url
                //.setImage(String image)
                //=================================================================================================

                // 开启预览
                .start();
    }

    @Override
    public void initListener() {
        dataBinding.nesc.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                //获取banner高度
                int height = dataBinding.banner.getHeight();

                if (i1 > height) {
                    //banner完全被遮住
                    dataBinding.titleBackground.setAlpha(1);
                    backButton.set(getResources().getColorStateList(R.color.black));
                    backBackgroundButton.set(getResources().getDrawable(R.color.white));
                } else {
                    //计算被遮住的比例
                    float i4 = (float) i1 / height;
                    backButton.set(getResources().getColorStateList(R.color.white));
                    backBackgroundButton.set(getResources().getDrawable(R.color.goods_detail_back));
                    dataBinding.titleBackground.setAlpha(i4);
                }
                Rect scrollRect = new Rect();
                nestedScrollView.getHitRect(scrollRect);
                //子控件在可视范围内（至少有一个像素在可视范围内）
                if (dataBinding.banner.getLocalVisibleRect(scrollRect)) {
                    Log.e("TAG", "onScrollChange: ");
                    dataBinding.titleName.setText("第一个");
                }
            }
        });
    }

    /**
     * 退出
     *
     * @param view
     */
    public void onClickBack(View view) {
        finish();
    }

    /**
     * 去分享页面
     *
     * @param view
     */
    public void onClickShare(View view) {
        ShareFragment fragment = (ShareFragment) getFragment(RouteConstants.APPShare);
        fragment.setBitmap(ShareImageUtils.viewToBitmap(dataBinding.titleBackground));
//        fragment.setShareClassify(ShareMenu.Url, ShareMenu.WEIXIN);
        loadRootFragment(R.id.dio, fragment);
    }

    /**
     * 去评价页面
     *
     * @param view
     */
    public void onClickToEva(View view) {
        start(RouteShopConstants.SHOPGOODSEVALUATE);

    }

    /**
     * 去商品规格页
     *
     * @param view
     */
 public void onClickToSku(View view) {
        switch (view.getId()) {
//            case R.id.collect:
//                ToastUtil.show("collect");
//                break;
//            case R.id.sku:
//                ToastUtil.show("sku");
//                break;
        }
//        Basefragment fragment = getFragment(RouteShopConstants.SHOPGOODSSKU);
//
//        loadRootFragment(R.id.dio, fragment);
    }
}