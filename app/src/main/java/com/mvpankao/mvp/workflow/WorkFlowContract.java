package com.mvpankao.mvp.workflow;

import android.content.Context;

import com.mvpankao.mvp.base.BasePresenter;
import com.mvpankao.mvp.base.BaseView;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class WorkFlowContract {
    interface View extends BaseView {
        void showToast(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void initData(Context context, MyOkHttp myOkHttp,int page,String userId,String role,String statue);
        void initData(Context context, MyOkHttp myOkHttp,String id);
    }
}
