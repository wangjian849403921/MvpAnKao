package com.mvpankao.model;

import android.content.Context;

import com.mvpankao.model.bean.Address;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：地址接口
 *
 * @auth wangjian
 * @time 2017/3/8 13:51
 */

public interface AddressModel {
    //获取地址列表
    void initData(OnAddressListener OnAddressListener, Context context, MyOkHttp myOkHttp, List<Address.ObjectBean> list,String id);
   //编辑地址
    void editAddress(OnAddressListener OnAddressListener, Context context, MyOkHttp myOkHttp,String userId,String addressId,String receiver,String phone,String provice,String city,String area,String detailaddress,String isdefault);

}
