package com.mvpankao.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.presenter.AddressPresenter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.ContactsUtil;
import com.mvpankao.utils.DialogUtils;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.EditAddressView;
import com.mvpankao.widget.ClearEditText;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Street;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * 新建地址
 * Created by wangjian
 * On  2016/11/28
 */

public class NewAddressActivity extends BaseActivity implements OnAddressSelectedListener, AddressSelector.OnDialogCloseListener, EditAddressView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mScheduleAddsure;
    @BindView(R.id.et_name)
    ClearEditText mEtName;
    @BindView(R.id.et_phone)
    ClearEditText mEtPhone;
    @BindView(R.id.ll_choose_contract)
    LinearLayout mLlChooseContract;
    @BindView(R.id.text_address)
    TextView mTextAddress;
    @BindView(R.id.Area)
    TextView mArea;
    @BindView(R.id.rl_choose_address)
    RelativeLayout mRlChooseAddress;
    @BindView(R.id.address_detail)
    ClearEditText mAddressDetail;
    @BindView(R.id.ll_select)
    LinearLayout mLlSelect;
    @BindView(R.id.ll_save_address)
    LinearLayout mLlSaveAddress;

    BottomDialog dialog;
    @BindView(R.id.text_save)
    TextView mTextSave;
    @BindView(R.id.switchbtn)
    Switch mSwitchBtn;
    String Province, City, Area;
    int isDefault = 0;
    private AddressPresenter AddressPresenter = new AddressPresenter(this);

    @Override
    protected void initView() {
        mTitle.setText("新建收货地址");

        mScheduleAddsure.setVisibility(View.INVISIBLE);
        if (getIntent().getStringExtra("size").equals("0")) {
            mSwitchBtn.setChecked(true);
            if (mSwitchBtn.isChecked()) {
                mSwitchBtn.setClickable(false);
            } else {
                mSwitchBtn.setEnabled(true);
            }
        }
        mEtName.addTextChangedListener(new textChange());

        mEtPhone.addTextChangedListener(new textChange());

        mAddressDetail.addTextChangedListener(new textChange());


    }

    @Override
    protected int getLayout() {
        return R.layout.ac_newaddress;
    }

    @Override
    protected void initEventAndData() {


    }

    @Override
    public String getUserId() {
        return mSPUtils.getString("userid");
    }

    @Override
    public String getAddressId() {
        return "";
    }

    @Override
    public String getReceiver() {
        return mEtName.getText().toString();
    }

    @Override
    public String getPhone() {
        return mEtPhone.getText().toString();
    }

    @Override
    public String getProvince() {
        return Province;
    }

    @Override
    public String getCity() {
        return City;
    }

    @Override
    public String getArea() {
        return Area;
    }

    @Override
    public String getDetailAddress() {
        return mAddressDetail.getText().toString();
    }

    @Override
    public String getIsDefault() {
        return isDefault + "";
    }

    @Override
    public void showDialog() {
        DialogUtils.Progress(mContext, "   提交中...");

    }

    @Override
    public void dismissDialog() {
        DialogUtils.dismiss();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        EventBus.getDefault().post(new MyEvent(Constants.ADDRESSEDIT_CODE));

        finish();
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onError() {

    }


    class textChange implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            boolean Sign1 = mEtName.getText().length() > 0;

            boolean Sign2 = mEtPhone.getText().length() > 0;

            boolean Sign3 = mAddressDetail.getText().length() > 0;

            boolean Sign4 = mArea.getText().length() > 0;

            if (Sign1 && Sign2 && Sign3 && Sign4) {

                mLlSaveAddress.setBackgroundResource(R.drawable.btn);

                mLlSaveAddress.setClickable(true);
                mTextSave.setTextColor(Color.parseColor("#ffffff"));

            } else {

                mLlSaveAddress.setBackgroundResource(R.drawable.btn_unclickeable);
                mTextSave.setTextColor(Color.parseColor("#c7c7c7"));
                mLlSaveAddress.setClickable(false);

            }

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {

        }

        @Override
        public void afterTextChanged(Editable arg0) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data == null) {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri Uri = data.getData();
//                String[] contacts = getPhoneContacts(Uri);
                String[] contacts = ContactsUtil.getPhoneContacts(Uri, mContext);
                mEtName.setText(contacts[0]);
                mEtPhone.setText(contacts[1]);

                break;
        }
    }

    /**
     * 导入手机联系人
     */
    @PermissionSuccess(requestCode = 300)
    public void selectContacts() {
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 0);
    }

    @OnClick({R.id.rl_back, R.id.ll_choose_contract, R.id.rl_choose_address, R.id.ll_save_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_choose_contract:
                PermissionGen.needPermission(mContext, 300, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE});

                break;
            case R.id.rl_choose_address:
                if (dialog != null) {
                    dialog.show();
                } else {
                    dialog = new BottomDialog(this);
                    dialog.setOnAddressSelectedListener(this);
                    dialog.setDialogDismisListener(this);
                    dialog.show();
                }
                break;
            case R.id.ll_save_address:
                if (mSwitchBtn.isChecked()) {
                    isDefault = 1;
                } else {
                    isDefault = 0;
                }
                if(mContext.isDestroyed()){
                    return;
                }
                AddressPresenter.editAddress(mContext, mMyOkhttp);
                break;
        }
    }

    @Override
    public void onAddressSelected(com.smarttop.library.bean.Province province, com.smarttop.library.bean.City city, County county, Street street) {

        Province = (province == null ? "" : province.name);
        City = (city == null ? "" : city.name);
        Area = (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
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
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
