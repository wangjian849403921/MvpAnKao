package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnWorkOrderListener;
import com.mvpankao.model.WorkOrderModel;
import com.mvpankao.model.bean.CreateOrderAssert;
import com.mvpankao.model.bean.ParentEntity;
import com.mvpankao.model.bean.WorkOrderDeatil;
import com.mvpankao.model.bean.WorkOrderGroupItem;
import com.mvpankao.model.bean.WorkOrderLogBean;
import com.mvpankao.model.impl.WorkOrderModelImpl;
import com.mvpankao.view.WorkOrderView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/13 10:20
 */

public class WorkOrderPresenter implements OnWorkOrderListener {
    private WorkOrderModel mModel;
    private WorkOrderView mView;


    public WorkOrderPresenter(WorkOrderView mView) {
        this.mView = mView;
        mModel = new WorkOrderModelImpl();
    }

    /**
     * 工单列表
     * @param context
     * @param myOkHttp
     * @param list
     */
    public void initData(Context context, MyOkHttp myOkHttp, List<WorkOrderGroupItem> list) {
        String userId = mView.getUserId();
        if (NetworkUtils.isConnected(context)) {
            mModel.initData(this, context, myOkHttp, list, userId);
        } else {
            mView.showToast("网络异常");
        }


    }

    /**
     * 筛选结果工单列表
     * @param context
     * @param myOkHttp
     * @param list
     */
    public void initSearchData(Context context, MyOkHttp myOkHttp, List<WorkOrderGroupItem> list,Map<String,String> map) {
        String userId = mView.getUserId();
        if (NetworkUtils.isConnected(context)) {
            mModel.initSearchData(this, context, myOkHttp, list, map);
        } else {
            mView.showToast("网络异常");
        }


    }
    /**
     * 工单类型
     * @param context
     * @param myOkHttp
     */
    public void initSelectData(Context context, MyOkHttp myOkHttp) {

        if (NetworkUtils.isConnected(context)) {
            mModel.initWorkOrderTypeList(this, context, myOkHttp);
        } else {
            mView.showToast("网络异常");
        }


    }
    /**
     * 警报类型
     * @param context
     * @param myOkHttp
     */
    public void initWarningSelectData(Context context, MyOkHttp myOkHttp) {

        if (NetworkUtils.isConnected(context)) {
            mModel.initWarningTypeList(this, context, myOkHttp);
        } else {
            mView.showToast("网络异常");
        }


    }
    /**
     * 工单详情
     * @param context
     * @param myOkHttp
     * @param userid
     */
    public void initWorkOrderDetailData(Context context, MyOkHttp myOkHttp, String userid) {

        if (NetworkUtils.isConnected(context)) {
            mModel.initDetailData(this, context, myOkHttp, userid);
        } else {
            mView.showToast("网络异常");
        }
    }

    /**
     * 工单操作
     * @param Context
     * @param MyOkHttp
     * @param url
     * @param map
     */
    public void editWorkOrder(Context Context, MyOkHttp MyOkHttp, String url, Map<String, String> map) {
        if (NetworkUtils.isConnected(Context)) {
            mModel.createWorkOrder(this, Context, MyOkHttp, url, map);
        } else {
            mView.showToast("网络异常");
        }
    }

    /**
     * 选择创建工单的客户
     * @param Context
     * @param MyOkHttp
     * @param list
     */
    public void initWorkOrderAssert(Context Context, MyOkHttp MyOkHttp, List<CreateOrderAssert.ObjectBean> list) {
        String userId=mView.getUserId();
        if (NetworkUtils.isConnected(Context)) {
            mModel.initWorkOrderAssert(this, Context, MyOkHttp, list, userId);
        } else {
            mView.showToast("网络异常");
        }
    }

    /**
     * 工单日志
     * @param Context
     * @param MyOkHttp
     * @param list
     */
    public void initWorkOrderLog(Context Context, MyOkHttp MyOkHttp, List<WorkOrderLogBean.ObjectBean> list) {
        String Id=mView.getUserId();
        if (NetworkUtils.isConnected(Context)) {
            mModel.initWorkOrderLog(this, Context, MyOkHttp, list, Id);
        } else {
            mView.showToast("网络异常");
        }
    }

    public void initAssertArea(Context Context, MyOkHttp MyOkHttp, ArrayList<ParentEntity> list) {
        String Id=mView.getUserId();
        if (NetworkUtils.isConnected(Context)) {
            mModel.initAssertArea(this, Context, MyOkHttp, list, Id);
        } else {
            mView.showToast("网络异常");
        }
    }
    @Override
    public void onError() {
        if (mView != null) {
            mView.onError();
        }

    }

    @Override
    public void setJsonArray(JSONArray JSONArray) {
        mView.setJsonArray(JSONArray);
    }

    @Override
    public void onEditWorkOrderSuccess() {
        mView.onEditWorkOrderSuccess();
    }

    @Override
    public void onEditFailure() {
        mView.onEditFailure();
    }

    @Override
    public void onEditError() {
        mView.onEditError();
    }


    @Override
    public void onSuccess(WorkOrderDeatil response) {
        mView.onWorkOrderSuccess(response);
    }

    @Override
    public void onWorkOrderSuccess() {
        mView.onWorkOrderSuccess();
    }

    @Override
    public void onWarningSuccess() {
        mView.onWarningSuccess();
    }



    @Override
    public void onFailure() {
        mView.onFailure();


    }

}
