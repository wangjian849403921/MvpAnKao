package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.model.bean.ParentEntity;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.ui.adapter.ParentAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WorkOrderView;
import com.mvpankao.widget.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2017/1/20
 */

public class AssetDetailActivity extends BaseActivity implements WorkOrderView, ExpandableListView.OnGroupExpandListener,
        ParentAdapter.OnChildTreeViewClickListener {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.eList)
    ExpandableListView mEList;
    //
    private ArrayList<ParentEntity> parents = new ArrayList<ParentEntity>();

    private ParentAdapter adapter;
    private WorkOrderPresenter mWorkOrderPresenter = new WorkOrderPresenter(this);

    @Override
    protected void initView() {
        showProgress();
        mTitle.setText(getIntent().getStringExtra("name"));
        mContactCustomerService.setVisibility(View.GONE);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        mSwipeRefreshLayout.setDistanceToTriggerSync(100);
////        refreshLayout.setSize(1);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        parents.clear();
//                        initData();
//                        mSwipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 3000);
//            }
//        });
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_assert_detail;
    }

    @Override
    protected void initEventAndData() {
        initData();

    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        mWorkOrderPresenter.initAssertArea(mContext, mMyOkhttp, parents);

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.AssertDetail_CODE:
                adapter = new ParentAdapter(mContext, parents);

                mEList.setAdapter(adapter);
                initEList();

                break;
            case Constants.ASSERTLISTUPDATE_CODE:
                parents.clear();
                initData();
                break;
        }
    }

    /**
     * @author wangjian
     * <p>
     * 初始化ExpandableListView
     */
    private void initEList() {


//		eList.setOnGroupExpandListener(this);


        final int groupCount = mEList.getCount();

        for (int i = 0; i < groupCount; i++) {

            mEList.expandGroup(i);

        }
        mEList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                String parentid = parents.get(groupPosition).getGroupId();
                String name = parents.get(groupPosition).getGroupName();
                String level = parents.get(groupPosition).getLevel();
                String image = parents.get(groupPosition).getIcon();

                Intent intent = new Intent(mContext, ParentActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("id", parentid);
                intent.putExtra("level", level);
                intent.putExtra("image", image);

                startActivity(intent);


                return true;
            }
        });
        adapter.setOnChildTreeViewClickListener(this);

    }

    /**
     * @author wangjian
     * <p>
     * 点击子ExpandableListView的子项时，回调本方法，根据下标获取值来做相应的操作
     */
    @Override
    public void onClickPosition(int parentPosition, int groupPosition,
                                int childPosition) {
        // do something
        String id = parents.get(parentPosition).getChilds()
                .get(groupPosition).getChildIds().get(childPosition)
                .toString();

        String name = parents.get(parentPosition).getChilds()
                .get(groupPosition).getChildNames().get(childPosition)
                .toString();
        String level = parents.get(parentPosition).getChilds()
                .get(groupPosition).getChildLevels().get(childPosition)
                .toString();
        String image = parents.get(parentPosition).getChilds()
                .get(groupPosition).getChildIcons().get(childPosition)
                .toString();
        Intent intent = new Intent(mContext, ParentActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        intent.putExtra("level", level);
        intent.putExtra("image", image);
        startActivity(intent);

    }

    /**
     * @author wangjian
     * <p>
     * 展开一项，关闭其他项，保证每次只能展开一项
     */
    @Override
    public void onGroupExpand(int groupPosition) {
        for (int i = 0; i < parents.size(); i++) {
            if (i != groupPosition) {
                mEList.expandGroup(groupPosition);
            }
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

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mEList.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mEList.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void setJsonArray(JSONArray jsonArray) {

    }

    @Override
    public String getUserId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWorkOrderSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.AssertDetail_CODE));
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
