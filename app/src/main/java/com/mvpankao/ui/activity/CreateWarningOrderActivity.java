package com.mvpankao.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;
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
import org.json.JSONArray;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新建报警工单
 * Created by wangjian
 * On  2017/1/3
 */

public class CreateWarningOrderActivity extends BaseActivity implements WorkOrderView, DatePickerDialog.OnDateSetListener {
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
    @BindView(R.id.AssetsArea)
    TextView mAssetsArea;
    @BindView(R.id.task)
    TextView mTask;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.starttime)
    TextView mStarttime;
    @BindView(R.id.rl_starttime)
    RelativeLayout mRlStarttime;
    @BindView(R.id.endtime)
    TextView mEndtime;
    @BindView(R.id.rl_endtime)
    RelativeLayout mRlEndtime;

    @BindView(R.id.rl_Task)
    RelativeLayout mRlTask;
    private static int mRequestCode = 0x001101;
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
        mContactCustomerService.setVisibility(View.GONE);
        mAssetsArea.setText(getIntent().getStringExtra("name"));
        mStarttime.setText(TimeUtil.getCurrentTime());
        mEndtime.setText(TimeUtil.getCurrentTime());
        String time = getIntent().getStringExtra("time");
        String position = getIntent().getStringExtra("position");
        String level = getIntent().getStringExtra("level");
        String note = getIntent().getStringExtra("note");


        mTask.setText(time + "\n" + position + "\n" + level + "\n" + note);

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_createwarningorder;
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

    @OnClick({R.id.rl_back, R.id.save, R.id.rl_starttime, R.id.rl_endtime})
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
                        createWarning();

                    } else {
                        Toast.makeText(mContext, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                }
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
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mRequestCode == requestCode
                && resultCode == EditOrderContentActivity.resultCode) {

            mTask.setText(data.getStringExtra("value"));
        }

    }

    /**
     * 创建报警工单
     */
    private void createWarning() {
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
        map.put("engineeringNote", mTask.getText().toString());
        map.put("isOverdue", "0");
        map.put("engineeringIcon", getIntent().getStringExtra("assertimage"));
        String position = getIntent().getStringExtra("position");
        map.put("engineering_asset_position", position);
        map.put("engineeringAssetExtent", mAssetsArea.getText().toString());
        map.put("engineeringTypeIdfk", "2");
        map.put("engineeringStatus", "0");

        mWorkOrderPresenter.editWorkOrder(mContext, mMyOkhttp, url, map);

    }

    @OnClick(R.id.rl_Task)
    public void onClick() {
        Intent intent = new Intent(this, EditOrderContentActivity.class);
        intent.putExtra("value", mTask.getText().toString());
        startActivityForResult(intent, mRequestCode);
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
        LemonHello.getSuccessHello("提示", "恭喜您，创建成功！")
                .addAction(new LemonHelloAction("我知道啦", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                        helloView.hide();
                        EventBus.getDefault().post(new MyEvent(Constants.WORKORDERLISTUPDATE_CODE));
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
}
