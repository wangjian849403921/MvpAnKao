package com.mvpankao.ui.adapter;

import android.content.Context;

import com.blankj.utilcode.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.StepBean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/2/8
 */

public class StepAdapter extends SimpleAdapter<StepBean.ObjectBean> {
    List<String> imageList = null;
    public StepAdapter(Context context, List<StepBean.ObjectBean> list) {
        super(context, list, R.layout.step_item);
    }


    @Override
    public void bindData(BaseViewHolder viewHolder, StepBean.ObjectBean data, int position) {
        if (data.getIconList().size()!=0){
            imageList= data.getIconList();
            Glide.with(mContext).load(NetUrl.DOCHeader+imageList.get(0)).into(viewHolder.getImageView(R.id.step_image));
        }
        viewHolder.getTextView(R.id.userName).setText(data.getWorkflowStepOperationName());
        viewHolder.getTextView(R.id.step_time).setText(TimeUtils.milliseconds2String(data.getWorkflowStepCommentCreateDate()));
        viewHolder.getTextView(R.id.step_content).setText(data.getWorkflowStepComment());


    }
}
