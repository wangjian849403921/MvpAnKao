package com.mvpankao.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.blankj.utilcode.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.entity.LocalMedia;
import com.mvpankao.R;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/20
 */

public class PictrueAdapter extends BaseAdapter {
    Context mContext;
    private LayoutInflater inflater;
    List<LocalMedia> list;

    public PictrueAdapter(Context context, List<LocalMedia> list) {
        mContext = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.picture_item, null);
            viewHolder = new ViewHolder();


            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (!StringUtils.isEmpty(list.get(position).getPath())) {
            Glide.with(mContext).load(list.get(position).getPath()).into(viewHolder.image);
        }
//        viewHolder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (((ImageView)v).getDrawable()!=null) {
//                    Intent intent = new Intent(mContext, PictureDetailActivity.class);
//                    intent.putExtra("url", list.get(position).getPath());
//
//                    mContext.startActivity(intent);
//                }
//            }
//        });
        return convertView;
    }

    static class ViewHolder {

        private ImageView image;

    }
}
