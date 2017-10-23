package com.mvpankao.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.model.bean.ChildEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjian
 *         <p>
 *         <br/>
 *         <br/>
 *         <p>
 *         子类分组的适配器
 *         <p>
 *         <br/>
 *         <br/>
 *         <p>
 *         方法{@link #isChildSelectable(int, int)} <b><font color='#ff00ff'
 *         size='2'>必须返回true</font></b>
 */
public class ChildwithCheckAdapter extends BaseExpandableListAdapter {

    private Context mContext;// 上下文

    private ArrayList<ChildEntity> mChilds;// 数据源


    //名字数组
    private List<String> groupnamearray = new ArrayList<String>();
    private List<String> childnamearray = new ArrayList<String>();

    //id数组
    private List<String> groupidarray = new ArrayList<String>();
    private List<String> childidarray = new ArrayList<String>();

    private List<String> grouppositionarray = new ArrayList<String>();
    private List<String> childpositionarray = new ArrayList<String>();
    private ArrayList<String> childNames;
    private ArrayList<String> childIds;
    private ArrayList<String> childIcons;
    private ArrayList<String> Positions;
    String groupposition[];
    String childposition[];
    private List<String> imagearray = new ArrayList<String>();

    public ChildwithCheckAdapter(Context context, ArrayList<ChildEntity> childs, List<String> groupname, List<String> groupid, List<String> childname, List<String> childid, List<String> groupposition, List<String> childposition, List<String> imagearrays) {
        this.mContext = context;
        this.mChilds = childs;
        this.groupnamearray = groupname;
        this.groupidarray = groupid;
        this.childnamearray = childname;
        this.childidarray = childid;
        this.grouppositionarray = groupposition;
        this.childpositionarray = childposition;
        this.imagearray = imagearrays;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChilds.get(groupPosition).getChildNames() != null ? mChilds
                .get(groupPosition).getChildNames().size() : 0;
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        if (mChilds.get(groupPosition).getChildNames() != null
                && mChilds.get(groupPosition).getChildNames().size() > 0)
            return mChilds.get(groupPosition).getChildNames()
                    .get(childPosition).toString();

//		if (mChilds.get(groupPosition).getGroupIcon() != null
//				&& mChilds.get(groupPosition).getChildIcons().size() > 0)
//			return mChilds.get(groupPosition).getChildIcons()
//					.get(childPosition).toString();
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isExpanded, View convertView, ViewGroup parent) {
        childIds = mChilds.get(groupPosition).getChildIds();
        childNames = mChilds.get(groupPosition).getChildNames();
        childIcons=mChilds.get(groupPosition).getChildIcons();
        ChildHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.child_childcheck_item, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
//        SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
//
//        String index = sp.getString("childposition", "");
//        if (!TextUtils.isEmpty(index)) {
//            LogUtils.d(index);
//            childposition = index.split(";");
//            int pos = 0;
//            for (int j = 0; j < childposition.length; j++) {
//                pos = Integer.parseInt(childposition[j]);
//
//                if (groupPosition == pos) {
//                    holder.childChildCheck.setChecked(true);
//                }
//            }
//
//
//        }
        final String childchildid = childIds.get(childPosition);
        final String childchildname = childNames.get(childPosition);
        final String childchildimage = childNames.get(childPosition);

        holder.update(getChild(groupPosition, childPosition));
        holder.childChildCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!childnamearray.contains(childchildname)) {
                        childnamearray.add(childchildname);
                        childidarray.add(childchildid);
                        childpositionarray.add(childPosition + "");
                        imagearray.add(childchildimage);
                    }

                } else {
                    childnamearray.remove(childchildname);
                    childidarray.remove(childchildid);
                    childpositionarray.remove(childPosition + "");
                    imagearray.remove(childchildimage);

                }
            }
        });


        return convertView;
    }

    /**
     * @author wangjian
     *         <p>
     *         Holder优化
     */
    class ChildHolder {

        private TextView childChildTV;
        private ImageView childChildIV;
        private CheckBox childChildCheck;

        public ChildHolder(View v) {
            childChildTV = (TextView) v.findViewById(R.id.childChildTV);
            childChildIV = (ImageView) v.findViewById(R.id.childChildIV);
            childChildCheck = (CheckBox) v.findViewById(R.id.child_child_check);

        }

        public void update(String str) {
            childChildTV.setText(str);
//			childChildTV.setTextColor(Color.parseColor("#00ffff"));
//			Glide.with(mContext).load()
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (mChilds != null && mChilds.size() > 0)
            return mChilds.get(groupPosition);
        return null;
    }

    @Override
    public int getGroupCount() {
        return mChilds != null ? mChilds.size() : 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ChildEntity groupItem = mChilds.get(groupPosition);

        GroupHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.child_groupcheck_item, null);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.update(mChilds.get(groupPosition));
        final String childrenId = groupItem.getGroupId();
        final String childname = groupItem.getGroupName();
        final String childimage = groupItem.getGroupIcon();
//        SPUtils sp = new SPUtils(mContext, "ASSERTAREA");
//
//        String index = sp.getString("groupposition", "");
//        if (!TextUtils.isEmpty(index)) {
//            LogUtils.d(index);
//            groupposition = index.split(";");
//            int pos = 0;
//            for (int j = 0; j < groupposition.length; j++) {
//                pos = Integer.parseInt(groupposition[j]);
//
//                if (groupPosition == pos) {
//                    holder.childGroupCheck.setChecked(true);
//                }
//            }

//
//        }
        holder.childGroupCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!groupnamearray.contains(childname)) {
                        groupnamearray.add(childname);
                        groupidarray.add(childrenId);
                        grouppositionarray.add(groupPosition + "");
                        imagearray.add(childimage);

                    }

                } else {
                    groupnamearray.remove(childname);
                    groupidarray.remove(childrenId);
                    grouppositionarray.remove(groupPosition + "");
                    imagearray.remove(childimage);
                }
            }
        });

        return convertView;
    }

    /**
     * @author wangjian
     *         <p>
     *         Holder优化
     */
    class GroupHolder {

        private TextView childGroupTV;
        private CheckBox childGroupCheck;

        public GroupHolder(View v) {
            childGroupTV = (TextView) v.findViewById(R.id.childGroupTV);
            childGroupCheck = (CheckBox) v.findViewById(R.id.child_group_check);
        }

        public void update(ChildEntity model) {
            childGroupTV.setText(model.getGroupName());
//			childGroupTV.setTextColor(model.getGroupColor());
        }
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        /**
         * ==============================================
         * 此处必须返回true，否则无法响应子项的点击事件===============
         * ==============================================
         **/
        return true;
    }

    public List<String> getAssertImage() {
        return imagearray;
    }

    public List<String> getChildGroupName() {
        return groupnamearray;
    }

    public List<String> getChildChildName() {
        return childnamearray;
    }

    public List<String> getChildGroupId() {
        return groupidarray;
    }

    public List<String> getChildChildid() {
        return childidarray;
    }

    public List<String> getChildGroupPosition() {
        return grouppositionarray;
    }

    public List<String> getChildChildPosition() {
        return childpositionarray;
    }
}