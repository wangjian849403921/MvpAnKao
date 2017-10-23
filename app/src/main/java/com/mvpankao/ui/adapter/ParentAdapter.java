package com.mvpankao.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.model.bean.ChildEntity;
import com.mvpankao.model.bean.ParentEntity;
import com.mvpankao.ui.activity.ParentActivity;

import java.util.ArrayList;


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

public class ParentAdapter extends BaseExpandableListAdapter {

    private Context mContext;// 上下文

    private ArrayList<ParentEntity> mParents;// 数据源

    private OnChildTreeViewClickListener mTreeViewClickListener;// 点击子ExpandableListView子项的监听

    public ParentAdapter(Context context, ArrayList<ParentEntity> parents) {
        this.mContext = context;
        this.mParents = parents;
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

        final ChildAdapter childAdapter = new ChildAdapter(this.mContext,
                childs);

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
                Intent intent = new Intent(mContext, ParentActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("id", groupid);
                intent.putExtra("level", level);
                intent.putExtra("image", image);
                mContext.startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);

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
     * @author Apathy、恒
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
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.parent_group_item, null);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.update(mParents.get(groupPosition));
        return convertView;
    }

    /**
     * @author Apathy、恒
     *         <p>
     *         Holder优化
     */
    class GroupHolder {

        private TextView parentGroupTV;
        private ImageView parentGroupImage;

        public GroupHolder(View v) {
            parentGroupTV = (TextView) v.findViewById(R.id.parentGroupTV);
            parentGroupImage = (ImageView) v.findViewById(R.id.parentGroupIcon);

        }

        public void update(ParentEntity model) {
            parentGroupTV.setText(model.getGroupName());
//			parentGroupTV.setTextColor(model.getGroupColor());
//			Glide.with(mContext).load(model.getIcon()).into(parentGroupImage);

        }
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
