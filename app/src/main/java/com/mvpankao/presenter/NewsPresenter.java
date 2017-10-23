package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.NewsModel;
import com.mvpankao.model.OnNewsListener;
import com.mvpankao.model.bean.NewsBean;
import com.mvpankao.model.impl.NewsModelImpl;
import com.mvpankao.view.NewsView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class NewsPresenter implements OnNewsListener {
    private NewsModel mModel;
    private NewsView mView;

    public NewsPresenter(NewsView mView) {
        this.mView = mView;
        mModel = new NewsModelImpl();
    }


    public void initData(Context context, MyOkHttp myOkHttp, List<NewsBean.ObjectBean.ListBean> list) {
        int page = mView.getPage();
            if(NetworkUtils.isConnected(context)){
                mModel.initNews(page, this,context,myOkHttp,list);
            }else{
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

    @Override
    public void getItemSize(int size) {
        mView.getItemSize(size);
    }
}
