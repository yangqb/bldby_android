package com.bldby.shoplibrary.goods;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteConstants;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.share.ShareFragment;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.ui.basefragment.Basefragment;
import com.bldby.baselibrary.core.util.ShareImageUtils;
import com.bldby.loginlibrary.AccountManager;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityGoosDetailBinding;
import com.bldby.shoplibrary.goods.adapter.AdapterGoodsDetailEvaluate;
import com.bldby.shoplibrary.goods.adapter.AdapterGoodsDetailGetDiscounts;
import com.bldby.shoplibrary.goods.model.BannerViewHolder;
import com.bldby.shoplibrary.goods.model.GoodsDetailModel;
import com.bldby.shoplibrary.goods.model.ShopDetailModel;
import com.bldby.shoplibrary.goods.request.GoodsDetailRequest;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.easeui.EaseUiManager;
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
    @Autowired()
    public int spuId;

    private ActivityGoosDetailBinding dataBinding;
    public ObservableField<ColorStateList> backButton = new ObservableField<ColorStateList>();
    public ObservableField<Drawable> backBackgroundButton = new ObservableField<Drawable>();
    private AdapterGoodsDetailGetDiscounts detailGetDiscounts;
    private AdapterGoodsDetailEvaluate adapterGoodsDetailEvaluate;
    private ShopDetailModel detailModel;


    @Override
    public void bindIngView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_goos_detail);
        dataBinding.setViewmodel(this);
        detailModel = ViewModelProviders.of(this).get(ShopDetailModel.class);
    }

    @Override
    public void initView() {
        String userPhone = AccountManager.getInstance().getUserPhone();
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

        adapterGoodsDetailEvaluate = new AdapterGoodsDetailEvaluate(null);
        dataBinding.evaluate.setAdapter(adapterGoodsDetailEvaluate);
        //添加Android自带的分割线
        dataBinding.evaluate.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapterGoodsDetailEvaluate.notifyDataSetChanged();

        dataBinding.goodsShoppingcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(RouteShopConstants.SHOPGOODSSHOPPING);
            }
        });
        dataBinding.goodsCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EaseUiManager.getInstance().isLogin()) {
                    start(RouteShopConstants.SHOPGOODSCUST);
                } else {
                    EaseUiManager.getInstance().login(new ApiCallBack() {
                        @Override
                        public void onAPIResponse(Object response) {
                            start(RouteShopConstants.SHOPGOODSCUST);
                        }

                        @Override
                        public void onAPIError(int errorCode, String errorMsg) {

                        }
                    });
                }

            }
        });
    }

    @Override
    public void loadData() {
        detailModel.getGoodsDetailModel().observe(this, new Observer<GoodsDetailModel>() {
            @Override
            public void onChanged(@Nullable GoodsDetailModel goodsDetailModel) {
                dataBinding.goodsName.setText(goodsDetailModel.getTitle());
//                dataBinding.goodsDes.setText(response.getSpec());
                initbanner(goodsDetailModel);

                initEvaluate(goodsDetailModel);
                //设置不同的字号
                String price = "¥ " + 20 + "起";
                Spannable sp = new SpannableString(price);
                sp.setSpan(new AbsoluteSizeSpan(12, true), 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                sp.setSpan(new AbsoluteSizeSpan(16, true), 2, price.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                sp.setSpan(new AbsoluteSizeSpan(12, true), price.length() - 1, price.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                dataBinding.price.setText(sp);
            }
        });
        goodsRequest();
    }

    private void goodsRequest() {
        spuId = 2;
        GoodsDetailRequest goodsDetailRequest = new GoodsDetailRequest(spuId);
        goodsDetailRequest.isShowLoading = true;
        goodsDetailRequest.call(new ApiCallBack<GoodsDetailModel>() {
            @Override
            public void onAPIResponse(GoodsDetailModel response) {
                detailModel.getGoodsDetailModel().setValue(response);
            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {

            }
        });
    }

    private void initEvaluate(GoodsDetailModel response) {
        List<GoodsDetailModel.EvalsBean> evals = response.getEvals();
        if (evals != null && evals.size() > 0) {
            dataBinding.evaluateNull.setVisibility(View.GONE);
            dataBinding.evaluate.setVisibility(View.VISIBLE);
            dataBinding.evaluateNum.setText("评价（" + evals.size() + "）");

            if (evals.size() > 2) {
                evals = evals.subList(0, 2);
            }
            adapterGoodsDetailEvaluate.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    start(RouteShopConstants.SHOPGOODSEVALUATE);

                }
            });
            adapterGoodsDetailEvaluate.setNewData(evals);
            adapterGoodsDetailEvaluate.notifyDataSetChanged();
        } else {
            dataBinding.evaluateNum.setText("评价（0）");
            dataBinding.evaluateNull.setVisibility(View.VISIBLE);
            dataBinding.evaluate.setVisibility(View.GONE);
        }
    }

    private void initbanner(GoodsDetailModel response) {
        if (response.getImgList() != null && response.getImgList().size() > 0) {
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
                    onClickBanner(position, response.getImgList());
                }
            }).create(response.getImgList());
            dataBinding.banner.startLoop();
        }
    }

    public void onClickBanner(int pos, List<String> imgList) {
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
                .setImageList(imgList)

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

        Basefragment fragment = getFragment(RouteShopConstants.SHOPGOODSSKU);

        loadRootFragment(R.id.dio, fragment);
    }
}