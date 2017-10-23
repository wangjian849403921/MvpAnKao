package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.model.bean.RepairBean;
import com.mvpankao.presenter.RepairPresenter;
import com.mvpankao.ui.adapter.MyRepairAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.RepairView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.RecycleEmptyViewHelper;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 我的报修
 * Created by wangjian
 * On  2016/11/24
 */

public class MyRepairActivity extends BaseActivity implements RepairView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContact_Customer_service;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.MyRepairRecyclerView)
    SwipeRecyclerView mMyRepairRecyclerView;

    private MyRepairAdapter mAdapter;
    List<RepairBean.ObjectBean.DataBean.ListBean> list = new ArrayList<>();
    private int page = 1;
    int itemsize = 0;
    int refresh = 0;
    private RepairPresenter mRepairPresenter = new RepairPresenter(this);

    @Override
    protected void initView() {
        showProgress();
        mTitle.setText("我的报修");
        mMyRepairRecyclerView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mMyRepairRecyclerView.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        new RecycleEmptyViewHelper(mMyRepairRecyclerView, "暂无数据");
        mMyRepairRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (itemsize <= Constants.SIZE) {
                            mMyRepairRecyclerView.stopLoadingMore();
                            mMyRepairRecyclerView.setLoadMoreEnable(false);
                        } else {
                            mMyRepairRecyclerView.setLoadMoreEnable(true);
                        }
                        refresh = 1;
                        page = 1;
                        list.clear();
                        initData();
                        mMyRepairRecyclerView.setLoadMoreEnable(true);
                        mMyRepairRecyclerView.complete();
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    }


                }, 3000);

            }


            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        initData();
                        if (list.size() >= itemsize && list.size() >= Constants.SIZE) {
                            mMyRepairRecyclerView.onNoMore("已加载所有数据!");
                        } else {
                            mMyRepairRecyclerView.stopLoadingMore();
                            if (mAdapter != null) {
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }, 1000);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_myrepair;
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

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.MYREPAIR_CODE:

                if (itemsize <= Constants.SIZE) {
                    mMyRepairRecyclerView.stopLoadingMore();
                    mMyRepairRecyclerView.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new MyRepairAdapter(mContext, list);
                    mMyRepairRecyclerView.setAdapter(mAdapter);

                }
                mAdapter.setOnItemOnClick(new MyRepairAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View v, int position) {

                        Intent intent = new Intent(mContext, RepairDetailActivity.class);
                        String id = list.get(position).getId();
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        mRepairPresenter.initData(mContext, mMyOkhttp, list);
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mMyRepairRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mMyRepairRecyclerView.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.contact_Customer_service
            , R.id.rl_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    public String getUserId() {
        return mSPUtils.getString("userid");
    }

    @Override
    public String getRole() {
        return mSPUtils.getString("role");
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void getItemSize(int size) {
        itemsize = size;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.MYREPAIR_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mMyRepairRecyclerView.stopLoadingMore();
    }

    @Override
    public void onError() {
        hideProgress();
        mMyRepairRecyclerView.stopLoadingMore();
    }

}
