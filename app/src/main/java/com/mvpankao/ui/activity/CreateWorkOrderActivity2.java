package com.mvpankao.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.DialogUtils;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.utils.TimeUtil;
import com.mvpankao.view.WorkOrderView;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.LemonBubbleInfo;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;
import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2017/1/3
 */

public class CreateWorkOrderActivity2 extends BaseActivity implements WorkOrderView, DatePickerDialog.OnDateSetListener {
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.customerName)
    TextView mCustomerName;
    @BindView(R.id.save)
    TextView mSave;
    @BindView(R.id.assertArea)
    TextView mAssertArea;
    @BindView(R.id.rl_area)
    RelativeLayout mRlArea;
    @BindView(R.id.attendance)
    RadioButton mAttendance;
    @BindView(R.id.business)
    RadioButton mBusiness;
    @BindView(R.id.radiogroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.starttime)
    TextView mStarttime;
    @BindView(R.id.rl_starttime)
    RelativeLayout mRlStarttime;
    @BindView(R.id.endtime)
    TextView mEndtime;
    @BindView(R.id.rl_endtime)
    RelativeLayout mRlEndtime;
    @BindView(R.id.orderTask)
    TextView mOrderTask;
    @BindView(R.id.rl_orderTask)
    RelativeLayout mRlOrderTask;
    @BindView(R.id.executors)
    TextView mExecutors;
    @BindView(R.id.rl_Executor)
    RelativeLayout mRlExecutor;
    String type = "3";
    private static int mRequestCode = 0x001101;
    List<String> namearray = new ArrayList<>();
    String assertimage = "";
    private WorkOrderPresenter mWorkOrderPresenter = new WorkOrderPresenter(this);

    public static final String DATEPICKER_TAG = "datepicker";
    DatePickerDialog datePickerDialog, datePickerDialog2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Calendar calendar = Calendar.getInstance();

        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);
        datePickerDialog2 = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), true);

        if (savedInstanceState != null) {
            DatePickerDialog dpd = (DatePickerDialog) getSupportFragmentManager().findFragmentByTag(DATEPICKER_TAG);
            if (dpd != null) {
                dpd.setOnDateSetListener(this);
            }
        }
    }

    @Override
    protected void initView() {

        mTitle.setText("创建工单");
        mContactCustomerService.setVisibility(View.INVISIBLE);
        mCustomerName.setText(getIntent().getStringExtra("name"));
        mStarttime.setText(TimeUtil.getCurrentTime());
        mEndtime.setText(TimeUtil.getCurrentTime());
        SPUtils sp = new SPUtils(mContext, "EXECUTOR");
        sp.clear();
        SPUtils sp2 = new SPUtils(mContext, "ASSERTAREA");
        sp2.clear();
        mAssertArea.addTextChangedListener(new textChange());
        mStarttime.addTextChangedListener(new textChange());
        mEndtime.addTextChangedListener(new textChange());
        mOrderTask.addTextChangedListener(new textChange());
        mExecutors.addTextChangedListener(new textChange());

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                if (checkId == mAttendance.getId()) {
                    type = "3";

                } else {
                    type = "4";

                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_createworkorder2;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mRequestCode == requestCode
                && resultCode == EditOrderContentActivity.resultCode) {

            mOrderTask.setText(data.getStringExtra("value"));
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case 123:
                SPUtils sp = new SPUtils(mContext, "EXECUTOR");
                String executor = sp.getString("executor", "");

                mExecutors.setText(executor);
                break;
            case 321:
                namearray.clear();

                String names = "";
                SPUtils sp2 = new SPUtils(mContext, "ASSERTAREA");
                if (sp2.contains("parentname")) {
                    String name = sp2.getString("parentname", "");
                    if (!StringUtils.isEmpty(name)) {
                        namearray.add(name.substring(0, name.length()));
                    }
                }
                if (sp2.contains("childgroupname")) {
                    String name2 = sp2.getString("childgroupname", "");
                    if (!StringUtils.isEmpty(name2)) {
                        namearray.add(name2.substring(0, name2.length()));
                    }
                }
                if (sp2.contains("childchildname")) {
                    String name3 = sp2.getString("childchildname", "");


                    if (!StringUtils.isEmpty(name3)) {
                        namearray.add(name3.substring(0, name3.length()));
                    }
                }


                for (int i = 0; i < namearray.size(); i++) {
                    names += ";" + namearray.get(i);
                }

                if (!StringUtils.isEmpty(names)) {
                    mAssertArea.setText(names.substring(1, names.length()));
                }
                assertimage = sp2.getString("image", "");
                break;
        }
    }

    @OnClick({R.id.rl_back, R.id.save, R.id.rl_area, R.id.rl_starttime, R.id.rl_endtime, R.id.rl_orderTask, R.id.rl_Executor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.save:

                long start = TimeUtils.string2Milliseconds(mStarttime.getText().toString() + " 00:00:00");
                long end = TimeUtils.string2Milliseconds(mEndtime.getText().toString() + " 00:00:00");
                LogUtils.d(start);
                LogUtils.d(end);

                if (end < start) {
                    LemonBubbleInfo myInfo = LemonBubble.getErrorBubbleInfo();
                    // 设置图标在左侧，标题在右侧
                    myInfo.setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT);
                    // 设置泡泡控件在底部
                    myInfo.setLocationStyle(LemonBubbleLocationStyle.CENTER);
                    // 设置泡泡控件的动画图标颜色为蓝色
                    myInfo.setIconColor(Color.parseColor("#1bbc9b"));
                    // 设置泡泡控件的尺寸，单位dp
                    myInfo.setBubbleSize(250, 80);
                    // 设置泡泡控件的偏移比例为整个屏幕的0.01,
                    myInfo.setProportionOfDeviation(0.01f);
                    // 设置泡泡控件的标题
                    myInfo.setTitle("结束时间不能早于开始时间");
                    myInfo.setTitleFontSize(13);
                    // 展示自定义的泡泡控件，并显示2s后关闭
                    LemonBubble.showBubbleInfo(mContext, myInfo, 1500);
//                    Toast.makeText(mContext, "结束时间不能比开始时间早", Toast.LENGTH_SHORT).show();
                } else {
                    if (NetworkUtils.isConnected(mContext)) {
                        DialogUtils.Progress(mContext, "创建中...");
                        createWorkOrder();

                    } else {
                        Toast.makeText(mContext, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.rl_area:
                Intent area = new Intent(mContext, AssetAreaActivity.class);
                area.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(area);
                break;
            case R.id.rl_starttime:


                datePickerDialog.setVibrate(true);
                datePickerDialog.setYearRange(1985, 2028);
                datePickerDialog.setCloseOnSingleTapDay(false);
                datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
                        mStarttime.setText(year + "-" + (month + 1) + "-" + day);
                    }
                });

                break;
            case R.id.rl_endtime:

                datePickerDialog2.setVibrate(true);
                datePickerDialog2.setYearRange(1985, 2028);
                datePickerDialog2.setCloseOnSingleTapDay(false);
                datePickerDialog2.show(getSupportFragmentManager(), DATEPICKER_TAG);
                datePickerDialog2.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
                        mEndtime.setText(year + "-" + (month + 1) + "-" + day);

                    }
                });

                break;
            case R.id.rl_orderTask:
                Intent intent = new Intent(this, EditOrderContentActivity.class);
                intent.putExtra("value", mOrderTask.getText().toString());
                startActivityForResult(intent, mRequestCode);
                break;

        }
    }

    private void createWorkOrder() {
        if(mContext.isDestroyed()){
            return;
        }
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.CREATEWORKORDER;
        Map<String, String> map = new HashMap<>();
        map.put("createUserId", mSPUtils.getString("userid"));
        long start = TimeUtils.string2Milliseconds(mStarttime.getText().toString() + " 00:00:00");
        long end = TimeUtils.string2Milliseconds(mEndtime.getText().toString() + " 00:00:00");
        map.put("executor_plan_begin_date", start + "");
        map.put("executor_plan_end_date", end + "");
        map.put("engineeringNote", mOrderTask.getText().toString());
        map.put("isOverdue", "0");
        map.put("engineeringIcon", assertimage);
        map.put("engineeringAssetExtent", mAssertArea.getText().toString());
        map.put("engineeringTypeIdfk", type);
        map.put("engineeringStatus", "0");
        map.put("engineering_asset_position", getIntent().getStringExtra("name"));
        mWorkOrderPresenter.editWorkOrder(mContext, mMyOkhttp, url, map);


    }

    @Override
    public void setJsonArray(JSONArray jsonArray) {

    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onWorkOrderSuccess() {

    }

    @Override
    public void onWarningSuccess() {

    }

    @Override
    public void onWorkOrderSuccess(WorkOrderDeatil response) {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onEditWorkOrderSuccess() {
        DialogUtils.dismiss();
        EventBus.getDefault().post(new MyEvent(Constants.WORKORDERLISTUPDATE_CODE));

        LemonHello.getSuccessHello("提示", "恭喜您，创建成功！")
                .addAction(new LemonHelloAction("我知道啦", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                        helloView.hide();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                finish();

                            }
                        }, 1000);
                    }
                }))
                .show(mContext);
    }

    @Override
    public void onEditFailure() {

    }

    @Override
    public void onEditError() {

    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

    }


    class textChange implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            boolean Sign1 = mAssertArea.getText().length() > 0;

            boolean Sign2 = mStarttime.getText().length() > 0;
            boolean Sign3 = mEndtime.getText().length() > 0;
            boolean Sign4 = mOrderTask.getText().length() > 0;
//            boolean Sign5 = mExecutors.getText().length() > 0;
            if (Sign1 & Sign2 & Sign3 & Sign4) {
                mSave.setClickable(true);
                mSave.setBackgroundResource(R.drawable.orderbtn);

            } else {
                mSave.setClickable(false);
                mSave.setBackgroundResource(R.drawable.orderbtn2);
            }


        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void afterTextChanged(Editable arg0) {

        }

    }
}
