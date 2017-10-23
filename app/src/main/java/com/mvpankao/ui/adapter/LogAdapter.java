package com.mvpankao.ui.adapter;

import android.content.Context;

import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.WorkOrderLogBean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/2/8
 */

public class LogAdapter extends SimpleAdapter<WorkOrderLogBean.ObjectBean> {
    List<String> imageList = null;
    public LogAdapter(Context context, List<WorkOrderLogBean.ObjectBean> list) {
        super(context, list, R.layout.step_item);
    }


    @Override
    public void bindData(BaseViewHolder viewHolder, WorkOrderLogBean.ObjectBean data, int position) {
        if (data.getImgUrlList().size()!=0){
            imageList= data.getImgUrlList();
            Glide.with(mContext).load(NetUrl.DOCHeader+imageList.get(0)).into(viewHolder.getImageView(R.id.step_image));
        }
        viewHolder.getTextView(R.id.userName).setText(data.getUsername());
        viewHolder.getTextView(R.id.step_time).setText(TimeUtils.milliseconds2String(data.getLogCreateTime()).substring(0,10));
        viewHolder.getTextView(R.id.step_content).setText(data.getLogComment());


    }
}
