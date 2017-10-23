package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnOrderListener;
import com.mvpankao.model.OrderModel;
import com.mvpankao.model.bean.MyOrder;
import com.mvpankao.model.impl.OrderModelImpl;
import com.mvpankao.view.OrderView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class OrderPresenter implements OnOrderListener {
    private OrderModel Model;
    private OrderView View;

    public OrderPresenter(OrderView View) {
        this.View = View;
        Model = new OrderModelImpl();
    }


    public void initData(Context context, MyOkHttp myOkHttp, List<MyOrder.ObjectBean.ListBean> list) {
        int page = View.getPage();
        String userid = View.getUserId();
        String statue = View.getStatue();
        if (NetworkUtils.isConnected(context)) {
            Model.initOrder(page, this, context, myOkHttp, list, userid, statue);
        } else {
            View.showToast("网络异常");
        }
    }
    public void deleteOrder(Context context, MyOkHttp myOkHttp,String orderid,int position) {

        if (NetworkUtils.isConnected(context)) {
            Model.deleteOrder(this, context, myOkHttp,orderid, position);
        } else {
            View.showToast("网络异常");
        }
    }
    @Override
    public void onError() {
        View.onError();

    }

    @Override
    public void ondeleteError() {
        View.ondeleteError();
    }


    @Override
    public void onSuccess() {
        View.onSuccess();

    }

    @Override
    public void ondeleteSuccess(int position) {
        View.ondeleteSuccess(position);
    }


    @Override
    public void onFailure() {
        View.onFailure();
    }

    @Override
    public void ondeleteFailure() {
        View.ondeleteFailure();
    }

    @Override
    public void getItemSize(int size) {
        View.getItemSize(size);
    }
}
