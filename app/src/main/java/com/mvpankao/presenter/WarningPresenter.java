package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnWarningListener;
import com.mvpankao.model.WarningModel;
import com.mvpankao.model.bean.WarningBean;
import com.mvpankao.model.bean.WarningDetailBean;
import com.mvpankao.model.impl.WarningModelImpl;
import com.mvpankao.view.WarningView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class WarningPresenter implements OnWarningListener {
    private WarningModel mModel;
    private WarningView mView;

    public WarningPresenter(WarningView mView) {
        this.mView = mView;
        mModel = new WarningModelImpl();
    }


    public void initData(Context context, MyOkHttp myOkHttp, List<WarningBean.ObjectBean.ListBean> list) {
        int page = mView.getPage();
        if (NetworkUtils.isConnected(context)) {
            mModel.initData(page, this, context, myOkHttp, list);
        } else {
            mView.showToast("网络异常");
        }
    }
    public void initSearchData(Context context, MyOkHttp myOkHttp, List<WarningBean.ObjectBean.ListBean> list,Map<String,String> map) {
        int page = mView.getPage();
        if (NetworkUtils.isConnected(context)) {
            mModel.initSearchData(page, this, context, myOkHttp, list,map);
        } else {
            mView.showToast("网络异常");
        }
    }

    /**
     * 警报详情
     *
     * @param context
     * @param myOkHttp
     */
    public void initWarningDetailData(Context context, MyOkHttp myOkHttp, List<WarningDetailBean.ObjectBean.TimePointBean> list) {
        String Id = mView.getWarningId();
        if (NetworkUtils.isConnected(context)) {
            mModel.initDetailData(this, context, myOkHttp,list, Id);
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
    public void onSuccess(WarningDetailBean response) {
        mView.onSuccess(response);
    }


    @Override
    public void onFailure() {
        mView.onFailure();
    }

    @Override
    public void getItemSize(int size) {
        mView.getItemSize(size);
    }
}
