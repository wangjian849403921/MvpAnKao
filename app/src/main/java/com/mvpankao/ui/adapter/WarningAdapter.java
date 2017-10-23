package com.mvpankao.ui.adapter;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.model.bean.WarningBean;

import java.util.List;


/**
 * 警报adapter
 * Created by wangjian
 * On  2016/9/9
 */
public class WarningAdapter extends SimpleAdapter<WarningBean.ObjectBean.ListBean> {


    public WarningAdapter(Context context, List<WarningBean.ObjectBean.ListBean> mDatas) {
        super(context, mDatas, R.layout.warning_item);
    }

    @Override
    public void bindData(BaseViewHolder viewHolder, WarningBean.ObjectBean.ListBean WarningBean, int position) {


        viewHolder.getTextView(R.id.police_title).setText(WarningBean.getAlarm_parm_name());
        viewHolder.getTextView(R.id.police_name).setText(WarningBean.getAlarm_assetsname());
        String time = TimeUtils.milliseconds2String(WarningBean.getAlarm_begindate());
        if (StringUtils.isEmpty(time)) {
            viewHolder.getTextView(R.id.police_time).setText(time.substring(0, 10));
        }
        String statue = WarningBean.getAlarm_status();
        String level = WarningBean.getAlarm_level();


        if (statue.equals("已解除") && level.equals("高")) {
            viewHolder.getTextView(R.id.police_log).setBackgroundResource(R.drawable.policeshape2);
            viewHolder.getTextView(R.id.police_log).setText("已解除");
            viewHolder.getImageView(R.id.police_image).setImageResource(R.drawable.policehigh);
            viewHolder.getImageView(R.id.police_logimage).setVisibility(View.INVISIBLE);
        } if (statue.equals("已解除") && level.equals("低")){

            viewHolder.getTextView(R.id.police_log).setBackgroundResource(R.drawable.policeshape2);
            viewHolder.getTextView(R.id.police_log).setText("已解除");
            viewHolder.getImageView(R.id.police_image).setImageResource(R.drawable.policelow);
            viewHolder.getImageView(R.id.police_logimage).setVisibility(View.INVISIBLE);
        } if (statue.equals("未解除") && level.equals("高")){
            viewHolder.getTextView(R.id.police_log).setBackgroundResource(R.drawable.policeshape);
            viewHolder.getTextView(R.id.police_log).setText("待解除");
            viewHolder.getImageView(R.id.police_image).setImageResource(R.drawable.policehigh_light);
            viewHolder.getImageView(R.id.police_logimage).setVisibility(View.VISIBLE);
        } if (statue.equals("未解除") && level.equals("低")){
            viewHolder.getTextView(R.id.police_log).setBackgroundResource(R.drawable.policeshape);
            viewHolder.getTextView(R.id.police_log).setText("待解除");
            viewHolder.getImageView(R.id.police_image).setImageResource(R.drawable.policelow_light);
            viewHolder.getImageView(R.id.police_logimage).setVisibility(View.VISIBLE);
        }


    }
}
