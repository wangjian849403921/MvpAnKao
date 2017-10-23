package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.model.bean.ProductOrder;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.presenter.CommitOrderPresenter;
import com.mvpankao.ui.adapter.CommitOrderAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.DialogUtils;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.CommitOrderView;
import com.mvpankao.widget.ClearEditText;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;

import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 填写提交订单
 * Created by wangjian
 * On  2016/12/1
 */

public class CommitOrderActivity extends BaseActivity implements CommitOrderView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.UserName)
    TextView mUserName;
    @BindView(R.id.PhoneNum)
    TextView mPhoneNum;
    @BindView(R.id.Address)
    TextView mAddress;
    @BindView(R.id.OrderRecycleView)
    RecyclerView mOrderRecycleView;
    @BindView(R.id.ProjectName)
    ClearEditText mProjectName;
    @BindView(R.id.Demand)
    EditText mDemand;
    @BindView(R.id.commit)
    TextView mCommitOrder;
    @BindView(R.id.AddressSelect)
    LinearLayout mAddressSelect;
    private static int mRequestCode = 0x001101;
    ProductOrder mProductOrder;
    List<ProductsBean.ObjectBean.ListBean> mProductList;
    CommitOrderAdapter mAdapter;
    String addressid = "", phone;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    private CommitOrderPresenter CommitOrderPresenter = new CommitOrderPresenter(this);

    @Override
    protected void initView() {
        mTitle.setText("填写订单");
        mProductList = (ArrayList<ProductsBean.ObjectBean.ListBean>) getIntent().getSerializableExtra("mProductList");
        mOrderRecycleView.setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        mAddress.setText(getIntent().getStringExtra("address"));
        mUserName.setText(getIntent().getStringExtra("reciver"));
        phone = getIntent().getStringExtra("phone");
        if (!TextUtils.isEmpty(phone) && phone.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phone.length(); i++) {
                char c = phone.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

            mPhoneNum.setText(sb.toString());
        }
        getData();
    }

    private void getData() {

        mAdapter = new CommitOrderAdapter(mContext, mProductList);
        mOrderRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.ac_commitorder;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (mRequestCode == requestCode
                && resultCode == AddressActivity.resultCode) {
            addressid = data.getStringExtra("addressid");
            mUserName.setText(data.getStringExtra("UserName"));
            phone = data.getStringExtra("PhoneNum");
            if (!TextUtils.isEmpty(phone) && phone.length() > 6) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < phone.length(); i++) {
                    char c = phone.charAt(i);
                    if (i >= 3 && i <= 6) {
                        sb.append('*');
                    } else {
                        sb.append(c);
                    }
                }

                mPhoneNum.setText(sb.toString());
            }


            mAddress.setText(data.getStringExtra("Province") + data.getStringExtra("City") + data.getStringExtra("Area") + data.getStringExtra("AddressDetail"));

        }


    }

    @OnClick({R.id.rl_back, R.id.commit, R.id.AddressSelect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                new AlertDialog(mContext).builder().setTitle("取消订单")
                        .setMsg("您确定要取消订单？")
                        .setPositiveButtonColor("#FD4A2E")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mProductList.clear();

                                finish();

                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

                break;
            case R.id.commit:

                if (mSPUtils.contains("shopcar")) {

                    if (mSPUtils.getString("shopcar").equals("1")) {
                        commitShopCarOrder();
                    } else {
                        commitOrder();
                    }


                }

                break;
            case R.id.AddressSelect:
                Intent intent = new Intent(this, AddressActivity.class);

                startActivityForResult(intent, mRequestCode);
                break;
        }
    }

    private void commitShopCarOrder() {
        if(mContext.isDestroyed()){
            return;
        }
        String productid = "";
        String productStockDetailId = "";
        String productOrderNum = "";
        String shopcarid = "";
        for (int i = 0; i < mProductList.size(); i++) {
            productid += ";" + mProductList.get(i).getProductId();
            productStockDetailId += ";" + mProductList.get(i).getProductStockDetailIdfk();
            productOrderNum += ";" + mProductList.get(i).getProductCount();
            shopcarid += ";" + mProductList.get(i).getShopCarid();
        }
        String stockid = productStockDetailId.substring(1, productStockDetailId.length());
        String productId = productid.substring(1, productid.length());
        String number = productOrderNum.substring(1, productOrderNum.length());
        String carId = shopcarid.substring(1, shopcarid.length());
        String receiver = mUserName.getText().toString();
        String address = mAddress.getText().toString();
        String require = mDemand.getText().toString();
        CommitOrderPresenter.commitShopCarOrder(mContext, mMyOkhttp, mSPUtils.getString("userid"), stockid, productId, number, carId, receiver, phone, address, require);
    }

    /**
     * 提交订单
     */
    private void commitOrder() {
        String userid = mSPUtils.getString("userid");
        String stockid = mProductList.get(0).getProductStockDetailIdfk() + "";
        String productId=mProductList.get(0).getProductId();
        String number=mProductList.get(0).getProductCount() + "";
        String receiver=mUserName.getText().toString();
        String address=mAddress.getText().toString();
        String require=mDemand.getText().toString();
        CommitOrderPresenter.commitOrder(mContext,mMyOkhttp,userid,stockid,productId,number,receiver,phone,address,require);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.contact_Customer_service)
    public void onClick() {
        CallPhoneUtils.callphone(mContext);

    }

    @Override
    public void showDialog() {
        DialogUtils.Progress(mContext, "   提交订单中...");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        EventBus.getDefault().post(new MyEvent(Constants.WorkFlowStep_CODE));
        EventBus.getDefault().post(new MyEvent(Constants.SHOPCARUPDATE_CODE));
        DialogUtils.dismiss();
        LemonHello.getSuccessHello("提示", "恭喜您，下单成功！")
                .addAction(new LemonHelloAction("我知道啦", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView helloView, LemonHelloInfo helloInfo, LemonHelloAction helloAction) {
                        helloView.hide();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                EventBus.getDefault().post(new MyEvent(Constants.ProductDetail_CODE));

                                finish();
                            }
                        }, 1000);
                    }
                }))
                .show(mContext);

    }

    @Override
    public void onFailure() {
        DialogUtils.dismiss();
    }

    @Override
    public void onError() {
        DialogUtils.dismiss();
    }
}
