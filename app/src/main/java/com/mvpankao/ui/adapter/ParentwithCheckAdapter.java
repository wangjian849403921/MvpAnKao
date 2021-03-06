package com.mvpankao.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.model.bean.ChildEntity;
import com.mvpankao.model.bean.ParentEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author wangjian
 *         <p>
 *         父类分组的适配器
 *         <p>
 *         <br/>
 *         <br/>
 *         <p>
 *         方法 {@link #getChildView(int, int, boolean, View, ViewGroup)}<b><font
 *         color='#ff00ff' size='2'>极其重要</font></b>
 */

public class ParentwithCheckAdapter extends BaseExpandableListAdapter {

    private Context mContext;// 上下文

    private ArrayList<ParentEntity> mParents;// 数据源

    private OnChildTreeViewClickListener mTreeViewClickListener;// 点击子ExpandableListView子项的监听
    //记录checkbox的状态
    public HashMap<Integer, Boolean> state = new HashMap<Integer, Boolean>();
     public ChildwithCheckAdapter childAdapter;
    //名字数组
    private List<String> groupnamearray = new ArrayList<String>();

    private List<String> childnamearray = new ArrayList<String>();

    //id数组
    private List<String> groupidarray = new ArrayList<String>();
    private List<String> childidarray = new ArrayList<String>();
    String positionarray[];

    private List<String> grouppositionarray = new ArrayList<String>();

    private List<String> childpositionarray = new ArrayList<String>();
    private List<String> groupimagearray = new ArrayList<String>();

    public ParentwithCheckAdapter(Context context, ArrayList<ParentEntity> parents, List<String> groupname, List<String> groupid, List<String> childname, List<String> childid, List<String> grouppositionarray, List<String> childpositionarray, List<String> imagearray) {
        this.mContext = context;
        this.mParents = parents;
        this.groupnamearray = groupname;
        this.groupidarray = groupid;
        this.childnamearray = childname;
        this.childidarray = childid;
        this.grouppositionarray=grouppositionarray;
        this.childpositionarray=childpositionarray;
        this.groupimagearray=imagearray;
    }

    @Override
    public ChildEntity getChild(int groupPosition, int childPosition) {
        return mParents.get(groupPosition).getChilds().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mParents.get(groupPosition).getChilds() != null ? mParents
                .get(groupPosition).getChilds().size() : 0;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isExpanded, View convertView, ViewGroup parent) {

        final ExpandableListView eListView = getExpandableListView();

        ArrayList<ChildEntity> childs = new ArrayList<ChildEntity>();

        final ChildEntity child = getChild(groupPosition, childPosition);

        childs.add(child);

          childAdapter = new ChildwithCheckAdapter(this.mContext,
                childs,groupnamearray,groupidarray,childnamearray,childidarray,grouppositionarray,childpositionarray,groupimagearray);

        eListView.setAdapter(childAdapter);
        //设置展开
        final int groupCount = eListView.getCount();

        for (int i = 0; i < groupCount; i++) {

            eListView.expandGroup(i);
            LayoutParams lp = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, (child
                    .getChildNames().size() + 1)
                    * (int) mContext.getResources().getDimension(
                    R.dimen.parent_expandable_list_height));
            eListView.setLayoutParams(lp);
        }
        eListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                String groupid = mParents.get(groupPosition).getChilds()
                        .get(childPosition).getGroupId();


                String name = mParents.get(groupPosition).getChilds()
                        .get(childPosition).getGroupName();
                String level = mParents.get(groupPosition).getChilds()
                        .get(childPosition).getGroupLevel();
                String image = mParents.get(groupPosition).getChilds()
                        .get(childPosition).getGroupIcon();


//				Toast.makeText(
//						mContext,
//						"groupid: "
//								+ groupid, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        /**
         * @author wangjian
         *
         *         点击子ExpandableListView子项时，调用回调接口
         * */
        eListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView arg0, View arg1,
                                        int groupIndex, int childIndex, long arg4) {

                if (mTreeViewClickListener != null) {

                    mTreeViewClickListener.onClickPosition(groupPosition,
                            childPosition, childIndex);
                }
                return false;
            }
        });

//
//		/**
//		 * @author Apathy、恒
//		 *
//		 *         子ExpandableListView展开时，因为group只有一项，所以子ExpandableListView的总高度=
//		 *         （子ExpandableListView的child数量 + 1 ）* 每一项的高度
//		 * */
//		eListView.setOnGroupExpandListener(new OnGroupExpandListener() {
//			@Override
//			public void onGroupExpand(int groupPosition) {
//
//				LayoutParams lp = new LayoutParams(
//						ViewGroup.LayoutParams.MATCH_PARENT, (child
//						.getChildNames().size() + 1)
//						* (int) mContext.getResources().getDimension(
//						R.dimen.parent_expandable_list_height));
//				eListView.setLayoutParams(lp);
//			}
//		});
//
//		/**
//		 * @author Apathy、恒
//		 *
//		 *         子ExpandableListView关闭时，此时只剩下group这一项，
//		 *         所以子ExpandableListView的总高度即为一项的高度
//		 * */
//		eListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
//			@Override
//			public void onGroupCollapse(int groupPosition) {
//
//				LayoutParams lp = new LayoutParams(
//						ViewGroup.LayoutParams.MATCH_PARENT, (int) mContext
//						.getResources().getDimension(
//								R.dimen.parent_expandable_list_height));
//				eListView.setLayoutParams(lp);
//			}
//		});
        return eListView;

    }

    /**
     * @author wangjian
     * <p>
     * 动态创建子ExpandableListView
     */
    public ExpandableListView getExpandableListView() {
        ExpandableListView mExpandableListView = new ExpandableListView(
                mContext);
        LayoutParams lp = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) mContext
                .getResources().getDimension(
                        R.dimen.parent_expandable_list_height));
        mExpandableListView.setLayoutParams(lp);
        mExpandableListView.setDividerHeight(0);// 取消group项的分割线
        mExpandableListView.setChildDivider(null);// 取消child项的分割线
        mExpandableListView.setGroupIndicator(null);// 取消展开折叠的指示图标
        return mExpandableListView;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mParents.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mParents != null ? mParents.size() : 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.parent_groupcheck_item, null);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
//        SPUtils sp=new SPUtils(mContext,"ASSERTAREA");
//        String index = sp.getString("position", "");
//        if (!TextUtils.isEmpty(index)) {
//            positionarray = index.split(";");
//            int pos = 0;
//            for (int j = 0; j < positionarray.length; j++) {
//                pos = Integer.parseInt(positionarray[j]);
//                state.put(pos, true);
//            }
//
//
//
//        }
        holder.update(mParents.get(groupPosition));
        holder.parentGroupCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    state.put(groupPosition, isChecked);
                    groupimagearray.add(mParents.get(groupPosition).getIcon());
                } else {
                    state.remove(groupPosition);
                    groupimagearray.remove(mParents.get(groupPosition).getIcon());

                }
            }
        });



        holder.parentGroupCheck.setChecked((state.get(groupPosition) == null ? false : true));
        return convertView;
    }

    /**
     * @author wangjian
     *         <p>
     *         Holder优化
     */
    class GroupHolder {

        private TextView parentGroupTV;
        private ImageView parentGroupImage;
        private CheckBox parentGroupCheck;

        public GroupHolder(View v) {
            parentGroupTV = (TextView) v.findViewById(R.id.parentGroupTV);
            parentGroupImage = (ImageView) v.findViewById(R.id.parentGroupIcon);
            parentGroupCheck=(CheckBox)v.findViewById(R.id.parent_group_check);
        }

        public void update(ParentEntity model) {
            parentGroupTV.setText(model.getGroupName());
//			parentGroupTV.setTextColor(model.getGroupColor());
//			Glide.with(mContext).load(model.getIcon()).into(parentGroupImage);

        }
    }
    public List<String> getAssertImage() {
        return groupimagearray;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * @author Apathy、恒
     * <p>
     * 设置点击子ExpandableListView子项的监听
     */
    public void setOnChildTreeViewClickListener(
            OnChildTreeViewClickListener treeViewClickListener) {
        this.mTreeViewClickListener = treeViewClickListener;
    }

    /**
     * @author Apathy、恒
     *         <p>
     *         点击子ExpandableListView子项的回调接口
     */
    public interface OnChildTreeViewClickListener {

        void onClickPosition(int parentPosition, int groupPosition,
                             int childPosition);
    }

}
