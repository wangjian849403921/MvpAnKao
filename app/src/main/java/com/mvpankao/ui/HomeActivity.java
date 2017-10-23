package com.mvpankao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.model.bean.Tab;
import com.mvpankao.ui.fragment.CenterFragment;
import com.mvpankao.ui.fragment.HomeFragment;
import com.mvpankao.ui.fragment.NewsFragment;
import com.mvpankao.ui.fragment.ProductsFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 14:26
 */

public class HomeActivity extends BaseActivity {
    private List<Tab> mTabs = new ArrayList<>(5);
    private LayoutInflater mLayoutInflater;
    public static FragmentTabHost mTabHost;
    long preTime;
    public static final long TWO_SECOND = 2 * 1000;
    public static Activity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=this;
    }


    @Override
    protected void initView() {
//        initDrawableLayout();
        initTab();
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_home_page;
    }

    @Override
    protected void initEventAndData() {

    }

//    /**
//     * 初始化侧边栏
//     */
//    private void initDrawableLayout() {
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(mContext, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        mNvMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.item_one:
//
//                        break;
//                    case R.id.item_two:
//
//                        break;
//                    case R.id.item_three:
//
//                        break;
//                    case R.id.item_four:
//
//                        break;
//
//                }
//                item.setChecked(true);//点击了把它设为选中状态
//                mDrawerLayout.closeDrawers();//关闭抽屉
//                return true;
//            }
//        });
//    }

    private void initTab() {
        Tab home = new Tab(R.string.home, R.drawable.tab_home_page, HomeFragment.class);
        Tab products = new Tab(R.string.products, R.drawable.tab_product, ProductsFragment.class);
//        Tab report = new Tab(R.string.myreport, R.drawable.tab_project, ReportFragment.class);
        Tab news = new Tab(R.string.news, R.drawable.tab_news, NewsFragment.class);
        Tab center = new Tab(R.string.corporate_center, R.drawable.tab_personal_center, CenterFragment.class);
        mTabs.add(home);
        mTabs.add(products);
//        mTabs.add(report);
        mTabs.add(news);
        mTabs.add(center);
        mLayoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(R.id.tabHost);

        mTabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.flContainer);

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabspec = mTabHost.newTabSpec(getString(tab.getTitle()));

            tabspec.setIndicator(buildIndicator(tab));
            mTabHost.addTab(tabspec, tab.getFragment(), null);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);
    }

    private View buildIndicator(Tab tab) {
        View view = mLayoutInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.iv);
        TextView text = (TextView) view.findViewById(R.id.tv);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 截获后退键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = new Date().getTime();

            // 如果时间间隔大于2秒, 不处理
            if ((currentTime - preTime) > TWO_SECOND) {
                // 显示消息
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 更新时间
                preTime = currentTime;

                // 截获事件,不再处理
                return true;
            }


        }

        return super.onKeyDown(keyCode, event);
    }
}
