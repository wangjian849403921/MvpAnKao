package com.mvpankao.presenter;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.mvpankao.model.OnProductsListener;
import com.mvpankao.model.ProductsModel;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.model.impl.ProductsImpl;
import com.mvpankao.view.ProductAdapterView;
import com.mvpankao.view.ProductDetailView;
import com.mvpankao.view.ProductSelectView;
import com.mvpankao.view.ProductsView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import org.json.JSONArray;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/8 13:54
 */

public class ProductsPresenter implements OnProductsListener {
    private ProductsModel mModel;

    private ProductsView mView;
    private ProductDetailView mDetailView;
    private ProductSelectView mSelectView;
    private ProductAdapterView mAdapterView;

    public ProductsPresenter(ProductsView view) {
        this.mView = view;
        mModel = new ProductsImpl();

    }

    public ProductsPresenter(ProductDetailView view) {
        this.mDetailView = view;
        mModel = new ProductsImpl();

    }

    public ProductsPresenter(ProductSelectView view) {
        this.mSelectView = view;
        mModel = new ProductsImpl();

    }

    public ProductsPresenter(ProductAdapterView view) {
        this.mAdapterView = view;
        mModel = new ProductsImpl();

    }

    /**
     * 产品列表
     *
     * @param context
     * @param myOkHttp
     * @param list
     * @param type
     * @param typeid
     */
    public void initData(Context context, MyOkHttp myOkHttp, List<ProductsBean.ObjectBean.ListBean> list, String type, String typeid) {
        int page = mView.getPage();
        if (NetworkUtils.isConnected(context)) {
            mModel.initData(page, this, context, myOkHttp, list, type, typeid);
        } else {
            mView.showToast("网络异常");
        }


    }

    /**
     * 产品详情
     *
     * @param context
     * @param myOkHttp
     * @param product
     * @param listimage
     */
    public void initData(Context context, MyOkHttp myOkHttp, ProductsBean.ObjectBean.ListBean product, List<String> listimage) {
        String id = mDetailView.getProductsId();
        if (NetworkUtils.isConnected(context)) {
            mModel.initData(this, context, myOkHttp, product, id, listimage);
        } else {
            mDetailView.showToast("网络异常");
        }
    }

    /**
     * 筛选数据
     *
     * @param context
     * @param myOkHttp
     */
    public void initSelectData(Context context, MyOkHttp myOkHttp) {

        if (NetworkUtils.isConnected(context)) {
            mModel.initSelectData(this, context, myOkHttp);
        } else {
            mSelectView.showToast("网络异常");
        }
    }

    /**
     * Adapter数据
     *
     * @param context
     * @param myOkHttp
     */
    public void initAdapterData(Context context, MyOkHttp myOkHttp, ProductsBean.ObjectBean.ListBean mProductOrder) {
        String addressid = mAdapterView.getAddressId();
        String address = mAdapterView.getAddress();
        String reciver = mAdapterView.getReciver();
        String phone = mAdapterView.getPhone();

        if (NetworkUtils.isConnected(context)) {
            mModel.initAdapterData(this, context, myOkHttp, mProductOrder.getProductId(), mProductOrder,addressid,address,reciver,phone);
        } else {
            mAdapterView.showToast("网络异常");
        }
    }

    @Override
    public void onError() {
        if (mView != null) {
            mView.onError();
        }
        if (mDetailView != null) {
            mDetailView.onError();
        }
        if (mSelectView != null) {
            mSelectView.onError();
        }
        if (mAdapterView != null) {
            mAdapterView.onError();
        }
    }

    @Override
    public void onSuccess() {
        if (mView != null) {
            mView.onSuccess();
        }
        if (mDetailView != null) {
            mDetailView.onSuccess();
        }
        if (mSelectView != null) {
            mSelectView.onSuccess();
        }

    }

    @Override
    public void onSuccess(ProductsBean.ObjectBean.ListBean mProductOrder) {
        if (mAdapterView != null) {
            mAdapterView.onSuccess(mProductOrder);
        }
    }

    @Override
    public void onFailure() {
        if (mView != null) {
            mView.onFailure();
        }
        if (mDetailView != null) {
            mDetailView.onFailure();
        }
        if (mSelectView != null) {
            mSelectView.onFailure();
        }
    }

    @Override
    public void getItemSize(int size) {
        mView.getItemSize(size);
    }

    @Override
    public void setJsonArray(JSONArray JSONArray) {
        if (mSelectView != null) {
            mSelectView.setJsonArray(JSONArray);
        }
        if (mAdapterView != null) {
            mAdapterView.setJsonArray(JSONArray);
        }
    }
}
