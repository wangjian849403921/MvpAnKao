package com.mvpankao.view;

import com.mvpankao.model.bean.WorkOrderDeatil;

import org.json.JSONArray;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/13 16:52
 */
public interface WorkOrderView {
    void setJsonArray(JSONArray jsonArray);
    String getUserId();
    void showToast(String msg);

    void onWorkOrderSuccess();
    void onWarningSuccess();
    void onWorkOrderSuccess(WorkOrderDeatil response);
    void onFailure();

    void onError();

    void onEditWorkOrderSuccess();

    void onEditFailure();

    void onEditError();
}
