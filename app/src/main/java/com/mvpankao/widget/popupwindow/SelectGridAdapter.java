package com.mvpankao.widget.popupwindow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mvpankao.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * 子属性GridView的适配器
 */
public class SelectGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<SelectValue> data = new ArrayList<SelectValue>();
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();	// 定义一个向量作为选中与否容器

    private int lastPosition = -1;		//记录上一次选中的图片位置，-1表示未选中任何图片
    private boolean multiChoose;		//表示当前适配器是否允许多选
    public SelectGridAdapter(Context context,int index, boolean isMulti) {
        this.mContext = context;
        multiChoose = isMulti;
        lastPosition=index;

    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        final MyView myView;
        if (v == null) {
            myView = new MyView();
            v = View.inflate(mContext, R.layout.item_select_value, null);
            myView.attr = (TextView) v.findViewById(R.id.attr_name);
            v.setTag(myView);
        } else {
            myView = (MyView) v.getTag();
        }
        myView.attr.setText(data.get(position).getValue());
        /**
         * 根据选中状态来设置item的背景和字体颜色
         */
        if (mImage_bs.elementAt(position)) {
            myView.attr.setBackgroundResource(R.drawable.selected_shape);
            myView.attr.setTextColor(Color.WHITE);

        } else {
            myView.attr.setBackgroundResource(R.drawable.unselected_shape);
            myView.attr.setTextColor(Color.BLACK);
        }

        return v;
    }

    static class MyView {
        public TextView attr;
    }
    private LayerDrawable makeBmp(int id, boolean isChosen){
        Bitmap mainBmp = ((BitmapDrawable)mContext.getResources().getDrawable(id)).getBitmap();

        // 根据isChosen来选取对勾的图片
        Bitmap seletedBmp;
        if(isChosen == true)
            seletedBmp = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.selected_shape);
        else
            seletedBmp = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.unselected_shape);

        // 产生叠加图
        Drawable[] array = new Drawable[2];
        array[0] = new BitmapDrawable(mainBmp);
        array[1] = new BitmapDrawable(seletedBmp);
        LayerDrawable la = new LayerDrawable(array);
        la.setLayerInset(0, 0, 0, 0, 0);
        la.setLayerInset(1, 0, -5, 60, 45 );

        return la;	//返回叠加后的图
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(boolean isUnfold,
                                     final List<SelectValue> tempData) {
        if (tempData == null || 0 == tempData.size()) {
            return;
        }
        data.clear();
        // 如果是展开的，则加入全部data，反之则只显示3条
        if (isUnfold) {
            data.addAll(tempData);
        } else {
            data.add(tempData.get(0));
            data.add(tempData.get(1));
            data.add(tempData.get(2));
        }
        notifyDataSetChanged();
        for(int i=0; i<data.size(); i++)
            mImage_bs.add(false);


    }



    // 修改选中的状态
    public void changeState(int position){
        // 多选时
        if(multiChoose == true){
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position);	//直接取反即可
        }

        // 单选时
        else{
            if(lastPosition != -1)
                mImage_bs.setElementAt(false, lastPosition);	//取消上一次的选中状态
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position);	//直接取反即可
            lastPosition = position;		//记录本次选中的位置
        }
        notifyDataSetChanged();		//通知适配器进行更新
    }

}
