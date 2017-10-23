package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.model.bean.WorkOrderGroupItem;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.ui.adapter.WorkOrderAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WorkOrderView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.ExpandListViewEmptyViewHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * Created by wangjian
 * On  2017/2/14
 */

public class WorkOrderSearchActivity extends BaseActivity implements WorkOrderView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.explistview)
    ExpandableListView mExplistview;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    WorkOrderAdapter mWorkOrderAdapter;
    private List<WorkOrderGroupItem> dataList = new ArrayList<WorkOrderGroupItem>();
    private int expandFlag = -1;//控制列表的展开
    int page = 1;
    private WorkOrderPresenter mWorkOrderPresenter=new WorkOrderPresenter(this);
    @Override
    protected void initView() {
        mTitle.setText("工单筛选结果");
        mContactCustomerService.setVisibility(View.GONE);
        showProgress();
        ExpandListViewEmptyViewHelper helper=new ExpandListViewEmptyViewHelper(mExplistview,"没有查到匹配的工单");
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setDistanceToTriggerSync(100);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataList.clear();
                        initData();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_workorder;
    }

    @Override
    protected void initEventAndData() {
      runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });

    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        String url = NetUrl.URLHeader + NetUrl.WorkOrder.WorkOrderList;
        Map<String, String> map = new HashMap<>();
        map.put("createUserId", mSPUtils.getString("userid"));
        map.put("page", page + "");

        if (!StringUtils.isEmpty(getIntent().getStringExtra("engineeringTypeIdfk"))){
            map.put("engineeringTypeIdfk", getIntent().getStringExtra("engineeringTypeIdfk"));
        }
        if (!StringUtils.isEmpty(getIntent().getStringExtra("engineeringStatus"))){
            map.put("engineeringStatus",getIntent().getStringExtra("engineeringStatus"));
        }
        if (!StringUtils.isEmpty(getIntent().getStringExtra("engineeringId"))){
            map.put("engineeringId",getIntent().getStringExtra("engineeringId"));
        }
        LogUtils.d(map);
        LogUtils.d(url);

     mWorkOrderPresenter.initSearchData(mContext,mMyOkhttp,dataList,map);
    }
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WORKORDERSELECT_CODE:

                mWorkOrderAdapter = new WorkOrderAdapter(mContext, dataList);
                mExplistview.setAdapter(mWorkOrderAdapter);
                mExplistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                int groupPosition, long id) {

                        if (expandFlag == -1) {
                            mExplistview.expandGroup(groupPosition);
                            expandFlag = groupPosition;
                        } else {
                            mExplistview.collapseGroup(groupPosition);
                            // 展开被选的group
//                    mExplistview.expandGroup(groupPosition);
                            expandFlag = -1;
                        }
                        return true;
                    }
                });
                mExplistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        Intent intent = new Intent(mContext, WorkOrderDetailActivity.class);
                        String workorderid = dataList.get(groupPosition).getChildItem(childPosition).getId();
                        String name = dataList.get(groupPosition).getChildItem(childPosition).getName();
                        intent.putExtra("id", workorderid);
                        intent.putExtra("name", name);
                        startActivity(intent);

                        return true;
                    }
                });

                break;
        }
    }
    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mExplistview.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mExplistview.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_back)
    public void onClick() {
        finish();
    }

    @Override
    public void setJsonArray(JSONArray jsonArray) {

    }

    @Override
    public String getUserId() {
        return mSPUtils.getString("userid");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWorkOrderSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.WORKORDERSELECT_CODE));
    }

    @Override
    public void onWarningSuccess() {

    }

    @Override
    public void onWorkOrderSuccess(WorkOrderDeatil response) {

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
    public void onEditWorkOrderSuccess() {

    }

    @Override
    public void onEditFailure() {

    }

    @Override
    public void onEditError() {

    }
}
