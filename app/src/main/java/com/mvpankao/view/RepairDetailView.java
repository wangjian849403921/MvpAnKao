package com.mvpankao.view;

import com.mvpankao.model.bean.RepairDetailBean;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/10 17:36
 */
public interface RepairDetailView {
    String getRepairId();

    void showToast(String msg);
    void onFailure();

    void onError();
    void onSuccess(RepairDetailBean repairDetail);
}
