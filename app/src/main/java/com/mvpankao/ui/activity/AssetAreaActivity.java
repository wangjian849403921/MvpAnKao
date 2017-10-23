package com.mvpankao.ui.activity;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.model.bean.ParentEntity;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.presenter.WorkOrderPresenter;
import com.mvpankao.ui.adapter.ParentwithCheckAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.WorkOrderView;
import com.mvpankao.widget.CircleProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2017/1/20
 */

public class AssetAreaActivity extends BaseActivity implements WorkOrderView, ExpandableListView.OnGroupExpandListener,
        ParentwithCheckAdapter.OnChildTreeViewClickListener {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.eList)
    ExpandableListView mEList;
    @BindView(R.id.righttext)
    TextView mRighttext;
    @BindView(R.id.rl_edit)
    RelativeLayout mRlEdit;

    List<String> ChildGroupNameArray = new ArrayList<>();
    List<String> ChildChildNameArray = new ArrayList<>();


    List<String> ChildGroupImageArray = new ArrayList<>();


    List<String> ChiidGroupIdArray = new ArrayList<>();
    List<String> ChildChildIdArray = new ArrayList<>();

    List<String> GroupPositionArray = new ArrayList<>();
    List<String> ChildPositionArray = new ArrayList<>();


    private ArrayList<ParentEntity> parents = new ArrayList<ParentEntity>();

    private ParentwithCheckAdapter adapter;

    private WorkOrderPresenter mWorkOrderPresenter = new WorkOrderPresenter(this);

    @Override
    protected void initView() {
        showProgress();
        mTitle.setText("资产范围");
        mRighttext.setText("完成");

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_assert_area;
    }

    @Override
    protected void initEventAndData() {
        initData();

    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        mWorkOrderPresenter.initAssertArea(mContext, mMyOkhttp, parents);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.AssertDetail_CODE:
                adapter = new ParentwithCheckAdapter(mContext, parents, ChildGroupNameArray, ChiidGroupIdArray, ChildChildNameArray, ChildChildIdArray, GroupPositionArray, ChildPositionArray, ChildGroupImageArray);

                mEList.setAdapter(adapter);
                initEList();

                break;
            case Constants.ASSERTLISTUPDATE_CODE:
                parents.clear();
                initData();
                break;
        }
    }

    /**
     * @author wangjian
     * <p>
     * 初始化ExpandableListView
     */
    private void initEList() {


        final int groupCount = mEList.getCount();

        for (int i = 0; i < groupCount; i++) {

            mEList.expandGroup(i);

        }
        mEList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                return true;
            }
        });
        adapter.setOnChildTreeViewClickListener(this);

    }

    /**
     * @author wangjian
     * <p>
     * 点击子ExpandableListView的子项时，回调本方法，根据下标获取值来做相应的操作
     */
    @Override
    public void onClickPosition(int parentPosition, int groupPosition,
                                int childPosition) {

    }

    /**
     * @author wangjian
     * <p>
     * 展开一项，关闭其他项，保证每次只能展开一项
     */
    @Override
    public void onGroupExpand(int groupPosition) {
        for (int i = 0; i < parents.size(); i++) {
            if (i != groupPosition) {
                mEList.expandGroup(groupPosition);
            }
        }
    }

    @OnClick({R.id.rl_back, R.id.rl_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_edit:
                String position = "";
                String parentname = "";
                String parentid = "";
                String childgroupname = "";
                String childgroupid = "";
                String groupposition = "";
                String childchildname = "";
                String childchildid = "";
                String childposition = "";
                String assertimage = "";
                HashMap<Integer, Boolean> state = adapter.state;


                for (int j = 0; j < adapter.getGroupCount(); j++) {


                    if (state.get(j) != null) {

                        ParentEntity parentItem = (ParentEntity) adapter.getGroup(j);
                        String name = parentItem.getGroupName();
                        String id = parentItem.getGroupId();
                        parentname += ";" + name;
                        parentid += ";" + id;
                        position += ";" + j;
                    }

                }
                //获取第二级的名字
                ChildGroupNameArray = adapter.childAdapter.getChildGroupName();
                if (ChildGroupNameArray != null && !ChildGroupNameArray.isEmpty()) {
                    for (String name : ChildGroupNameArray) {

                        childgroupname += ";" + name;
                    }

                }
                //获取第二级的ID
                ChiidGroupIdArray = adapter.childAdapter.getChildGroupId();
                if (ChiidGroupIdArray != null && !ChiidGroupIdArray.isEmpty()) {
                    for (String id : ChiidGroupIdArray) {

                        childgroupid += ";" + id;
                    }

                }
                //获取第二级的Position
                GroupPositionArray = adapter.childAdapter.getChildGroupPosition();
                if (GroupPositionArray != null && !GroupPositionArray.isEmpty()) {
                    for (String positions : GroupPositionArray) {

                        groupposition += ";" + positions;
                    }

                }

                //获取第三级的名字
                ChildChildNameArray = adapter.childAdapter.getChildChildName();
                if (ChildChildNameArray != null && !ChildChildNameArray.isEmpty()) {
                    for (String name : ChildChildNameArray) {

                        childchildname += ";" + name;
                    }

                }
                //获取第三级的ID
                ChildChildIdArray = adapter.childAdapter.getChildChildid();
                if (ChildChildIdArray != null && !ChildChildIdArray.isEmpty()) {
                    for (String id : ChildChildIdArray) {

                        childchildid += ";" + id;
                    }

                }
                //获取第三级的ID
                ChildPositionArray = adapter.childAdapter.getChildChildPosition();
                if (ChildPositionArray != null && !ChildPositionArray.isEmpty()) {
                    for (String positions : ChildPositionArray) {

                        childposition += ";" + positions;
                    }

                }
                //获取第二级的ID
                ChildGroupImageArray = adapter.childAdapter.getAssertImage();
                if (ChildGroupImageArray != null && ChildGroupImageArray.size() > 0) {
                    SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
                    assertimage = ChildGroupImageArray.get(0);
                    sp.putString("image", assertimage);

                }
                if (parentname.length() > 0) {
                    SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
                    sp.putString("parentname", parentname.substring(1, parentname.length()));
                    sp.putString("parentid", parentid.substring(1, parentid.length()));

                    LogUtils.d(parentname.substring(1, parentname.length()));
                    LogUtils.d(parentid.substring(1, parentid.length()));
                    sp.putString("position", position.substring(1, position.length()));
                    EventBus.getDefault().post(new MyEvent(321));
                    finish();

                } else {
                    SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
                    sp.remove("parentname");
                    sp.remove("parentid");
                    sp.remove("position");
                    EventBus.getDefault().post(new MyEvent(321));
                    finish();

                }
                if (childgroupname.length() > 0) {
                    SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
                    sp.putString("childgroupname", childgroupname.substring(1, childgroupname.length()));
                    sp.putString("childgroupid", childgroupid.substring(1, childgroupid.length()));
                    sp.putString("groupposition", groupposition.substring(1, groupposition.length()));


                    LogUtils.d(childgroupname.substring(1, childgroupname.length()));
                    LogUtils.d(childgroupid.substring(1, childgroupid.length()));
                    EventBus.getDefault().post(new MyEvent(321));
                    finish();

                } else {
                    SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
                    sp.remove("childgroupname");
                    sp.remove("childgroupid");
                    sp.remove("groupposition");


                    EventBus.getDefault().post(new MyEvent(321));
                    finish();

                }
                if (childchildname.length() > 0) {
                    SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
                    sp.putString("childchildname", childchildname.substring(1, childchildname.length()));
                    sp.putString("childchildid", childchildid.substring(1, childchildid.length()));


                    LogUtils.d(childchildname.substring(1, childchildname.length()));
                    LogUtils.d(childchildid.substring(1, childchildid.length()));
                    sp.putString("childposition", childposition.substring(1, childposition.length()));

                    EventBus.getDefault().post(new MyEvent(321));
                    finish();

                } else {
                    SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
                    sp.remove("childchildname");
                    sp.remove("childchildid");
                    sp.remove("childposition");
                    EventBus.getDefault().post(new MyEvent(321));
                    finish();

                }


                break;
        }
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mEList.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mEList.setVisibility(View.VISIBLE);
    }


    @Override
    public void setJsonArray(JSONArray jsonArray) {

    }

    @Override
    public String getUserId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWorkOrderSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.AssertDetail_CODE));
    }

    @Override
    public void onWarningSuccess() {

    }

    @Override
    public void onWorkOrderSuccess(WorkOrderDeatil response) {

    }

    @Override
    public void onFailure() {
        hideProgress();
    }

    @Override
    public void onError() {
        hideProgress();
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
