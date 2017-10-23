package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.mvpankao.model.AddressModel;
import com.mvpankao.model.OnAddressListener;
import com.mvpankao.model.bean.Address;
import com.mvpankao.model.impl.AddressModelImpl;
import com.mvpankao.view.AddressView;
import com.mvpankao.view.EditAddressView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class AddressPresenter implements OnAddressListener {
    private AddressModel addressModel;
    private AddressView addressView;
    private EditAddressView editView;

    public AddressPresenter(AddressView addressView) {
        this.addressView = addressView;
        addressModel = new AddressModelImpl();
    }

    public AddressPresenter(EditAddressView editView) {
        this.editView = editView;
        addressModel = new AddressModelImpl();
    }

    public void initData(Context context, MyOkHttp myOkHttp, List<Address.ObjectBean> list, String id) {

        if (NetworkUtils.isConnected(context)) {
            addressModel.initData(this, context, myOkHttp, list, id);
        } else {
            addressView.showToast("网络异常");
        }


    }

    public void editAddress(Context context, MyOkHttp myOkHttp) {
        String userid = editView.getUserId();
        String addressid = editView.getAddressId();
        String receiver = editView.getReceiver();
        String phone = editView.getPhone();
        String province = editView.getProvince();
        String city = editView.getCity();
        String area = editView.getArea();
        String detailaddress = editView.getDetailAddress();
        String isdefault = editView.getIsDefault();


        if (NetworkUtils.isConnected(context)) {
            if (RegexUtils.isMobileExact(phone)) {
                editView.showDialog();

                addressModel.editAddress(this, context, myOkHttp, userid, addressid, receiver, phone, province, city, area, detailaddress, isdefault);

            } else {
                editView.showToast("请输入正确的手机号");
            }

        }
    else
    {
        editView.showToast("网络异常");
    }


}

    @Override
    public void onError() {
        if (addressView != null) {
            addressView.onError();
        }
        if (editView != null) {
            editView.onError();
            editView.dismissDialog();
        }
    }


    @Override
    public void onSuccess() {
        if (addressView != null) {
            addressView.onSuccess();
        }
        if (editView != null) {
            editView.onSuccess();
            editView.dismissDialog();
            editView.showToast("提交失败");
        }
    }


    @Override
    public void onFailure() {
        if (addressView != null) {
            addressView.onFailure();
        }
        if (editView != null) {
            editView.onFailure();
            editView.dismissDialog();
        }

    }


}
