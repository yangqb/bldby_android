package com.bldby.airticket.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bldby.airticket.R;
import com.bldby.airticket.databinding.ActivityEditPassengerBinding;
import com.bldby.airticket.model.CustomAirPriceDetailInfo;
import com.bldby.airticket.model.PassengerModel;
import com.bldby.airticket.request.AddPassengerRequest;
import com.bldby.airticket.request.UpdatePassengerRequest;
import com.bldby.airticket.view.CustomDialog;
import com.bldby.airticket.view.CustomListDialog;
import com.bldby.baselibrary.constants.RouteAirConstants;
import com.bldby.baselibrary.core.network.ApiCallBack;
import com.bldby.baselibrary.core.network.ApiLifeCallBack;
import com.bldby.baselibrary.core.ui.baseactivity.BaseUiActivity;
import com.bldby.baselibrary.core.util.DateUtil;
import com.bldby.baselibrary.core.util.ToastUtil;
import com.bldby.loginlibrary.AccountManager;
import com.lxj.xpopup.XPopup;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.xml.transform.sax.TemplatesHandler;

/**
 * package name: com.bldby.airticket.ui
 * user: yangqinbo
 * date: 2020/6/20
 * time: 17:12
 * email: 694125155@qq.com
 */
@Route(path = RouteAirConstants.AIREDITPASSENGER)
public class EditPassengerActivity extends BaseUiActivity {
    private ActivityEditPassengerBinding binding;
    private Calendar calendar;
    private int ageType = -1;
    private String cardType;
    private int sex = -1;
    @Autowired
    public int airType;
    @Autowired
    public PassengerModel passengerModel;
    @Autowired
    public CustomAirPriceDetailInfo customPriceDetailInfo;

    @Override
    protected View initContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        binding = ActivityEditPassengerBinding.inflate(layoutInflater, viewGroup, false);
        binding.setViewModel(this);
        return binding.getRoot();
    }

    @Override
    public void initView() {
        initTitle("添加乘机人");
        setRightText("确定");
        calendar = Calendar.getInstance();
        if (passengerModel != null) {
            ageType = passengerModel.ageType;
            sex = passengerModel.sex;
            if (passengerModel.ageType == 0) {
                binding.tvPassengerType.setText("成人票");
            } else {
                binding.tvPassengerType.setText("儿童票");
            }
            binding.editName.setText(passengerModel.name);
            if (passengerModel.sex == 0) {
                binding.tvSexType.setText("女");
            } else {
                binding.tvSexType.setText("男");
            }
            binding.tvBirthday.setText(passengerModel.birthday);
            cardType = passengerModel.cardType;
            if ("NI".equals(passengerModel.cardType)) {
                binding.tvCertificatesType.setText("身份证");
            } else if ("PP".equals(passengerModel.cardType)) {
                binding.tvCertificatesType.setText("护照");
            } else if ("GA".equals(passengerModel.cardType)) {
                binding.tvCertificatesType.setText("港澳通行证");
            } else if ("TW".equals(passengerModel.cardType)) {
                binding.tvCertificatesType.setText("台湾通行证");
            } else if ("TB".equals(passengerModel.cardType)) {
                binding.tvCertificatesType.setText("台胞证");
            } else if ("HX".equals(passengerModel.cardType)) {
                binding.tvCertificatesType.setText("回乡证");
            } else if ("HY".equals(passengerModel.cardType)) {
                binding.tvCertificatesType.setText("国际海员证");
            } else {
                binding.tvCertificatesType.setText("其他");
            }
            binding.editCardId.setText(passengerModel.cardNo);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(passengerModel.birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);
        } else {
            Date date = new Date();
            calendar.setTime(date);
        }
    }

    @Override
    public void onClickRight(View view) {
        super.onClickRight(view);
        if (TextUtils.isEmpty(binding.tvPassengerType.getText().toString().trim())) {
            ToastUtil.show("请选择乘机人类型");
            return;
        }
        if (TextUtils.isEmpty(binding.editName.getText().toString().trim())) {
            ToastUtil.show("请填写乘机人姓名");
            return;
        }
        if (TextUtils.isEmpty(binding.tvSexType.getText().toString().trim())) {
            ToastUtil.show("请选择乘机人性别");
            return;
        }
        if (TextUtils.isEmpty(binding.tvBirthday.getText().toString().trim())) {
            ToastUtil.show("请选择乘机人生日");
            return;
        }
        if (TextUtils.isEmpty(binding.tvCertificatesType.getText().toString().trim())) {
            ToastUtil.show("请选择证件类型");
            return;
        }
        if (TextUtils.isEmpty(binding.editCardId.getText().toString().trim())) {
            ToastUtil.show("请填写证件号码");
            return;
        }
        submit();
    }

    public void onClickBtn(View view) {
        if (view.getId() == R.id.passenger_type) {
            String[] strings1 = new String[]{"成人票（满12周岁）", "儿童票（2~12周岁）"};

            new XPopup.Builder(this)
                    .asCustom(new CustomListDialog(this).setData(Arrays.asList(strings1)).setOnItemClickListener(new CustomListDialog.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            if (position == 0) {
                                binding.tvPassengerType.setText("成人票");
                            } else {
                                binding.tvPassengerType.setText("儿童票");
                            }
                            ageType = position;
                        }
                    })).show();
        } else if (view.getId() == R.id.certificates_type) {
            String[] strings2 = new String[]{"身份证", "护照", "港澳通行证", "台湾通行证", "台胞证", "回乡证", "国际海员证", "其他"};
            new XPopup.Builder(this)
                    .asCustom(new CustomListDialog(EditPassengerActivity.this)
                            .setData(Arrays.asList(strings2))
                            .setOnItemClickListener(new CustomListDialog.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    binding.tvCertificatesType.setText(strings2[position]);
                                    if (position == 0) {
                                        cardType = "NI";
                                    } else if (position == 1) {
                                        cardType = "PP";
                                    } else if (position == 2) {
                                        cardType = "GA";
                                    } else if (position == 3) {
                                        cardType = "TW";
                                    } else if (position == 4) {
                                        cardType = "TB";
                                    } else if (position == 5) {
                                        cardType = "HX";
                                    } else if (position == 6) {
                                        cardType = "HY";
                                    } else {
                                        cardType = "ID";
                                    }
                                }
                            }))
                    .show();
        } else if (view.getId() == R.id.nameTips) {
            String content = "1. 请您严格按照办理登记手续时所持有效证件上的姓名填写。\n" +
                    "2. 如果您的姓名中含有生僻字或繁体字，请用拼音代替生僻字（如：陶喆喆，请填写‘陶zhezhe’）。\n" +
                    "3. 少数民族乘客可不输入姓名中的符号点。\n" +
                    "4. 持护照或使用英文名的乘客，须按照护照或所持证件上的顺序填写姓名且不区分大小写，并将姓与名用/分割（姓/名）。\n" +
                    "5. 姓名过长时请使用缩写（中文名不超过12个汉字；英文名不超过29个字母，包括空格和/）。";

            new XPopup.Builder(this).enableDrag(false).asCustom(new CustomDialog(this).setContent(content)).show();

        } else if (view.getId() == R.id.sex_type) {
            String[] strings3 = new String[]{"女", "男"};
            new XPopup.Builder(this)
                    .asCustom(new CustomListDialog(EditPassengerActivity.this)
                            .setData(Arrays.asList(strings3))
                            .setOnItemClickListener(new CustomListDialog.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    binding.tvSexType.setText(strings3[position]);
                                    sex = position;
                                }
                            }))
                    .show();
        } else if (view.getId() == R.id.rl_birthday) {
            //时间选择器
            TimePickerView pvTime = new TimePickerBuilder(EditPassengerActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    String birthday = DateUtil.formatDateYYYYMMDDWithDate(date);
                    binding.tvBirthday.setText(birthday);
                }
            }).setDate(calendar).build();
            pvTime.show();
        }
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initListener() {

    }

    public void submit() {
        if (passengerModel != null) {  //修改乘机人信息
            UpdatePassengerRequest updatePassengerRequest = new UpdatePassengerRequest();
            updatePassengerRequest.accessToken = AccountManager.getInstance().getToken();
            updatePassengerRequest.userId = AccountManager.getInstance().getUserId();
            updatePassengerRequest.name = binding.editName.getText().toString().trim();
            updatePassengerRequest.id = passengerModel.id;
            updatePassengerRequest.ageType = ageType + "";
            updatePassengerRequest.cardType = cardType + "";
            updatePassengerRequest.sex = sex + "";
            updatePassengerRequest.birthday = binding.tvBirthday.getText().toString().trim();
            updatePassengerRequest.call(new ApiCallBack() {
                @Override
                public void onAPIResponse(Object response) {
                    ToastUtil.show("修改成功");
                    setResult(RESULT_OK);
                    finish();
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        } else { //添加乘机人
            if (airType == 2) {
                if (ageType == 1) {
                    ToastUtil.show("不支持儿童购买");
                    return;
                }
            } else if (airType == 1 || airType == 3) {
                if (ageType == 1 && customPriceDetailInfo.customInterPriceInfo.cPrice == 0) {
                    ToastUtil.show("不支持儿童购买");
                    return;
                }
            } else {
                if (customPriceDetailInfo.customDocGoPriceInfo.businessExtMap == null) {
                    if (ageType == 1) {
                        ToastUtil.show("不支持儿童购买");
                        return;
                    }
                } else {
                    if (!customPriceDetailInfo.customDocGoPriceInfo.businessExtMap.supportChild && !customPriceDetailInfo.customDocGoPriceInfo.businessExtMap.supportChildBuyAdult && ageType == 1) {
                        ToastUtil.show("不支持儿童购买");
                        return;
                    }
                }
            }

            AddPassengerRequest addPassengerRequest = new AddPassengerRequest();
            addPassengerRequest.accessToken = AccountManager.getInstance().getToken();
            addPassengerRequest.userId = AccountManager.getInstance().getUserId();
            addPassengerRequest.name = binding.editName.getText().toString().trim();
            addPassengerRequest.ageType = ageType + "";
            addPassengerRequest.cardType = cardType + "";
            addPassengerRequest.sex = sex + "";
            addPassengerRequest.birthday = binding.tvBirthday.getText().toString().trim();
            addPassengerRequest.call(new ApiCallBack() {
                @Override
                public void onAPIResponse(Object response) {
                    ToastUtil.show("添加成功");
                    setResult(RESULT_OK);
                    finish();
                }

                @Override
                public void onAPIError(int errorCode, String errorMsg) {

                }
            });
        }
    }
}
