package com.mvpankao.ui.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.utils.CallPhoneUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 关于安靠
 * Created by wangjian
 * On  2016/11/22
 */

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.top_image)
    ImageView mTopImage;
    @BindView(R.id.aboutcontent)
    TextView mAboutcontent;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;

    @Override
    protected void initView() {
        mTitle.setText("关于安靠");
        String content = " \u3000" + "随着中国经济的快速发展，对电力高效输送服务和现代化智能电网提出了新的挑战。未来，环保安全的地下输电将成为城市电网和电源端输出的主要方式，超高压电缆线路和新型气体管道母线（GIL）将相互弥补来完成这一重要任务。\n" +
                " \u3000" + "江苏安靠智能输电工程科技股份有限公司创建于2006 年，公司前期主要致力于超高压电缆输电线路关键技术装备——超高压电缆连接件的国产化研发和制造。为适应智能电网对地下输电提出新的技术需求，公司逐步由关键技术解决向整体系统方案提供发展。时至今日，安靠已成为国内唯一专业的超高压电缆系统解决方案整体供应商；不但为客户提供连接件、敷设用材、监测、控制智能设备；更为客户提供包括：设计、施工、设备成套、试验、运行维护在内的全面专业服务。目前，在电力系统中运行的所有国产化330kV,500kV 电缆线路，均由安靠提供上述服务。这些线路在国家电网、三峡集团、大唐集团等重大工程项目中正为社会经济发展输送着源源不断的绿色能源。\n" +
                " \u3000" + "作为国家级高新技术企业，安靠聚焦全球新能源与智能电网建设的发展机遇，不断创新，成功研发了适应大容量地下输电 330kV-1000kV 气体管道母线 （GIL）,填补国内和国际空白，为实现绿色、智能、安全、可靠的国产化输电系统提供更多选择，安靠助力中国电网健康发展！";
        mAboutcontent.setText(content);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setDistanceToTriggerSync(100);
//        refreshLayout.setSize(1);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.about_us;
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
}
