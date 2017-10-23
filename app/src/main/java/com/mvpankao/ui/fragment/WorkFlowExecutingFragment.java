package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.model.bean.WorkFlowBean;
import com.mvpankao.presenter.WorkFlowPresenter;
import com.mvpankao.ui.activity.WorkFlowDetailActivity;
import com.mvpankao.ui.adapter.WorkFlowAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WorkFlowView;
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

import static com.mvpankao.utils.Constants.EXECUTING_CODE;


/**
 * 执行中（工作流）
 * Created by wangjian
 * On  2016/11/24
 */

public class WorkFlowExecutingFragment extends BaseFragment implements WorkFlowView {

    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.WorkFlowRecyclerView)
    SwipeRecyclerView mWorkFlowRecyclerView;
    private WorkFlowAdapter mAdapter;
    List<WorkFlowBean.ObjectBean.ListBean> list = new ArrayList<>();
    private int page = 1;
    int itemsize = 0;
    int refresh = 0;
    private WorkFlowPresenter mWorkFlowPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkFlowPresenter = new WorkFlowPresenter(this);
    }

    @Override
    protected void initView() {
        showProgress();

        mWorkFlowRecyclerView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mWorkFlowRecyclerView.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mWorkFlowRecyclerView.setLoadMoreEnable(true);
        new RecycleEmptyViewHelper(mWorkFlowRecyclerView,"暂无数据");
        mWorkFlowRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress();
                        if (itemsize <= Constants.SIZE) {
                            mWorkFlowRecyclerView.stopLoadingMore();
                            mWorkFlowRecyclerView.setLoadMoreEnable(false);
                        } else {
                            mWorkFlowRecyclerView.setLoadMoreEnable(true);
                        }
                        refresh = 1;
                        page = 1;
                        list.clear();
                        initData();
                        mWorkFlowRecyclerView.setLoadMoreEnable(true);
                        mWorkFlowRecyclerView.complete();
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
                            mWorkFlowRecyclerView.onNoMore("已加载所有数据!");
                        } else {
                            mWorkFlowRecyclerView.stopLoadingMore();
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
    protected int getLayoutId() {
        return R.layout.fg_workflow;
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

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case EXECUTING_CODE:

                if (itemsize <= Constants.SIZE) {
                    mWorkFlowRecyclerView.stopLoadingMore();
                    mWorkFlowRecyclerView.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new WorkFlowAdapter(mContext, list);
                    mWorkFlowRecyclerView.setAdapter(mAdapter);

                }
                mAdapter.setOnItemOnClick(new WorkFlowAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View v, int position) {
                        Intent intent = new Intent(getActivity(), WorkFlowDetailActivity.class);
                        intent.putExtra("id", list.get(position).getId());
                        intent.putExtra("name", list.get(position).getWorkflowProName());
                        intent.putExtra("image", list.get(position).getWorkflowProIcon());
                        String starttime = TimeUtils.milliseconds2String(list.get(position).getWorkflowProBeginDate());
                        intent.putExtra("time", starttime);
                        startActivity(intent);


                    }
                });
                break;
        }
    }

    private void initData() {
        if(mActivity.isDestroyed()){
            return;
        }
        mWorkFlowPresenter.initData(getActivity(), mMyOkhttp, list);
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mWorkFlowRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mWorkFlowRecyclerView.setVisibility(View.VISIBLE);
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
    public String getStatue() {
        return "1";
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
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();

        EventBus.getDefault().post(new MyEvent(Constants.EXECUTING_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mWorkFlowRecyclerView.stopLoadingMore();
    }

    @Override
    public void onError() {
        hideProgress();
        mWorkFlowRecyclerView.stopLoadingMore();
    }
}
