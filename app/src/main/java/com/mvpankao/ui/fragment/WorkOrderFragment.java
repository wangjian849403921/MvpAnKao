package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.model.bean.WorkOrderGroupItem;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.ui.activity.WorkOrderDetailActivity;
import com.mvpankao.ui.adapter.WorkOrderAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WorkOrderView;
import com.mvpankao.widget.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 工单
 * Created by wangjian
 * On  2016/12/9
 */

public class WorkOrderFragment extends BaseFragment implements WorkOrderView {
    @BindView(R.id.explistview)
    ExpandableListView mExplistview;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;

    WorkOrderAdapter mWorkOrderAdapter;
    private List<WorkOrderGroupItem> dataList = new ArrayList<WorkOrderGroupItem>();
    private int expandFlag = -1;//控制列表的展开
    int page = 1;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private WorkOrderPresenter mWorkOrderPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkOrderPresenter = new WorkOrderPresenter(this);
    }

    @Override
    protected void initView() {
        showProgress();
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
    protected int getLayoutId() {
        return R.layout.fg_workorder;
    }

    @Override
    protected void initEventAndData() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });
    }

    private void initData() {
        if(mActivity.isDestroyed()){
            return;
        }
        mWorkOrderPresenter.initData(getActivity(), mMyOkhttp, dataList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WORKORDERLISTUPDATE_CODE:
                dataList.clear();
                initData();
                break;
            case Constants.WORKORDERLIST_CODE:

                mWorkOrderAdapter = new WorkOrderAdapter(getActivity(), dataList);
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
                        Intent intent = new Intent(getActivity(), WorkOrderDetailActivity.class);
                        String workorderid = dataList.get(groupPosition).getChildItem(childPosition).getId();
                        String name = dataList.get(groupPosition).getChildItem(childPosition).getName();
                        String statue = dataList.get(groupPosition).getStatue();
                        String isOverdue = dataList.get(groupPosition).getChildItem(childPosition).getOverdue();


                        intent.putExtra("id", workorderid);
                        intent.putExtra("isOverdue", isOverdue);
                        intent.putExtra("starttime", workorderid);
                        intent.putExtra("name", name);
                        intent.putExtra("statue", statue);
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
    public void setJsonArray(JSONArray jsonArray) {

    }

    @Override
    public String getUserId() {
        return mSPUtils.getString("userid");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWorkOrderSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.WORKORDERLIST_CODE));
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
