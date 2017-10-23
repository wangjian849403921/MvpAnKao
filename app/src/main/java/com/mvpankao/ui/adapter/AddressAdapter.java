package com.mvpankao.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseViewHolder;
import com.mvpankao.base.SimpleAdapter;
import com.mvpankao.model.bean.Address;
import com.mvpankao.ui.activity.EditAddressActivity;

import java.util.List;



/**
 * Created by wangjian
 * On  2016/9/9
 */
public class AddressAdapter extends SimpleAdapter<Address.ObjectBean> {
    String username = "";
    String phone = "";
    String addressdetail = "";
    String provincecityarea = "";
    boolean isDefault = false;

    public AddressAdapter(Context context, List<Address.ObjectBean> addressBeen) {
        super(context, addressBeen, R.layout.address_item);

        mDatas = addressBeen;

    }

    // 选中当前选项时，让其他选项不被选中
    public void select(int position) {
        if (mDatas.get(position).getIsdefault() != 0) {
            mDatas.get(position).setIsdefault(1);
            for (int i = 0; i < mDatas.size(); i++) {
                if (i != position) {
                    mDatas.get(i).setIsdefault(0);
                }
            }
        }

        notifyDataSetChanged();
    }


    @Override
    public void bindData(BaseViewHolder viewHolder, final Address.ObjectBean addressBean, final int position) {
        if (addressBean.getIsdefault() == 0) {
            isDefault = false;
        } else {
            isDefault = true;

        }
        username = mDatas.get(0).getReceiveName();
        phone = mDatas.get(0).getReceivePhone();
        provincecityarea = mDatas.get(0).getProvince() + mDatas.get(0).getCitys() + mDatas.get(0).getAreas();
        addressdetail = mDatas.get(0).getReceiveAddress();
        final String phonenum = addressBean.getReceivePhone();

        viewHolder.getTextView(R.id.UserName).setText(addressBean.getReceiveName());

        if (!TextUtils.isEmpty(phonenum) && phonenum.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phonenum.length(); i++) {
                char c = phonenum.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }

            viewHolder.getTextView(R.id.PhoneNum).setText(sb.toString());
        }
        if (StringUtils.isEmpty(addressBean.getReceiveAddress())) {
            addressdetail = "";
        } else {
            addressdetail = addressBean.getReceiveAddress();
        }
        viewHolder.getTextView(R.id.Address).setText(addressBean.getProvince() + addressBean.getCitys() + addressBean.getAreas() + addressdetail);
        viewHolder.getRelativeLayout(R.id.AddressEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, EditAddressActivity.class);
                intent.putExtra("UserName", addressBean.getReceiveName());

                intent.putExtra("PhoneNum", addressBean.getReceivePhone());
                intent.putExtra("Province", addressBean.getProvince());
                intent.putExtra("City", addressBean.getCitys());
                intent.putExtra("Area", addressBean.getAreas());
                intent.putExtra("AddressDetail", addressBean.getReceiveAddress());
                intent.putExtra("id", addressBean.getId());

                if (addressBean.getIsdefault() == 0) {
                    intent.putExtra("isDefault", false);
                } else {
                    intent.putExtra("isDefault", true);

                }

                mContext.startActivity(intent);


            }
        });

//        viewHolder.getRelativeLayout(R.id.rl_AddressCheck).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                select(position);
//
//                if (mDatas.get(position).isDefaultAddress()) {
//                    //将设置的默认地址放到第一位
//                    mDatas.get(0).setAddressDetail(mDatas.get(position).getAddressDetail());
//                    mDatas.get(0).setProvinceCityArea(mDatas.get(position).getProvinceCityArea());
//                    mDatas.get(0).setPhoneNum(mDatas.get(position).getPhoneNum());
//                    mDatas.get(0).setUserName(mDatas.get(position).getUserName());
//                    mDatas.get(0).setDefaultAddress(true);
//
//                    mDatas.get(position).setAddressDetail(addressdetail);
//                    mDatas.get(position).setProvinceCityArea(provincecityarea);
//                    mDatas.get(position).setPhoneNum(phone);
//                    mDatas.get(position).setUserName(username);
//                    mDatas.get(position).setDefaultAddress(false);
//
//
//                    notifyDataSetChanged();
//                }
////                if (position != 0) {
////                    mDatas.get(0).setAddressDetail(mDatas.get(position).getAddressDetail());
////                    mDatas.get(0).setProvinceCityArea(mDatas.get(position).getProvinceCityArea());
////                    mDatas.get(0).setPhoneNum(mDatas.get(position).getPhoneNum());
////                    mDatas.get(0).setUserName(mDatas.get(position).getUserName());
////                    mDatas.get(0).setDefaultAddress(mDatas.get(position).isDefaultAddress());
////
////                    mDatas.get(position).setAddressDetail(mDatas.get(0).getAddressDetail());
////                    mDatas.get(position).setProvinceCityArea(mDatas.get(0).getProvinceCityArea());
////                    mDatas.get(position).setPhoneNum(mDatas.get(0).getPhoneNum());
////                    mDatas.get(position).setUserName(mDatas.get(0).getUserName());
////                    mDatas.get(position).setDefaultAddress(mDatas.get(0).isDefaultAddress());
////
////
////                    notifyDataSetChanged();
////                }
//
//            }
//        });
        viewHolder.getRadioButton(R.id.AddressCheck).setClickable(false);
        viewHolder.getRadioButton(R.id.AddressCheck).setChecked(isDefault);

    }
}
