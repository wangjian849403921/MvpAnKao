package com.mvpankao.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.ui.fragment.AssetFragment;
import com.mvpankao.ui.fragment.WarningFragment;
import com.mvpankao.ui.fragment.WorkOrderFragment;
import com.mvpankao.view.WorkOrderView;
import com.mvpankao.widget.popupwindow.FilterPopupWindow;
import com.mvpankao.widget.switchbar.SegmentControl;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 千里眼
 * Created by wangjian
 * On  2016/12/9
 */

public class TelescopeActivity extends BaseActivity implements WorkOrderView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.dowmarrow)
    ImageView mDowmarrow;
    @BindView(R.id.Rl_Select)
    RelativeLayout mRlSelect;
    @BindView(R.id.header)
    RelativeLayout mHeader;
    @BindView(R.id.segment_control)
    SegmentControl mSegmentControl;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.LL_Layout)
    LinearLayout mLinearLayout;
    WorkOrderFragment mWorkOrderFragment;

    WarningFragment mWarningFragment;

    AssetFragment mAssetFragment;
    @BindView(R.id.createorder)
    ImageView mCreateOrder;
    private FilterPopupWindow popupWindow;
    int indexof = 0;
    String role = "";
    JSONArray obj;
    private WorkOrderPresenter mWorkOrderPresenter = new WorkOrderPresenter(this);

    @Override
    protected void initView() {
        mTitle.setText("千里眼");
        if (mSPUtils.contains("role")) {
            role = mSPUtils.getString("role");
        }
//        String role = "1";
        if (!StringUtils.isEmpty(role)) {

            switch (role) {

                case "1":
                    mSegmentControl.setText("工单", "报警", "资产");

                    break;

                case "2":
                    mTitle.setText("工单");
                    mSegmentControl.setText("工单");
                    mLinearLayout.setVisibility(View.GONE);

                    break;
            }
        } else {
            mSegmentControl.setText("工单", "报警", "资产");
        }
        loadFragment(0);
        indexof = 0;
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                indexof = index;
                loadFragment(index);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_telescope;
    }

    private void loadFragment(int type) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        hideFragments(transaction);

        switch (type) {
            case 0:
                if (mSPUtils.contains("role")) {
                    role = mSPUtils.getString("role");
                }
//                String role = "1";
                switch (role) {
                    case "1":

                        mCreateOrder.setVisibility(View.VISIBLE);
                        break;

                    case "2":
                        mCreateOrder.setVisibility(View.GONE);

                        break;
                    default:
                        mCreateOrder.setVisibility(View.VISIBLE);

                        break;
                }
                mRlSelect.setVisibility(View.VISIBLE);
                if (mWorkOrderFragment == null) {
                    // 如果mWorkOrderFragment为空，则创建一个并添加到界面上
                    mWorkOrderFragment = new WorkOrderFragment();
                    transaction.add(R.id.fl_content, mWorkOrderFragment);


                } else {
                    // 如果mWorkOrderFragment不为空，则直接将它显示出来
                    transaction.show(mWorkOrderFragment);
                }

                break;

            case 1:
                mCreateOrder.setVisibility(View.GONE);

                mRlSelect.setVisibility(View.VISIBLE);
                if (mWarningFragment == null) {
                    // 如果mWarningFragment为空，则创建一个并添加到界面上
                    mWarningFragment = new WarningFragment();
                    transaction.add(R.id.fl_content, mWarningFragment);

                } else {
                    // 如果mWarningFragment不为空，则直接将它显示出来
                    transaction.show(mWarningFragment);
                }

                break;

            case 2:
                mCreateOrder.setVisibility(View.GONE);

                mRlSelect.setVisibility(View.GONE);
                if (mAssetFragment == null) {
                    // 如果mAssetFragment为空，则创建一个并添加到界面上
                    mAssetFragment = new AssetFragment();
                    transaction.add(R.id.fl_content, mAssetFragment);


                } else {
                    // 如果mAssetFragment不为空，则直接将它显示出来
                    transaction.show(mAssetFragment);
                }


                break;
        }


        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (mWorkOrderFragment != null) {
            transaction.hide(mWorkOrderFragment);
        }
        if (mWarningFragment != null) {
            transaction.hide(mWarningFragment);
        }
        if (mAssetFragment != null) {
            transaction.hide(mAssetFragment);
        }

    }

    @Override
    protected void initEventAndData() {

    }


    @OnClick({R.id.rl_back, R.id.Rl_Select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.Rl_Select:

                if (mSPUtils.getBoolean("hasLogin")) {
                    switch (indexof) {
                        case 0:

                            mWorkOrderPresenter.initSelectData(mContext, mMyOkhttp);
                            break;

                        case 1:

                            /**
                             * 警报筛选列表
                             *
                             * @param index
                             */
                            mWorkOrderPresenter.initWarningSelectData(mContext, mMyOkhttp);


                            break;
                    }
                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("您尚未登录不能进行相关操作")
                            .setPositiveButtonColor("#FD4A2E")
                            .setPositiveButton("去登录", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    startActivity(new Intent(mContext, LoginActivity.class));

                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }


                break;

        }
    }


    @OnClick(R.id.createorder)
    public void onClick() {
        if (mSPUtils.getBoolean("hasLogin")) {
            startActivity(new Intent(mContext, CreateWorkOrderActivity.class));
        } else {
            new AlertDialog(mContext).builder().setTitle("提示")
                    .setMsg("您尚未登录不能进行相关操作")
                    .setPositiveButtonColor("#FD4A2E")
                    .setPositiveButton("去登录", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            startActivity(new Intent(mContext, LoginActivity.class));

                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
        }


    }

    @Override
    public void setJsonArray(JSONArray jsonArray) {
        obj = jsonArray;
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWorkOrderSuccess() {
        popupWindow = new FilterPopupWindow(mContext, mDowmarrow, obj, true, true, 2, indexof);
        popupWindow.showFilterPopup(mHeader);
    }

    @Override
    public void onWarningSuccess() {
        popupWindow = new FilterPopupWindow(mContext, mDowmarrow, obj, false, true, 3, indexof);
        popupWindow.showFilterPopup(mHeader);
    }

    @Override
    public void onWorkOrderSuccess(WorkOrderDeatil response) {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onEditWorkOrderSuccess() {

    }

    @Override
    public void onEditFailure() {

    }

    @Override
    public void onEditError() {

    }
}
