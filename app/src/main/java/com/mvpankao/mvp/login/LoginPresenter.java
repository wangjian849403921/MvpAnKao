package com.mvpankao.mvp.login;

import android.content.Context;

import com.blankj.utilcode.utils.EncryptUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.UserBeans;
import com.mvpankao.mvp.base.BasePresenterImpl;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{
    @Override
    public void login(String name,String password,final Context context, MyOkHttp myOkHttp) {
        if (StringUtils.isEmpty(name)||StringUtils.isEmpty(password)){
            mView.showToast("用户名或密码不能为空");
        }else{
            if(NetworkUtils.isConnected(context)){
                mView.showLogin();
                String url = NetUrl.URLHeader + NetUrl.Account.Login;
                Map<String, String> map = new HashMap<>();
                map.put("password", EncryptUtils.encryptMD5ToString(password).toLowerCase());
                map.put("username", name);
                myOkHttp.post().params(map).url(url).tag(this).enqueue(new GsonResponseHandler<UserBeans>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                       mView.onRequestFailed();
                    }

                    @Override
                    public void onSuccess(int statusCode, UserBeans response) {
                        if (response.getCode() == 200) {
                            mView.onSuccess(response);
                        } else {
                            mView.onFailure();
                        }
                    }
                });
            }else{
                mView.showToast("网络异常");
            }
        }
    }
}
