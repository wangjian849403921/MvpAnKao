package com.mvpankao.widget.popupwindow;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.ui.activity.ProductActivity;
import com.mvpankao.ui.activity.WarningSearchActivity;
import com.mvpankao.ui.activity.WorkOrderSearchActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * 筛选属性选择的popupwindow
 */
public class FilterPopupWindow extends PopupWindow {
    private View contentView;
    private Context context;
    private View goodsNoView;

    private LinearLayout mLLEdit;
    private EditText mEditQuery;
    private ListView selectionList;
    private TextView filterReset;
    private TextView filterSure;
    private SelectListAdapter adapter;
    private List<SelectTypeName> itemData;
    private boolean multiChoose;        //表示当前适配器是否允许多选

    //是否头edittext
    boolean haveEdit = false;
    /**
     * 商品属性选择的popupwindow
     */
    int type = 0;
    int producttype;

    public FilterPopupWindow(final Activity context, final View mDowmarrow, JSONArray json, boolean have, boolean isMulti, final int type
            , final int index) {
        this.producttype = index;
        multiChoose = isMulti;
        this.type = type;
        this.context = context;
        haveEdit = have;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup_select_details, null);
        mLLEdit = (LinearLayout) contentView.findViewById(R.id.ll_edit);
        mEditQuery = (EditText) contentView.findViewById(R.id.edit_query);
        goodsNoView = contentView.findViewById(R.id.popup_goods_noview);
        selectionList = (ListView) contentView.findViewById(R.id.selection_list);
        filterReset = (TextView) contentView.findViewById(R.id.filter_reset);
        filterSure = (TextView) contentView.findViewById(R.id.filter_sure);

        itemData = new ArrayList<SelectTypeName>();
        adapter = new SelectListAdapter(context, itemData, multiChoose, type, "");
        selectionList.setAdapter(adapter);
        try {
            refreshAttrs(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 重置的点击监听，将所有选项全设为false
        filterReset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        itemData.get(i).getSaleVo().get(j).setChecked(false);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
        // 确定的点击监听，将所有已选中项列出
        filterSure.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {

//                contentView.startAnimation(AnimationUtils.loadAnimation(context,
//                        R.anim.popupwindow_out));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        dismiss();
                    }
                }, 250);

                String id = "";
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                            id += ";" + itemData.get(i).getSaleVo().get(j).getGoodsAndValId();
                        }
                    }
                }

                switch (type) {
                    case 1:

                        if (id.length() > 0) {
                            id = id.substring(1, id.length());
                        }

                        Intent intent = new Intent(context, ProductActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("producttype", producttype);
                        context.startActivity(intent);
                        break;
                    //工单筛选
                    case 2:
                        String statue = "";
                        for (int i = 0; i < itemData.size(); i++) {
                            for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                                if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                                    String sta= itemData.get(i).getSaleVo().get(j).getStatue();
                                    if (!StringUtils.isEmpty(sta)) {
                                        statue += ";" + itemData.get(i).getSaleVo().get(j).getStatue();
                                    }

                                }
                            }
                        }
                        String ids = "";
                        for (int i = 0; i < itemData.size(); i++) {
                            for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                                if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                                    String ID= itemData.get(i).getSaleVo().get(j).getGoodsAndValId();
                                    if (!StringUtils.isEmpty(ID)) {
                                        ids += ";" + itemData.get(i).getSaleVo().get(j).getGoodsAndValId();
                                    }

                                }
                            }
                        }
                        if (!StringUtils.isEmpty(ids) && ids != null) {
                            ids = ids.substring(1, ids.length());


                        }
                        if (!StringUtils.isEmpty(statue) && statue != null) {
                            statue = statue.substring(1, statue.length());

                        }
                        Intent intent2 = new Intent(context, WorkOrderSearchActivity.class);
                        intent2.putExtra("engineeringId",mEditQuery.getText().toString());
                        intent2.putExtra("engineeringTypeIdfk", ids);
                        intent2.putExtra("engineeringStatus", statue);

                        context.startActivity(intent2);

                        break;
                    case 3:
                        String level = "";
                        for (int i = 0; i < itemData.size(); i++) {
                            for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                                if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                                    String lev= itemData.get(i).getSaleVo().get(j).getLevel();
                                    if (!StringUtils.isEmpty(lev)) {
                                        level += ";" + itemData.get(i).getSaleVo().get(j).getLevel();
                                    }

                                }
                            }
                        }
                        String types = "";
                        for (int i = 0; i < itemData.size(); i++) {
                            for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                                if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                                    String typename= itemData.get(i).getSaleVo().get(j).getTypeName();
                                    if (!StringUtils.isEmpty(typename)) {
                                        types += ";" + itemData.get(i).getSaleVo().get(j).getTypeName();
                                    }

                                }
                            }
                        }
                        String statues = "";
                        for (int i = 0; i < itemData.size(); i++) {
                            for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                                if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                                    String sta= itemData.get(i).getSaleVo().get(j).getStatue();
                                    if (!StringUtils.isEmpty(sta)) {
                                        statues += ";" + itemData.get(i).getSaleVo().get(j).getStatue();
                                    }

                                }
                            }
                        }
                        String id2 = "";
                        for (int i = 0; i < itemData.size(); i++) {
                            for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                                if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                                    String ID= itemData.get(i).getSaleVo().get(j).getGoodsAndValId();
                                    if (!StringUtils.isEmpty(ID)) {
                                        id2 += ";" + itemData.get(i).getSaleVo().get(j).getGoodsAndValId();                                    }

                                }
                            }
                        }

                        if (!StringUtils.isEmpty(level) && level != null) {
                            level = level.substring(1, level.length());


                        }
                        if (!StringUtils.isEmpty(types) && types != null) {
                            types = types.substring(1, types.length());


                        }
                        if (!StringUtils.isEmpty(id2) && id2 != null) {
                            id2 = id2.substring(1, id2.length());


                        }
                        if (!StringUtils.isEmpty(statues) && statues != null) {
                            statues = statues.substring(1, statues.length());

                        }

                        Intent intent3 = new Intent(context, WarningSearchActivity.class);

                        intent3.putExtra("statue", statues);
                        intent3.putExtra("level", level);
                        intent3.putExtra("type", id2);
                        context.startActivity(intent3);
                        break;
                }
            }
        });
        setAnimationStyle(R.style.PopupWindowAnimation);

        this.setContentView(contentView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);

        ColorDrawable dw = new ColorDrawable(00000000);
        this.setBackgroundDrawable(dw);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        mDowmarrow.animate().setDuration(500).rotation(180).start();

        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                mDowmarrow.animate().setDuration(500).rotation(0).start();

            }
        });

        this.update();
        this.setListViewHeightBasedOnChildren(selectionList);

        if (haveEdit) {
            mLLEdit.setVisibility(View.VISIBLE);
        } else {
            mLLEdit.setVisibility(View.GONE);
        }
    }

    /**
     * 刷新商品属性
     *
     * @param json
     * @throws JSONException
     */
    public void refreshAttrs(JSONArray json) throws JSONException {
        itemData.clear();
        for (int i = 0; i < json.length(); i++) {
            SelectTypeName saleName = new SelectTypeName();
            JSONObject obj = (JSONObject) json.opt(i);
            saleName.setName(obj.getString("name"));
            List<SelectValue> list = new ArrayList<SelectValue>();
            JSONArray array = obj.getJSONArray("data");
            for (int j = 0; j < array.length(); j++) {
                JSONObject object = array.getJSONObject(j);
                SelectValue vo = new SelectValue();
                if (object.has("product_category_type")) {
                    vo.setGoods(object.getString("product_category_type"));
                }
                if (object.has("product_category_name")) {
                    vo.setValue(object.getString("product_category_name"));
                }
                if (object.has("engineeringStatusName")) {
                    vo.setValue(object.getString("engineeringStatusName"));
                    vo.setStatue(object.getString("engineeringStatus"));
                }
                if (object.has("engineeringTypeName")) {
                    vo.setValue(object.getString("engineeringTypeName"));
                }
                if (object.has("asset_name")) {
                    vo.setAssertName(object.getString("asset_name"));
                    vo.setValue(object.getString("asset_name"));
                }
                if (object.has("id")) {
                    vo.setGoodsAndValId(object.getString("id"));
                }

                if (object.has("alarmParmName")) {
                    vo.setValue(object.getString("alarmParmName"));
                    vo.setTypeName(object.getString("alarmParmName"));
                }
                if (object.has("alarmStatus")) {
                    vo.setStatue(object.getString("alarmStatus"));
                    vo.setValue(object.getString("alarmStatus"));

                }
                if (object.has("alarmLevel")) {
                    vo.setLevel(object.getString("alarmLevel"));
                    vo.setValue(object.getString("alarmLevel"));

                }
                if (!object.has("hahah")) {
                    vo.setChecked(false);
                }
                list.add(vo);
            }
            saleName.setSaleVo(list);
            // 是否展开
            saleName.setNameIsChecked(true);
            itemData.add(saleName);
        }
        adapter.notifyDataSetChanged();
    }

    public class CancelOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    public boolean onKeyDown(Context context, int keyCode, KeyEvent event) {
        this.context = context;
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
        }
        return true;
    }

    public void showFilterPopup(View parent) {
        if (!this.isShowing()) {
//            this.showAsDropDown(parent);
            showAsDropDown(parent, getWidth(), getHeight());

        } else {
            this.dismiss();
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
