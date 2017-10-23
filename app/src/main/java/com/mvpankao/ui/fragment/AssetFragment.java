package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.model.bean.AssetBean;
import com.mvpankao.presenter.AssetPresenter;
import com.mvpankao.ui.activity.AssetDetailActivity;
import com.mvpankao.ui.adapter.AssetAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.AssetView;
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
 * 资产
 * Created by wangjian
 * On  2016/12/9
 */

public class AssetFragment extends BaseFragment implements AssetView {
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.AssetList)
    SwipeRecyclerView mAssetList;
    List<AssetBean.ObjectBean> list = new ArrayList<>();

    AssetAdapter mAdapter;
    private AssetPresenter mAssetPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAssetPresenter = new AssetPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_asset;
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

    @Override
    protected void initView() {
        showProgress();

        mAssetList.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mAssetList.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAssetList.setRefreshEnable(true);
        mAssetList.setLoadMoreEnable(false);
        new RecycleEmptyViewHelper(mAssetList, "暂无数据");
        mAssetList.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        list.clear();
                        initData();
                        mAssetList.setLoadMoreEnable(false);
                        mAssetList.complete();
                        if (mAdapter != null) {
                            mAdapter.notifyDataSetChanged();
                        }
                    }


                }, 3000);

            }

            @Override
            public void onLoadMore() {

            }

        });
    }


    private void initData() {
        if(mActivity.isDestroyed()){
            return;
        }
        mAssetPresenter.initData(mContext, mMyOkhttp, list);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.ASSET_CODE:
                mAdapter = new AssetAdapter(getActivity(), list);
                mAssetList.setAdapter(mAdapter);

                mAdapter.setOnItemOnClick(new AssetAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), AssetDetailActivity.class);
                        intent.putExtra("id", list.get(position).getId());
                        intent.putExtra("name", list.get(position).getAssetName());
                        startActivity(intent);

                    }
                });
                break;
        }
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mAssetList.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mAssetList.setVisibility(View.VISIBLE);
    }

    @Override
    public String getUserId() {
        return mSPUtils.getString("userid");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.ASSET_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mAssetList.stopLoadingMore();

    }

    @Override
    public void onError() {
        hideProgress();
        mAssetList.stopLoadingMore();

    }
}
