package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.entity.LocalMedia;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.model.bean.WorkOrderLogBean;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.ui.adapter.LogAdapter;
import com.mvpankao.ui.adapter.StepAdapter;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 工单日志
 * Created by wangjian
 * On  2017/1/5
 */

public class LogActivity extends BaseActivity implements WorkOrderView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.rl_add)
    RelativeLayout mRlAdd;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.StepsRecyclerView)
    SwipeRecyclerView mStepsRecyclerView;
    List<WorkOrderLogBean.ObjectBean> list = new ArrayList<>();
    LogAdapter mAdapter;
    List<String> imageList = null;
    List<LocalMedia> lists = new ArrayList<>();
    private WorkOrderPresenter mWorkOrderPresenter = new WorkOrderPresenter(this);

    @Override

    protected void initView() {
        mTitle.setText("工单日志");
        String statue = getIntent().getStringExtra("statue");
        if (statue.equals("2")) {
            mRlAdd.setVisibility(View.GONE);
        } else {
            mRlAdd.setVisibility(View.VISIBLE);
        }
        showProgress();
        View emptyView = View.inflate(mContext, R.layout.empty_view, null);
        mStepsRecyclerView.setEmptyView(emptyView);
        mStepsRecyclerView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mStepsRecyclerView.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mStepsRecyclerView.setLoadMoreEnable(false);
        mStepsRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress();

                        list.clear();
                        initData();
                        mStepsRecyclerView.complete();
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    }


                }, 1000);

            }


            @Override
            public void onLoadMore() {
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_steplist;
    }

    @Override
    protected void initEventAndData() {
        initData();

    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        mWorkOrderPresenter.initWorkOrderLog(mContext,mMyOkhttp,list);

    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mStepsRecyclerView.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {

            case Constants.LogLISTUPDATE_CODE:
                list.clear();
                initData();
                break;
            case Constants.LogLIST_CODE:
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new LogAdapter(mContext, list);
                    mStepsRecyclerView.setAdapter(mAdapter);
                }
                mAdapter.setOnItemOnClick(new StepAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {


                        lists.clear();
                        Intent intent = new Intent(mContext, LogDetailActiviry.class);
                        intent.putExtra("content", list.get(position).getLogComment());
                        intent.putExtra("statue", getIntent().getStringExtra("statue"));


                        if (list.get(position).getImgUrlList() != null) {
                            imageList = list.get(position).getImgUrlList();
                            for (int i = 0; i < imageList.size(); i++) {
                                LocalMedia path = new LocalMedia();
                                path.setPath(NetUrl.DOCHeader + imageList.get(i));
                                lists.add(path);
                            }
                            intent.putExtra("imagelist", (Serializable) lists);
                            intent.putExtra("have", "1");

                        } else {
                            intent.putExtra("have", "0");
                        }
                        startActivity(intent);
                    }
                });
                break;

        }
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mStepsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.rl_add})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add:
                Intent intent = new Intent(mContext, CreateLogActivity.class);
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("statue", getIntent().getStringExtra("statue"));
                intent.putExtra("have", "0");
                startActivity(intent);
                break;
        }
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
        EventBus.getDefault().post(new MyEvent(Constants.LogLIST_CODE));
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
