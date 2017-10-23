package com.mvpankao.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.mvpankao.R;


/**
 * 自定义的RecycleView  EmptyView工具
 * Created by wangjian
 * On  2017/01/06
 */

public class ExpandListViewEmptyViewHelper {
    private ExpandableListView mRecyclerView;
    private View emptyView;
    private Context mContext;
    private String mEmptyText;
    private TextView mTextView;

    public ExpandListViewEmptyViewHelper(ExpandableListView recyclerView) {
        mRecyclerView = recyclerView;
        mContext = recyclerView.getContext();
        initEmptyView();
    }

    public ExpandListViewEmptyViewHelper(ExpandableListView recyclerView, String text) {
        mRecyclerView = recyclerView;
        mContext = recyclerView.getContext();
        mEmptyText = text;
        initEmptyView();
    }

    private void initEmptyView() {
        emptyView = View.inflate(mContext, R.layout.empty_view, null);
        ((ViewGroup)mRecyclerView.getParent()).addView(emptyView);
        mRecyclerView.setEmptyView(emptyView);
        if (!TextUtils.isEmpty(mEmptyText)) {
            ((TextView)emptyView.findViewById(R.id.textview)).setText(mEmptyText);
        }
    }

}
