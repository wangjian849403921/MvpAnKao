package com.mvpankao.mvp.workflow;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.model.bean.WorkFlowBean;
import com.mvpankao.mvp.base.MVPBaseFragment;
import com.mvpankao.ui.adapter.WorkFlowAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
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

import static com.mvpankao.utils.Constants.NOTSTART_CODE;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WorkFlowNotStartFragment extends MVPBaseFragment<WorkFlowContract.View, WorkFlowPresenter> implements WorkFlowContract.View {
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.WorkFlowRecyclerView)
    SwipeRecyclerView mWorkFlowRecyclerView;
    private WorkFlowAdapter mAdapter;
    List<WorkFlowBean.ObjectBean.ListBean> list = new ArrayList<>();
    private int page = 1;
    int itemsize = 0;
    int refresh = 0;
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
            case NOTSTART_CODE:

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
        mPresenter.initData(getActivity(), mMyOkhttp, page,mSPUtils.getString("userid"),mSPUtils.getString("role"),"0");
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
    public void onFailure() {
        hideProgress();
        mWorkFlowRecyclerView.stopLoadingMore();
    }

    @Override
    public void onRequestFailed() {
        hideProgress();
        mWorkFlowRecyclerView.stopLoadingMore();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(Object responses) {
        WorkFlowBean response=(WorkFlowBean)responses;
       itemsize=response.getObject().getTotal();
        for (int i = 0; i < response.getObject().getList().size(); i++) {
            list.add(response.getObject().getList().get(i));
        }
        hideProgress();

        EventBus.getDefault().post(new MyEvent(Constants.NOTSTART_CODE));
    }




}
