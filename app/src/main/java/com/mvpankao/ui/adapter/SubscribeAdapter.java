package com.mvpankao.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.model.bean.SubscribeChildrenItem;
import com.mvpankao.model.bean.SubscribeGroupItem;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangjian
 * On  2016/12/30
 */

public class SubscribeAdapter extends BaseExpandableListAdapter {
    private List<SubscribeGroupItem> groups;
    private Context context;
    private LayoutInflater inflater;

    public SubscribeAdapter(Context context, List<SubscribeGroupItem> groups) {
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
        final SubscribeGroupItem groupItem = groups.get(groupPosition);
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
        SubscribeGroupItem groupItem = groups.get(groupPosition);
        String month = groupItem.getMonth();
//        String id = groupItem.getId();
        TextView testmonth = (TextView) view.findViewById(R.id.test_month);
        if (!TextUtils.isEmpty(month)) {
            testmonth.setText(month+"月");
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubscribeChildrenItem childrenItem = groups.get(groupPosition).getChildItem(childPosition);
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = createChildrenView();
        }
        ImageView statueimage = (ImageView) view.findViewById(R.id.statuelog);
        TextView testtime = (TextView) view.findViewById(R.id.test_time);
        TextView teststatue = (TextView) view.findViewById(R.id.statue);
        TextView name = (TextView) view.findViewById(R.id.testtype);

        String statue = childrenItem.getStatue();
        String starttime= TimeUtils.milliseconds2String(Long.parseLong(childrenItem.getTime()));

//        String starttime= strToDateLong(childrenItem.getTime())+"";

        String endtime= TimeUtils.milliseconds2String(Long.parseLong(childrenItem.getEndtime()));



        String type = childrenItem.getName();
        if (!TextUtils.isEmpty(starttime)) {
            testtime.setText(starttime.substring(5,10)+"-"+endtime.substring(5,10));
        }
        if (!TextUtils.isEmpty(type)) {
            name.setText(type);
        }

        switch (statue) {
            case "1":
                teststatue.setText("审核中");
                teststatue.setTextColor(Color.parseColor("#ff0000"));
                statueimage.setImageResource(R.drawable.subscribeshape);
                break;
            case "2":
                teststatue.setText("待实验");
                teststatue.setTextColor(Color.parseColor("#ff7800"));
                statueimage.setImageResource(R.drawable.wait_test);

                break;
            case "3":
                teststatue.setText("试验中");
                teststatue.setTextColor(Color.parseColor("#1bbc9b"));
                statueimage.setImageResource(R.drawable.testingshap);
                break;
            default:
                teststatue.setText("已完成");
                teststatue.setTextColor(Color.parseColor("#666666"));
                statueimage.setImageResource(R.drawable.shape2);
                break;
        }


        return view;
    }
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private View createGroupView() {
        return inflater.inflate(R.layout.subscribe_group, null);
    }

    private View createChildrenView() {
        return inflater.inflate(R.layout.subscribe_child, null);
    }


}
