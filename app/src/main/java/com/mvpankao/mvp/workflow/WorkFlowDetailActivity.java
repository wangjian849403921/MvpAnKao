package com.mvpankao.mvp.workflow;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WorkFlowDetailBean;
import com.mvpankao.model.bean.WorkFlowDetailBean2;
import com.mvpankao.mvp.base.MVPBaseActivity;
import com.mvpankao.ui.activity.StepListActivity;
import com.mvpankao.ui.adapter.WorkFlowDetailAdapter2;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.widget.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2016/12/29
 */

public class WorkFlowDetailActivity extends MVPBaseActivity<WorkFlowContract.View, WorkFlowPresenter> implements WorkFlowContract.View {

    //    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    WorkFlowDetailAdapter2 mWorkFlowDetailAdapter;
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
    List<WorkFlowDetailBean2.ObjectBean> list = new ArrayList<>();
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
        LogUtils.d(NetUrl.DOCHeader + getIntent().getStringExtra("image"));
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
       mPresenter.initData(mContext,mMyOkhttp,getIntent().getStringExtra("id"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WORKFLOWDETAIL_CODE:
                mWorkFlowDetailAdapter = new WorkFlowDetailAdapter2(mContext, list);
                mLvList.setAdapter(mWorkFlowDetailAdapter);
                this.setListViewHeightBasedOnChildren(mLvList);
//                if (workerlist.size()>0) {
//                    for (int j = 0; j < workerlist.size(); j++) {
//                        if (workerlist.get(j).getIsManager() == 1) {
//                            worker = workerlist.get(j).getUsername();
//
//                        }
//                    }
//                    if (!StringUtils.isEmpty(worker)) {
//                        mResponsiblePerson.setText("项目负责人：" + worker);
//                    } else {
//                        mResponsiblePerson.setText("项目负责人：无");
//
//                    }
//                }
                mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int statue = list.get(position).getWorkflowStepStatus();
                        switch (statue) {
                            case 0:

                                break;
                            case 1:

                                break;
                            case 2:
                                if (mSPUtils.getString("role").equals("2")) {
                                    Intent intent = new Intent(mContext, StepListActivity.class);
                                    intent.putExtra("step", list.get(position).getWorkflowStepOrder() + "");
                                    intent.putExtra("id", list.get(position).getWorkflowProIdFk() + "");
                                    intent.putExtra("stepid", list.get(position).getId() + "");
                                    intent.putExtra("statue", statue + "");
                                    startActivity(intent);
                                }
                                break;
                            case 3:
                                if (mSPUtils.getString("role").equals("2")) {
                                    Intent intent = new Intent(mContext, StepListActivity.class);
                                    intent.putExtra("step", list.get(position).getWorkflowStepOrder()+"");
                                    intent.putExtra("id", list.get(position).getWorkflowProIdFk()+"");
                                    intent.putExtra("stepid", list.get(position).getId()+"");
                                    intent.putExtra("statue",statue+"");
                                    startActivity(intent);
                                }
                                break;
                            case 4:
                                if (mSPUtils.getString("role").equals("2")) {

                                    Intent intent = new Intent(mContext, StepListActivity.class);
                                    intent.putExtra("step", list.get(position).getWorkflowStepOrder()+"");
                                    intent.putExtra("id", list.get(position).getWorkflowProIdFk()+"");
                                    intent.putExtra("stepid", list.get(position).getId()+"");
                                    intent.putExtra("statue", statue+"");
                                    startActivity(intent);
                                }
                                break;

                        }
                    }
                });
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


    @Override
    public void onFailure() {
        hideProgress();
    }

    @Override
    public void onRequestFailed() {
        hideProgress();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(Object responses) {
        WorkFlowDetailBean2 response=(WorkFlowDetailBean2)responses;
        for (int i = 0; i < response.getObject().size(); i++) {
            list.add(response.getObject().get(i));
        }
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.WORKFLOWDETAIL_CODE));
    }


}
