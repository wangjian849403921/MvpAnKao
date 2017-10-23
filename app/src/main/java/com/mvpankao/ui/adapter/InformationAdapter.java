package com.mvpankao.ui.adapter;

import android.content.Context;

import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.NewsBean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/11/2
 */

public class InformationAdapter extends SimpleAdapter<NewsBean.ObjectBean.ListBean> {

    public InformationAdapter(Context context, List<NewsBean.ObjectBean.ListBean> list) {
        super(context, list, R.layout.information_item);
    }


    @Override
    public void bindData(BaseViewHolder viewHolder, NewsBean.ObjectBean.ListBean data, int position) {

        viewHolder.getTextView(R.id.news_title).setText(data.getTitle());
        String time = TimeUtils.milliseconds2String(data.getCreateDate());
        if (!StringUtils.isEmpty(time)) {
            viewHolder.getTextView(R.id.news_time).setText(time.substring(0, 10));
        }
        String image=data.getIcon();
        if (!StringUtils.isEmpty(image)) {
            RequestOptions options = new RequestOptions();
            options.error(R.drawable.icon_error);
            Glide.with(mContext).load(NetUrl.DOCHeader+image).apply(options).into(viewHolder.getImageView(R.id.news_image));
        }
    }
}
