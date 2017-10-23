package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnStepListListener;
import com.mvpankao.model.StepListModel;
import com.mvpankao.model.bean.StepBean;
import com.mvpankao.model.impl.StepListModelImpl;
import com.mvpankao.view.StepListView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class StepListPresenter implements OnStepListListener {
    private StepListModel newsModel;
    private StepListView newsView;

    public StepListPresenter(StepListView newsView) {
        this.newsView = newsView;
        newsModel = new StepListModelImpl();
    }


    public void initData(Context context, MyOkHttp myOkHttp, List<StepBean.ObjectBean> list) {
            if(NetworkUtils.isConnected(context)){
                String id=newsView.getStepId();
                newsModel.initStepData(this,context,myOkHttp,list,id);
            }else{
                newsView.showToast("网络异常");
            }


    }

    @Override
    public void onError() {
        newsView.onError();

    }



    @Override
    public void onSuccess() {
       newsView.onSuccess();

    }



    @Override
    public void onFailure() {
        newsView.onFailure();
    }

}
