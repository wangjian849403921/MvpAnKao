package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.model.bean.NewsBean;
import com.mvpankao.presenter.NewsPresenter;
import com.mvpankao.ui.activity.NewsDetailsActivity;
import com.mvpankao.ui.adapter.InformationAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.NewsView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mvpankao.utils.Constants.NEWS_CODE;


/**
 * Created by wangjian
 * On  2016/9/7
 */
public class NewsFragment extends BaseFragment implements NewsView {

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.NewsRecyclerView)
    SwipeRecyclerView mNewsRecyclerView;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    private InformationAdapter mAdapter;
    List<NewsBean.ObjectBean.ListBean> mInfo = new ArrayList<>();
    private int page = 1;
    int itemsize = 0;

    private NewsPresenter mNewsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenter(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_news;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case NEWS_CODE:

                if (itemsize <= Constants.SIZE) {
                    mNewsRecyclerView.stopLoadingMore();
                    mNewsRecyclerView.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new InformationAdapter(getActivity(), mInfo);
                    mNewsRecyclerView.setAdapter(mAdapter);

                }
                mAdapter.setOnItemOnClick(new InformationAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {

                        String url = mInfo.get(position).getContent();

                        String image = mInfo.get(position).getIcon();
                        String title = mInfo.get(position).getTitle();
                        String time = TimeUtils.milliseconds2String(mInfo.get(position).getCreateDate());


                        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                        intent.putExtra("url", url);
                        intent.putExtra("image", image);
                        intent.putExtra("title", title);
                        intent.putExtra("time", time);
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    protected void initView() {
        showProgress();
        mRlBack.setVisibility(View.INVISIBLE);
        mTitle.setText("新闻资讯");
        mNewsRecyclerView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mNewsRecyclerView.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        View emptyView = View.inflate(mContext, R.layout.empty_view, null);
        mNewsRecyclerView.setEmptyView(emptyView);

        mNewsRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress();
                        if (itemsize <= Constants.SIZE) {
                            mNewsRecyclerView.stopLoadingMore();
                            mNewsRecyclerView.setLoadMoreEnable(false);
                        } else {
                            mNewsRecyclerView.setLoadMoreEnable(true);
                        }

                        page = 1;
                        mInfo.clear();
                        mNewsPresenter.initData(getActivity(), mMyOkhttp, mInfo);
                        mNewsRecyclerView.setLoadMoreEnable(true);
                        mNewsRecyclerView.complete();
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
                        mNewsPresenter.initData(getActivity(), mMyOkhttp, mInfo);

                        if (mInfo.size() >= itemsize && mInfo.size() >= Constants.SIZE) {
                            mNewsRecyclerView.onNoMore("已加载所有数据!");
                        } else {
                            mNewsRecyclerView.stopLoadingMore();
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
                if(mActivity.isDestroyed()){
                    return;
                }
                mNewsPresenter.initData(getActivity(), mMyOkhttp, mInfo);
            }
        });

    }


    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mNewsRecyclerView.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mNewsRecyclerView.setVisibility(View.VISIBLE);
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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.NEWS_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mNewsRecyclerView.stopLoadingMore();
    }

    @Override
    public void onError() {
        hideProgress();
    }
}



