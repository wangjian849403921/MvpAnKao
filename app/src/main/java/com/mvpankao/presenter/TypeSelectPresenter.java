package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnTyeSelectListener;
import com.mvpankao.model.TypeSelectModel;
import com.mvpankao.model.bean.TypeBean;
import com.mvpankao.model.impl.TypeSelectModelImpl;
import com.mvpankao.view.TypeView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class TypeSelectPresenter implements OnTyeSelectListener {
    private TypeSelectModel mModel;
    private TypeView mView;

    public TypeSelectPresenter(TypeView mView) {
        this.mView = mView;
        mModel = new TypeSelectModelImpl();
    }


    public void initData(Context context, MyOkHttp myOkHttp, List<TypeBean.ObjectBean.RepairTypeListBean> list) {

            if(NetworkUtils.isConnected(context)){
                mModel.initTypeList(this,context,myOkHttp,list);
            }else{
                mView.showToast("网络异常");
            }


    }

    @Override
    public void onError() {
        mView.onError();

    }



    @Override
    public void onSuccess() {
        mView.onSuccess();

    }



    @Override
    public void onFailure() {
        mView.onFailure();
    }


}
