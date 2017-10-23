package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseAdapter;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.model.bean.MyOrder;
import com.mvpankao.presenter.OrderPresenter;
import com.mvpankao.ui.activity.OrderDetailActivity;
import com.mvpankao.ui.adapter.OrderAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.OrderView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * （我的订单）待安装
 * Created by wangjian
 * On  2016/11/29
 */

public class OrderUnCompletedFragment extends BaseFragment implements OrderView {
    @BindView(R.id.OrderList)
    SwipeRecyclerView mOrderList;
    OrderAdapter mAdapter;
    List<MyOrder.ObjectBean.ListBean> list = new ArrayList<>();

    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    View emptyView;
    int page = 1;
    int itemsize = 0;
    private OrderPresenter mOrderPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrderPresenter = new OrderPresenter(this);
    }

    @Override
    protected void initView() {
        hideProgress();
        mOrderList.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);

        mOrderList.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        emptyView = View.inflate(getActivity(), R.layout.empty_layout, null);
        mOrderList.setEmptyView(emptyView);


        mOrderList.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress();
                        if (itemsize <= Constants.SIZE) {
                            mOrderList.stopLoadingMore();
                            mOrderList.setLoadMoreEnable(false);
                        } else {
                            mOrderList.setLoadMoreEnable(true);
                        }

                        page = 1;
                        list.clear();
                        initData();
                        mOrderList.setLoadMoreEnable(true);
                        mOrderList.complete();
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
                            mOrderList.onNoMore("已加载所有数据!");
                        } else {
                            mOrderList.stopLoadingMore();
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
        return R.layout.fg_order;
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
        mOrderPresenter.initData(getActivity(), mMyOkhttp, list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WAIT_CODE:

                if (itemsize <= Constants.SIZE) {
                    mOrderList.stopLoadingMore();
                    mOrderList.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new OrderAdapter(getActivity(), list);
                    mOrderList.setAdapter(mAdapter);

                }
                mAdapter.setOnItemOnClick(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        intent.putExtra("id", list.get(position).getId());
                        startActivity(intent);
                    }
                });
                mAdapter.setOnItemLongClickListener(new BaseAdapter.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(View view, final int position) {
                        new AlertDialog(mContext).builder().setTitle("删除订单")
                                .setMsg("您确定要删除该订单吗？")
                                .setPositiveButtonColor("#FD4A2E")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        deleteOrder(list.get(position).getId());


                                    }

                                    /**
                                     * 删除订单
                                     * @param id
                                     */
                                    private void deleteOrder(String id) {
                                        mOrderPresenter.deleteOrder(getActivity(), mMyOkhttp, id, position);
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                    }
                });
                break;
        }
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mOrderList.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mOrderList.setVisibility(View.VISIBLE);
    }

    @Override
    public String getUserId() {
        return mSPUtils.getString("userid");
    }

    @Override
    public String getStatue() {
        return "2";
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
        EventBus.getDefault().post(new MyEvent(Constants.WAIT_CODE));
    }

    @Override
    public void ondeleteSuccess(final int position) {
        new AlertDialog(mContext).builder().setTitle("提示")
                .setMsg("删除成功！")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(position);
                        mAdapter.notifyDataSetChanged();

                    }
                }).show();
    }

    @Override
    public void onFailure() {
        hideProgress();
        mOrderList.stopLoadingMore();
    }

    @Override
    public void ondeleteFailure() {

    }

    @Override
    public void onError() {
        hideProgress();
        mOrderList.stopLoadingMore();
    }

    @Override
    public void ondeleteError() {

    }
}
