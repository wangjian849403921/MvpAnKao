package com.mvpankao.ui.fragment;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.camnter.easyslidingtabs.widget.EasySlidingTabs;
import com.mvpankao.R;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.model.bean.SliderImage;
import com.mvpankao.ui.activity.ExperimentalAppointmentActivity;
import com.mvpankao.ui.activity.FaultRepairActivity;
import com.mvpankao.ui.activity.KnowledgeForuActivity;
import com.mvpankao.ui.activity.LoginActivity;
import com.mvpankao.ui.activity.ProductsOrderActivity;
import com.mvpankao.ui.activity.QueryReportsActivity;
import com.mvpankao.ui.activity.QueryTechnologyActivity;
import com.mvpankao.ui.activity.TelescopeActivity;
import com.mvpankao.ui.activity.WorkFlowActivity;
import com.mvpankao.ui.adapter.TabsFragmentAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.HasLoginUtils;
import com.mvpankao.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wangjian
 * On  2016/9/7
 */
public class HomeFragment extends BaseFragment {

    List<SliderImage> list = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    SliderImage sliderImage;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.TestOrder)
    RelativeLayout mTestOrder;
    @BindView(R.id.ReportQuery)
    RelativeLayout mReportQuery;
    @BindView(R.id.KnowledgeForum)
    RelativeLayout mKnowledgeForum;
    @BindView(R.id.ProductOrder)
    RelativeLayout mProductOrder;
    @BindView(R.id.tabs)
    EasySlidingTabs mTabLayout;
    @BindView(R.id.vp_view)
    ViewPager mViewPager;
    @BindView(R.id.ll_dot)
    LinearLayout mLlDot;

    ElectricCableFragment mElectricCableFragment;
    GILFragment mGILFragment;
    @BindView(R.id.more_products)
    TextView mMoreProducts;
    @BindView(R.id.id_nv_menu)
    NavigationView mNvMenu;
    @BindView(R.id.id_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ll_technologyshare)
    LinearLayout mLlTechnologyshare;
    @BindView(R.id.install_search)
    RelativeLayout mInstallSearch;
    @BindView(R.id.project_supervision)
    RelativeLayout mProjectSupervision;
    @BindView(R.id.ll_projectsupervision)
    LinearLayout mLlProjectsupervision;
    @BindView(R.id.ControlforOnline)
    RelativeLayout mControlforOnline;
    @BindView(R.id.diagnosis)
    RelativeLayout mDiagnosis;
    @BindView(R.id.ll_diagnosis)
    LinearLayout mLlDiagnosis;
    @BindView(R.id.repair_line)
    RelativeLayout mRepairLine;
    @BindView(R.id.ll_repair)
    LinearLayout mLlRepair;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
//    @BindView(R.id.circle_menu)
//    CircleMenu mCircleMenu;

//    @BindView(R.id.more_information)
//    TextView mMoreInformation;

    private List<Fragment> list_fragment;
    private int curIndex = 0;
    private LayoutInflater mInflater;
    private TabsFragmentAdapter adapter;

    private List<String> mTitleList = new ArrayList<>();//页卡标题集合

    public static final String[] titles = {"电缆输电系统", "GIL输电系统"};



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_home;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initView() {

        Selectplatform();

        //初始化侧边栏
        initDrawableLayout();

        tabEvent();


        getData();

        initBanner();


    }

    private void Selectplatform() {
        String type = getActivity().getIntent().getStringExtra("type");
        if (type != null) {
            switch (type) {

                case "1":
                    //技术分享平台
                    selectTechnology();

                    break;
                case "2":
                    //工程监理平台
                    selectProject();
                    break;
                case "3":
                    //故障诊断平台
                    selectDiagnosis();
                    break;
                case "4":
                    //抢修平台
                    selectRepair();
                    break;

            }
        } else {
            mLlTechnologyshare.setVisibility(View.VISIBLE);

        }
    }



    /**
     * 初始化侧边栏
     */

    private void initDrawableLayout() {


        Resources resource = (Resources) getActivity().getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.navigation_menu_item_color);
        mNvMenu.setItemTextColor(csl);
//        mNvMenu.setItemIconTintList(resource.getColorStateList(R.color.navigation_menu_item_color));
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        View headerview = mNvMenu.inflateHeaderView(R.layout.drawable_header);
//        mLlTechnologyshare.setVisibility(View.VISIBLE);

        //技术平台
        TextView technology = (TextView) headerview.findViewById(R.id.technology);
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();//关闭抽屉
                selectTechnology();

            }
        });
        //工程质量监理
        TextView project = (TextView) headerview.findViewById(R.id.project);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();//关闭抽屉

                selectProject();
            }
        });
        //诊断平台
        TextView fault_diagnosis = (TextView) headerview.findViewById(R.id.fault_diagnosis);
        fault_diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();//关闭抽屉
                selectDiagnosis();
            }
        });
        //故障报修
        TextView repair = (TextView) headerview.findViewById(R.id.repair);

        repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawers();//关闭抽屉
                selectRepair();
            }
        });

    }

    /**
     * 选择故障报修平台
     */
    private void selectRepair() {
        mLlTechnologyshare.setVisibility(View.GONE);
        mLlDiagnosis.setVisibility(View.GONE);
        mLlProjectsupervision.setVisibility(View.GONE);
        mLlRepair.setVisibility(View.VISIBLE);
    }

    /**
     * 选择诊断平台
     */
    private void selectDiagnosis() {
        mLlTechnologyshare.setVisibility(View.GONE);
        mLlDiagnosis.setVisibility(View.VISIBLE);
        mLlProjectsupervision.setVisibility(View.GONE);
        mLlRepair.setVisibility(View.GONE);
    }

    /**
     * 选择工程监理平台
     */
    private void selectProject() {
        mLlTechnologyshare.setVisibility(View.GONE);
        mLlDiagnosis.setVisibility(View.GONE);
        mLlProjectsupervision.setVisibility(View.VISIBLE);
        mLlRepair.setVisibility(View.GONE);
    }

    /**
     * 选择技术平台
     */
    private void selectTechnology() {
        mLlTechnologyshare.setVisibility(View.VISIBLE);
        mLlDiagnosis.setVisibility(View.GONE);
        mLlProjectsupervision.setVisibility(View.GONE);
        mLlRepair.setVisibility(View.GONE);
    }

    /**
     * 实例化轮播图
     */
    private void initBanner() {
        String titles[] = {"娱乐", "财经", "新闻", "音乐"};
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置图片加载器

        mBanner.setImageLoader(new GlideImageLoader());

        mBanner.setImages(list2);

        //设置banner动画效果

        mBanner.setBannerAnimation(Transformer.Default);

        //设置标题集合（当banner样式有显示title时）

        mBanner.setBannerTitles(Arrays.asList(titles));

        //设置自动轮播，默认为true

        mBanner.isAutoPlay(true);

        //设置轮播时间

        mBanner.setDelayTime(3000);

        //设置指示器位置（当banner模式中有指示器时）

        mBanner.setIndicatorGravity(BannerConfig.CENTER);


        mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //position从1开始
//                Toast.makeText(mActivity, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //banner设置方法全部调用完毕时最后调用


        mBanner.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 给tab添加内容
     */
    private void tabEvent() {
        initFragments();

        adapter = new TabsFragmentAdapter(getChildFragmentManager(), titles, list_fragment);
        mViewPager.setAdapter(adapter);
        mTabLayout.setViewPager(mViewPager);
        mViewPager.setAdapter(adapter);//给ViewPager设置适配器
        //设置圆点
        setOvalLayout();

    }


    private void getData() {


        for (int i = 0; i < 3; i++) {
            sliderImage = new SliderImage();
            if (i % 3 == 0) {

                sliderImage.setImage("http://www.ankura.com.cn/imageRepository/8adc4bfa-160e-4f1a-a664-c927d56defa7.jpg");
                sliderImage.setInfo("hahah");
                sliderImage.setId("1");
            }
            if (i % 3 == 1) {
                sliderImage.setImage("http://www.ankura.com.cn/imageRepository/e96b0c50-7a96-4487-bd87-3e1365b2816d.jpg");
                sliderImage.setInfo("Yana");
                sliderImage.setId("2");
            }
            if (i % 3 == 2) {
                sliderImage.setImage("http://www.ankura.com.cn/imageRepository/37482065-8db3-4cff-b29d-7d42da660a5e.jpg");
                sliderImage.setInfo("Yuner");
                sliderImage.setId("3");
            }
            list.add(sliderImage);

            list2.add(list.get(i).getImage());


        }


    }


    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }


    /**
     * 添加tab下面的fragment
     */
    private void initFragments() {

        list_fragment = new ArrayList<>();
        mElectricCableFragment = new ElectricCableFragment();
        mGILFragment = new GILFragment();


        list_fragment.add(mElectricCableFragment);
        list_fragment.add(mGILFragment);


    }


    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < titles.length; i++) {
            mLlDot.addView(mInflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @OnClick({R.id.contact_Customer_service,R.id.more_products, R.id.ProductOrder, R.id.KnowledgeForum, R.id.TestOrder, R.id.ReportQuery, R.id.install_search, R.id.project_supervision, R.id.ControlforOnline, R.id.diagnosis, R.id.repair_line})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(getActivity());
                break;
            //更多产品
            case R.id.more_products:
                Intent intent = new Intent(getActivity(), ProductsOrderActivity.class);
                intent.putExtra("type", "5");
                startActivity(intent);
                break;
            //产品订购
            case R.id.ProductOrder:
                Intent intent2 = new Intent(getActivity(), ProductsOrderActivity.class);
                intent2.putExtra("type", "5");
                startActivity(intent2);
                break;
            //论坛
            case R.id.KnowledgeForum:
                startActivity(new Intent(getActivity(), KnowledgeForuActivity.class));
                break;
            //实验预约
            case R.id.TestOrder:

                HasLoginUtils.checkLogin(getActivity(),new Intent(getActivity(), ExperimentalAppointmentActivity.class));
                break;
            //报告查询
            case R.id.ReportQuery:
                if (mSPUtils.getBoolean("hasLogin")) {
                    if (mSPUtils.getString("role").equals("1")) {
                        startActivity(new Intent(getActivity(), QueryReportsActivity.class));
                    } else {
                        new AlertDialog(getActivity()).builder().setTitle("提示")
                                .setMsg("你没权限")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();
                    }
                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("您尚未登录不能进行相关操作")
                            .setPositiveButtonColor("#FD4A2E")
                            .setPositiveButton("去登录", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    startActivity(new Intent(getActivity(), LoginActivity.class));

                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }


                break;
            //工艺查询
            case R.id.install_search:
                startActivity(new Intent(getActivity(), QueryTechnologyActivity.class));
                break;
            //工程监理(工作流)
            case R.id.project_supervision:
                HasLoginUtils.checkLogin(getActivity(),new Intent(getActivity(), WorkFlowActivity.class));
                break;
            //千里眼
            case R.id.ControlforOnline:
                HasLoginUtils.checkLogin(getActivity(),new Intent(getActivity(), TelescopeActivity.class));

                break;
            case R.id.diagnosis:

                break;
            //故障报修
            case R.id.repair_line:

                if (mSPUtils.getBoolean("hasLogin")) {
                    if (mSPUtils.getString("role").equals("1")) {
                        startActivity(new Intent(getActivity(), FaultRepairActivity.class));
                    } else {
                        new AlertDialog(getActivity()).builder().setTitle("提示")
                                .setMsg("你没权限")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();
                    }
                } else {
                    new AlertDialog(getActivity()).builder().setTitle("提示")
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





}
