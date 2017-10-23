package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseAdapter;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.model.bean.ProductOrder;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.presenter.ProductsPresenter;
import com.mvpankao.ui.activity.ProductDetailActivity;
import com.mvpankao.ui.adapter.ProductsAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.ProductsView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 电缆输电系统产品
 * Created by wangjian
 * On  2017/1/6
 */

public class ProductGILFragment extends BaseFragment implements ProductsView {
    @BindView(R.id.GILRecyclerView)
    SwipeRecyclerView mGILRecyclerView;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    ProductOrder mProductOrder = null;
    List<ProductsBean.ObjectBean.ListBean> list = new ArrayList<>();

    ProductsAdapter mAdapter;
    int page = 1;
    int itemsize = 0;
    private ProductsPresenter ProductsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductsPresenter = new ProductsPresenter(this);
    }

    @Override
    protected void initView() {
        showProgress();
        mGILRecyclerView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mGILRecyclerView.getRecyclerView().setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mGILRecyclerView.setLoadMoreEnable(true);
        View emptyView = View.inflate(mContext, R.layout.empty_view, null);
        mGILRecyclerView.setEmptyView(emptyView);
        mGILRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress();
                        if (itemsize <= Constants.SIZE) {
                            mGILRecyclerView.stopLoadingMore();
                            mGILRecyclerView.setLoadMoreEnable(false);
                        } else {
                            mGILRecyclerView.setLoadMoreEnable(true);
                        }

                        page = 1;
                        list.clear();
                        initData();
                        mGILRecyclerView.setLoadMoreEnable(true);
                        mGILRecyclerView.complete();
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
                            mGILRecyclerView.onNoMore("已加载所有数据!");
                        } else {
                            mGILRecyclerView.stopLoadingMore();
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

        return R.layout.fg_gil;
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
        ProductsPresenter.initData(getActivity(), mMyOkhttp, list, "2","");
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.ProductList3_CODE:
                if (itemsize <= Constants.SIZE) {
                    mGILRecyclerView.stopLoadingMore();
                    mGILRecyclerView.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new ProductsAdapter(getActivity(), list);
                    mGILRecyclerView.setAdapter(mAdapter);

                }


                mAdapter.setOnItemOnClick(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {
                        ActivityOptionsCompat option =
                                ActivityOptionsCompat.makeScaleUpAnimation(view, //The View that the new activity is animating from
                                        (int) view.getWidth() / 2, (int) view.getHeight() / 2, //拉伸开始的坐标
                                        0, 0);
                        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);

                        intent.putExtra("id", list.get(position).getProductId());
                        startActivity(intent, option.toBundle());
                    }
                });

                break;
        }
    }


    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mGILRecyclerView.setVisibility(View.GONE);

    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mGILRecyclerView.setVisibility(View.VISIBLE);

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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.ProductList3_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mGILRecyclerView.stopLoadingMore();
    }

    @Override
    public void onError() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.ProductList3_CODE));
    }
}
