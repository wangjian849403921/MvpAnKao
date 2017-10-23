package com.mvpankao.ui.activity;

import android.content.Intent;
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
import com.mvpankao.model.bean.StepBean;
import com.mvpankao.presenter.StepListPresenter;
import com.mvpankao.ui.adapter.StepAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.StepListView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2017/2/8
 */

public class StepListActivity extends BaseActivity implements StepListView {
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
    List<StepBean.ObjectBean> list = new ArrayList<>();
    StepAdapter mAdapter;
    List<String> imageList = null;
    List<LocalMedia> lists = new ArrayList<>();
    private StepListPresenter mStepListPresenter = new StepListPresenter(this);

    @Override
    protected void initView() {
        mTitle.setText("工作流");
        String statue = getIntent().getStringExtra("statue");
        if (statue.equals("0")) {
            mRlAdd.setVisibility(View.GONE);
        } else {
            mRlAdd.setVisibility(View.VISIBLE);
        }
        showProgress();
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
        mStepListPresenter.initData(mContext, mMyOkhttp, list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.STEP_CODE:
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new StepAdapter(mContext, list);
                    mStepsRecyclerView.setAdapter(mAdapter);
                }
                mAdapter.setOnItemOnClick(new StepAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {


                        lists.clear();
                        Intent intent = new Intent(mContext, StepDetailActiviry.class);
                        intent.putExtra("content", list.get(position).getWorkflowStepComment());
                        String statue = list.get(position).getWorkflowStepOperationStatus();
                        if (statue.equals("3")) {
                            intent.putExtra("reason", list.get(position).getWorkflowStepVerifyComment());
                        } else {
                            intent.putExtra("reason", "");

                        }

                        if (list.get(position).getIconList() != null) {
                            imageList = list.get(position).getIconList();
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
            case Constants.STEP_CODE2:
                list.clear();
                initData();
                break;
        }
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mStepsRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mStepsRecyclerView.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.rl_back, R.id.rl_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add:
                Intent intent = new Intent(mContext, UploadTaskActivity.class);
                intent.putExtra("step", getIntent().getStringExtra("step"));
                intent.putExtra("id", getIntent().getStringExtra("id"));
                intent.putExtra("stepid", getIntent().getStringExtra("stepid"));
                startActivity(intent);

                break;
        }
    }

    @Override
    public String getStepId() {
        return getIntent().getStringExtra("stepid");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.STEP_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
    }

    @Override
    public void onError() {
        hideProgress();
    }
}
