package com.mvpankao.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.ProductOrder;
import com.mvpankao.utils.UpdateView;
import com.mvpankao.widget.SmoothCheckBox;

import java.util.List;



/**
 * Created by wangjian
 * On  2016/12/28
 */

public class ShopCarAdapter extends BaseAdapter {
    private UpdateView updateViewListener;
    protected static final int KEY_DATA = 0xFFF11133;
    // 填充数据的list
    private List<ProductOrder> list;
    // 上下文
    private Context context;
    // 用来导入布局
    private LayoutInflater inflater = null;
    //    ProductOrder mProductOrder;
    int positions = 0;

    // 构造器
    public ShopCarAdapter(List<ProductOrder> list, Context context) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        positions = position;
//        mProductOrder = list.get(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.shopcar_mid_layout, null);
            holder = new ViewHolder(convertView);
            // 导入布局并赋值给convertview

//            holder.mProductname = (TextView) convertView.findViewById(R.id.productname);
//            holder.mProductcount = (TextView) convertView.findViewById(R.id.productcount);
//            holder.cbItem = (SmoothCheckBox) convertView.findViewById(R.id.cb_Item);
//            holder.etCount = (EditText) convertView.findViewById(R.id.et_Count);
//            holder.tvReduce = (TextView) convertView.findViewById(R.id.tv_Reduce);
//            holder.tvAdd = (TextView) convertView.findViewById(R.id.tv_Add);
//            holder.mNumberButton = (NumberButton) convertView.findViewById(R.id.number_button);

            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }
        String tag = position + ",";
        holder.cbItem.setTag(tag);
        holder.tvReduce.setTag(tag);
        holder.tvAdd.setTag(tag);

        final ViewHolder finalHolder = holder;
        holder.cbItem.setOnClickListener(listener);
        holder.tvReduce.setOnClickListener(listener);

        if (Integer.parseInt(holder.etCount.getText().toString()) < list.get(position).getProductStockDetailSize()) {
            holder.tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Integer.parseInt(finalHolder.etCount.getText().toString())==list.get(positions).getProductStockDetailSize()){
                        Toast.makeText(context, "库存不足", Toast.LENGTH_SHORT).show();
                    }else {
                        //添加商品数量
                        String tag = view.getTag().toString();
                        String[] split;
                        int id = 0;
//                int childId = 0;
                        int allCount = list.get(id).getAllcount();

                        if (tag.contains(",")) {
                            split = tag.split(",");
                            id = Integer.parseInt(split[0]);

                        }
                        String var2 = String.valueOf(list.get(id).getProductCount());
                        list.get(id).setProductCount(Integer.parseInt(addCount(var2)));
//                    if (list.get(id).isChecked()) {
//                        updateViewListener.update(list.get(id).isAllSelect(), allCount);
//                    }

                        notifyDataSetChanged();
                    }
                }
            });
        }
        if (list.get(positions).isChecked()) {
            holder.cbItem.setChecked(true);
        } else {
            holder.cbItem.setChecked(false);
        }

        EditTextWatcher textWatcher = (EditTextWatcher) holder.etCount.getTag(KEY_DATA);
        if (textWatcher != null) {
            holder.etCount.removeTextChangedListener(textWatcher);
        }
        //holder.tvAdd.setText();

        final int count = list.get(positions).getProductCount();
        holder.mProductcount.setText("X" + count);
        holder.mProductname.setText(list.get(positions).getProductName());
        holder.mProductParam.setText(list.get(positions).getProductParam());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.product1).error(R.drawable.product1);
        Glide.with(context).load(NetUrl.DOCHeader+list.get(positions).getImage()).apply(options).into(holder.mProductImage);
        holder.etCount.setText(count + "");
        EditTextWatcher watcher = new EditTextWatcher(list.get(positions));
        holder.etCount.setTag(KEY_DATA, watcher);
        holder.etCount.addTextChangedListener(watcher);

        holder.etCount.setText(list.get(positions).getProductCount() + "");
//        holder.mNumberButton.setCurrentNumber(count);
//        int number = holder.mNumberButton.getNumber();
//        LogUtils.d(number);
////        holder.mNumberButton.
//        holder.mNumberButton.setOnWarnListener(new NumberButton.OnWarnListener() {
//            @Override
//            public void onWarningForInventory(int inventory) {
//
//            }
//
//            @Override
//            public void onWarningForBuyMax(int max) {
//
//            }
//        });

//        holder.mNumberButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
//
//            }
//        });


        return convertView;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SmoothCheckBox checkBox;


            String tag = v.getTag().toString();
            String[] split;
            int groupId = 0;
            int childId = 0;
            int childSize = 0;
            int groupPosition = 0;
            if (tag.contains(",")) {
                split = tag.split(",");
                groupId = Integer.parseInt(split[0]);

            } else {
                groupPosition = Integer.parseInt(tag);

            }
            int allCount = list.get(groupId).getAllcount();
            switch (v.getId()) {
                //单个子项item被点击
                case R.id.cb_Item:
                    checkBox = (SmoothCheckBox) v;
                    int n = 0;
                    list.get(groupId).setChecked(!checkBox.isChecked());

//                    //遍历item所有数据，统计被选中的item数量
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isChecked()) {
                            n++;
                        }
                    }
                    if (n == list.size()) {
                        list.get(groupId).setAllSelect(true);
                    } else {
                        list.get(groupId).setAllSelect(false);
                    }
                    if (!checkBox.isChecked()) {
                        allCount++;
                    } else {
                        allCount--;
                    }

                    list.get(groupId).setAllcount(allCount);
                    notifyDataSetChanged();
                    updateViewListener.update(list.get(groupId).isAllSelect(), allCount);

                    break;
                case R.id.tv_Reduce:
                    //减少商品数量
                    String var1 = String.valueOf(list.get(groupId).getProductCount());
                    if (Integer.valueOf(var1) > 1) {
                        list.get(groupId).setProductCount(Integer.parseInt(reduceCount(var1)));
//                        if (list.get(groupId).isChecked()) {
//                            updateViewListener.update(list.get(groupId).isAllSelect(), allCount);
//                        }
                        notifyDataSetChanged();


                    }

                    break;
            }
        }
    };


    static class ViewHolder {
        TextView mProductname;
        TextView mProductParam;
        ImageView mProductImage;
        TextView mProductcount;
        //        NumberButton mNumberButton;
        public SmoothCheckBox cbItem;
        EditText etCount;
        TextView tvReduce;
        TextView tvAdd;

        ViewHolder(View view) {
            mProductname = (TextView) view.findViewById(R.id.productname);
            mProductParam = (TextView) view.findViewById(R.id.product_param);
            mProductImage = (ImageView) view.findViewById(R.id.productimage);
            mProductcount = (TextView) view.findViewById(R.id.productcount);
            cbItem = (SmoothCheckBox) view.findViewById(R.id.cb_Item);
            etCount = (EditText) view.findViewById(R.id.et_Count);
            tvReduce = (TextView) view.findViewById(R.id.tv_Reduce);
            tvAdd = (TextView) view.findViewById(R.id.tv_Add);

        }
    }

    private String addCount(String var2) {
        Integer inte = Integer.valueOf(var2);
        inte++;
        return inte + "";
    }

    private String reduceCount(String var) {
        Integer integer = Integer.valueOf(var);
        if (integer > 1) {
            integer--;
        }
        return integer + "";
    }

    /**
     * EditText内容改变的监听
     */
    class EditTextWatcher implements TextWatcher {

        private ProductOrder Gooddetail;

        public EditTextWatcher(ProductOrder item) {
            this.Gooddetail = item;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s.toString().trim())) {
                String textNum = s.toString().trim();
                Gooddetail.setProductCount(Integer.parseInt(textNum));
            }
        }
    }

    public void setChangedListener(UpdateView listener) {
        if (updateViewListener == null) {
            this.updateViewListener = listener;
        }
    }

}
