package com.bldby.shoplibrary.seach;

import android.databinding.DataBindingUtil;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipDrawable;
import android.text.Editable;
import android.text.TextUtils;
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
        //点击取消方法（）
        seachBinding.seachDeletetext.setClickable(true);
        seachBinding.seachDeletetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popToRoot();
            }
        });
        s = seachBinding.etKeyword.getText().toString();

        seachBinding.etKeyword.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart ;
            private int editEnd ;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;

            }
            @Override
            public void afterTextChanged(Editable editable) {
                editStart = seachBinding.etKeyword.getSelectionStart();
                editEnd = seachBinding.etKeyword.getSelectionEnd();
                if (temp.length()>0){
                    seachBinding.seachDeleteImg.setVisibility(View.VISIBLE);
                    seachBinding.seachDeleteImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            seachBinding.etKeyword.setText("");
                        }
                    });
                }else{
                    seachBinding.seachDeleteImg.setVisibility(View.GONE);
                }

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
                    startWith(SHOPGOODSSEACHDETAIL).withString("seach", s).navigation(SeachActivity.this, SeachActivity.this);
                }
            });
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            seachBinding.chipGroup.addView(inflate.getRoot(), params);
        }
    }

    public void onClickSeach(View view) {
        s = seachBinding.etKeyword.getText().toString();
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
                AlertView alertView = new AlertView("确认删除历史记录！", null, "取消", null, new String[]{"删除"}
                        , SeachActivity.this, AlertView.Style.Alert,
                        new OnItemClickListener() {
                            @Override
                            public void onItemClick(Object o, int position) {
                                SeachHisUtil.getInstance().deleteHis();
                                seachBinding.lineChipGroup.setVisibility(View.GONE);
                                his();
                            }
                        });
                alertView.show();
//                new AlertView.Builder().setContext(SeachActivity.this)
//                        .setStyle(AlertView.Style.Alert)
//                        .setTitle("")
//                        .setCancelText("取消")
//                        .setDestructive("删除")
//                        .setOthers(null)
//                        .setOnItemClickListener(new OnItemClickListener() {
//                            @Override
//                            public void onItemClick(Object o, int position) {
//                                SeachHisUtil.getInstance().deleteHis();
//                                his();
//                            }
//                        })
//                        .build()
//                        .show();
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