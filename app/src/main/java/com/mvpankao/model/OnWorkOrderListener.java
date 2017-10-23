package com.mvpankao.model;

import com.mvpankao.model.bean.WorkOrderDeatil;

import org.json.JSONArray;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/13 17:00
 */
public interface OnWorkOrderListener {


    void onSuccess(WorkOrderDeatil response);
    void onWorkOrderSuccess();
    void onWarningSuccess();

    void onFailure();

    void onError();
    void setJsonArray(JSONArray JSONArray);
    void onEditWorkOrderSuccess();

    void onEditFailure();

    void onEditError();
}
