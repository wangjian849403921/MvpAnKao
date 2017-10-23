package com.mvpankao.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.model.bean.WorkFlowDetailBean;
import com.mvpankao.ui.activity.StepListActivity;
import com.mvpankao.ui.activity.UploadTaskActivity;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/11/22
 */

public class WorkFlowDetailAdapter extends BaseAdapter {
    private Context context;
    private List<WorkFlowDetailBean.ObjectBean.WorkflowProcesslistBean> list;
    private LayoutInflater inflater;
    SPUtils mSPUtils;

    public WorkFlowDetailAdapter(Context context, List<WorkFlowDetailBean.ObjectBean.WorkflowProcesslistBean> list) {
        super();
        this.context = context;
        this.list = list;
        mSPUtils = new SPUtils(context, "USER_INFO");
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        TimeLineHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.workdetail_item, null);
            viewHolder = new TimeLineHolder();

            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.statue = (TextView) convertView.findViewById(R.id.statue);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.view = convertView.findViewById(R.id.view_2);
            viewHolder.view0 = convertView.findViewById(R.id.view_0);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TimeLineHolder) convertView.getTag();
        }

        String title = list.get(position).getWorkflowStepName();
        String endtime = TimeUtils.milliseconds2String(list.get(position).getWorkflowStepEndDate());
        String starttime = TimeUtils.milliseconds2String(list.get(position).getWorkflowStepCreateDate());

        final int statue = list.get(position).getWorkflowStepStatus();
/**
 *
 */
        switch (statue) {

            case 4:
                viewHolder.title.setTextColor(Color.parseColor("#333333"));
                viewHolder.image.setImageResource(R.drawable.workcompleteshape);
                viewHolder.statue.setTextColor(Color.parseColor("#30a6e3"));
                viewHolder.statue.setText("已完成");
                viewHolder.time.setVisibility(View.VISIBLE);
                viewHolder.view.setVisibility(View.VISIBLE);

                if (!StringUtils.isEmpty(endtime)) {
                    viewHolder.time.setText(endtime);
                }
                if (mSPUtils.getString("role").equals("2")) {
                    viewHolder.statue.setClickable(true);
                    viewHolder.statue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, StepListActivity.class);
                            intent.putExtra("step", list.get(position).getWorkflowStepOrder()+"");
                            intent.putExtra("id", list.get(position).getWorkflowProIdFk()+"");
                            intent.putExtra("stepid", list.get(position).getId()+"");
                            intent.putExtra("statue", statue+"");

                            context.startActivity(intent);
                        }
                    });
                }
                break;

            case 1:
                viewHolder.title.setTextColor(Color.parseColor("#333333"));
                viewHolder.image.setImageResource(R.drawable.workoperationshape);
                if (!StringUtils.isEmpty(starttime)) {
                    viewHolder.time.setText(starttime);
                }
                viewHolder.view.setVisibility(View.VISIBLE);
                if (mSPUtils.getString("role").equals("1")) {
                    viewHolder.statue.setText("进行中");
                    viewHolder.statue.setTextColor(Color.parseColor("#1bbc9b"));
                    viewHolder.statue.setClickable(false);
                } else {
                    viewHolder.statue.setText("操作");
                    viewHolder.statue.setTextColor(Color.parseColor("#1bbc9b"));
                    viewHolder.statue.setClickable(true);
                    viewHolder.statue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent =new Intent(context, UploadTaskActivity.class);
                            intent.putExtra("step",list.get(position).getWorkflowStepOrder()+"");
                            intent.putExtra("id",list.get(position).getWorkflowProIdFk()+"");
                            intent.putExtra("stepid",list.get(position).getId()+"");
                            context.startActivity(intent);
                        }
                    });
                }

                break;
            case 0:
                viewHolder.time.setText("");
                viewHolder.statue.setClickable(false);
                viewHolder.title.setTextColor(Color.parseColor("#bebebe"));
                viewHolder.image.setImageResource(R.drawable.shape2);
                viewHolder.statue.setTextColor(Color.parseColor("#bebebe"));
                viewHolder.statue.setText("未开始");
                viewHolder.time.setVisibility(View.INVISIBLE);
                viewHolder.view.setVisibility(View.VISIBLE);
                break;
            case 2:
                if (!StringUtils.isEmpty(starttime)) {
                    viewHolder.time.setText(starttime);
                }
                viewHolder.statue.setClickable(false);
                viewHolder.title.setTextColor(Color.parseColor("#bebebe"));
                viewHolder.image.setImageResource(R.drawable.completeshape);
                viewHolder.statue.setTextColor(Color.parseColor("#ff6600"));
                viewHolder.statue.setText("待审核");
                if (mSPUtils.getString("role").equals("2")) {
                    viewHolder.statue.setClickable(true);
                    viewHolder.statue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, StepListActivity.class);
                            intent.putExtra("step", list.get(position).getWorkflowStepOrder()+"");
                            intent.putExtra("id", list.get(position).getWorkflowProIdFk()+"");
                            intent.putExtra("stepid", list.get(position).getId()+"");
                            intent.putExtra("statue",statue+"");

                            context.startActivity(intent);
                        }
                    });
                }
                viewHolder.view.setVisibility(View.VISIBLE);

                break;
            case 3:
                if (!StringUtils.isEmpty(starttime)) {
                    viewHolder.time.setText(starttime);
                }
                viewHolder.statue.setClickable(false);
                viewHolder.title.setTextColor(Color.parseColor("#bebebe"));
                viewHolder.image.setImageResource(R.drawable.completeshape);
                viewHolder.statue.setTextColor(Color.parseColor("#ff6600"));
                viewHolder.statue.setText("审核未通过");
                viewHolder.view.setVisibility(View.VISIBLE);
                if (mSPUtils.getString("role").equals("2")) {
                    viewHolder.statue.setClickable(true);
                    viewHolder.statue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, StepListActivity.class);
                            intent.putExtra("step", list.get(position).getWorkflowStepOrder()+"");
                            intent.putExtra("id", list.get(position).getWorkflowProIdFk()+"");
                            intent.putExtra("stepid", list.get(position).getId()+"");
                            intent.putExtra("statue",statue+"");

                            context.startActivity(intent);
                        }
                    });
                }
                break;
        }
        if (position == list.size() - 1) {
            viewHolder.view.setVisibility(View.INVISIBLE);
        }
        if (position == 0) {
            viewHolder.view0.setVisibility(View.INVISIBLE);
        }
        if (!StringUtils.isEmpty(title)) {
            viewHolder.title.setText(title);
        }

        return convertView;

    }

    static class TimeLineHolder {
        private TextView title, time, statue;
        private ImageView image;
        private View view;
        private View view0;
    }
}
