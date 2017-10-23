package com.mvpankao.mvp.products;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseAdapter;
import com.mvpankao.model.bean.ProductOrder;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.mvp.base.MVPBaseFragment;


import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ProductAllFragment extends MVPBaseFragment<ProductsContract.View, ProductsPresenter> implements ProductsContract.View {
    @BindView(R.id.AllProductRecyclerView)
    SwipeRecyclerView mAllProductRecyclerView;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    ProductOrder mProductOrder = null;
    List<ProductOrder> mProductList = new ArrayList<>();
    List<ProductsBean.ObjectBean.ListBean> list = new ArrayList<>();

    ProductsAdapter mAdapter;
    int page = 1;
    int itemsize = 0;
    @Override
    protected void initView() {
        showProgress();
        mAllProductRecyclerView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mAllProductRecyclerView.getRecyclerView().setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mAllProductRecyclerView.setLoadMoreEnable(true);
        View emptyView = View.inflate(mContext, R.layout.empty_view, null);
        mAllProductRecyclerView.setEmptyView(emptyView);
        mAllProductRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress();
                        if (itemsize <= Constants.SIZE) {
                            mAllProductRecyclerView.stopLoadingMore();
                            mAllProductRecyclerView.setLoadMoreEnable(false);
                        } else {
                            mAllProductRecyclerView.setLoadMoreEnable(true);
                        }

                        page = 1;
                        list.clear();
                        initData();
                        mAllProductRecyclerView.setLoadMoreEnable(true);
                        mAllProductRecyclerView.complete();
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
                            mAllProductRecyclerView.onNoMore("已加载所有数据!");
                        } else {
                            mAllProductRecyclerView.stopLoadingMore();
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

        return R.layout.fg_allproducts;
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
        mPresenter.initData(getActivity(), mMyOkhttp,page, "","");
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.ProductList_CODE:


                if (itemsize <= Constants.SIZE) {
                    mAllProductRecyclerView.stopLoadingMore();
                    mAllProductRecyclerView.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new ProductsAdapter(getActivity(), list,mPresenter);
                    mAllProductRecyclerView.setAdapter(mAdapter);

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
        mAllProductRecyclerView.setVisibility(View.GONE);

    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mAllProductRecyclerView.setVisibility(View.VISIBLE);

    }
    @Override
    public void onFailure() {
        hideProgress();
        mAllProductRecyclerView.stopLoadingMore();
    }

    @Override
    public void onRequestFailed() {
        hideProgress();
        mAllProductRecyclerView.stopLoadingMore();
        EventBus.getDefault().post(new MyEvent(Constants.ProductList_CODE));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSuccess(Object responses) {
        ProductsBean response=(ProductsBean)responses;
        for (int i = 0; i < response.getObject().getList().size(); i++) {
            list.add(response.getObject().getList().get(i));
        }
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.ProductList_CODE));
    }


}
