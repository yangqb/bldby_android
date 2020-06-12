package com.bldby.shoplibrary.goods;

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
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivityGoosDetailBinding;
import com.bldby.shoplibrary.goods.adapter.AdapterGoodsDetailEvaluate;
import com.bldby.shoplibrary.goods.adapter.AdapterGoodsDetailGetDiscounts;

import java.util.ArrayList;

import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSDETAIL;

@Route(path = SHOPGOODSDETAIL)
public class GoosDetailActivity extends BaseActivity {

    private ActivityGoosDetailBinding dataBinding;
    public ObservableField<ColorStateList> backButton = new ObservableField<ColorStateList>();
    public ObservableField<Drawable> backBackgroundButton = new ObservableField<Drawable>();
    private AdapterGoodsDetailGetDiscounts detailGetDiscounts;
    private AdapterGoodsDetailEvaluate adapterGoodsDetailEvaluate;

    @Override
    public void bindIngView() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_goos_detail);
        dataBinding.setViewmodel(this);
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
        String price = "¥ " + "99" + "起";
        Spannable sp = new SpannableString(price);
        sp.setSpan(new AbsoluteSizeSpan(12, true), 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(16, true), 2, price.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(12, true), price.length() - 1, price.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        dataBinding.price.setText(sp);

    }

    @Override
    public void initListener() {
        dataBinding.nesc.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                int height = dataBinding.banner.getHeight();
                Log.e("TAG", "onScrollChange: " + height + ".." + i1 + ".." + ".." + i3);

                if (i1 > height) {
                    dataBinding.titleBackground.setAlpha(1);
                    backButton.set(getResources().getColorStateList(R.color.black));
                    backBackgroundButton.set(getResources().getDrawable(R.color.white));
                } else {
                    float i4 = (float) i1 / height;
                    Log.e("TAG", "onScrollChange: " + i4);
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
//                else if (dataBinding.one2.getLocalVisibleRect(scrollRect)) {
//                    ////子控件完全不在可视范围内
//                    Log.e("TAG", "onScrollChange:00 ");
//                    dataBinding.titleName.setText("第2个");
//                } else if (dataBinding.one3.getLocalVisibleRect(scrollRect)) {
//                    ////子控件完全不在可视范围内
//                    Log.e("TAG", "onScrollChange:00 ");
//                    dataBinding.titleName.setText("第3个");
//                }


            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }

    public void onClickShare(View view) {
        ShareFragment fragment = (ShareFragment) getFragment(RouteConstants.APPShare);
        fragment.setBitmap(ShareImageUtils.viewToBitmap(dataBinding.titleBackground));
//        fragment.setShareClassify(ShareMenu.Url, ShareMenu.WEIXIN);
        loadRootFragment(R.id.dio, fragment);

    }

}