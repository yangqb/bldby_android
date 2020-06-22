package com.bldby.shoplibrary.seach;

import android.databinding.DataBindingUtil;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipDrawable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
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
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSSEACH;
import static com.bldby.baselibrary.constants.RouteShopConstants.SHOPGOODSSEACHDETAIL;

@Route(path = SHOPGOODSSEACH)
public class SeachActivity extends BaseActivity {
    public static final String HISTORY_KEY = "history_key";
    private ActivitySeachBinding seachBinding;
    @Autowired()
    public String seachText;
    private String s;

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
        ArrayList<String> his = SeachHisUtil.getInstance().getHis();
        if (his.size() > 0) {
            seachBinding.lineChipGroup.setVisibility(View.VISIBLE);
        } else {
            seachBinding.lineChipGroup.setVisibility(View.GONE);
        }
        //点击取消方法（）
        seachBinding.seachDeletetext.setClickable(true);
        seachBinding.seachDeletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popToRoot();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        his();
        seachBinding.etKeyword.setSelection(seachBinding.etKeyword.getText().toString().length());
    }

    @Override
    public void loadData() {
        if (!StringUtil.isEmptyString(seachText)) {
            seachBinding.etKeyword.setText(seachText);
        }
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
                    SeachHisUtil.getInstance().addHis(s);
                    startWith(SHOPGOODSSEACHDETAIL).withString("seach", s).navigation(SeachActivity.this, SeachActivity.this);
                }
            });
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            seachBinding.chipGroup.addView(inflate.getRoot(), params);
        }
    }

    public void onClickSeach(View view) {

        String s = seachBinding.etKeyword.getText().toString();
        if (s != null && !s.equals("")) {
            SeachHisUtil.getInstance().addHis(s);
            startWith(SHOPGOODSSEACHDETAIL).withString("seach", s).navigation(this, this);

        } else {
            ToastUtil.show("不能搜索,不能为空");
        }
    }

    @Override
    public void initListener() {
        InputFilter inputFilter = new InputFilter() {

            Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]");

            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                Matcher matcher = pattern.matcher(charSequence);
                if (!matcher.find()) {
                    return null;
                } else {
                    if (charSequence.equals(" ")) {
                        return null;
                    }
//                    MyToast.showText("只能输入汉字,英文，数字");
                    return "";
                }

            }
        };
        seachBinding.etKeyword.setFilters(new InputFilter[]{inputFilter});
        seachBinding.seachGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertView alertView = new AlertView("确认删除历史记录吗！", null, "取消", null, new String[]{"删除"}
                        , SeachActivity.this, AlertView.Style.Alert,
                        new OnItemClickListener() {
                            @Override
                            public void onItemClick(Object o, int position) {
                                if (position == 0) {
                                    SeachHisUtil.getInstance().deleteHis();
                                    his();
                                    seachBinding.lineChipGroup.setVisibility(View.GONE);
                                }
                            }
                        });
                alertView.show();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            popToRoot();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}