package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnQueryListener;
import com.mvpankao.model.QueryResultModel;
import com.mvpankao.model.bean.ReportBean;
import com.mvpankao.model.bean.TechnologyBean;
import com.mvpankao.model.impl.QueryResultModelImpl;
import com.mvpankao.view.QueryResultView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class QueryResultPresenter implements OnQueryListener {
    private QueryResultModel mModel;
    private QueryResultView mView;

    public QueryResultPresenter(QueryResultView mView) {
        this.mView = mView;
        mModel = new QueryResultModelImpl();
    }


    public void initReportData(Context context, MyOkHttp myOkHttp, List<ReportBean.ObjectBean.ListBean> list) {
        int page = mView.getPage();
        String userId=mView.getUserId();
        String role=mView.getRole();
        String query=mView.getQueryContent();
            if(NetworkUtils.isConnected(context)){
                mModel.initReport(page, this,context,myOkHttp,list,userId,role,query);
            }else{
                mView.showToast("网络异常");
            }
    }
    public void initTechnologyData(Context context, MyOkHttp myOkHttp, List<TechnologyBean.ObjectBean.ListBean> list) {
        int page = mView.getPage();
        String userId=mView.getUserId();
        String role=mView.getRole();
        String query=mView.getQueryContent();
        if(NetworkUtils.isConnected(context)){
            mModel.initTechnology(page, this,context,myOkHttp,list,userId,role,query);
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
