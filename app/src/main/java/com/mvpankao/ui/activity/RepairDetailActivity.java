package com.mvpankao.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.model.bean.RepairDetailBean;
import com.mvpankao.presenter.RepairPresenter;
import com.mvpankao.ui.adapter.RepairDetailAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.RepairDetailView;
import com.mvpankao.widget.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 报修详情
 * Created by wangjian
 * On  2016/12/28
 */

public class RepairDetailActivity extends BaseActivity implements RepairDetailView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.statue)
    TextView mStatue;
    @BindView(R.id.repairmember)
    TextView mRepairmember;
    @BindView(R.id.estimatetime)
    TextView mEstimatetime;
    @BindView(R.id.lv_list)
    ListView mLvList;
    @BindView(R.id.line_name)
    TextView mLineName;
    @BindView(R.id.fault_type)
    TextView mFaultType;
    @BindView(R.id.address)
    TextView mAddress;
    @BindView(R.id.detailaddress)
    TextView mDetailaddress;
    @BindView(R.id.company)
    TextView mCompany;
    @BindView(R.id.member)
    TextView mMember;
    @BindView(R.id.phonenumber)
    TextView mPhonenumber;
    @BindView(R.id.remarks)
    TextView mRemarks;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    RepairDetailAdapter mRepairDetailAdapter;
    List<RepairDetailBean.ObjectBean.RepailogListBean> list = new ArrayList<>();
    String repairmember, linename, typename, area, detailaddress, company, phonenum, remarks;
    String starttime, endtime;
    List<String> executorList = null;
    int statue;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.text2)
    TextView mText2;
    private RepairPresenter mRepairPresenter = new RepairPresenter(this);

    @Override
    protected void initView() {
        mTitle.setText("报修详情");
        mScrollView.smoothScrollTo(0, 0);
        mLvList.setDividerHeight(0);
        showProgress();

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_repairdetail;
    }

    @Override
    protected void initEventAndData() {
        initData();
    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        mRepairPresenter.initDetail(mContext, mMyOkhttp, list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.REPAIRDETAIL_CODE:
                switch (statue) {
                    case 0:
                        mStatue.setText("未开始");
                        mStatue.setTextColor(Color.parseColor("#999999"));
                        break;
                    case 1:
                        mStatue.setText("检修中");
                        mStatue.setTextColor(Color.parseColor("#ff2626"));
                        break;
                    case 2:
                        mStatue.setText("已完成");
                        mStatue.setTextColor(Color.parseColor("#30a6e3"));
                        break;
                }
                String members = "";
                if (executorList != null) {
                    for (int i = 0; i < executorList.size(); i++) {
                        members += "," + executorList.get(i);
                    }
                    mRepairmember.setText(members.substring(1, members.length()));
                } else {
                    mRepairmember.setText("");
                }


                if (!StringUtils.isEmpty(starttime)) {
                    mEstimatetime.setText(starttime + "至" + endtime);
                }

                if (!StringUtils.isEmpty(linename)) {
                    mLineName.setText(linename);

                } else {
                    mLineName.setText("");
                }
                if (!StringUtils.isEmpty(typename)) {
                    mFaultType.setText(typename);
                }
                if (!StringUtils.isEmpty(area)) {
                    mAddress.setText(area);
                } else {
                    mAddress.setText("");

                }
                if (!StringUtils.isEmpty(detailaddress)) {
                    mDetailaddress.setText(detailaddress);
                } else {
                    mDetailaddress.setText("");
                }
                if (!StringUtils.isEmpty(company)) {
                    mCompany.setText(company);
                } else {
                    mCompany.setText("");
                }
                if (!StringUtils.isEmpty(repairmember)) {
                    mMember.setText(repairmember);

                } else {
                    mMember.setText("");
                }
                if (!StringUtils.isEmpty(phonenum)) {
                    mPhonenumber.setText(phonenum);

                } else {
                    mPhonenumber.setText("");
                }
                if (!StringUtils.isEmpty(remarks)) {
                    mRemarks.setText(remarks);

                } else {
                    mRemarks.setText("");
                }
                mRepairDetailAdapter = new RepairDetailAdapter(mContext, list);
                mLvList.setAdapter(mRepairDetailAdapter);
                this.setListViewHeightBasedOnChildren(mLvList);
                break;
        }
    }

    @OnClick({R.id.rl_back, R.id.contact_Customer_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.contact_Customer_service:
                break;
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mScrollView.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.contact_Customer_service)
    public void onClick() {
        CallPhoneUtils.callphone(mContext);

    }

    @Override
    public String getRepairId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        hideProgress();

    }

    @Override
    public void onError() {
        hideProgress();
    }

    @Override
    public void onSuccess(RepairDetailBean response) {
        repairmember = response.getObject().getRepair().getUsername();
        linename = response.getObject().getRepair().getRepairCircuit();
        typename = response.getObject().getRepair().getRepair_type_name();
        area = response.getObject().getRepair().getArea();
        detailaddress = response.getObject().getRepair().getDetailaddress();
        company = response.getObject().getRepair().getCompany();
        executorList = response.getObject().getRepairExecutors();
        phonenum = response.getObject().getRepair().getPhoneNum();
        remarks = response.getObject().getRepair().getRemarks();
        starttime = TimeUtils.milliseconds2String(response.getObject().getRepair().getRepairPlanBeginDate());
        endtime = TimeUtils.milliseconds2String(response.getObject().getRepair().getRepairPlanEndDate());
        statue = response.getObject().getRepair().getStatue();
        hideProgress();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new MyEvent(Constants.REPAIRDETAIL_CODE));
            }
        }, 300);
    }
}
