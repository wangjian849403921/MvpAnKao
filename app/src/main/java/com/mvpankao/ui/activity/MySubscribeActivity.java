package com.mvpankao.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.model.bean.SubscribeGroupItem;
import com.mvpankao.presenter.SubscribePresenter;
import com.mvpankao.ui.adapter.SubscribeAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.SubscribeView;
import com.mvpankao.widget.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的预约
 * Created by wangjian
 * On  2016/12/30
 */

public class MySubscribeActivity extends BaseActivity implements SubscribeView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.year)
    TextView mYear;
    @BindView(R.id.dowmarrow)
    ImageView mDowmarrow;
    @BindView(R.id.Rl_Select)
    RelativeLayout mRlSelect;
    @BindView(R.id.explistview)
    ExpandableListView mExplistview;
    SubscribeAdapter mSubscribeAdapter;
    @BindView(R.id.Layout)
    LinearLayout mLayout;
    private List<SubscribeGroupItem> dataList = new ArrayList<SubscribeGroupItem>();
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.LL_Layout)
    LinearLayout mLinearLayout;
    String date = "2016-01-01";
    private SubscribePresenter mSubscribePresenter = new SubscribePresenter(this);

    @Override
    protected void initView() {
        mTitle.setText("我的预约");
        showProgress();
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_subscribe;
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
        mSubscribePresenter.initData(mContext, mMyOkhttp, dataList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {

            case Constants.MYSUBSCRIBE_CODE:
                mSubscribeAdapter = new SubscribeAdapter(mContext, dataList);
                mExplistview.setAdapter(mSubscribeAdapter);
                int groupCount = mExplistview.getCount();

                for (int i = 0; i < groupCount; i++) {

                    mExplistview.expandGroup(i);

                }
                mExplistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                mExplistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                int groupPosition, long id) {
                        // TODO Auto-generated method stub
                        return true;
                    }
                });
//                mExplistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//                    @Override
//                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                        Intent intent = new Intent(mContext, WorkOrderDetailActivity.class);
//                        String workorderid = dataList.get(groupPosition).getChildItem(childPosition).getId();
//                        String name = dataList.get(groupPosition).getChildItem(childPosition).getName();
//                        intent.putExtra("id", workorderid);
//                        intent.putExtra("name", name);
//                        startActivity(intent);
//
//                        return true;
//                    }
//                });
                break;

        }
    }


    @OnClick({R.id.rl_back, R.id.contact_Customer_service, R.id.Rl_Select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);

                break;
            case R.id.Rl_Select:
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public String getUserId() {
        return mSPUtils.getString("userid");
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.MYSUBSCRIBE_CODE));
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
