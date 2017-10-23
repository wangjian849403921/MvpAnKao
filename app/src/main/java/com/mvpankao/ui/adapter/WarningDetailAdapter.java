package com.mvpankao.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.TimeUtils;
import com.mvpankao.R;
import com.mvpankao.model.bean.WarningDetailBean;

import java.util.List;

/**
 * 报警详情 时间轴
 * Created by wangjian
 * On  2016/11/22
 */

public class WarningDetailAdapter extends BaseAdapter {
    private Context context;
    private List<WarningDetailBean.ObjectBean.TimePointBean> list;
    private LayoutInflater inflater;

    public WarningDetailAdapter(Context context, List<WarningDetailBean.ObjectBean.TimePointBean> list) {
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
            convertView = inflater.inflate(R.layout.warningdetail_item, null);
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

        String starttime = TimeUtils.milliseconds2String(list.get(position).getData());

        String statue = list.get(position).getName();


        if (position==0){
            viewHolder.view0.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.view0.setVisibility(View.VISIBLE);

        }
        switch (statue) {

            case "0":
                viewHolder.title.setTextColor(Color.parseColor("#999999"));
                viewHolder.image.setImageResource(R.drawable.warningshap1);
                viewHolder.title.setText("发生警报");
                viewHolder.time.setText(starttime);
                viewHolder.view.setVisibility(View.VISIBLE);

                break;
            case "2":
                viewHolder.title.setTextColor(Color.parseColor("#999999"));
                viewHolder.image.setImageResource(R.drawable.shape2);

                viewHolder.time.setVisibility(View.VISIBLE);
                viewHolder.view.setVisibility(View.VISIBLE);

                break;
            case "1":

                viewHolder.title.setTextColor(Color.parseColor("#999999"));
                viewHolder.image.setImageResource(R.drawable.warningshap2);
                viewHolder.title.setText("报警解除，数据恢复正常");
                viewHolder.time.setText(starttime);
                viewHolder.view.setVisibility(View.VISIBLE);
                break;

        }

        if (position == list.size() - 1) {
            viewHolder.view.setVisibility(View.INVISIBLE);
        }

        return convertView;

    }

    static class TimeLineHolder {
        private TextView title, time, statue;
        private ImageView image;
        private View view0, view;
    }
}
