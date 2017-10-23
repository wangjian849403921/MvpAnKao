package com.mvpankao.model.impl;


import android.content.Context;
import android.util.ArrayMap;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.AddressModel;
import com.mvpankao.model.OnAddressListener;
import com.mvpankao.model.bean.Address;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 地址Model实现，这里主要是网络请求的操作。
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 10:42
 */
public class AddressModelImpl implements AddressModel  {


    @Override
    public void initData(final OnAddressListener OnAddressListener, Context context, MyOkHttp myOkHttp, final List<Address.ObjectBean> list, String id) {
        String url = NetUrl.URLHeader + NetUrl.Address.AddressList;
//        Map<String, String> map = new HashMap<>();
        ArrayMap<String,String> map=new ArrayMap<>();
        map.put("userId", id);

        myOkHttp.post().url(url).params(map).tag(context)
                .enqueue(new GsonResponseHandler<Address>() {
                             @Override
                             public void onFailure(int statusCode, String error_msg) {
                                 OnAddressListener.onFailure();
                             }

                             @Override
                             public void onSuccess(int statusCode, Address response) {

                                 if (response.getCode() == 200) {

                                     if (response.getObject() != null) {
                                         for (int i = 0; i < response.getObject().size(); i++) {
                                             list.add(response.getObject().get(i));
                                         }
                                         OnAddressListener.onSuccess();
                                     } else {
                                         OnAddressListener.onError();
                                     }

                                 } else {
                                     OnAddressListener.onError();
                                 }
                             }
                         }

                );
    }

    /**
     * 编辑新建地址
     * @param OnAddressListener
     * @param context
     * @param myOkHttp
     * @param userId
     * @param addressId
     * @param receiver
     * @param phone
     * @param provice
     * @param city
     * @param area
     * @param detailaddress
     * @param isdefault
     */
    @Override
    public void editAddress(final OnAddressListener OnAddressListener, Context context, MyOkHttp myOkHttp, String userId, String addressId, String receiver, String phone, String provice, String city, String area, String detailaddress, String isdefault) {
        String url = NetUrl.URLHeader + NetUrl.Address.AddressEdit;
        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isEmpty(addressId)){
            map.put("id", addressId);
        }
        map.put("userId",userId);
        map.put("receiveName",receiver);
        map.put("receivePhone",phone);
        map.put("province",provice);
        map.put("citys", city);
        map.put("areas", area);
        map.put("receiveAddress", detailaddress);
        map.put("isdefault",isdefault);

        myOkHttp.post().url(url).params(map).tag(context).enqueue( new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                LogUtils.i(response.toString());
                try {
                    if (response.getString("code").equals("200")) {
                        OnAddressListener.onSuccess();
                    } else {
                        OnAddressListener.onError();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                OnAddressListener.onFailure();
            }
        });
    }
}
