package com.mvpankao.base;

import android.content.Context;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/9/9
 */
public abstract class SimpleAdapter<T> extends BaseAdapter<T,BaseViewHolder> {

    public SimpleAdapter(Context context, List<T> mDatas, int mLayoutResId) {
        super(context, mDatas, mLayoutResId);
    }
}
