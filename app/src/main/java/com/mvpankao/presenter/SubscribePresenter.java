package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnSubscribeListener;
import com.mvpankao.model.SubscribeModel;
import com.mvpankao.model.bean.SubscribeGroupItem;
import com.mvpankao.model.impl.SubscribeImpl;
import com.mvpankao.view.SubscribeView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/13 10:20
 */

public class SubscribePresenter implements OnSubscribeListener {
    private SubscribeModel mModel;
    private SubscribeView mView;

    public SubscribePresenter(SubscribeView mView) {
        this.mView = mView;
        mModel = new SubscribeImpl();
    }


    public void initData(Context context, MyOkHttp myOkHttp, List<SubscribeGroupItem> list) {
        String userId = mView.getUserId();
        String date=mView.getDate();
        if (NetworkUtils.isConnected(context)) {
            mModel.initData(this, context, myOkHttp, list,userId,date);
        } else {
            mView.showToast("网络异常");
        }


    }

    @Override
    public void onError() {
        mView.onError();

    }


    @Override
    public void onSuccess() {
        mView.onSuccess();

    }


    @Override
    public void onFailure() {
        mView.onFailure();
    }

}
