package com.mvpankao.ui.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.CreateOrderAssert;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/9/9
 */
public class WorkOrderAssertAdapter extends SimpleAdapter<CreateOrderAssert.ObjectBean> {


    public WorkOrderAssertAdapter(Context context, List<CreateOrderAssert.ObjectBean> mDatas) {
        super(context, mDatas, R.layout.customer_item);
    }

    @Override
    public void bindData(BaseViewHolder viewHolder, CreateOrderAssert.ObjectBean customerBean, int position) {


        viewHolder.getTextView(R.id.customerName).setText(customerBean.getAssetName());
//        viewHolder.getTextView(R.id.news_time).setText(infomation.getInfotime());
        Glide.with(mContext).load(NetUrl.DOCHeader+customerBean.getAssetIcon()).into(viewHolder.getImageView(R.id.customerImage));


    }
}
