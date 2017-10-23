package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WarningDetailBean;
import com.mvpankao.presenter.WarningPresenter;
import com.mvpankao.ui.adapter.WarningDetailAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WarningView;
import com.mvpankao.widget.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 报警详情
 * Created by wangjian
 * On  2017/1/3
 */

public class WarningDeatilActivity extends BaseActivity implements WarningView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.position)
    TextView mPosition;
    @BindView(R.id.groupName)
    TextView mGroupName;
    @BindView(R.id.xiangName)
    TextView mXiangName;
    @BindView(R.id.description)
    TextView mDescription;
    @BindView(R.id.lv_list)
    ListView mLvList;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.btn)
    TextView mBtn;
    WarningDetailAdapter mWarningDetailAdapter;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    //    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    List<WarningDetailBean.ObjectBean.TimePointBean> list = new ArrayList<>();
    String warningname, time, groupname, image, assertarea, statues, level, point, note;
    @BindView(R.id.rl_layout)
    RelativeLayout mRlLayout;
    @BindView(R.id.warningimage)
    ImageView mWarningimage;
    @BindView(R.id.level)
    ImageView mLevel;
    String assertid, assertlevel;
    @BindView(R.id.statue)
    TextView mStatue;
    @BindView(R.id.assertName)
    TextView mAssertName;
    @BindView(R.id.rl_Assert)
    RelativeLayout mRlAssert;

    private WarningPresenter mWarningPresenter = new WarningPresenter(this);

    @Override

    protected void initView() {
        mContactCustomerService.setVisibility(View.INVISIBLE);
        mTitle.setText("报警详情");
        mScrollView.smoothScrollTo(0, 0);
        mLvList.setDividerHeight(0);
        showProgress();
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_warningdetail;
    }

    @Override
    protected void initEventAndData() {
        initData();
    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
       mWarningPresenter.initWarningDetailData(mContext,mMyOkhttp,list);


    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.WARNINGDETAIL_CODE:
                LogUtils.d(assertid);
                LogUtils.d(getIntent().getStringExtra("id"));
                mName.setText(warningname);
                mAssertName.setText(assertarea);
                if (statues.equals("已解除") && level.equals("高")) {
                    mStatue.setBackgroundResource(R.drawable.policeshape2);
                    mStatue.setText("已解除");
                    mLevel.setImageResource(R.drawable.policehigh);

                }
                if (statues.equals("已解除") && level.equals("低")) {

                    mStatue.setBackgroundResource(R.drawable.policeshape2);
                    mStatue.setText("已解除");
                    mLevel.setImageResource(R.drawable.policelow);

                }
                if (statues.equals("未解除") && level.equals("高")) {
                    mStatue.setBackgroundResource(R.drawable.policeshape);
                    mStatue.setText("待解除");
                    mLevel.setImageResource(R.drawable.policehigh_light);

                }
                if (statues.equals("未解除") && level.equals("低")) {
                    mStatue.setBackgroundResource(R.drawable.policeshape);
                    mStatue.setText("待解除");
                    mLevel.setImageResource(R.drawable.policelow_light);

                }
                mTime.setText(time);
                RequestOptions options = new RequestOptions();
                options.error(R.drawable.about_us);
                Glide.with(mContext).load(NetUrl.DOCHeader + image).apply(options).into(mWarningimage);
                mPosition.setText(groupname);
                mXiangName.setText(point);
                mDescription.setText(note);
                mWarningDetailAdapter = new WarningDetailAdapter(this, list);
                mLvList.setAdapter(mWarningDetailAdapter);
                this.setListViewHeightBasedOnChildren(mLvList);
                break;
        }
    }

    @OnClick({R.id.rl_back, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn:
                if (mSPUtils.getBoolean("hasLogin")) {
                    Intent intent = new Intent(mContext, CreateWarningOrderActivity.class);
                    intent.putExtra("name", mAssertName.getText().toString());
                    intent.putExtra("time", "时间:" + mTime.getText().toString());
                    intent.putExtra("level", "级别:" + level);
                    intent.putExtra("position", "位置:" + mPosition.getText().toString());
                    intent.putExtra("note", "变相说明:" + mDescription.getText().toString());
                    intent.putExtra("assertimage", image);
                    startActivity(intent);
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


//                overridePendingTransition(R.anim.popupwindow_out, R.anim.popupwindow_in);

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
        mRlLayout.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mRlLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_Assert)
    public void onClick() {
        Intent intent = new Intent(mContext, ParentActivity.class);
        intent.putExtra("name", mAssertName.getText().toString());
        intent.putExtra("id",assertid);
        intent.putExtra("level", assertlevel);
        intent.putExtra("image", image);

        startActivity(intent);
    }

    @Override
    public String getWarningId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public void getItemSize(int size) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(WarningDetailBean response) {

        warningname = response.getObject().getAlarm_name();
        time = TimeUtils.milliseconds2String(response.getObject().getAlarm_time());
        groupname = response.getObject().getGroupname();
        image = response.getObject().getAsset_icon();
        assertarea = response.getObject().getAsset_area();
        statues = response.getObject().getAlarm_status();
        level = response.getObject().getAlarm_level();
        point = response.getObject().getAlarm_point();
        note = response.getObject().getAlarm_note();
        assertid = response.getObject().getAlarm_assetsname_id();
        assertlevel = response.getObject().getAlarm_assets_level();
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.WARNINGDETAIL_CODE));
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure() {
        hideProgress();
        mRlLayout.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        hideProgress();
        mRlLayout.setVisibility(View.GONE);
    }
}
