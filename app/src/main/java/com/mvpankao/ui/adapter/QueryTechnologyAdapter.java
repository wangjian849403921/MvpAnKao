package com.mvpankao.ui.adapter;

import android.content.Context;

import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.model.bean.TechnologyBean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/9/9
 */
public class QueryTechnologyAdapter extends SimpleAdapter<TechnologyBean.ObjectBean.ListBean> {

    public QueryTechnologyAdapter(Context context, List<TechnologyBean.ObjectBean.ListBean> mDatas) {
        super(context, mDatas, R.layout.report_item);
    }



    @Override
    public void bindData(BaseViewHolder viewHolder, TechnologyBean.ObjectBean.ListBean data, int position) {


        viewHolder.getTextView(R.id.report_title).setText(data.getDocTitle());
        String time = TimeUtils.milliseconds2String(data.getDocCreateTime());
        if (!StringUtils.isEmpty(time)) {
            viewHolder.getTextView(R.id.report_time).setText(time.substring(0,10));
        }
//        viewHolder.getImageView(R.id.news_image).setImageURI(Uri.parse(infomation.getInfoImg()));

    }
}
