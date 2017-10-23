package com.mvpankao.widget.popupwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.mvpankao.R;
import com.mvpankao.dialog.MyActionSheetDialog;
import com.mvpankao.http.NetUrl;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * 属性listview的适配器
 */
public class SelectListAdapter extends BaseAdapter {

    private Context context;
    private List<SelectTypeName> data;
    private boolean multiChoose;        //表示当前适配器是否允许多选
    int type = 0;
    String id = "";

    public SelectListAdapter(Context context, List<SelectTypeName> data, boolean isMulti, int type, String id) {

        this.context = context;
        this.data = data;
        multiChoose = isMulti;
        this.type = type;
        this.id = id;

    }

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
            v = View.inflate(context, R.layout.item_select_value_list, null);
            myView.name = (TextView) v.findViewById(R.id.attr_list_name);
//            myView.img = (ImageView) v.findViewById(R.id.attr_list_img);
            myView.grid = (GridView) v.findViewById(R.id.attr_list_grid);
            myView.grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
            v.setTag(myView);
        } else {
            myView = (MyView) v.getTag();
        }
        myView.name.setText(data.get(position).getName());
        final SelectGridAdapter adapter = new SelectGridAdapter(context, data.size() - 1, multiChoose);
        myView.grid.setAdapter(adapter);
        adapter.notifyDataSetChanged(data.get(position).isNameIsChecked(), data.get(position).getSaleVo());
//        myView.img.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (data.get(position).isNameIsChecked()) {
//                    ((ImageView) v).setImageResource(R.drawable.sort_common_up);
//                } else {
//                    ((ImageView) v).setImageResource(R.drawable.sort_common_down);
//                }
//                adapter.notifyDataSetChanged(data.get(position).isNameIsChecked(), data.get(position).getSaleVo());
//                data.get(position).setNameIsChecked(!data.get(position).isNameIsChecked());
//            }
//        });

        myView.grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //设置当前选中的位置的状态为非。
                data.get(position).getSaleVo().get(arg2).setChecked(!data.get(position).getSaleVo().get(arg2).isChecked());


//                        Toast.makeText(context, id.substring(1,id.length()), Toast.LENGTH_SHORT).show();
                if (!multiChoose) {


                    for (int i = 0; i < data.get(position).getSaleVo().size(); i++) {
                        //跳过已设置的选中的位置的状态
                        if (i == arg2) {
                            continue;
                        }
                        data.get(position).getSaleVo().get(i).setChecked(false);
                    }
                }
//                if (!data.get(position).isNameIsChecked()) {
//                    myView.img.setImageResource(R.drawable.sort_common_up);
//                } else {
//                    myView.img.setImageResource(R.drawable.sort_common_down);
//                }

                adapter.notifyDataSetChanged(data.get(position).isNameIsChecked(), data.get(position).getSaleVo());
                adapter.changeState(arg2);
                if (type == 999) {
                    String str = "";
                    for (int i = 0; i < data.size(); i++) {
                        for (int j = 0; j < data.get(i).getSaleVo().size(); j++) {
                            if (data.get(i).getSaleVo().get(j).isChecked()) {
                                str += data.get(i).getSaleVo().get(j).getValue();
                            }
                        }
                    }

                    if (str.contains("KV") & str.contains("m")) {
                        initData(id, str, position);
                    }

                }
            }

            private void initData(String id, String str, final int position) {
                String VLevel = "";
                String Spec = "";
                if (str.contains("KV") & !str.contains("m")) {
                    VLevel = "";
                    Spec = "";
                    int i = str.indexOf("KV");
                    VLevel = str.substring(0, i + 2);

                }
                if (str.contains("m") & !str.contains("KV")) {
                    VLevel = "";
                    Spec = "";
                    int i = str.indexOf("m");
                    Spec = str.substring(0, i + 2);

                }
                if (str.contains("KV") & str.contains("m")) {
                    VLevel = "";
                    Spec = "";

                    int i = str.indexOf("KV");
                    int j = str.indexOf("m");
                    if (i < j) {
                        VLevel = str.substring(0, i + 2);

                        Spec = str.substring(i + 2, j + 3);
                    } else {
                        Spec = str.substring(0, i + 3);

                        VLevel = str.substring(i + 3, j + 2);
                    }

                }


                String url = NetUrl.URLHeader + NetUrl.Product.TypeSelect3;
                Map<String, String> map = new HashMap<>();
                map.put("productId", id);
                map.put("VLevel", VLevel);
                map.put("Spec", Spec);
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                        .readTimeout(10000L, TimeUnit.MILLISECONDS)
                        //其他配置
                        .build();

                MyOkHttp mMyOkhttp = new MyOkHttp(okHttpClient);
                mMyOkhttp.post().params(map).url(url).tag(this).enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {

                        try {

                            if (response.getString("code").equals("200")) {
                                JSONObject obj = response.getJSONObject("object");
                                String productInventory = obj.getString("productStockDetailSize");
                                String id = obj.getString("id");
                                //更新库存量  和设置ID
                                MyActionSheetDialog.setId(id);
                                MyActionSheetDialog.setCount(productInventory);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

            }
        });
        return v;
    }

    static class MyView {
        public TextView name;
        //        public ImageView img;
        public GridView grid;
    }

}
