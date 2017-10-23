package com.mvpankao.base;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mvpankao.widget.ShapeImageView;


/**
 * 封装的ViewHolder
 * Created by wangjian
 * On  2016/9/9
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private SparseArray<View> views;
    protected BaseAdapter.OnItemClickListener listener;
    protected BaseAdapter.OnItemLongClickListener longclick;

    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener listener, BaseAdapter.OnItemLongClickListener longclick) {
        super(itemView);
        views = new SparseArray<View>();
        this.listener = listener;
        this.longclick = longclick;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public View getView(int id) {
        return findView(id);

    }
    public RelativeLayout getRelativeLayout(int id) {
        return findView(id);
    }

    public LinearLayout getLinearLayout(int id) {
        return findView(id);
    }

    public TextView getTextView(int id) {
        return findView(id);
    }

    public ImageView getImageView(int id) {
        return findView(id);
    }

    public ShapeImageView getShapeImageView(int id) {
        return findView(id);
    }

    public RadioButton getRadioButton(int id) {
        return findView(id);
    }

    public Button getButton(int id) {
        return findView(id);
    }

    public CheckBox getCheckBox(int id) {
        return findView(id);
    }

    public ListView getListView(int id) {
        return findView(id);
    }
    public GridView getGridView(int id) {
        return findView(id);
    }
    public CardView getCardView(int id) {
        return findView(id);
    }
    private <T extends View> T findView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.OnClick(v, getLayoutPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longclick != null) {
            longclick.onItemLongClick(v, getLayoutPosition());
        }
        return true;
    }
}
