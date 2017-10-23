package com.mvpankao.ui.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.ProductsBean;

import java.util.List;

/**
 * 提交订单Adapter
 * Created by wangjian
 * On  2016/9/9
 */
public class CommitOrderAdapter extends SimpleAdapter<ProductsBean.ObjectBean.ListBean> {


    public CommitOrderAdapter(Context context, List<ProductsBean.ObjectBean.ListBean> mDatas) {
        super(context, mDatas, R.layout.commitorder_item);
    }

    @Override
    public void bindData(BaseViewHolder viewHolder, ProductsBean.ObjectBean.ListBean mProductOrder, int position) {


        viewHolder.getTextView(R.id.product_name).setText(mProductOrder.getProductName());
        viewHolder.getTextView(R.id.product_count).setText("X "+mProductOrder.getProductCount());
        viewHolder.getTextView(R.id.product_param).setText(mProductOrder.getProductParam());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.product1).error(R.drawable.product1);
        Glide.with(mContext).load(NetUrl.DOCHeader+mProductOrder.getImage()).apply(options).into(viewHolder.getImageView(R.id.product_image));
//        viewHolder.getImageView(R.id.product_image).setImageURI(Uri.parse(infomation.getInfoImg()));

    }
}
