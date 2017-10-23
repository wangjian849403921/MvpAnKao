package com.mvpankao.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WorkFlowDetailBean;
import com.mvpankao.ui.adapter.WorkFlowDetailAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.widget.CircleProgressView;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2016/12/29
 */

public class WorkFlowDetailActivity extends BaseActivity {

    //    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    WorkFlowDetailAdapter mWorkFlowDetailAdapter;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.workdetail_image)
    ImageView mWorkdetailImage;
    @BindView(R.id.workflow_name)
    TextView mWorkflowName;
    @BindView(R.id.workflow_time)
    TextView mWorkflowTime;
    @BindView(R.id.workflow_number)
    TextView mWorkFlowNumber;
    @BindView(R.id.lv_list)
    ListView mLvList;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    List<WorkFlowDetailBean.ObjectBean.WorkflowProcesslistBean> list = new ArrayList<>();
    @BindView(R.id.responsiblePerson)
    TextView mResponsiblePerson;
    List<WorkFlowDetailBean.ObjectBean.WorkflowWorkerlistBean> workerlist = new ArrayList<>();
    String worker = "";
    String responsiblePerson;

    @Override
    protected void initView() {
        mTitle.setText("工作流详情");
        mScrollView.smoothScrollTo(0, 0);

        mLvList.setDividerHeight(0);
//        showProgress();
        mWorkflowName.setText(getIntent().getStringExtra("name"));
        mWorkFlowNumber.setText("工作流编号：" + getIntent().getStringExtra("id"));
        LogUtils.d(NetUrl.DOCHeader +getIntent().getStringExtra("image"));
        RequestOptions options = new RequestOptions();
        options.error(R.drawable.about_us);
        Glide.with(mContext).load(NetUrl.DOCHeader + getIntent().getStringExtra("image")).apply(options).into(mWorkdetailImage);
        mWorkflowTime.setText(getIntent().getStringExtra("time"));

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_workflowdetail;
    }

    @Override
    protected void initEventAndData() {
        iniData();
    }

    private void iniData() {
        if(mContext.isDestroyed()){
            return;
        }
        String url = NetUrl.URLHeader + NetUrl.WorkFlow.WorkFlowDetail;
        Map<String, String> map = new HashMap<>();
        map.put("id", getIntent().getStringExtra("id"));
        mMyOkhttp.post().url(url).params(map).tag(mContext).enqueue( new GsonResponseHandler<WorkFlowDetailBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                hideProgress();
            }

            @Override
            public void onSuccess(int statusCode, WorkFlowDetailBean response) {
                if (response.getCode() == 200) {
                    hideProgress();
                    for (int i = 0; i < response.getObject().getWorkflowProcesslist().size(); i++) {
                        list.add(response.getObject().getWorkflowProcesslist().get(i));
                    }
                    if (response.getObject().getWorkflowWorkerlist()!=null) {
                        for (int j = 0; j < response.getObject().getWorkflowWorkerlist().size(); j++) {
                            workerlist.add(response.getObject().getWorkflowWorkerlist().get(j));
                        }
                    }
                    EventBus.getDefault().post(new MyEvent(Constants.WORKFLOWDETAIL_CODE));
                } else {
                    hideProgress();
                }
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WORKFLOWDETAIL_CODE:
                mWorkFlowDetailAdapter = new WorkFlowDetailAdapter(mContext, list);
                mLvList.setAdapter(mWorkFlowDetailAdapter);
                this.setListViewHeightBasedOnChildren(mLvList);
                if (workerlist.size()>0) {
                    for (int j = 0; j < workerlist.size(); j++) {
                        if (workerlist.get(j).getIsManager() == 1) {
                            worker = workerlist.get(j).getUsername();

                        }
                    }
                    if (!StringUtils.isEmpty(worker)) {
                        mResponsiblePerson.setText("项目负责人：" + worker);
                    } else {
                        mResponsiblePerson.setText("项目负责人：无");

                    }
                }
                break;
            //上传图片后刷新界面
            case Constants.WorkFlowStep_CODE:
                list.clear();
                iniData();
                break;
        }
    }

    @OnClick({R.id.rl_back, R.id.contact_Customer_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);
                break;
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }


    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mScrollView.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mScrollView.setVisibility(View.VISIBLE);
    }


}
