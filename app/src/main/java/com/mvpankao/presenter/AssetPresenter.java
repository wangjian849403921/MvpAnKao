package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.AssetModel;
import com.mvpankao.model.OnAssetListener;
import com.mvpankao.model.bean.AssetBean;
import com.mvpankao.model.bean.ParamGroupItem;
import com.mvpankao.model.impl.AssetModelImpl;
import com.mvpankao.view.AssetParamView;
import com.mvpankao.view.AssetView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class AssetPresenter implements OnAssetListener {
    private AssetModel mModel;
    private AssetView mView;
    private AssetParamView mAssetParamView;

    public AssetPresenter(AssetView mView) {
        this.mView = mView;
        mModel = new AssetModelImpl();
    }

    public AssetPresenter(AssetParamView assetParamView) {
        mAssetParamView = assetParamView;
        mModel = new AssetModelImpl();
    }

    public void initData(Context context, MyOkHttp myOkHttp, List<AssetBean.ObjectBean> list) {
        String userId = mView.getUserId();
            if(NetworkUtils.isConnected(context)){
                mModel.initData(this,context,myOkHttp,list,userId);
            }else{
                mView.showToast("网络异常");
            }


    }
    public void initParamData(Context context, MyOkHttp myOkHttp, List<ParamGroupItem> list) {
        String Id = mAssetParamView.getAssetId();
        String level = mAssetParamView.getAssetLevel();

        if(NetworkUtils.isConnected(context)){
            mModel.initAssetParam(this,context,myOkHttp,list,Id,level);
        }else{
            mAssetParamView.showToast("网络异常");
        }


    }
    @Override
    public void onError() {
        if (mView!=null){
            mView.onError();
        }
        if (mAssetParamView!=null){
            mAssetParamView.onParamError();
        }
    }



    @Override
    public void onSuccess() {

        if (mView!=null){
            mView.onSuccess();
        }
        if (mAssetParamView!=null){
            mAssetParamView.onParamSuccess();
        }
    }



    @Override
    public void onFailure() {
        if (mView!=null){
            mView.onFailure();
        }
        if (mAssetParamView!=null){
            mAssetParamView.onParamFailure();
        }
    }

}
