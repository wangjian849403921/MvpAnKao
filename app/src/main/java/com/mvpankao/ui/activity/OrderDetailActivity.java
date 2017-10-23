package com.mvpankao.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.OrderDetailBean;
import com.mvpankao.ui.adapter.OrderDetailAdapter;
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

public class OrderDetailActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    @BindView(R.id.statue)
    TextView mStatue;
    @BindView(R.id.UserName)
    TextView mUserName;
    @BindView(R.id.PhoneNum)
    TextView mPhoneNum;
    @BindView(R.id.Address)
    TextView mAddress;
    @BindView(R.id.product_image)
    ImageView mProductImage;
    @BindView(R.id.product_name)
    TextView mProductName;
    @BindView(R.id.product_count)
    TextView mProductCount;
    @BindView(R.id.lv_list)
    ListView mLvList;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    OrderDetailAdapter mOrderDetailAdapter;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    String statue, username, phone, address, productname, productimage, count, param;
    @BindView(R.id.AddressSelect)
    LinearLayout mAddressSelect;
    @BindView(R.id.rl_image)
    RelativeLayout mRlImage;
    @BindView(R.id.product_param)
    TextView mProductParam;
    @BindView(R.id.orderNumber)
    TextView mOrderNumber;

    @Override

    protected void initView() {
        showProgress();
        mContactCustomerService.setVisibility(View.INVISIBLE);
        mTitle.setText("订单详情");
        mScrollView.smoothScrollTo(0, 0);
        mLvList.setDividerHeight(0);

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_orderdetail;
    }

    @Override
    protected void initEventAndData() {

        initData();


    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        String url = NetUrl.URLHeader + NetUrl.MyOrder.MyOrderDETAIL;
        Map<String, String> map = new HashMap<>();
        map.put("id", getIntent().getStringExtra("id"));
        mMyOkhttp.post().url(url).params(map).tag(mContext).enqueue( new GsonResponseHandler<OrderDetailBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                hideProgress();
                mScrollView.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(int statusCode, OrderDetailBean response) {
                if (response.getCode() == 200) {
                    hideProgress();
                    statue = response.getObject().getProductOrderStatus();
                    username = response.getObject().getReceiveName();
                    phone = response.getObject().getReceivePhone();
                    address = response.getObject().getReceiveAddress();
                    productname = response.getObject().getProductName();
                    productimage = NetUrl.DOCHeader + response.getObject().getProductIcon();
                    count = response.getObject().getProductOrderNum();
                    param = response.getObject().getProductParmv0() + "  " + response.getObject().getProductParmv1();
                    EventBus.getDefault().post(new MyEvent(Constants.ORDERDETAIL_CODE));
                }else{
                    hideProgress();
                    mScrollView.setVisibility(View.GONE);
                }
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.ORDERDETAIL_CODE:
                mOrderNumber.setText("订单号：" + getIntent().getStringExtra("id"));
                mUserName.setText(username);
                mPhoneNum.setText(phone);
                mAddress.setText(address);
                if (statue.equals("0")) {
                    mStatue.setText("(待安装)");
                    mStatue.setTextColor(Color.parseColor("#ff2626"));

                } else {
                    mStatue.setText("(已完成)");
                    mStatue.setTextColor(Color.parseColor("#398ab8"));
                }
                Glide.with(mContext).load(productimage).into(mProductImage);
                mProductParam.setText(param);
                mProductName.setText(productname);
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
