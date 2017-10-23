package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnUpLoadListener;
import com.mvpankao.model.UploadModel;
import com.mvpankao.model.impl.UpLoadModelImpl;
import com.mvpankao.view.UpLoadView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.io.File;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 15:48
 */

public class UpLoadPresenter implements OnUpLoadListener {

    UpLoadView mView;
    UploadModel mModel;

    public UpLoadPresenter(UpLoadView view) {
        this.mView = view;
        mModel = new UpLoadModelImpl();
    }

    public void upLoad(Context mContext, MyOkHttp mMyOkHttp,String type, Map<String, String> map, Map<String, File> filemap) {
        if(NetworkUtils.isConnected(mContext)){
            mView.showDialog();
            mModel.upLoad(this,mContext,mMyOkHttp,type,map,filemap);
        }else{
           mView.showToast("网络异常");
        }
    }

    @Override
    public void onSuccess() {
        mView.onSuccess();
        mView.dismissDialog();
    }

    @Override
    public void onFailure() {
        mView.onFailure();
    }

    @Override
    public void onError() {
        mView.onError();
    }
}
