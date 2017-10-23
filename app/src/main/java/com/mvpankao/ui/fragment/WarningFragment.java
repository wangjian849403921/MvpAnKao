package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseAdapter;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.model.bean.WarningBean;
import com.mvpankao.model.bean.WarningDetailBean;
import com.mvpankao.presenter.WarningPresenter;
import com.mvpankao.ui.activity.WarningDeatilActivity;
import com.mvpankao.ui.adapter.WarningAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WarningView;
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


/**
 * 警报
 * Created by wangjian
 * On  2016/12/9
 */

public class WarningFragment extends BaseFragment implements WarningView {
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
    protected int getLayoutId() {
        return R.layout.fg_warning;
    }

    @Override
    protected void initView() {
        showProgress();
        mWarningList.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);

        mWarningList.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        new RecycleEmptyViewHelper(mWarningList,"暂无数据");
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
        mWarningPresenter.initData(mContext, mMyOkhttp, list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WARNINGLIST_CODE:
                if (itemsize <= Constants.SIZE) {
                    mWarningList.stopLoadingMore();
                    mWarningList.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new WarningAdapter(getActivity(), list);
                    mWarningList.setAdapter(mAdapter);

                }
                mAdapter = new WarningAdapter(getActivity(), list);
                mWarningList.setAdapter(mAdapter);
                mAdapter.setOnItemOnClick(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), WarningDeatilActivity.class);
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

    @Override
    public String getWarningId() {
        return null;
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
    public void onSuccess(WarningDetailBean response) {

    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.WARNINGLIST_CODE));
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
