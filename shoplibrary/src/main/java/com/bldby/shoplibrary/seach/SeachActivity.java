package com.bldby.shoplibrary.seach;

import android.databinding.DataBindingUtil;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bldby.baselibrary.constants.RouteShopConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseActivity;
import com.bldby.baselibrary.core.util.StringUtil;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.baselibrary.core.util.UIUtil;
import com.bldby.shoplibrary.R;
import com.bldby.shoplibrary.databinding.ActivitySeachBinding;
import com.bldby.shoplibrary.databinding.ItemSeachChipBinding;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;

import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSSEACH;
import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSSEACHDETAIL;

@Route(path = SHOPGOODSSEACH)
public class SeachActivity extends BaseActivity {
    public static final String HISTORY_KEY = "history_key";
    private ActivitySeachBinding seachBinding;

    @Override
    public void bindIngView() {
        seachBinding = DataBindingUtil.setContentView(SeachActivity.this, R.layout.activity_seach);
        seachBinding.setViewModel(this);
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

    @Override
    public void initView() {
        //点击取消方法（）
        seachBinding.seachDeletetext.setClickable(true);
        seachBinding.seachDeletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        his();
    }

    @Override
    public void loadData() {
//        his();
    }

    private void his() {
        seachBinding.chipGroup.removeAllViews();
        ArrayList<String> his = SeachHisUtil.getInstance().getHis();
        for (String s : his) {
            ItemSeachChipBinding inflate = ItemSeachChipBinding.inflate(getLayoutInflater());
            Chip chip = inflate.chip;
            chip.setTextColor(getResources().getColor(R.color.black));
            chip.setTextSize(UIUtil.px2sp(this, getResources().getDimension(R.dimen.sp_12)));
            chip.setText(s);
            chip.setCheckable(true);
            chip.setChipCornerRadius(getResources().getDimension(R.dimen.dp_20));
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startWith(SHOPGOODSSEACHDETAIL).withString("seach", s).navigation(SeachActivity.this, SeachActivity.this);
                }
            });
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            seachBinding.chipGroup.addView(inflate.getRoot(), params);
        }
    }

    public void onClickSeach(View view) {

        String s = seachBinding.etKeyword.getText().toString();
        if (!StringUtil.isEmptyString(s)) {
            SeachHisUtil.getInstance().addHis(s);
            startWith(SHOPGOODSSEACHDETAIL).withString("seach", s).navigation(this, this);
        } else {
            ToastUtil.show("不能搜索空");
        }
    }

    @Override
    public void initListener() {
        seachBinding.seachGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeachHisUtil.getInstance().deleteHis();
                his();
            }
        });
        seachBinding.etKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                    String s = seachBinding.etKeyword.getText().toString();
                    if (!StringUtil.isEmptyString(s)) {
                        SeachHisUtil.getInstance().addHis(s);
                        startWith(SHOPGOODSSEACHDETAIL).withString("seach", s).navigation(SeachActivity.this, SeachActivity.this);
                    } else {
                        ToastUtil.show("不能搜索空");
                    }
                    return true;
                }
                return false;
            }
        });
    }
}