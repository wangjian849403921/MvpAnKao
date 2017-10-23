package com.mvpankao.ui.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.TechnologyBean;
import com.mvpankao.presenter.QueryResultPresenter;
import com.mvpankao.ui.adapter.QueryTechnologyAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.DownFile;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.QueryResultView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;
import com.mvpankao.widget.refresh.SwipeRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mvpankao.utils.Constants.TECHNOLOGY_CODE;


/**
 * 工艺查询
 * Created by wangjian
 * On  2016/11/28
 */

public class QueryTechnologyActivity extends BaseActivity implements QueryResultView {
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContact_Customer_Service;
    @BindView(R.id.Report_RecycleView)
    SwipeRecyclerView mReportRecycleView;

    List<TechnologyBean.ObjectBean.ListBean> list = new ArrayList<>();
    @BindView(R.id.report_query)
    SearchView mReportQuery;
    private QueryTechnologyAdapter mAdapter;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.LL_Layout)
    LinearLayout mLinearLayout;
    private int page = 1;
    int itemsize = 0;
    int refresh = 0;
    String querycontent = "";
    private QueryResultPresenter mQueryResultPresenter = new QueryResultPresenter(this);

    @Override
    protected void initView() {
        showProgress();
        //去除searchview的下划线
        if (mReportQuery != null) {
            try {        //--拿到字节码
                Class<?> argClass = mReportQuery.getClass();
                //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                //--暴力反射,只有暴力反射才能拿到私有属性
                ownField.setAccessible(true);
                View mView = (View) ownField.get(mReportQuery);
                //--设置背景
                mView.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mTitle.setText("工艺查询");
        mReportRecycleView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);
        mReportRecycleView.getRecyclerView().setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        View emptyView = View.inflate(mContext, R.layout.empty_view, null);
        mReportRecycleView.setEmptyView(emptyView);
        mReportRecycleView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (itemsize <= Constants.SIZE) {
                            mReportRecycleView.stopLoadingMore();
                            mReportRecycleView.setLoadMoreEnable(false);
                        } else {
                            mReportRecycleView.setLoadMoreEnable(true);
                        }
                        refresh = 1;
                        page = 1;
                        list.clear();
                        initData();
                        mReportRecycleView.setLoadMoreEnable(true);
                        mReportRecycleView.complete();
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
                            mReportRecycleView.onNoMore("已加载所有数据!");
                        } else {
                            mReportRecycleView.stopLoadingMore();
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
        return R.layout.ac_technologyquery;
    }

    @Override
    protected void initEventAndData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });
        //设置搜索事件的监控
        mReportQuery.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //当点击搜索按钮,输入法搜索按钮,会触发这个方法.在这里做相应的搜索事件,query为用户输入的值
                //当输入框为空或者""时,此方法没有被调用
                querycontent = query;
                queryReport(querycontent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //当输入的文字发生变化的时候,会触发这个方法.在这里做匹配提示的操作等
                if (StringUtils.isEmpty(newText)) {
                    initData();
                }
                return true;
            }
        });

    }

    /**
     * 报告列表
     */
    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
       mQueryResultPresenter.initTechnologyData(mContext,mMyOkhttp,list);
    }

    /**
     * 报告查询
     *
     * @param query
     */
    private void queryReport(String query) {
        mQueryResultPresenter.initTechnologyData(mContext,mMyOkhttp,list);

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case TECHNOLOGY_CODE:
                if (itemsize <= Constants.SIZE) {
                    mReportRecycleView.stopLoadingMore();
                    mReportRecycleView.setLoadMoreEnable(false);
                }
                if (mAdapter != null) {

                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter = new QueryTechnologyAdapter(mContext, list);
                    mReportRecycleView.setAdapter(mAdapter);

                }
                mAdapter.setOnItemOnClick(new QueryTechnologyAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View v, int position) {
                        String path = NetUrl.DOCHeader + list.get(position).getDocUrl();
                        DownFile downFile = new DownFile(mContext, path);
                        downFile.checkUpdateInfo();
                    }
                });
                break;
        }
    }



    @OnClick({R.id.contact_Customer_service
            , R.id.rl_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);

                break;
            case R.id.rl_back:
                finish();
                break;
        }

    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mLinearLayout.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mLinearLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.contact_Customer_service)
    public void onClick() {
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
    public String getQueryContent() {
        return querycontent;
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
        EventBus.getDefault().post(new MyEvent(Constants.TECHNOLOGY_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        hideProgress();
        mLinearLayout.setVisibility(View.GONE);

    }
}
