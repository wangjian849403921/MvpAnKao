package com.mvpankao.ui.activity;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;

import net.lemonsoft.lemonbubble.LemonBubble;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLayoutStyle;
import net.lemonsoft.lemonbubble.enums.LemonBubbleLocationStyle;
import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置
 * Created by wangjian
 * On  2016/12/28
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.cachesize)
    TextView mCachesize;
    @BindView(R.id.clear_cache)
    LinearLayout mClearCache;
    @BindView(R.id.versionCode)
    TextView mVersionCode;
    @BindView(R.id.loginout)
    LinearLayout mLoginout;

    @Override
    protected void initView() {
        mTitle.setText("设置");
        if (!mSPUtils.getBoolean("hasLogin")) {
            mLoginout.setVisibility(View.GONE);
        } else {
            mLoginout.setVisibility(View.VISIBLE);

        }

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_setting;
    }

    @Override
    protected void initEventAndData() {


    }


    @OnClick({R.id.rl_back, R.id.contact_Customer_service, R.id.clear_cache, R.id.loginout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;


            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);
                break;
            case R.id.clear_cache:
                break;
            case R.id.loginout:
                LemonHello.getInformationHello("您确定要注销吗？", "注销登录后有些功能您将无法实现。")
                        .addAction(new LemonHelloAction("取消", new LemonHelloActionDelegate() {
                            @Override
                            public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                                helloView.hide();
                            }
                        }))
                        .addAction(new LemonHelloAction("我要注销", Color.RED, new LemonHelloActionDelegate() {
                            @Override
                            public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                                helloView.hide();
                                // 提示框使用了LemonBubble，请您参考：https://github.com/1em0nsOft/LemonBubble4Android
                                LemonBubble.getRoundProgressBubbleInfo()
                                        .setLocationStyle(LemonBubbleLocationStyle.CENTER)
                                        .setLayoutStyle(LemonBubbleLayoutStyle.ICON_LEFT_TITLE_RIGHT)
                                        .setBubbleSize(200, 50)
                                        .setProportionOfDeviation(0.1f)
                                        .setTitle("正在注销...")
                                        .show(mContext);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mSPUtils.clear();
                                        mSPUtils.putBoolean("hasLogin", false);
                                        mLoginout.setVisibility(View.GONE);
                                        EventBus.getDefault().post(new MyEvent(Constants.UserName_CODE));
                                        LemonBubble.showRight(mContext, "注销成功，欢迎您下次登录", 2000);
                                    }
                                }, 1500);
                            }
                        }))
                        .show(mContext);
//                new AlertDialog(mContext).builder().setTitle("退出登录")
//                        .setMsg("您确定要退出吗？")
//                        .setPositiveButtonColor("#FD4A2E")
//                        .setPositiveButton("确定", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                mSPUtils.clear();
//                                mSPUtils.putBoolean("hasLogin", false);
//                                EventBus.getDefault().post(new MyEvent(Constants.UserName_CODE));
//                                finish();
////                                HomePageActivity.instance.finish();
////                                startActivity(new Intent(mContext, LoginActivity.class));
//
//                            }
//                        }).setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                }).show();
                break;
        }
    }
}
