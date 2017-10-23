package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnWorkFlowListener;
import com.mvpankao.model.WorkFlowModel;
import com.mvpankao.model.bean.WorkFlowBean;
import com.mvpankao.model.impl.WorkFlowModelImpl;
import com.mvpankao.view.WorkFlowView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class WorkFlowPresenter implements OnWorkFlowListener {
    private WorkFlowModel Model;
    private WorkFlowView View;

    public WorkFlowPresenter(WorkFlowView View) {
        this.View = View;
        Model = new WorkFlowModelImpl();
    }


    public void initData(Context context, MyOkHttp myOkHttp, List<WorkFlowBean.ObjectBean.ListBean> list) {
        int page = View.getPage();
        String userId=View.getUserId();
        String role=View.getRole();
        String statue=View.getStatue();
            if(NetworkUtils.isConnected(context)){
                Model.initData(page, this,context,myOkHttp,list,userId,role,statue);
            }else{
                View.showToast("网络异常");
            }
    }

    @Override
    public void onError() {
        View.onError();

    }



    @Override
    public void onSuccess() {
        View.onSuccess();

    }



    @Override
    public void onFailure() {

        View.onFailure();
    }

    @Override
    public void getItemSize(int size) {
        View.getItemSize(size);
    }
}
