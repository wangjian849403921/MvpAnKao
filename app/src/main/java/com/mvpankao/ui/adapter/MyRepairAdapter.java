package com.mvpankao.ui.adapter;

import android.content.Context;
import android.graphics.Color;

import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.model.bean.RepairBean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/11/2
 */

public class MyRepairAdapter extends SimpleAdapter<RepairBean.ObjectBean.DataBean.ListBean> {

    public MyRepairAdapter(Context context, List<RepairBean.ObjectBean.DataBean.ListBean> list) {
        super(context, list, R.layout.faultrepair_item);
    }


    @Override
    public void bindData(BaseViewHolder viewHolder, RepairBean.ObjectBean.DataBean.ListBean data, int position) {
//        LogUtils.d("title",data.getInfoTitle());
        if (!StringUtils.isEmpty(data.getRepairTypeName())) {
            viewHolder.getTextView(R.id.repair_title).setText(data.getRepairTypeName());
        }
        if (!StringUtils.isEmpty(data.getRepairCircuit())) {
            viewHolder.getTextView(R.id.repair_content).setText(data.getRepairCircuit());
        }
        String time = TimeUtils.milliseconds2String(data.getRepairCreateDate());
        if (!StringUtils.isEmpty(time)) {
            viewHolder.getTextView(R.id.repair_time).setText(time.substring(0, 10));
        }
        int statue = data.getRepairStatus();
        switch (statue) {
            case 0:
                viewHolder.getTextView(R.id.repair_statue).setText("未开始");
                viewHolder.getTextView(R.id.repair_statue).setTextColor(Color.parseColor("#999999"));

                break;
            case 1:
                viewHolder.getTextView(R.id.repair_statue).setText("检修中");
                viewHolder.getTextView(R.id.repair_statue).setTextColor(Color.parseColor("#ff2626"));

                break;
            case 2:
                viewHolder.getTextView(R.id.repair_statue).setText("已完成");
                viewHolder.getTextView(R.id.repair_statue).setTextColor(Color.parseColor("#30a6e3"));
                break;
        }


    }
}
