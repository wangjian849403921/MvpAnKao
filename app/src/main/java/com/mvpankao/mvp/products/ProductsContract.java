package com.mvpankao.mvp.products;

import android.content.Context;

import com.mvpankao.mvp.base.BasePresenter;
import com.mvpankao.mvp.base.BaseView;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ProductsContract {
    interface View extends BaseView {
        void showToast(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        //产品列表
        void initData(Context context, MyOkHttp myOkHttp,int page, String type, String typeid);

        //产品详情
        void initData(Context context, MyOkHttp myOkHttp, String id);

        void initSelectData(Context context, MyOkHttp myOkHttp);

        void initAdapterData(Context context, MyOkHttp myOkHttp,String productId,String addressid,String address, String reciver,String phone);

    }
}
