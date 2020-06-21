package com.bldby.shoplibrary.seach;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.core.load.LoadingUtil;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.smart.MRefreshFooter;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.baselibrary.databinding.ViewCommonNodataBinding;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivitySeachDetailBinding;
import com.bldby.shoplibrary.databinding.ItemSeachTabitemBinding;
import com.bldby.shoplibrary.seach.adapter.ItemSeachShopsAdapter;
import com.bldby.shoplibrary.seach.model.GoodsSeachModel;
import com.bldby.shoplibrary.seach.request.GoodsSeachRequest;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSDETAIL;
import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSSEACH;
import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSSEACHDETAIL;
import static com.bldby.baselibrary.core.network.BaseApiRequest.kErrorTypeNoNetworkConnect;

@Route(path = SHOPGOODSSEACHDETAIL)
public class SeachDetailActivity extends BaseActivity {
    @Autowired()
    public String seach;
    //    排序规则 0 销量降序 1 价格降序 2 价格升序
    public int type = 0;
    //第一页
    public int currentPage = 1;
    private ActivitySeachDetailBinding viewDataBinding;
    private ItemSeachShopsAdapter itemSeachShopsAdapter;
    //是否是刷新
    private boolean isrefresh = false;

    @Override
    public void bindIngView() {
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_seach_detail);
        viewDataBinding.etKeyword.setText(seach);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("销量");
        strings.add("价格");
        for (int i = 0; i < 2; i++) {
            ItemSeachTabitemBinding inflate = ItemSeachTabitemBinding.inflate(LayoutInflater.from(this), viewDataBinding.tab, false);
            inflate.title.setText(strings.get(i));
            if (i == 0) {
                inflate.title.setTextColor(getResources().getColor(R.color.default_orange));
            }
            if (i == 1) {
                Drawable drawable = getResources().getDrawable(R.mipmap.seach_sort);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                inflate.title.setCompoundDrawables(null, null, drawable, null);
            }
            TabLayout.Tab tab = viewDataBinding.tab.newTab().setCustomView(inflate.getRoot());
            viewDataBinding.tab.addTab(tab);
        }

    }

    @Override
    public void initView() {

        viewDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        itemSeachShopsAdapter = new ItemSeachShopsAdapter(null);
        ViewCommonNodataBinding inflate = ViewCommonNodataBinding.inflate(getLayoutInflater());
        itemSeachShopsAdapter.setEmptyView(inflate.getRoot());
        viewDataBinding.recyclerView.setAdapter(itemSeachShopsAdapter);
        viewDataBinding.smartRe.setEnableRefresh(true);
        viewDataBinding.smartRe.setEnableLoadMore(true);
    }

    @Override
    public void loadData() {
        searchData(0, 1);
    }

    @Override
    public void initListener() {
        itemSeachShopsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GoodsSeachModel.ListBean listBean = itemSeachShopsAdapter.getData().get(i);
                startWith(SHOPGOODSDETAIL).withInt("spuId", listBean.getSpuId())
                        .navigation(SeachDetailActivity.this, SeachDetailActivity.this);
            }
        });
        //点击取消方法（）
        viewDataBinding.seachDeletetext.setClickable(true);
        viewDataBinding.seachDeletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popToRoot();
            }
        });
        viewDataBinding.editParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWith(SHOPGOODSSEACH)
                        .withString("seachText", viewDataBinding.etKeyword.getText().toString())
                        .navigation(SeachDetailActivity.this, SeachDetailActivity.this);
            }
        });
        viewDataBinding.smartRe.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isrefresh = false;
                currentPage++;
                searchData(type, currentPage);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isrefresh = true;
                itemSeachShopsAdapter.getData().clear();
                searchData(type, 1);

            }
        });
        viewDataBinding.tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //                    1价格降序 2 价格升序
                itemSeachShopsAdapter.getData().clear();
                View customView = tab.getCustomView();
                TextView viewById = customView.findViewById(R.id.title);
                viewById.setTextColor(getResources().getColor(R.color.default_orange));
                if (tab.getPosition() == 0) {
                    searchData(0, 1);
                } else {
                    Drawable drawable = getResources().getDrawable(R.mipmap.seach_sort1);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    viewById.setCompoundDrawables(null, null, drawable, null);
                    searchData(1, 1);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView viewById = tab.getCustomView().findViewById(R.id.title);
                viewById.setTextColor(getResources().getColor(R.color.black));

                if (tab.getPosition() == 1) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.seach_sort);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    viewById.setCompoundDrawables(null, null, drawable, null);

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    itemSeachShopsAdapter.getData().clear();
                    if (type == 1) {
                        View customView = tab.getCustomView();
                        TextView viewById = customView.findViewById(R.id.title);
                        Drawable drawable = getResources().getDrawable(R.mipmap.seach_sort2);
                        viewById.setTextColor(getResources().getColor(R.color.default_orange));

                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        viewById.setCompoundDrawables(null, null, drawable, null);
                        searchData(2, 1);
//                        tab.setCustomView(customView);
                    } else {
                        View customView = tab.getCustomView();
                        TextView viewById = customView.findViewById(R.id.title);
                        viewById.setTextColor(getResources().getColor(R.color.default_orange));

                        Drawable drawable = getResources().getDrawable(R.mipmap.seach_sort1);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        viewById.setCompoundDrawables(null, null, drawable, null);
                        searchData(1, 1);
//                        tab.setCustomView(customView);

                    }
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            popToRoot();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public ImmersionBar getOpenImmersionBar() {
        return ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .navigationBarColor(R.color.white)
                .statusBarColor(R.color.FCB432)
                .navigationBarDarkIcon(true);
    }

    private void searchData(int type, int page) {
        currentPage = page;
        this.type = type;
//        LoadingUtil.setLoadingViewShow(true);
        GoodsSeachRequest goodsSeachRequest = new GoodsSeachRequest();
        goodsSeachRequest.keyWord = seach;
        goodsSeachRequest.sort = type;
        goodsSeachRequest.isShowLoading = true;
        goodsSeachRequest.currentPage = currentPage;
        goodsSeachRequest.call(new ApiCallBack<GoodsSeachModel>() {
            @Override
            public void onAPIResponse(GoodsSeachModel response) {
                if (response.getList() != null && response.getList().size() > 0) {
                    itemSeachShopsAdapter.addData(response.getList());
                }
                if (!isrefresh) {
                    viewDataBinding.smartRe.finishLoadMore(true);
                } else {
                    viewDataBinding.smartRe.finishRefresh(true);
                }
//                viewDataBinding.smartRe.finishLoadMoreWithNoMoreData();

                if (currentPage >= response.getTotal()) {
//最后一页
                    if (!isrefresh) {
                        viewDataBinding.smartRe.finishLoadMoreWithNoMoreData();
                    }
                }
                itemSeachShopsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onAPIError(int errorCode, String errorMsg) {
                if (errorCode == kErrorTypeNoNetworkConnect) {
                    if (!isrefresh) {
                        ToastUtil.show("没有网络了");
                        RefreshFooter refreshFooter = viewDataBinding.smartRe.getRefreshFooter();
                        if (refreshFooter instanceof MRefreshFooter) {
                            ((MRefreshFooter) refreshFooter).setNoNetWork();
                        }
                        viewDataBinding.smartRe.finishLoadMore(1000);
                    } else {
                        viewDataBinding.smartRe.finishRefresh(1000);
                    }
                }
                itemSeachShopsAdapter.notifyDataSetChanged();

            }
        });
    }
}