package com.mvpankao.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.model.bean.WorkOrderChildrenItem;
import com.mvpankao.model.bean.WorkOrderGroupItem;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/12/30
 */

public class WorkOrderAdapter extends BaseExpandableListAdapter {

    private List<WorkOrderGroupItem> groups;
    private Context context;
    private LayoutInflater inflater;
    private int expand = 0;

    public WorkOrderAdapter(Context context, List<WorkOrderGroupItem> groups) {
        this.groups = groups;
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getGroupCount() {
        if (groups == null) {
            return 0;
        }
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        final WorkOrderGroupItem groupItem = groups.get(groupPosition);
        if (groupItem == null || groupItem.getChildrenItems() == null
                || groupItem.getChildrenItems().isEmpty()) {
            return 0;
        }
        return groupItem.getChildrenItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (groups == null) {
            return null;
        }
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChildItem(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = createGroupView();
        }
        WorkOrderGroupItem groupItem = groups.get(groupPosition);
        String statue = groupItem.getStatue();

        TextView workorder_statue = (TextView) view.findViewById(R.id.workorder_statue);
        ImageView statuelog = (ImageView) view.findViewById(R.id.statuelog);
        ImageView arrow = (ImageView) view.findViewById(R.id.arrow);
        TextView log = (TextView) view.findViewById(R.id.log);


        if (isExpanded) {
            expand = 1;
            arrow.animate().setDuration(500).rotation(180).start();

            log.setText("收回");
        } else {
            expand = 0;
            arrow.animate().setDuration(500).rotation(0).start();
            log.setText("更多");
        }

        switch (statue) {

            case "0":
                workorder_statue.setText("未开始");
                statuelog.setImageResource(R.drawable.shape2);

                break;
            case "1":
                workorder_statue.setText("执行中");
                statuelog.setImageResource(R.drawable.subscribeshape);
                break;
            case "2":
                workorder_statue.setText("已完成");
                statuelog.setImageResource(R.drawable.testingshap);
                break;
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        WorkOrderChildrenItem childrenItem = groups.get(groupPosition).getChildItem(childPosition);
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = createChildrenView();
        }
        RelativeLayout rlstatue = (RelativeLayout) view.findViewById(R.id.rl_statue);
        TextView testtime = (TextView) view.findViewById(R.id.time);
        TextView orderName = (TextView) view.findViewById(R.id.orderName);
        TextView orderContent = (TextView) view.findViewById(R.id.ordercontent);

        String statue = childrenItem.getOverdue();
        String starttime = "";
        String endtime = "";
        if (!StringUtils.isEmpty(childrenItem.getTime())) {
            starttime = TimeUtils.milliseconds2String(Long.parseLong(childrenItem.getTime()));
            endtime = TimeUtils.milliseconds2String(Long.parseLong(childrenItem.getEndtime()));
        }
        String name = childrenItem.getName();
        String content = childrenItem.getWorkOrderContent();
        if (!TextUtils.isEmpty(starttime)) {
            testtime.setText(starttime.substring(5, 10) + "至" + endtime.substring(5, 10));
        }
        if (!TextUtils.isEmpty(name)) {
            orderName.setText(name);
        }
        if (!TextUtils.isEmpty(content)) {
            orderContent.setText(content);
        }
        switch (statue) {
            case "0":
                rlstatue.setVisibility(View.GONE);
                break;
            case "1":
                rlstatue.setVisibility(View.VISIBLE);
                break;
        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private View createGroupView() {
        return inflater.inflate(R.layout.workorder_group, null);
    }

    private View createChildrenView() {
        return inflater.inflate(R.layout.workorder_child, null);
    }

}
