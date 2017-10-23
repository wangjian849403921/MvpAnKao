package com.mvpankao.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.widget.refresh.SwipeRecyclerView;


/**
 * Created by wangjian
 * On  2016/12/16
 */

public class EmptyViewHelper {
    private ListView mListView;
    private ExpandableListView mExpamdableListView;
    private SwipeRecyclerView mRecyclerView;

    private View emptyView;
    private Context mContext;
    private String mEmptyText;
    private TextView mTextView;

    public EmptyViewHelper(ListView listView) {
        mListView = listView;
        mContext = listView.getContext();
        initEmptyView(mListView);
    }
    public EmptyViewHelper(ExpandableListView listView) {
        mExpamdableListView = listView;
        mContext = listView.getContext();
        initEmptyView(mExpamdableListView);
    }
    public EmptyViewHelper(SwipeRecyclerView listView) {
        mRecyclerView = listView;
        mContext = listView.getContext();
        initEmptyView(mRecyclerView);
    }
    public EmptyViewHelper(ListView listView, String text) {
        mListView = listView;
        mContext = listView.getContext();
        mEmptyText = text;
        initEmptyView(mListView);
    }
    public EmptyViewHelper(ExpandableListView listView, String text) {
        mExpamdableListView = listView;
        mContext = listView.getContext();
        mEmptyText = text;
        initEmptyView(mExpamdableListView);
    }
    public EmptyViewHelper(SwipeRecyclerView listView, String text) {
        mRecyclerView = listView;
        mContext = listView.getContext();
        mEmptyText = text;
        initEmptyView(mRecyclerView);
    }
    private void initEmptyView(ListView listView) {
        emptyView = View.inflate(mContext, R.layout.empty_view, null);
        ((ViewGroup)listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
        if (!TextUtils.isEmpty(mEmptyText)) {
            ((TextView)emptyView.findViewById(R.id.textview)).setText(mEmptyText);
        }
    }
    private void initEmptyView(ExpandableListView listView) {
        emptyView = View.inflate(mContext, R.layout.empty_view, null);
        ((ViewGroup)listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
        if (!TextUtils.isEmpty(mEmptyText)) {
            ((TextView)emptyView.findViewById(R.id.textview)).setText(mEmptyText);
        }
    }
    private void initEmptyView(SwipeRecyclerView listView) {
        emptyView = View.inflate(mContext, R.layout.empty_view, null);
        ((ViewGroup)listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
        if (!TextUtils.isEmpty(mEmptyText)) {
            ((TextView)emptyView.findViewById(R.id.textview)).setText(mEmptyText);
        }
    }

}
