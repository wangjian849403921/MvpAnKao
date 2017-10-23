package com.mvpankao.ui.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.AssetBean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/9/9
 */
public class AssetAdapter extends SimpleAdapter<AssetBean.ObjectBean> {


    public AssetAdapter(Context context, List<AssetBean.ObjectBean> mDatas) {
        super(context, mDatas, R.layout.asset_item);
    }

    @Override
    public void bindData(BaseViewHolder viewHolder, AssetBean.ObjectBean assetBean, int position) {


        viewHolder.getTextView(R.id.asset_title).setText(assetBean.getAssetName());
        RequestOptions options = new RequestOptions();
        options.error(R.drawable.icon_error);
        Glide.with(mContext).load(NetUrl.DOCHeader+assetBean.getAssetIcon()).apply(options).into(viewHolder.getImageView(R.id.asset_image));
//        viewHolder.getImageView(R.id.asset_image).setImageURI(Uri.parse(assetBean.getAssetImage()));

    }
}
