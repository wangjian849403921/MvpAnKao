package com.mvpankao.ui.adapter;

import android.content.Context;
import android.graphics.Color;

import com.blankj.utilcode.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.MyOrder;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/11/2
 */

public class OrderAdapter extends SimpleAdapter<MyOrder.ObjectBean.ListBean> {

    public OrderAdapter(Context context, List<MyOrder.ObjectBean.ListBean> list) {
        super(context, list, R.layout.myorder_item);
    }



    @Override
    public void bindData(BaseViewHolder viewHolder, MyOrder.ObjectBean.ListBean data, int position) {
//        LogUtils.d("title",data.getInfoTitle());
//        viewHolder.getTextView(R.id.productnamep).setText(data.getProductNameP());
        if (!StringUtils.isEmpty(data.getProductName())) {
            viewHolder.getTextView(R.id.product_name).setText(data.getProductName());
        }if (!StringUtils.isEmpty(data.getProductOrderNum())) {
            viewHolder.getTextView(R.id.product_count).setText("X" + data.getProductOrderNum());
        }
        if (!StringUtils.isEmpty(data.getProductParmv0())) {
            viewHolder.getTextView(R.id.product_param).setText(data.getProductParmv0()+"  "+data.getProductParmv1());
        }
        RequestOptions options = new RequestOptions();
        options.error(R.drawable.product1);
        Glide.with(mContext).load(NetUrl.DOCHeader+data.getProductIcon()).apply(options).into(viewHolder.getImageView(R.id.product_image));
        String statue =data.getProductOrderStatus();
        if (statue.equals("0")) {
            viewHolder.getTextView(R.id.statue).setText("(待安装)");
            viewHolder.getTextView(R.id.statue).setTextColor(Color.parseColor("#ff2626"));

        }else{
            viewHolder.getTextView(R.id.statue).setText("(已完成)");
            viewHolder.getTextView(R.id.statue).setTextColor(Color.parseColor("#398ab8"));
        }


    }
}
