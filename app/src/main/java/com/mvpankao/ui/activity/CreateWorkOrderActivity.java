package com.mvpankao.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.base.BaseAdapter;
import com.mvpankao.model.bean.CreateOrderAssert;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.ui.adapter.WorkOrderAssertAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WorkOrderView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 创建工单
 * Created by wangjian
 * On  2017/1/3
 */

public class CreateWorkOrderActivity extends BaseActivity implements WorkOrderView {
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.customerList)
    SwipeRecyclerView mCustomerList;
    @BindView(R.id.ll_List)
    LinearLayout mLlList;
    List<CreateOrderAssert.ObjectBean> list = new ArrayList<>();
    WorkOrderAssertAdapter mWorkOrderAssertAdapter;
    @BindView(R.id.role)
    TextView mRole;
    private WorkOrderPresenter mWorkOrderPresenter = new WorkOrderPresenter(this);

    @Override
    protected void initView() {
        showProgress();
        String role = mSPUtils.getString("role");
        if (role.equals("1")) {
            mRole.setText("选择资产");
            initData();
        } else {
            mRole.setText("选择客户");

        }


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                hideProgress();
//            }
//        }, 1000);

        mCustomerList.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mCustomerList.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCustomerList.setRefreshEnable(false);
        mCustomerList.setLoadMoreEnable(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_createworkorder;
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

    /**
     * 选择资产列表
     */
    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        mWorkOrderPresenter.initWorkOrderAssert(mContext, mMyOkhttp, list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.CreateOrderAssert_CODE:
                mWorkOrderAssertAdapter = new WorkOrderAssertAdapter(mContext, list);
                mCustomerList.setAdapter(mWorkOrderAssertAdapter);
                mWorkOrderAssertAdapter.setOnItemOnClick(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {

                        Intent intent = new Intent(mContext, CreateWorkOrderActivity2.class);
                        intent.putExtra("name", list.get(position).getAssetName());
                        intent.putExtra("id", list.get(position).getId());

                        startActivity(intent);
                    }
                });
                break;
        }
    }

    @OnClick(R.id.rl_back)
    public void onClick() {
        finish();
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mLlList.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mLlList.setVisibility(View.VISIBLE);
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
        EventBus.getDefault().post(new MyEvent(Constants.CreateOrderAssert_CODE));
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
        mLlList.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        hideProgress();
        mLlList.setVisibility(View.GONE);
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
