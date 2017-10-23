package com.mvpankao.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.base.BaseAdapter;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.Address;
import com.mvpankao.presenter.AddressPresenter;
import com.mvpankao.ui.adapter.AddressAdapter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.DialogUtils;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.AddressView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.recycleview.WrapContentLinearLayoutManager;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2016/11/30
 */

public class AddressActivity extends BaseActivity implements AddressView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.AddressRecycleView)
    RecyclerView mAddressRecycleView;

    List<Address.ObjectBean> list = new ArrayList<>();
    AddressAdapter mAdapter;
    @BindView(R.id.CreatAddress)
    TextView mCreatAddress;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    @BindView(R.id.rl_Layout)
    RelativeLayout mLayout;
    public static int resultCode = 0x006666;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    private AddressPresenter AddressPresenter;

    @Override
    protected void initView() {
        mTitle.setText("收货地址");
        showProgress();
        mAddressRecycleView.setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));


    }

    @Override
    protected int getLayout() {
        return R.layout.ac_addresslist;
    }

    @Override
    protected void initEventAndData() {

        initData();

//        getData();

    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        AddressPresenter = new AddressPresenter(this);
        AddressPresenter.initData(mContext, mMyOkhttp, list, mSPUtils.getString("userid"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.ADDRESSLIST_CODE:
                mAdapter = new AddressAdapter(mContext, list);
                mAddressRecycleView.setAdapter(mAdapter);
                if (list.size() > 0) {
                    mSPUtils.putString("addressid", list.get(0).getId());
                    mSPUtils.putString("defaultaddress", list.get(0).getProvince() + list.get(0).getCitys() + list.get(0).getAreas() + list.get(0).getReceiveAddress());
                } else {

                }
                mAdapter.setOnItemOnClick(new BaseAdapter.OnItemClickListener() {
                    @Override
                    public void OnClick(View view, int position) {
                        Intent intent = getIntent();
                        String data = intent.getDataString();
                        intent.putExtra("addressid", list.get(position).getId());
                        intent.putExtra("UserName", list.get(position).getReceiveName());
                        intent.putExtra("PhoneNum", list.get(position).getReceivePhone());
                        intent.putExtra("Province", list.get(position).getProvince());
                        intent.putExtra("City", list.get(position).getCitys());
                        intent.putExtra("Area", list.get(position).getAreas());
                        intent.putExtra("AddressDetail", list.get(position).getReceiveAddress());
                        setResult(resultCode, intent);
                        finish();
                    }
                });
                mAdapter.setOnItemLongClickListener(new BaseAdapter.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(View view, final int position) {


                        new AlertDialog(mContext).builder().setTitle("删除地址")
                                .setMsg("您确定要删除？")
                                .setPositiveButtonColor("#FD4A2E")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (NetworkUtils.isConnected(mContext)) {


                                            deleteAddress();


                                        } else {
                                            Toast.makeText(mContext, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
                                        }


                                    }

                                    private void deleteAddress() {
                                        String url = NetUrl.URLHeader + NetUrl.Address.AddressDelete;
                                        Map<String, String> map = new HashMap<>();

                                        map.put("id", list.get(position).getId());
                                        mMyOkhttp.post().url(url).params(map).tag(mContext).enqueue(new JsonResponseHandler() {
                                            @Override
                                            public void onSuccess(int statusCode, JSONObject response) {
                                                try {
                                                    if (response.getString("code").equals("200")) {
                                                        if (list.get(position).getIsdefault() == 1) {
                                                            mSPUtils.putString("addressid", "");
                                                            mSPUtils.putString("defaultaddress", "");


                                                        }

                                                        list.remove(position);
                                                        mAdapter.notifyDataSetChanged();
                                                        new AlertDialog(mContext).builder().setTitle("提示")
                                                                .setMsg("删除成功")
                                                                .setPositiveButton("确定", new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        if (list.size() == 0) {
                                                                            mSPUtils.putString("addressid", "");
                                                                            mSPUtils.putString("defaultaddress", "");
                                                                        }
                                                                        EventBus.getDefault().post(new MyEvent(Constants.Address_CODE));
                                                                    }
                                                                }).show();

                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            @Override
                                            public void onFailure(int statusCode, String error_msg) {

                                            }
                                        });

                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();

                    }
                });

                break;
            //编辑新建地址成功后  更新list列表
            case Constants.ADDRESSEDIT_CODE:
                list.clear();
                initData();
                mAdapter.notifyDataSetChanged();
                break;
        }
    }


    @OnClick({R.id.contact_Customer_service, R.id.rl_back, R.id.CreatAddress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);


                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.CreatAddress:
                Intent intent = new Intent(mContext, NewAddressActivity.class);
                if (list.size() == 0) {
                    intent.putExtra("size", "0");
                } else {
                    intent.putExtra("size", "1");

                }
                startActivity(intent);

                break;
        }
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mLayout.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(Constants.ADDRESSLIST_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
    }

    @Override
    public void onError() {
        hideProgress();
        DialogUtils.dismiss();
    }
}
