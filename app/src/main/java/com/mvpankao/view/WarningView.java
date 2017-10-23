package com.mvpankao.view;

import com.mvpankao.model.bean.WarningDetailBean;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/14 10:10
 */
public interface WarningView {

    String getWarningId();
    //获取页码
    int getPage();

    void getItemSize(int size);

    void showToast(String msg);

    void onSuccess(WarningDetailBean response);
    void onSuccess();
    void onFailure();

    void onError();

}
