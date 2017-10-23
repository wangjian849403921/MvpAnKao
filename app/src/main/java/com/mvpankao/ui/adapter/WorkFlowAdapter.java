package com.mvpankao.ui.adapter;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.model.bean.WorkFlowBean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/11/2
 */

public class WorkFlowAdapter extends SimpleAdapter<WorkFlowBean.ObjectBean.ListBean> {

    public WorkFlowAdapter(Context context, List<WorkFlowBean.ObjectBean.ListBean> list) {
        super(context, list, R.layout.workflow_item);
    }


    @Override
    public void bindData(BaseViewHolder viewHolder, WorkFlowBean.ObjectBean.ListBean data, int position) {
//        LogUtils.d("title",data.getInfoTitle());
        viewHolder.getTextView(R.id.workflow_content).setText(data.getWorkflowProName());
        viewHolder.getTextView(R.id.current_task).setText(data.getWorkflowFkName());
        String starttime = TimeUtils.milliseconds2String(data.getWorkflowProBeginDate());
        LogUtils.d(starttime);
        String endtime = TimeUtils.milliseconds2String(data.getWorkflowProEndDate());
        if (!StringUtils.isEmpty(starttime)) {
            viewHolder.getTextView(R.id.workflow_time).setText(starttime.substring(0, 10) + "至" + endtime.substring(5, 10));
        }
    }
}
