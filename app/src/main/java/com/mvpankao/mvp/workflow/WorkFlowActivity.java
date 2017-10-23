package com.mvpankao.mvp.workflow;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.mvp.base.MVPBaseActivity;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.widget.switchbar.SegmentControl;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2016/11/24
 */

public class WorkFlowActivity extends MVPBaseActivity<WorkFlowContract.View, WorkFlowPresenter> implements WorkFlowContract.View {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.segment_control)
    SegmentControl mSegmentControl;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    /**
     * 已完成
     */
    WorkFlowCompletedFragment mWorkFlowCompletedFragment;
    /**
     * 执行中
     */
    WorkFlowExecutingFragment mWorkFlowExecutingFragment;
    /**
     * 未开始
     */
    WorkFlowNotStartFragment mWorkFlowNotStartFragment;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;

    @Override
    protected void initView() {
        mTitle.setText("工作流");
        mSegmentControl.setText("已完成", "执行中", "未开始");

        loadFragment(0);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                loadFragment(index);
            }
        });

    }

    private void loadFragment(int type) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        hideFragments(transaction);

        switch (type) {
            case 0:
                if (mWorkFlowCompletedFragment == null) {
                    // 如果mCompletedFragment为空，则创建一个并添加到界面上
                    mWorkFlowCompletedFragment = new WorkFlowCompletedFragment();
                    transaction.add(R.id.fl_content, mWorkFlowCompletedFragment);


                } else {
                    // 如果mCompletedFragment不为空，则直接将它显示出来
                    transaction.show(mWorkFlowCompletedFragment);
                }

                break;

            case 1:
                if (mWorkFlowExecutingFragment == null) {
                    // 如果mCarTypeFragment为空，则创建一个并添加到界面上
                    mWorkFlowExecutingFragment = new WorkFlowExecutingFragment();
                    transaction.add(R.id.fl_content, mWorkFlowExecutingFragment);

                } else {
                    // 如果mExecutingFragment不为空，则直接将它显示出来
                    transaction.show(mWorkFlowExecutingFragment);
                }

                break;

            case 2:
                if (mWorkFlowNotStartFragment == null) {
                    // 如果mNotStartFragment为空，则创建一个并添加到界面上
                    mWorkFlowNotStartFragment = new WorkFlowNotStartFragment();
                    transaction.add(R.id.fl_content, mWorkFlowNotStartFragment);


                } else {
                    // 如果mNotStartFragment不为空，则直接将它显示出来
                    transaction.show(mWorkFlowNotStartFragment);
                }


                break;
        }


        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (mWorkFlowCompletedFragment != null) {
            transaction.hide(mWorkFlowCompletedFragment);
        }
        if (mWorkFlowExecutingFragment != null) {
            transaction.hide(mWorkFlowExecutingFragment);
        }
        if (mWorkFlowNotStartFragment != null) {
            transaction.hide(mWorkFlowNotStartFragment);
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_workflow;
    }

    @Override
    protected void initEventAndData() {

    }


    @OnClick({R.id.contact_Customer_service
            ,R.id.rl_back})
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

    @Override
    public void onFailure() {

    }

    @Override
    public void onRequestFailed() {

    }

    @Override
    public void showToast(String msg) {

    }



    @Override
    public void onSuccess(Object response) {

    }
}
