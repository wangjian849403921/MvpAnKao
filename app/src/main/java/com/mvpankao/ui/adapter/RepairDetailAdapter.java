package com.mvpankao.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.model.bean.RepairDetailBean;

import java.util.List;

/**
 * 订单详情 时间轴
 * Created by wangjian
 * On  2016/11/22
 */

public class RepairDetailAdapter extends BaseAdapter {
    private Context context;
    private List<RepairDetailBean.ObjectBean.RepailogListBean> list;
    private LayoutInflater inflater;

    public RepairDetailAdapter(Context context, List<RepairDetailBean.ObjectBean.RepailogListBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        TimeLineHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.repairdetail_item, null);
            viewHolder = new TimeLineHolder();

            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.view0 = convertView.findViewById(R.id.view_0);
            viewHolder.view = convertView.findViewById(R.id.view_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TimeLineHolder) convertView.getTag();
        }

        String titleStr = list.get(position).getLog();
        String time = TimeUtils.milliseconds2String(list.get(position).getTime());


//        switch (statue) {
//
//            case "1":
//                viewHolder.title.setTextColor(Color.parseColor("#999999"));
//                viewHolder.image.setImageResource(R.drawable.completeshape);
//
//                viewHolder.time.setVisibility(View.VISIBLE);
//                viewHolder.view.setVisibility(View.VISIBLE);
//
//                break;
//            case "2":
//                viewHolder.title.setTextColor(Color.parseColor("#999999"));
//                viewHolder.image.setImageResource(R.drawable.shape2);
//
//                viewHolder.time.setVisibility(View.VISIBLE);
//                viewHolder.view.setVisibility(View.VISIBLE);
//
//                break;
//            case "3":
//
//                viewHolder.title.setTextColor(Color.parseColor("#999999"));
//                viewHolder.image.setImageResource(R.drawable.shape2);
//                viewHolder.time.setVisibility(View.VISIBLE);
//                viewHolder.view.setVisibility(View.VISIBLE);
//                break;
//
//        }

        if (position == 0) {
            if (!StringUtils.isEmpty(titleStr)){
                viewHolder.title.setText(titleStr);
            }
            if (!StringUtils.isEmpty(time)){
                viewHolder.time.setText(time);
            }
            viewHolder.title.setTextColor(Color.parseColor("#999999"));
            viewHolder.image.setImageResource(R.drawable.completeshape);

            viewHolder.time.setVisibility(View.VISIBLE);

        }else{
            if (!StringUtils.isEmpty(titleStr)){
                viewHolder.title.setText(titleStr);
            }
            if (!StringUtils.isEmpty(time)){
                viewHolder.time.setText(time);
            }
            viewHolder.title.setTextColor(Color.parseColor("#999999"));
            viewHolder.image.setImageResource(R.drawable.shape2);

            viewHolder.time.setVisibility(View.VISIBLE);
        }

        viewHolder.title.setText(titleStr);
        viewHolder.time.setText(time);
        if (position == list.size() - 1) {
            viewHolder.view.setVisibility(View.INVISIBLE);
        }
        if (position == 0) {
            viewHolder.view0.setVisibility(View.INVISIBLE);
        }
        return convertView;

    }

    static class TimeLineHolder {
        private TextView title, time, statue;
        private ImageView image;
        private View view0, view;
    }
}
