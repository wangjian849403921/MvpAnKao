package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.base.BaseAdapter;
import com.mvpankao.model.bean.WarningBean;
import com.mvpankao.model.bean.WarningDetailBean;
import com.mvpankao.presenter.WarningPresenter;
import com.mvpankao.ui.adapter.WarningAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WarningView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2017/2/15
 */

public class WarningSearchActivity extends BaseActivity implements WarningView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.WarningList)
    SwipeRecyclerView mWarningList;
    List<WarningBean.ObjectBean.ListBean> list = new ArrayList<>();
    WarningAdapter mAdapter;
    int page = 1;
    int itemsize = 0;

    private WarningPresenter mWarningPresenter = new WarningPresenter(this);

    @Override
    protected void initView() {
        mTitle.setText("筛选结果");
        mContactCustomerService.setVisibility(View.GONE);
        View emptyView = View.inflate(mContext, R.layout.empty_view, null);
        mWarningList.setEmptyView(emptyView);
        showProgress();
        mWarningList.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);

        mWarningList.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mWarningList.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress();
                        if (itemsize <= Constants.SIZE) {
                            mWarningList.stopLoadingMore();
                            mWarningList.setLoadMoreEnable(false);
                        } else {
                            mWarningList.setLoadMoreEnable(true);
                        }
                        page = 1;
                        list.clear();
                        initData();
                        mWarningList.setLoadMoreEnable(true);
                        mWarningList.complete();
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
                            mWarningList.onNoMore("已加载所有数据!");
                        } else {
                            mWarningList.stopLoadingMore();
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
        return R.layout.ac_warningsearch;

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
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        if (!StringUtils.isEmpty(getIntent().getStringExtra("level"))) {
            map.put("alarm_level", getIntent().getStringExtra("level"));
        }
        if (!StringUtils.isEmpty(getIntent().getStringExtra("statue"))) {
            map.put("alarm_status", getIntent().getStringExtra("statue"));
        }
        if (!StringUtils.isEmpty(getIntent().getStringExtra("type"))) {
            map.put("alarm_category", getIntent().getStringExtra("type"));
        }
        LogUtils.d(map);

        mWarningPresenter.initSearchData(mContext,mMyOkhttp,list,map);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WARNINGSELECT_CODE:
                if (itemsize <= Constants.SIZE) {
                    mWarningList.stopLoadingMore();
                    mWarningList.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new WarningAdapter(mContext, list);
                    mWarningList.setAdapter(mAdapter);

                }
                mAdapter = new WarningAdapter(mContext, list);
                mWarningList.setAdapter(mAdapter);
                mAdapter.setOnItemOnClick(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {
                        Intent intent = new Intent(mContext, WarningDeatilActivity.class);
                        String id = list.get(position).getId();
                        intent.putExtra("id", id);
                        startActivity(intent);

                    }
                });

                break;
        }
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mWarningList.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mWarningList.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.rl_back)
    public void onClick() {
        finish();
    }

    @Override
    public String getWarningId() {
        return null;
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public void getItemSize(int size) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(WarningDetailBean response) {

    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.WARNINGSELECT_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mWarningList.stopLoadingMore();
    }

    @Override
    public void onError() {
        hideProgress();
        mWarningList.stopLoadingMore();
    }
}
