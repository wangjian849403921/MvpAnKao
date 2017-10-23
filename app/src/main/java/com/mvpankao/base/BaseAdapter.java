package com.mvpankao.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 封装的Adapter
 * Created by wangjian
 * On  2016/9/9
 */
public abstract class BaseAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected int mLayoutResId;
    protected OnItemClickListener listener;
    protected OnItemLongClickListener longclick;


    public interface OnItemClickListener {
        void OnClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        //        void OnLongClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnItemOnClick(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {

        this.longclick = listener;
    }

    public BaseAdapter(Context context, List<T> mDatas, int mLayoutResId) {
        this.mContext = context;
        this.mDatas = mDatas;
        this.mLayoutResId = mLayoutResId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutResId, null, false);

        return new BaseViewHolder(view, listener, longclick);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T t = getItem(position);
        bindData(holder, t,position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    public List<T> getDatas() {
        return mDatas;

    }

    public void clearData() {
        mDatas.clear();
        notifyItemRangeRemoved(0, mDatas.size());
    }
    public void removeData(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }
    public void addData(List<T> datas) {
        addData(0, datas);
    }

    public void addData(int position, List<T> datas) {
        if (datas != null && datas.size() > 0) {
            mDatas.addAll(datas);
            notifyItemRangeChanged(position, mDatas.size());
        }

    }

    public abstract void bindData(BaseViewHolder viewHolder, T t,int position);
}
