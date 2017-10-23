package com.mvpankao.view;

import org.json.JSONArray;

/**
 * description：产品筛选view
 *
 * @auth wangjian
 * @time 2017/3/9 16:39
 */
public interface ProductSelectView {

    void setJsonArray(JSONArray jsonArray);

    void showToast(String msg);

    //成功
    void onSuccess();

    //失败
    void onFailure();

    //错误
    void onError();
}
