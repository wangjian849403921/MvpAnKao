package com.mvpankao.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.model.bean.ParamChildrenItem;
import com.mvpankao.model.bean.ParamGroupItem;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/12/30
 */

public class ParamAdapter extends BaseExpandableListAdapter {

    private List<ParamGroupItem> groups;
    private Context context;
    private LayoutInflater inflater;
    private int expand = 0;

    public ParamAdapter(Context context, List<ParamGroupItem> groups) {
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
        final ParamGroupItem groupItem = groups.get(groupPosition);
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
        ParamGroupItem groupItem = groups.get(groupPosition);
        String name = groupItem.getName();
//        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();

        TextView groupname = (TextView) view.findViewById(R.id.groupname);

        if (!StringUtils.isEmpty(name)) {
            groupname.setText(name);
        }


        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ParamChildrenItem childrenItem = groups.get(groupPosition).getChildItem(childPosition);
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = createChildrenView();
        }

        TextView childname = (TextView) view.findViewById(R.id.childname);
        TextView childvalue = (TextView) view.findViewById(R.id.childvalue);


        String name = childrenItem.getName();
        String value = childrenItem.getData();

        if (!TextUtils.isEmpty(name)) {
            childname.setText(name);
        }
        if (!TextUtils.isEmpty(value)) {
            childvalue.setText(value);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private View createGroupView() {
        return inflater.inflate(R.layout.parameter_groupitem, null);
    }

    private View createChildrenView() {
        return inflater.inflate(R.layout.parameter_childitem, null);
    }

}
