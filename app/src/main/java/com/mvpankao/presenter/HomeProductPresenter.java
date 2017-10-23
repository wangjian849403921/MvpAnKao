package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.HomeProductModel;
import com.mvpankao.model.OnHomeProductListener;
import com.mvpankao.model.bean.HomePageProduct;
import com.mvpankao.model.impl.HomeProductImpl;
import com.mvpankao.view.HomeProductView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/8 11:25
 */

public class HomeProductPresenter implements OnHomeProductListener {
    private HomeProductModel Model;
    private HomeProductView View;

    public HomeProductPresenter(HomeProductView View) {
        this.View = View;
        Model = new HomeProductImpl();
    }


    public void initData(Context context, MyOkHttp myOkHttp, List<HomePageProduct.ObjectBean> list,String type) {
        if (NetworkUtils.isConnected(context)) {
            Model.initData(this, context, myOkHttp, list,type);
        } else {
            View.showToast("网络异常");
        }


    }

    @Override
    public void onError() {
        View.onError();
    }

    @Override
    public void onSuccess() {
        View.onSuccess();
    }

    @Override
    public void onFailure() {
        View.onFailure();
    }
}
