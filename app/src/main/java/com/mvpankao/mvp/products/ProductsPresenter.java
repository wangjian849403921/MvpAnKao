package com.mvpankao.mvp.products;

import android.content.Context;

import com.blankj.utilcode.utils.LogUtils;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.ProductDetailBean;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.mvp.base.BasePresenterImpl;
import com.smarttop.library.utils.LogUtil;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ProductsPresenter extends BasePresenterImpl<ProductsContract.View> implements ProductsContract.Presenter{

    @Override
    public void initData(Context context, MyOkHttp myOkHttp, int page,String type, String typeid) {
        String url = NetUrl.URLHeader + NetUrl.Product.ProductList;
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("categoryId", type);
        map.put("categoryIdTwo", typeid);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<ProductsBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
                mView.onRequestFailed();
            }

            @Override
            public void onSuccess(int statusCode, ProductsBean response) {
                LogUtils.d(response.getCode());
                if (response.getCode() == 200) {

                    if (response.getObject() != null) {


                        mView.onSuccess(response);
                    } else {
                        mView.onFailure();

                    }
                } else {
                    mView.onFailure();
                }
            }
        });
    }

    @Override
    public void initData(Context context, MyOkHttp myOkHttp,String id) {
        String url = NetUrl.URLHeader + NetUrl.Product.ProductDetail;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        myOkHttp.post().url(url).params(map).tag(context).enqueue(new GsonResponseHandler<ProductDetailBean>() {
            @Override
            public void onFailure(int statusCode, String error_msg) {
               mView.onRequestFailed();
            }

            @Override
            public void onSuccess(int statusCode, ProductDetailBean response) {
                if (response.getCode() == 200) {

                    mView.onSuccess(response);

                } else {
                    mView.onFailure();
                }
            }
        });
    }

    @Override
    public void initSelectData(Context context, MyOkHttp mMyOkhttp) {
        String url = NetUrl.URLHeader + NetUrl.Product.TypeSelect;
        Map<String, String> map = new HashMap<>();

        map.put("", "");

        mMyOkhttp.post().params(map).url(url).tag(this).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {
                        LogUtil.d("", response.toString());

                        mView.onSuccess(response.getJSONArray("object"));

                    } else {
                        mView.onFailure();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mView.onRequestFailed();

            }
        });
    }

    @Override
    public void initAdapterData(Context context, MyOkHttp myOkHttp, String productId, String addressid, String address, String reciver, String phone) {

        String url = NetUrl.URLHeader + NetUrl.Product.TypeSelect2;
        Map<String, String> map = new HashMap<>();

        map.put("productId", productId);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient);
        mMyOkhttp.post().url(url).params(map).tag(this).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {

                        mView.onSuccess(response.getJSONArray("object"));
                    } else {
                        mView.onFailure();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                mView.onRequestFailed();
            }
        });
    }
}
