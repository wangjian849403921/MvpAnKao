package com.mvpankao.view;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/14 11:28
 */
public interface AssetParamView {
    String getAssetId();

    String getAssetLevel();

    void showToast(String msg);

    void onParamSuccess();

    void onParamFailure();

    void onParamError();

}
