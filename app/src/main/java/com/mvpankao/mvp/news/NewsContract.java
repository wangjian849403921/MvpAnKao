package com.mvpankao.mvp.news;

import android.content.Context;

import com.mvpankao.mvp.base.BasePresenter;
import com.mvpankao.mvp.base.BaseView;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class NewsContract {
    interface View extends BaseView {
        void showToast(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void initData(Context context, MyOkHttp myOkHttp,int page);
    }
}
