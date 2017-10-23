package com.mvpankao.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.ui.adapter.TabAdapter;
import com.mvpankao.ui.fragment.OrderAllFragment;
import com.mvpankao.ui.fragment.OrderCompletedFragment;
import com.mvpankao.ui.fragment.OrderUnCompletedFragment;
import com.mvpankao.utils.CallPhoneUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的订购
 * Created by wangjian
 * On  2016/11/29
 */

public class MyOrderActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.ViewPager)
    ViewPager mViewPager;
    /**
     * 全部订单
     */
    OrderAllFragment mOrderAllFragment;
    /**
     * 待安装
     */
    OrderUnCompletedFragment mOrderUnCompletedFragment;
    /**
     * 已完成
     */
    OrderCompletedFragment mOrderCompletedFragment;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    private List<Fragment> list_fragment;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    TabAdapter mAdapter;

    @Override
    protected void initView() {

        mTitle.setText("我的订购");
        initFragments();
        //添加页卡标题
        initTitle();
        initTab();


    }

    @Override
    protected int getLayout() {
        return R.layout.ac_myorder;
    }

    @Override
    protected void initEventAndData() {

    }

    private void initTab() {
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        for (int i = 0; i < mTitleList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(i)));//添加tab选项卡
        }

        mAdapter = new TabAdapter(getSupportFragmentManager(), list_fragment, mTitleList);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }

    private void initTitle() {
        mTitleList.add("全部");
        mTitleList.add("待安装");
        mTitleList.add("已完成");
    }

    private void initFragments() {
        list_fragment = new ArrayList<>();
        mOrderAllFragment = new OrderAllFragment();
        mOrderUnCompletedFragment = new OrderUnCompletedFragment();
        mOrderCompletedFragment = new OrderCompletedFragment();


        list_fragment.add(mOrderAllFragment);
        list_fragment.add(mOrderUnCompletedFragment);
        list_fragment.add(mOrderCompletedFragment);

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


}
