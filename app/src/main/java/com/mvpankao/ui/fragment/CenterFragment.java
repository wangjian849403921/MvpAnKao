package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.http.NetUrl;
import com.mvpankao.ui.activity.AboutUsActivity;
import com.mvpankao.ui.activity.FeedBackActivity;
import com.mvpankao.ui.activity.MyOrderActivity;
import com.mvpankao.ui.activity.MyRepairActivity;
import com.mvpankao.ui.activity.MySubscribeActivity;
import com.mvpankao.ui.activity.SettingActivity;
import com.mvpankao.ui.activity.WorkFlowActivity;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.HasLoginUtils;
import com.mvpankao.utils.MyEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 企业中心
 * Created by Wangjian
 * On  2016/9/7
 */
public class CenterFragment extends BaseFragment {

    @BindView(R.id.MyPredeterMine)
    TextView mMyPredeterMine;
    @BindView(R.id.MyRepair)
    TextView mMyRepair;
    @BindView(R.id.MyOrder)
    TextView mMyOrder;
    @BindView(R.id.WorkFlow)
    TextView mWorkFlow;
    @BindView(R.id.Setting)
    TextView mSetting;
    @BindView(R.id.FeedBack)
    TextView mFeedBack;
    @BindView(R.id.AboutUs)
    TextView mAboutUs;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.logo)
    ImageView mLogo;
    @BindView(R.id.UserName)
    TextView mUserName;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mRlBack.setVisibility(View.INVISIBLE);
        mTitle.setText("用户中心");
        if (mSPUtils.contains("role")) {
            String role = mSPUtils.getString("role");
            if (!StringUtils.isEmpty(role)) {
                switch (role) {
                    case "1":

                        break;
                    case "2":
                        mMyOrder.setVisibility(View.GONE);
                        mMyPredeterMine.setVisibility(View.GONE);
                        break;
                }
                mUserName.setText(mSPUtils.getString("username"));
                RequestOptions options = new RequestOptions();
                options.error(R.drawable.icon_default);
                Glide.with(getActivity()).load(NetUrl.DOCHeader + mSPUtils.getString("userimage")).apply(options).into(mLogo);
            }

        } else {
            mUserName.setText(mSPUtils.getString("username"));
            mLogo.setImageResource(R.drawable.icon_default);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_center;
    }

    @Override
    protected void initEventAndData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.UserName_CODE:
                if (mSPUtils.contains("role")) {
                    String role = mSPUtils.getString("role");
                    if (!StringUtils.isEmpty(role)) {
                        switch (role) {
                            case "1":

                                break;
                            case "2":
                                mMyOrder.setVisibility(View.GONE);
                                mMyPredeterMine.setVisibility(View.GONE);
                                break;
                        }
                        mUserName.setText(mSPUtils.getString("username"));
                        RequestOptions options = new RequestOptions();
                        options.error(R.drawable.icon_default);
                        Glide.with(getActivity()).load(NetUrl.DOCHeader + mSPUtils.getString("userimage")).apply(options).into(mLogo);
                    }

                } else {
                    mUserName.setText(mSPUtils.getString("username"));
                    mLogo.setImageResource(R.drawable.icon_default);
                }
                break;
        }
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.contact_Customer_service, R.id.MyPredeterMine, R.id.MyRepair, R.id.MyOrder, R.id.WorkFlow, R.id.Setting, R.id.FeedBack, R.id.AboutUs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(getActivity());
                break;
            //跳转到我的预约
            case R.id.MyPredeterMine:
                HasLoginUtils.checkLogin(getActivity(), new Intent(getActivity(), MySubscribeActivity.class));
                break;
            //跳转到我的报修
            case R.id.MyRepair:
                HasLoginUtils.checkLogin(getActivity(), new Intent(getActivity(), MyRepairActivity.class));
                break;
            //跳转到我的订购
            case R.id.MyOrder:
                HasLoginUtils.checkLogin(getActivity(), new Intent(getActivity(), MyOrderActivity.class));
                break;
            //跳转到工作流
            case R.id.WorkFlow:
                HasLoginUtils.checkLogin(getActivity(), new Intent(getActivity(), WorkFlowActivity.class));
                break;
            //跳转到设置
            case R.id.Setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            //跳转到反馈
            case R.id.FeedBack:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            //跳转到关于安靠
            case R.id.AboutUs:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
        }
    }


}
