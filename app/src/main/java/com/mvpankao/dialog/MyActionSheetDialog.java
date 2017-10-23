package com.mvpankao.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.SPUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.ui.activity.CommitOrderActivity;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.widget.popupwindow.SelectListAdapter;
import com.mvpankao.widget.popupwindow.SelectTypeName;
import com.mvpankao.widget.popupwindow.SelectValue;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import net.lemonsoft.lemonbubble.LemonBubble;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import ren.qinc.numberbutton.NumberButton;

/**
 * 自定义  订购产品
 * author wangjian
 * create by 2016/11/28
 */
public class MyActionSheetDialog implements View.OnClickListener {

    RelativeLayout mDismiss;

    TextView mProductName;
    ImageView mProductImage;
    public static TextView mProductCount;
    TextView mOrder;

    public static NumberButton mNumberButton;
    private Context context;
    private Dialog dialog;
    private Display display;
    ProductsBean.ObjectBean.ListBean mProductOrder;
    //是否预定
    boolean mPredetermined = false;
    //是否是选择产品个数
    boolean mIsSelectCount = false;
    List<ProductsBean.ObjectBean.ListBean> mProductList = new ArrayList<>();
    private ListView selectionList;
    private List<SelectTypeName> itemData;
    private SelectListAdapter adapter;
    JSONArray json;
    String addressid;
    String address, reciver, phone;
    SPUtils mSPUtils;
    public static String ids = "";

    public MyActionSheetDialog(Context context, ProductsBean.ObjectBean.ListBean ProductOrder, boolean Predetermined, boolean IsSelectCount, JSONArray jsonArray, String addressid, String address, String reciver, String phone) {
        this.context = context;
        mSPUtils = new SPUtils(context, "USER_INFO");
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mProductOrder = ProductOrder;
        mPredetermined = Predetermined;
        mIsSelectCount = IsSelectCount;
        this.addressid = addressid;
        this.address = address;
        this.reciver = reciver;
        this.phone = phone;
        json = jsonArray;
    }

    public MyActionSheetDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.bottom_product, null);
        mDismiss = (RelativeLayout) view.findViewById(R.id.dismiss);
        mProductName = (TextView) view.findViewById(R.id.product_name);
        mProductImage = (ImageView) view.findViewById(R.id.productimage);
        mProductCount = (TextView) view.findViewById(R.id.product_count);
        mOrder = (TextView) view.findViewById(R.id.order);
        mNumberButton = (NumberButton) view.findViewById(R.id.number_button);
        selectionList = (ListView) view.findViewById(R.id.selection_list);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());
        itemData = new ArrayList<SelectTypeName>();
        adapter = new SelectListAdapter(context, itemData, false, 999, mProductOrder.getProductId());
        selectionList.setAdapter(adapter);
        try {
            refreshAttrs(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mPredetermined) {
            //加入预订单
            mOrder.setText("加入预订单");
            mOrder.setBackgroundColor(Color.parseColor("#FFAA00"));
        } else {
            //立即订购
            mOrder.setText("立即订购");
            mOrder.setBackgroundColor(Color.parseColor("#FF6600"));
        }

        mDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsSelectCount) {
                    EventBus.getDefault().post(new MyEvent(Constants.SELECTPRODUCTCOUNT_CODE, String.valueOf(mNumberButton.getNumber())));
                }
                dialog.dismiss();
            }
        });
        //按钮点击事件
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(ids)) {
                    if (mPredetermined) {//预定则加入到购物车
                        String count = mNumberButton.getNumber() + "";
                        //预订单的加入到购物车
                        insertToShopCar(count, ids, mProductOrder.getProductId());
                    } else {
                        //立即订购则跳到提交订单
                        Intent intent = new Intent(context, CommitOrderActivity.class);
                        mProductOrder.setProductCount(mNumberButton.getNumber());
                        mProductOrder.setProductStockDetailIdfk(Integer.parseInt(ids));
                        mProductList.add(mProductOrder);
                        intent.putExtra("mProductList", (Serializable) mProductList);
                        intent.putExtra("addressid", addressid);
                        intent.putExtra("address", address);
                        intent.putExtra("reciver", reciver);
                        mSPUtils.putString("shopcar", "2");
                        intent.putExtra("phone", phone);
                        context.startActivity(intent);
                        ids = "";
                        dialog.dismiss();
                    }
                } else {
                    Toast.makeText(context, "请先选择规格", Toast.LENGTH_SHORT).show();
                }
                String str = "";
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                            str = str + itemData.get(i).getSaleVo().get(j).getValue();
                        }
                    }
                }
//                    Toast.makeText(context, str, Toast.LENGTH_SHORT).show();


            }

            /**
             * 加入购物车
             * @param count
             * @param id
             * @param productid
             */
            private void insertToShopCar(String count, String id, String productid) {
                String url = NetUrl.URLHeader + NetUrl.ShopCar.InserttoShopCar;
                Map<String, String> map = new HashMap<>();
                map.put("userId", mSPUtils.getString("userid"));
                map.put("productChooseNum", count);
                map.put("productStockDetailIdfk", id);
                map.put("productIdfk", productid);
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
                                LemonBubble.showRight(context, "加入购物车成功", 1500);
                                dialog.dismiss();
                                ids = "";
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
        mProductCount.setText(mProductOrder.getProductInventory() + "");
        mProductName.setText(mProductOrder.getProductName());
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.product1).error(R.drawable.product1);
        Glide.with(context).load(NetUrl.DOCHeader + mProductOrder.getImage()).apply(options).into(mProductImage);
        int inventory = Integer.parseInt(mProductCount.getText().toString());
        assert mNumberButton != null;


        mNumberButton
                .setInventory(inventory)
                .setCurrentNumber(mProductOrder.getProductCount())
                .setOnWarnListener(new NumberButton.OnWarnListener() {
                    @Override
                    public void onWarningForInventory(int inventory) {
                        Toast.makeText(context, "当前库存:" + inventory, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onWarningForBuyMax(int buyMax) {
                        Toast.makeText(context, "超过最大购买数:" + buyMax, Toast.LENGTH_SHORT).show();

                    }
                });

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        this.setListViewHeightBasedOnChildren(selectionList);

        return this;
    }

    public MyActionSheetDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置库存量
     *
     * @param count
     */
    public static void setCount(String count) {
        mProductCount.setText(count);
        mNumberButton.setInventory(Integer.parseInt(count));
    }

    /**
     * 设置id
     *
     * @param id
     */
    public static void setId(String id) {
        ids = id;
    }

    public MyActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }


    public void show() {

        dialog.show();
    }


    @Override
    public void onClick(View v) {

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
                if (object.has("id")) {
                    vo.setGoodsAndValId(object.getString("id"));
                }
                if (object.has("product_category_level")) {
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
