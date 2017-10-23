package com.mvpankao.model.impl;


import android.content.Context;

import com.blankj.utilcode.utils.EncryptUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.LoginModel;
import com.mvpankao.model.OnLoginListener;
import com.mvpankao.model.bean.UserBeans;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import net.lemonsoft.lemonbubble.LemonBubble;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 登录Model实现，这里主要是网络请求的操作。
 * Author:wangjian
 * Date: 2017-03-07
 * Time: 10:42
 */
public class LoginModelImpl implements LoginModel {

    @Override
    public void login(String name, String password, final OnLoginListener onLoginListener, final Context context,MyOkHttp myOkHttp) {
        String url = NetUrl.URLHeader + NetUrl.Account.Login;
        Map<String, String> map = new HashMap<>();
        map.put("password", EncryptUtils.encryptMD5ToString(password).toLowerCase());
        map.put("username", name);
        myOkHttp.post().params(map).url(url).tag(this).enqueue(new GsonResponseHandler<UserBeans>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                LemonBubble.showError(context, "请求失败", 2000);

//                onLoginListener.onFailure();
            }

            @Override
            public void onSuccess(int statusCode, UserBeans response) {
                if (response.getCode() == 200) {
                    SPUtils mSPUtils = new SPUtils(context, "USER_INFO");

                    mSPUtils.putString("role", response.getObject().getUser().getRole());
                    mSPUtils.putString("username", response.getObject().getUser().getUsername());
                    mSPUtils.putString("userid", response.getObject().getUser().getId());
                    mSPUtils.putString("userimage", response.getObject().getUser().getIcon());
                    mSPUtils.putBoolean("hasLogin", true);
                    if (response.getObject().getReceiptAddress() != null) {
                        String reciver = response.getObject().getReceiptAddress().get(0).getReceiveName();
                        String phone = response.getObject().getReceiptAddress().get(0).getReceivePhone();
                        mSPUtils.putString("reciver", response.getObject().getReceiptAddress().get(0).getReceiveName());
                        mSPUtils.putString("phone", response.getObject().getReceiptAddress().get(0).getReceivePhone());

                        mSPUtils.putString("addressid", response.getObject().getReceiptAddress().get(0).getId());
                        mSPUtils.putString("defaultaddress", response.getObject().getReceiptAddress().get(0).getProvince() + response.getObject().getReceiptAddress().get(0).getCitys() + response.getObject().getReceiptAddress().get(0).getAreas() + response.getObject().getReceiptAddress().get(0).getReceiveAddress());
                    }

                    onLoginListener.onSuccess();

                    if (mSPUtils.contains("state")) {
                        if (mSPUtils.getString("state").equals("1")) {
                            onLoginListener.onUpdate();
                        }
                    }

                } else {
                    LemonBubble.showError(context, "用户名或密码错误", 2000);
//                    onLoginListener.onLoginError("用户名或密码错误");
                }
            }
        });

    }

}
