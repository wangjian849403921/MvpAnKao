package com.mvpankao.view;

/**
 * description：反馈View
 *
 * @auth wangjian
 * @time 2017/3/9 16:15
 */
public interface FeedBackView {
    void showDialog();
    String getUserName();
    String getPhone();
    String getfeedbackTitle();
    String getContent();
    void showToast(String msg);

    //成功
    void onSuccess();

    //失败
    void onFailure();

    //错误
    void onError();
}
