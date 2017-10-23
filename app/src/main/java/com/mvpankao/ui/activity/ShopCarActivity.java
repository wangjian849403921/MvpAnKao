package com.mvpankao.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.ActionSheetDialog;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.ProductOrder;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.model.bean.ShopCarBean;
import com.mvpankao.ui.adapter.ShopCarAdapter;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.utils.UpdateView;
import com.mvpankao.widget.EmptyViewHelper;
import com.mvpankao.widget.SmoothCheckBox;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by wangjian
 * On  2016/12/28
 */

public class ShopCarActivity extends BaseActivity implements UpdateView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.righttext)
    TextView mEdit;
    @BindView(R.id.rl_edit)
    RelativeLayout mRlEdit;
    @BindView(R.id.ShopCarList)
    ListView mShopCarList;
    @BindView(R.id.cb_SelectAll)
    SmoothCheckBox mSelectAll;
    @BindView(R.id.ensure_order)
    TextView mEnsureOrder;
    @BindView(R.id.order_delete)
    TextView mOrderDelete;
    @BindView(R.id.id_rl_foot)
    LinearLayout mIdRlFoot;
    private ShopCarAdapter mShopCarAdapter;
    private List<ProductOrder> list = new ArrayList<>();
    ProductOrder mProductOrder;
    int flag = 0;
    private int checkNum; // 记录选中的条目数量
    int counts = 0;
    List<ProductsBean.ObjectBean.ListBean> mProductList = new ArrayList<>();
    ProductsBean.ObjectBean.ListBean mProductOrders;

    @Override
    protected int getLayout() {
        return R.layout.ac_shopcar;
    }

    @Override
    protected void initEventAndData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });

    }

    @Override
    protected void initView() {
        mIdRlFoot.setVisibility(View.GONE);
        mRlEdit.setVisibility(View.GONE);
        mShopCarList.setEmptyView(View.inflate(mContext, R.layout.empty_view, null));
        //用于列表滑动时，EditText清除焦点，收起软键盘
        mShopCarList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity
                            .INPUT_METHOD_SERVICE);
                    View focusView = getCurrentFocus();
                    if (focusView != null) {
                        inputMethodManager.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager
                                .HIDE_NOT_ALWAYS);
                        focusView.clearFocus();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                initData();
//            }
//        });
//        // 全选按钮的回调接口
//        mSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//
//                    for (int i = 0; i < list.size(); i++) {
//                        list.get(i).setChecked(true);
//                    }
//                    checkNum = list.size();
//
//                    mShopCarAdapter.notifyDataSetChanged();
//
//                } else {
//
//                    for (int i = 0; i < list.size(); i++) {
//                        list.get(i).setChecked(false);
//                        checkNum--;// 数量减1
//                    }
//                    mShopCarAdapter.notifyDataSetChanged();
//
//                }
//            }
//        });

//
//        // 绑定listView的监听器
//        mShopCarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View view, int position,
//                                    long arg3) {
//                ProductOrder order = list.get(position);
//                ViewHolder holder = (ViewHolder) view.getTag();
//                holder.cbItem.toggle();
//                if (order.isChecked()) {
//                    order.setChecked(false);
//                    holder.cbItem.setChecked(false);
//                    checkNum++;
//                } else {
//                    order.setChecked(true);
//                    holder.cbItem.setChecked(true);
//                    checkNum--;
//                }
//
//
//            }
//        });

    }


    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        String url = NetUrl.URLHeader + NetUrl.ShopCar.ShopCarList;
        Map<String, String> map = new HashMap<>();
        map.put("userId", mSPUtils.getString("userid"));
        LogUtils.d(url);
        LogUtils.d(map);
//        MyOkHttp.get().post(url, map, new JsonResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, JSONObject response) {
//                        try {
//                            LogUtils.d(response);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, String error_msg) {
//
//                    }
//                }
//
//        );
        mMyOkhttp.post().url(url).params(map).tag(mContext)
                .enqueue(new GsonResponseHandler<ShopCarBean>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        mIdRlFoot.setVisibility(View.GONE);
                        mRlEdit.setVisibility(View.GONE);

                    }

                    @Override
                    public void onSuccess(int statusCode, ShopCarBean response) {
                        LogUtils.d(response.getCode());
                        if (response.getCode() == 200) {
                            list.clear();
                            if (response.getObject().size() > 0) {
                                mIdRlFoot.setVisibility(View.VISIBLE);
                                mRlEdit.setVisibility(View.VISIBLE);

                                for (int i = 0; i < response.getObject().size(); i++) {
                                    mProductOrder = new ProductOrder();
                                    mProductOrder.setProductCount(response.getObject().get(i).getProductChooseNum());
                                    mProductOrder.setImage(response.getObject().get(i).getProductIcon());
                                    mProductOrder.setShopCarid(response.getObject().get(i).getId());
                                    mProductOrder.setProductId(response.getObject().get(i).getProductIdfk());
                                    mProductOrder.setProductStockDetailIdfk(response.getObject().get(i).getProductStockDetailIdfk());
                                    mProductOrder.setProductStockDetailSize(response.getObject().get(i).getProductStockDetailSize());
                                    mProductOrder.setProductParam(response.getObject().get(i).getProductParmv0() + " " + response.getObject().get(i).getProductParmv1());
//                            mProductOrder.setProductParmv0(response.getObject().get(i).getProductParmv0());
//                            mProductOrder.setProductParmv1(response.getObject().get(i).getProductParmv1());
                                    mProductOrder.setProductName(response.getObject().get(i).getProductName());

                                    list.add(mProductOrder);

                                }
                                EventBus.getDefault().post(new MyEvent(Constants.SHOPCARLIST_CODE));
                            } else {
                                mIdRlFoot.setVisibility(View.GONE);
                                mRlEdit.setVisibility(View.GONE);
                                EventBus.getDefault().post(new MyEvent(Constants.SHOPCARLIST_CODE));

                            }


                        } else {
                            mIdRlFoot.setVisibility(View.GONE);
                            mRlEdit.setVisibility(View.GONE);

                        }
                    }
                });

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.SHOPCARLIST_CODE:
               new EmptyViewHelper(mShopCarList, "空空如也!");

                mShopCarAdapter = new ShopCarAdapter(list, mContext);
                mShopCarAdapter.setChangedListener(this);
                // 绑定Adapter
                mShopCarList.setAdapter(mShopCarAdapter);
                break;

            case Constants.SHOPCARUPDATE_CODE:
                list.clear();

                initData();
                mShopCarAdapter.notifyDataSetChanged();
                break;
        }
    }

    @OnClick({R.id.rl_back, R.id.rl_edit, R.id.ensure_order, R.id.order_delete, R.id.cb_SelectAll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_SelectAll:
                selectAll();

                break;
            case R.id.rl_back:
                finish();

                break;
            case R.id.rl_edit:
                if (flag == 0) {
                    mEdit.setText("完成");
                    mEnsureOrder.setVisibility(View.GONE);
                    mOrderDelete.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    mEdit.setText("编辑");
                    mEnsureOrder.setVisibility(View.VISIBLE);
                    mOrderDelete.setVisibility(View.GONE);
                    flag = 0;
                }


                break;
            case R.id.ensure_order:
                if (counts != 0) {
                    new ActionSheetDialog(this)
                            .builder()
                            .setTitle("确认要提交订单吗？")
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .addSheetItem("确定", ActionSheetDialog.SheetItemColor.Red,
                                    new ActionSheetDialog.OnSheetItemClickListener() {
                                        @Override
                                        public void onClick(int which) {
                                            mProductList.clear();
                                            String id = "";
                                            String count = "";
                                            Iterator<ProductOrder> iterator = list.iterator();
                                            while (iterator.hasNext()) {
                                                ProductOrder temp = iterator.next();

                                                if (temp.isChecked()) {

//                                                    iterator.remove();

                                                    id += ";" + temp.getProductId();
                                                    count += ";" + temp.getProductCount();
                                                    mProductOrders = new ProductsBean.ObjectBean.ListBean();
                                                    mProductOrders.setProductCount(temp.getProductCount());
                                                    mProductOrders.setShopCarid(temp.getShopCarid());
                                                    mProductOrders.setProductName(temp.getProductName());
                                                    mProductOrders.setImage(temp.getImage());
                                                    mProductOrders.setProductId(temp.getProductId());
                                                    mProductOrders.setProductStockDetailIdfk(temp.getProductStockDetailIdfk());
                                                    mProductOrders.setProductParam(temp.getProductParam());
                                                    mProductList.add(mProductOrders);

                                                }
                                            }
                                            Intent intent = new Intent(mContext, CommitOrderActivity.class);

                                            intent.putExtra("mProductList", (Serializable) mProductList);
                                            intent.putExtra("productStockDetailSize", id);
                                            intent.putExtra("address", mSPUtils.getString("defaultaddress"));
                                            intent.putExtra("reciver", mSPUtils.getString("reciver"));
                                            intent.putExtra("phone", mSPUtils.getString("phone"));
                                            mSPUtils.putString("shopcar", "1");
                                            startActivity(intent);
                                            finish();

//                                            mShopCarAdapter.notifyDataSetChanged();
                                            LogUtils.d("count：" + count.substring(1, count.length()));

//
//                                            if (list.size() == 0) {
//                                                new Handler().postDelayed(new Runnable() {
//                                                    @Override
//                                                    public void run() {
//                                                        finish();
//                                                    }
//                                                }, 500);
//
//                                            }

                                        }
                                    }).show();
                } else {
                    new AlertDialog(mContext).builder().setTitle("提示").setMsg("亲，你尚未选择产品哦！")
                            .setPositiveButton("好", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                }
                            }).setCancelable(false).show();
                }


                break;
            case R.id.order_delete:
                if (list.size() != 0) {
                    if (counts != 0) {
                        new ActionSheetDialog(this)
                                .builder()
                                .setTitle("确认要删除产品吗？")
                                .setCancelable(false)
                                .setCanceledOnTouchOutside(false)
                                .addSheetItem("确定", ActionSheetDialog.SheetItemColor.Red,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {

                                                String id = "";
                                                final Iterator<ProductOrder> iterator = list.iterator();
                                                while (iterator.hasNext()) {
                                                    ProductOrder temp = iterator.next();

                                                    if (temp.isChecked()) {

                                                        id += ";" + temp.getShopCarid();

                                                        iterator.remove();
                                                        mShopCarAdapter.notifyDataSetChanged();

                                                    }

                                                }
                                                if (!StringUtils.isEmpty(id)) {
                                                    deleteProduct(id, iterator);
                                                }

                                                counts = 0;
//                                                LogUtils.d("id：" + id.substring(1, id.length()));


                                                if (list.size() == 0) {
                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            finish();
                                                        }
                                                    }, 500);

                                                }

                                            }

                                            private void deleteProduct(String id, final Iterator<ProductOrder> iterator) {
                                                String url = NetUrl.URLHeader + NetUrl.ShopCar.DeleteShopCarProduct;
                                                Map<String, String> map = new HashMap<>();
                                                map.put("shoppingCartIdList", id.substring(1, id.length()));
                                                map.put("userId", mSPUtils.getString("userid"));

                                                LogUtils.d(map);
                                                mMyOkhttp.post().url(url).params(map).tag(mContext)
                                                        .enqueue(new JsonResponseHandler() {
                                                            @Override
                                                            public void onSuccess(int statusCode, JSONObject response) {
                                                                try {
                                                                    LogUtils.d(response);
//                                                            LogUtils.d(iterator);
                                                                    if (response.getString("code").equals("200")) {


                                                                    } else {

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
                                        }).show();


                    } else {
                        new AlertDialog(mContext).builder().setTitle("提示").setMsg("亲，你尚未选择产品哦！")
                                .setPositiveButton("好", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                    }
                                }).setCancelable(false).show();

                    }
                }

                break;
        }
    }

    /**
     * 设置全选
     */

    private void selectAll() {

        int allCount = mProductOrder.getAllcount();
        if (!mSelectAll.isChecked()) {
            mProductOrder.setAllSelect(true);
            allCount = list.size();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(true);

                if (!list.get(i).isChecked()) {

                    list.get(i).setChecked(true);
                }

            }
        } else {
            mProductOrder.setAllSelect(false);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setChecked(false);
                allCount = 0;

            }
        }

        mProductOrder.setAllcount(allCount);
        update(mProductOrder.isAllSelect(), allCount);
        mShopCarAdapter.notifyDataSetChanged();

    }

    @Override
    public void update(boolean isAllSelected, int count) {
        mSelectAll.setChecked(isAllSelected);
        counts = count;
    }
}
