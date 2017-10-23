package com.mvpankao.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WorkOrderView;
import com.mvpankao.widget.CircleProgressView;

import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2016/12/30
 */

public class WorkOrderDetailActivity extends BaseActivity implements WorkOrderView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.rl_edit)
    RelativeLayout mRlEdit;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.rl_Layout)
    RelativeLayout mRlLayout;

    @BindView(R.id.statueImage)
    ImageView mStatueImage;
    @BindView(R.id.gongdancount)
    TextView mGongdancount;
    @BindView(R.id.Rl_gongdan)
    RelativeLayout mRlGongdan;
    @BindView(R.id.assertArea)
    TextView mAssertArea;
    @BindView(R.id.task)
    TextView mTask;
    @BindView(R.id.moercontent)
    TextView mMoercontent;
    @BindView(R.id.executingtime)
    TextView mExecutingtime;
    @BindView(R.id.executingmember)
    TextView mExecutingmember;
    @BindView(R.id.creator)
    TextView mCreator;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.ID)
    TextView mID;
    @BindView(R.id.btn)
    TextView mBtn;
    @BindView(R.id.assetName)
    TextView mAsserName;
    List<String> executorList = null;
    //创建人名、ID
    String creator, createid;
    //工单状态
    int statue;
    //工单类型
    String type;
    String typeId;
    String note;
    String starttime, endtime, image, workorderid, assertarea, positions;
    @BindView(R.id.statue)
    TextView mStatue;
    @BindView(R.id.starttime)
    TextView mStarttime;
    String actualstarttime, actualendtime, logcount;
    public static Activity instance = null;
    @BindView(R.id.assertImage)
    ImageView mAssertImage;
    @BindView(R.id.image)
    ImageView mImage;
    private WorkOrderPresenter mWorkOrderPresenter = new WorkOrderPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

    }

    @Override
    protected void initView() {

        mRlEdit.setVisibility(View.GONE);
        mBtn.setVisibility(View.GONE);


    }

    @Override
    protected int getLayout() {
        return R.layout.ac_workorderdetail;
    }

    @Override
    protected void initEventAndData() {
        initData();
    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        showProgress();
        mWorkOrderPresenter.initWorkOrderDetailData(mContext,mMyOkhttp,getIntent().getStringExtra("id"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WorkOrderDetailUPDATE_CODE:
                initData();
                break;
            case Constants.WORKORDERDETAIL_CODE:
                switch (statue) {
                    case 0:
                        mRlEdit.setVisibility(View.VISIBLE);
                        mBtn.setVisibility(View.VISIBLE);

                        mRlGongdan.setVisibility(View.GONE);
                        mStatue.setText("未开始");
                        mBtn.setText("开始执行");
                        mStarttime.setVisibility(View.GONE);
                        mStatueImage.setImageResource(R.drawable.notstart);
                        break;
                    case 1:
                        mRlGongdan.setVisibility(View.VISIBLE);
                        mGongdancount.setText(logcount);
                        mRlEdit.setVisibility(View.VISIBLE);
                        mBtn.setVisibility(View.VISIBLE);
                        mStarttime.setVisibility(View.VISIBLE);
                        mStarttime.setText(actualstarttime.substring(0, actualstarttime.length() - 3) + "开始");
                        mBtn.setText("完成工单");

                        mStatue.setText("执行中");
                        mStatueImage.setImageResource(R.drawable.executing);

                        break;
                    case 2:
                        mStarttime.setVisibility(View.VISIBLE);

                        mRlEdit.setVisibility(View.GONE);
                        mRlGongdan.setVisibility(View.VISIBLE);
                        mGongdancount.setText(logcount);

                        mStatue.setText("已完成");
                        mBtn.setVisibility(View.GONE);
                        mStarttime.setText(actualstarttime.substring(0, actualstarttime.length() - 3) + "至" + actualendtime.substring(0, actualendtime.length() - 3));

                        mStatueImage.setImageResource(R.drawable.executed);
                        break;
                }
                RequestOptions options = new RequestOptions();
                options.error(R.drawable.about_us);
                Glide.with(mContext).load(image).apply(options).into(mAssertImage);
                mExecutingtime.setText(starttime.substring(0, 10) + "至" + endtime.substring(0, 10));
                mAsserName.setText(positions);
                mAssertArea.setText(assertarea);
                mCreator.setText(creator);
                mID.setText(createid);
                String name = "";
                for (int i = 0; i < executorList.size(); i++) {
                    name += "  " + executorList.get(i);
                }
                mExecutingmember.setText(name);
                mType.setText(type);
                mTask.setText(note);


                switch (type) {
                    case "现场工单":
                        typeId = "3";

                        break;
                    case "随工工单":
                        typeId = "4";
                        break;
                    case "报警工单":
                        typeId = "2";
                        break;
                    case "计划工单":
                        typeId = "1";
                        break;
                }
                break;

        }
    }

    @OnClick({R.id.rl_back, R.id.rl_edit, R.id.Rl_gongdan, R.id.moercontent, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_edit:
                if (mSPUtils.getBoolean("hasLogin")) {
                    Intent intent = new Intent(mContext, EditWorkOrderActivity.class);
                    intent.putExtra("starttime", starttime.substring(0, 10));
                    intent.putExtra("endtime", endtime.substring(0, 10));
                    intent.putExtra("note", note);
                    intent.putExtra("type", type);
                    intent.putExtra("id", workorderid);
                    intent.putExtra("position", positions);
                    intent.putExtra("isOverdue", getIntent().getStringExtra("isOverdue"));
                    intent.putExtra("assertimage", image);
                    intent.putExtra("engineeringStatus", statue + "");

                    startActivity(intent);
                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("您尚未登录不能进行相关操作")
                            .setPositiveButtonColor("#FD4A2E")
                            .setPositiveButton("去登录", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    startActivity(new Intent(mContext, LoginActivity.class));

                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }
                break;
            case R.id.Rl_gongdan:
                Intent logintent = new Intent(mContext, LogActivity.class);
                logintent.putExtra("id", workorderid);
                logintent.putExtra("statue", statue + "");
                startActivity(logintent);
                break;
            case R.id.moercontent:
                break;
            case R.id.btn:
                if (mSPUtils.getBoolean("hasLogin")) {
                    switch (statue) {
                        case 0:
                            LemonHello.getInformationHello("提示", "开始执行工单？")
                                    .addAction(new LemonHelloAction("取消", new LemonHelloActionDelegate() {
                                        @Override
                                        public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                                            helloView.hide();
                                        }
                                    }))
                                    .addAction(new LemonHelloAction("开始执行", Color.RED, new LemonHelloActionDelegate() {
                                        @Override
                                        public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                                            helloView.hide();
                                            executWorkOrder();
                                        }

                                        /**
                                         * 开始执行工单
                                         */
                                        private void executWorkOrder() {
                                            String url = NetUrl.URLHeader + NetUrl.WorkOrder.CREATEWORKORDER;
                                            Map<String, String> map = new HashMap<>();
                                            map.put("createUserId", mSPUtils.getString("userid"));
                                            map.put("engineeringId", workorderid);
                                            map.put("engineering_create_date", TimeUtils.getCurTimeMills() + "");
                                            map.put("engineeringTypeIdfk", typeId);
                                            map.put("engineeringStatus", "1");
                                            mWorkOrderPresenter.editWorkOrder(mContext,mMyOkhttp,url,map);



                                        }
                                    }))
                                    .show(mContext);

                            break;
                        case 1:
                            LemonHello.getInformationHello("确定要完成工单？", "工单完成后将无法编辑或添加日志，是否完成？？")
                                    .addAction(new LemonHelloAction("取消", new LemonHelloActionDelegate() {
                                        @Override
                                        public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                                            helloView.hide();
                                        }
                                    }))
                                    .addAction(new LemonHelloAction("完成工单", Color.RED, new LemonHelloActionDelegate() {
                                        @Override
                                        public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                                            helloView.hide();
                                            completeWorkOrder();
                                        }

                                        /**
                                         * 完成工单
                                         */
                                        private void completeWorkOrder() {
                                            String url = NetUrl.URLHeader + NetUrl.WorkOrder.CREATEWORKORDER;
                                            Map<String, String> map = new HashMap<>();
                                            map.put("createUserId", mSPUtils.getString("userid"));
                                            map.put("engineeringId", workorderid);
                                            map.put("executor_actual_end_date", TimeUtils.getCurTimeMills() + "");
                                            map.put("engineeringTypeIdfk", typeId);
                                            map.put("engineeringStatus", "2");
                                            mWorkOrderPresenter.editWorkOrder(mContext,mMyOkhttp,url,map);


                                        }
                                    }))
                                    .show(mContext);

                            break;
                    }

                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("您尚未登录不能进行相关操作")
                            .setPositiveButtonColor("#FD4A2E")
                            .setPositiveButton("去登录", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    startActivity(new Intent(mContext, LoginActivity.class));

                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }


                break;
        }
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mRlLayout.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mRlLayout.setVisibility(View.VISIBLE);
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
        Toast.makeText(instance, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditWorkOrderSuccess() {
        EventBus.getDefault().post(new MyEvent(Constants.WORKORDERLISTUPDATE_CODE));
        EventBus.getDefault().post(new MyEvent(Constants.WorkOrderDetailUPDATE_CODE));
    }

    @Override
    public void onEditFailure() {

    }

    @Override
    public void onEditError() {

    }

    @Override
    public void onWorkOrderSuccess() {

    }

    @Override
    public void onWarningSuccess() {

    }

    @Override
    public void onWorkOrderSuccess(WorkOrderDeatil response) {
        hideProgress();
        creator = response.getObject().getCreateUserName();
        createid = response.getObject().getCreate_user_id();
        executorList = response.getObject().getExecutor();
        statue = response.getObject().getEngineering_status();
        image = NetUrl.DOCHeader + response.getObject().getEngineeringIcon();
        positions = response.getObject().getEngineering_asset_position();
        type = response.getObject().getEngineeringTypeName();
        note = response.getObject().getEngineeringNote();
        starttime = TimeUtils.milliseconds2String(response.getObject().getExecutorPlanBeginDate());
        endtime = TimeUtils.milliseconds2String(response.getObject().getExecutorPlanEndDate());
        workorderid = response.getObject().getEngineeringId();
        assertarea = response.getObject().getEngineering_asset_extent();
        logcount = response.getObject().getEngineeringLogCount();
        if (response.getObject().getEngineeringCreateDate() != 0) {
            actualstarttime = TimeUtils.milliseconds2String(response.getObject().getEngineeringCreateDate());
        }
        if (response.getObject().getExecutorActualEndDate() != 0) {
            actualendtime = TimeUtils.milliseconds2String(response.getObject().getExecutorActualEndDate());
        }
        EventBus.getDefault().post(new MyEvent(Constants.WORKORDERDETAIL_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mRlLayout.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        hideProgress();
        mRlLayout.setVisibility(View.GONE);
    }
}
