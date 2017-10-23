package com.mvpankao.view;

/**
 * description：工作流步骤列表View
 *
 * @auth wangjian
 * @time 2017/3/10 17:05
 */
public interface StepListView {
    String getStepId();
    void showToast(String msg);

    void onSuccess();

    void onFailure();

    void onError();
}
