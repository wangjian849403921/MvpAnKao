package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.base.BaseAdapter;
import com.mvpankao.model.bean.ProductOrder;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.presenter.ProductsPresenter;
import com.mvpankao.ui.adapter.ProductsAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.ProductsView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.RecycleEmptyViewHelper;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2016/11/24
 */

public class ProductActivity extends BaseActivity implements ProductsView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;


    @BindView(R.id.ProductRecyclerView)
    SwipeRecyclerView mProductRecyclerView;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    ProductOrder mProductOrder = null;
    List<ProductsBean.ObjectBean.ListBean> list = new ArrayList<>();

    ProductsAdapter mAdapter;
    int page = 1;
    int itemsize = 0;
    Map<String, String> map;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    private ProductsPresenter ProductsPresenter;
    String categoryId="";
    int producttype=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void initView() {
        showProgress();
        mTitle.setText("筛选结果");
        mProductRecyclerView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mProductRecyclerView.getRecyclerView().setLayoutManager(new GridLayoutManager(mContext, 2));
        mProductRecyclerView.setLoadMoreEnable(true);
        new RecycleEmptyViewHelper(mProductRecyclerView, "暂无数据");
        mProductRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress();
                        if (itemsize <= Constants.SIZE) {
                            mProductRecyclerView.stopLoadingMore();
                            mProductRecyclerView.setLoadMoreEnable(false);
                        } else {
                            mProductRecyclerView.setLoadMoreEnable(true);
                        }

                        page = 1;
                        list.clear();
                        initData();
                        mProductRecyclerView.setLoadMoreEnable(true);
                        mProductRecyclerView.complete();
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
                            mProductRecyclerView.onNoMore("已加载所有数据!");
                        } else {
                            mProductRecyclerView.stopLoadingMore();
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
        return R.layout.fg_products;
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
        ProductsPresenter = new ProductsPresenter(this);

        categoryId = getIntent().getStringExtra("id");
        producttype = getIntent().getIntExtra("producttype", 0);
        LogUtils.d(categoryId);
        LogUtils.d(producttype);

        switch (producttype) {
            case 0:
                ProductsPresenter.initData(mContext, mMyOkhttp, list,"",categoryId);
                break;
            case 1:
                ProductsPresenter.initData(mContext, mMyOkhttp, list,producttype+"",categoryId);

                break;
            case 2:
                ProductsPresenter.initData(mContext, mMyOkhttp, list,producttype+"",categoryId);

                break;
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.ProductList4_CODE:


                if (itemsize <= Constants.SIZE) {
                    mProductRecyclerView.stopLoadingMore();
                    mProductRecyclerView.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new ProductsAdapter(mContext, list);
                    mProductRecyclerView.setAdapter(mAdapter);
                }


                mAdapter.setOnItemOnClick(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {
                        ActivityOptionsCompat option =
                                ActivityOptionsCompat.makeScaleUpAnimation(view, //The View that the new activity is animating from
                                        (int) view.getWidth() / 2, (int) view.getHeight() / 2, //拉伸开始的坐标
                                        0, 0);
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);

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
        mProductRecyclerView.setVisibility(View.GONE);

    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mProductRecyclerView.setVisibility(View.VISIBLE);


    }

    @OnClick({R.id.rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;

        }
    }



    @OnClick(R.id.contact_Customer_service)
    public void onClick() {
        CallPhoneUtils.callphone(mContext);
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
        EventBus.getDefault().post(new MyEvent(Constants.ProductList4_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mProductRecyclerView.stopLoadingMore();
    }

    @Override
    public void onError() {
        hideProgress();
    }
}
