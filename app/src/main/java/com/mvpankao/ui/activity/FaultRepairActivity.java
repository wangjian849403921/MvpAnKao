package com.mvpankao.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.model.bean.TypeBean;
import com.mvpankao.presenter.RepairPresenter;
import com.mvpankao.presenter.TypeSelectPresenter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.view.FaultRepairView;
import com.mvpankao.view.TypeView;
import com.mvpankao.widget.ClearEditText;
import com.mvpankao.widget.popupwindow.MyPopupWindow;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 故障报修
 * Created by wangjian
 * On  2016/11/23
 */

public class FaultRepairActivity extends BaseActivity implements TypeView, FaultRepairView, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContact_Customer_Service;
    @BindView(R.id.TypeSelect)
    RelativeLayout mTypeSelect;
    @BindView(R.id.Remarks)
    EditText mRemarks;
    @BindView(R.id.LineName)
    ClearEditText mLineName;
    @BindView(R.id.Area)
    TextView mArea;
    @BindView(R.id.AddressDetail)
    ClearEditText mAddressDetail;
    @BindView(R.id.Company)
    ClearEditText mCompany;
    @BindView(R.id.FaultRepairName)
    ClearEditText mFaultRepairName;
    @BindView(R.id.ContactWay)
    ClearEditText mContactWay;
    @BindView(R.id.commit)
    TextView mCommit;
    @BindView(R.id.type)
    TextView mType;

    private double lat;
    private double lon;

    BottomDialog dialog;
    List<String> list2;
    List<Integer> list3;

    List<TypeBean.ObjectBean.RepairTypeListBean> list = new ArrayList<>();
    int id = 0;
    private TypeSelectPresenter mTypeSelectPresenter = new TypeSelectPresenter(this);
    private RepairPresenter mRepairPresenter = new RepairPresenter(this);

    @Override

    protected void initView() {
        mTitle.setText("故障报修");

    }


    @Override
    protected int getLayout() {
        return R.layout.ac_faultrepair;
    }

    @Override
    protected void initEventAndData() {
        initData();


    }

    private void initData() {
        mTypeSelectPresenter.initData(mContext, mMyOkhttp, list);
    }


    @OnClick({R.id.contact_Customer_service, R.id.rl_back, R.id.TypeSelect, R.id.commit, R.id.Area})
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);

                break;


            case R.id.rl_back:
                finish();
                break;
            case R.id.TypeSelect:

                list2 = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    list2.add(list.get(i).getRepairTypeName());
                }

                final MyPopupWindow popupWindow = new MyPopupWindow(mContext, list2);
                popupWindow.setItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long ids) {
                        popupWindow.dismiss();
                        mType.setText(list2.get(position));
                        id = position + 1;
                        LogUtils.d(id);
                    }
                });

                popupWindow.show(view);

                break;
            case R.id.commit:
                if(mContext.isDestroyed()){
                    return;
                }
                mRepairPresenter.commit(mContext, mMyOkhttp);
                break;
            case R.id.Area:

//                LocationUtils utils=new LocationUtils(mContext);
//                if (utils.isGpsEnabled()){
//                    initBaiduMap();
//                    LogUtils.d(lat);
//                    Toast.makeText(mContext, lat+"", Toast.LENGTH_SHORT).show();
//                }else{
//                    utils.openGpsSettings();
//                }
                if (dialog != null) {
                    dialog.show();
                } else {
                    dialog = new BottomDialog(this);
                    dialog.setOnAddressSelectedListener(this);
                    dialog.setDialogDismisListener(this);
                    dialog.show();
                }

                break;
        }
    }


    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        String address =
                (province == null ? "" : province.name) +
                        (city == null ? "" : city.name) +
                        (county == null ? "" : county.name) +
                        (street == null ? "" : street.name);

        mArea.setText(address);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public String getType() {
        return mType.getText().toString();
    }

    @Override
    public String getUserId() {
        return mSPUtils.getString("userid");
    }

    @Override
    public String getUserName() {
        return mFaultRepairName.getText().toString();
    }

    @Override
    public String getPhone() {
        return mContactWay.getText().toString();
    }

    @Override
    public String getRepairId() {
        return id + "";
    }

    @Override
    public String getLineName() {
        return mLineName.getText().toString();
    }

    @Override
    public String getArea() {
        return mArea.getText().toString();
    }

    @Override
    public String getDetailAddress() {
        return mAddressDetail.getText().toString();
    }

    @Override
    public String getCompany() {
        return mCompany.getText().toString();
    }

    @Override
    public String getStatue() {
        return "0";
    }

    @Override
    public String getRemark() {
        return mRemarks.getText().toString();
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        new AlertDialog(mContext).builder().setTitle("提示")
                .setMsg("恭喜，报修成功！我们将尽快回复您")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();
                    }
                }).show();
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onError() {

    }
}
