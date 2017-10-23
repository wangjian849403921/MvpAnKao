package com.mvpankao.mvp.products;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.dialog.MyActionSheetDialog;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.ui.activity.AddressActivity;
import com.mvpankao.ui.activity.LoginActivity;
import com.tsy.sdk.myokhttp.MyOkHttp;

import org.json.JSONArray;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by wangjian
 * On  2017/1/19
 */

public class ProductsAdapter extends SimpleAdapter<ProductsBean.ObjectBean.ListBean> implements ProductsContract.View {
    Context mContext;
    JSONArray obj = null;
    SPUtils mSPUtils;
    ProductsPresenter mPresenters;
    MyOkHttp mMyOkhttp;
    String addressid="";
    String address="";
    String reciver="";
    String phone="";
    ProductsBean.ObjectBean.ListBean mProducts;
    public ProductsAdapter(Context context, List<ProductsBean.ObjectBean.ListBean> mDatas, ProductsPresenter mPresenter) {
        super(context, mDatas, R.layout.products_item);

        mContext = context;
        mPresenters=mPresenter;
        mSPUtils = new SPUtils(mContext, "USER_INFO");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        mMyOkhttp = new MyOkHttp(okHttpClient);
    }

    @Override
    public void bindData(BaseViewHolder viewHolder, final ProductsBean.ObjectBean.ListBean mProductOrder, int position) {
        mProducts=mProductOrder;
        if (!StringUtils.isEmpty(mProductOrder.getProductName())) {
            viewHolder.getTextView(R.id.products_name).setText(mProductOrder.getProductName());
        }
        if (!StringUtils.isEmpty(mProductOrder.getProductInventory() + "")) {
            viewHolder.getTextView(R.id.products_count).setText(mProductOrder.getProductInventory() + "");
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.product1).error(R.drawable.product1);
        if (!StringUtils.isEmpty(mProductOrder.getImage())) {
            Glide.with(mContext).load(NetUrl.DOCHeader + mProductOrder.getImage()).apply(options).into(viewHolder.getImageView(R.id.products_image));
        }

        viewHolder.getTextView(R.id.order_rightaway).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSPUtils.getBoolean("hasLogin")) {

                    addressid = mSPUtils.getString("addressid");
                     reciver = mSPUtils.getString("reciver");
                     phone = mSPUtils.getString("phone");

                    String address = mSPUtils.getString("defaultaddress");
                    if (!StringUtils.isEmpty(address)) {
                        if (mProductOrder.getProductInventory()!=0) {
                            mPresenters.initAdapterData(mContext, mMyOkhttp,mProductOrder.getProductId(),addressid,address,reciver,phone );
                        }else{
                            LogUtils.d(mProductOrder.getProductInventory());
                            Toast.makeText(mContext, "库存不足", Toast.LENGTH_SHORT).show();
                        }
//                        initData(mProductOrder.getProductId(), mProductOrder, addressid, address, reciver, phone);
                    } else {
                        new AlertDialog(mContext).builder().setTitle("提示")
                                .setMsg("没有地址，是否去新建？")
                                .setPositiveButtonColor("#FD4A2E")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mSPUtils.putString("state", "1");
                                        Intent intent = new Intent(mContext, AddressActivity.class);
                                        mContext.startActivity(intent);

                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                    }


                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("您尚未登录不能进行相关操作")
                            .setPositiveButtonColor("#FD4A2E")
                            .setPositiveButton("去登录", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mContext.startActivity(new Intent(mContext, LoginActivity.class));

                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }

            }


        });

    }




    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(Object reponses) {
        obj=(JSONArray)reponses;
        new MyActionSheetDialog(mContext, mProducts, false, false, obj, addressid, address, reciver, phone)
                .builder()
                .setCanceledOnTouchOutside(false)
                .show();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onRequestFailed() {

    }



}
