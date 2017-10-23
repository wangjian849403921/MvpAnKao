package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.RegexUtils;
import com.mvpankao.model.CommitOrderModel;
import com.mvpankao.model.OnCommitListener;
import com.mvpankao.model.impl.CommitOrderModelImpl;
import com.mvpankao.view.CommitOrderView;
import com.tsy.sdk.myokhttp.MyOkHttp;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class CommitOrderPresenter implements OnCommitListener {
    private CommitOrderModel Model;
    private CommitOrderView View;

    public CommitOrderPresenter(CommitOrderView View) {
        this.View = View;
        Model = new CommitOrderModelImpl();
    }


    public void commitOrder(Context context, MyOkHttp myOkHttp,String userId,String stockId,String productId,String number,String receiver,String phone,String address,String require) {

            if(NetworkUtils.isConnected(context)){
                if (RegexUtils.isMobileExact(phone)) {
                    View.showDialog();
                    Model.commitOrder(this, context, myOkHttp, userId, stockId, productId, number, receiver, phone, address, require);
                }else{
                    View.showToast("请输入正确的手机号");
                }
            }else{
                View.showToast("网络异常");
            }
    }
    public void commitShopCarOrder(Context context, MyOkHttp myOkHttp,String userId,String stockid,String productId,String number,String shopcarid,String receiver,String phone,String address,String require) {

        if(NetworkUtils.isConnected(context)){
            if (RegexUtils.isMobileExact(phone)) {
                View.showDialog();
                Model.commitShopCarOrder(this, context, myOkHttp, userId, stockid, productId, number, shopcarid, receiver, phone, address, require);
            }else{
                View.showToast("请输入正确的手机号");
            }
        }else{
            View.showToast("网络异常");
        }
    }
    @Override
    public void onError() {
        View.onError();
        View.showToast("提交失败");
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
