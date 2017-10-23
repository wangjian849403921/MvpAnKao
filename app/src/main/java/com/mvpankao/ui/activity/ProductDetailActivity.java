package com.mvpankao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.R;
import com.mvpankao.base.BaseActivity;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.dialog.MyActionSheetDialog;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.ProductsBean;
import com.mvpankao.model.bean.SliderImage;
import com.mvpankao.presenter.ProductsPresenter;
import com.mvpankao.utils.CallPhoneUtils;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.ProductDetailView;
import com.mvpankao.widget.CircleProgressView;
import com.mvpankao.widget.GlideImageLoader;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mvpankao.utils.Constants.ProductDetail_CODE;


/**
 * Created by wangjian
 * On  2016/12/1
 */

public class ProductDetailActivity extends BaseActivity implements ProductDetailView {
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.rl_Layout)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.Ordering)
    TextView mOrdering;
    @BindView(R.id.order_rightaway)
    TextView mOrderRightaway;
    @BindView(R.id.circle_progress)
    CircleProgressView mCircleProgressView;
    List<SliderImage> list = new ArrayList<>();
    List<String> listimage = new ArrayList<>();
    List<String> iconlist = null;
    SliderImage sliderImage;
    //    int[] url = {R.drawable.product1, R.drawable.product1};
    @BindView(R.id.ProductName)
    TextView mProductName;
    @BindView(R.id.ProductCount)
    TextView mProductInventory;
    @BindView(R.id.ProductEnglishName)
    TextView mProductEnglishName;
    @BindView(R.id.SelectCount)
    TextView mSelectCount;
    @BindView(R.id.SelectAddress)
    TextView mSelectAddress;
    @BindView(R.id.rl_SelectToWhere)
    RelativeLayout mRlSelectToWhere;
    @BindView(R.id.note)
    TextView mNote;
    ProductsBean.ObjectBean.ListBean mProducts=new ProductsBean.ObjectBean.ListBean();

    private static int mRequestCode = 0x005645;
    @BindView(R.id.ShopCar)
    RelativeLayout mShopCar;
    @BindView(R.id.contact_Customer_service)
    RelativeLayout mContactCustomerService;
    JSONArray obj = null;
    String note = "";
    String address, addressid, reciver, phone;

    private ProductsPresenter ProductDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayout() {
        return R.layout.ac_productdetail;
    }

    @Override
    protected void initView() {
        mTitle.setText("产品详情");
        showProgress();


    }

    private void getData() {
        for (int i = 0; i < 2; i++) {
            sliderImage = new SliderImage();
            if (i % 2 == 0) {
                sliderImage.setImage("http://www.ankura.com.cn/imageRepository/d4a6789a-e342-4336-b48f-5eaa9dda42ef.png");
                sliderImage.setInfo("hahah");
                sliderImage.setId("1");
            }
            if (i % 2 == 1) {
                sliderImage.setImage("http://www.ankura.com.cn/imageRepository/732776ba-752a-446f-b0df-5e8772f4290d.jpg");
                sliderImage.setInfo("Yana");
                sliderImage.setId("2");
            }

            list.add(sliderImage);

            listimage.add(list.get(i).getImage());


        }

    }

    /**
     * 顶部产品图片
     */
    private void initBanner() {

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        //设置图片加载器

        mBanner.setImageLoader(new GlideImageLoader());

        //设置图片集合

        mBanner.setImages(listimage);

        //设置banner动画效果

        mBanner.setBannerAnimation(Transformer.Default);

        //设置自动轮播，默认为true

        mBanner.isAutoPlay(false);

//        //设置轮播时间
//
//        mBanner.setDelayTime(3000);

        //设置指示器位置（当banner模式中有指示器时）

        mBanner.setIndicatorGravity(BannerConfig.CENTER);


        mBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                //position从1开始
//                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //banner设置方法全部调用完毕时最后调用

        mBanner.start();
    }


    @Override
    protected void initEventAndData() {
        initData();
        initData(getIntent().getStringExtra("id"));
    }

    private void initData() {
        if(mContext.isDestroyed()){
            return;
        }
        ProductDetailPresenter = new ProductsPresenter(this);
        ProductDetailPresenter.initData(mContext, mMyOkhttp, mProducts, listimage);
    }

    /**
     * 筛选信息
     *
     * @param productId
     */
    private void initData(String productId) {
        if(mContext.isDestroyed()){
            return;
        }
        String url = NetUrl.URLHeader + NetUrl.Product.TypeSelect2;
        Map<String, String> map = new HashMap<>();

        map.put("productId", productId);
        mMyOkhttp.post().url(url).params(map).tag(mContext).enqueue(new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    if (response.getString("code").equals("200")) {

                        obj = response.getJSONArray("object");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (mRequestCode == requestCode
                && resultCode == AddressActivity.resultCode) {
            reciver = data.getStringExtra("UserName");
            phone = data.getStringExtra("PhoneNum");
            addressid = data.getStringExtra("addressid");
            address = data.getStringExtra("Province") + data.getStringExtra("City") + data.getStringExtra("Area") + data.getStringExtra("AddressDetail");
            mSelectAddress.setText(data.getStringExtra("Province") + data.getStringExtra("City") + data.getStringExtra("Area") + data.getStringExtra("AddressDetail"));

        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.SELECTPRODUCTCOUNT_CODE:
                mSelectCount.setText(event.getData() + " 件");
                mProducts.setProductCount(Integer.parseInt(event.getData()));

                break;
            //登录后刷新地址
            case Constants.Address_CODE:
                mSPUtils.putString("state", "2");
                if (mSPUtils.contains("addressid")) {
                    addressid = mSPUtils.getString("addressid");
                    if (!StringUtils.isEmpty(addressid)) {
                        address = mSPUtils.getString("defaultaddress");
                        phone = mSPUtils.getString("phone");
                        reciver = mSPUtils.getString("reciver");
                        mSelectAddress.setText(address);
                    } else {
                        mSelectAddress.setText("");

                    }
                }
                break;
            case Constants.ProductDetail_CODE:
                if (mSPUtils.contains("addressid")) {
                    addressid = mSPUtils.getString("addressid");
                    if (!StringUtils.isEmpty(addressid)) {
                        address = mSPUtils.getString("defaultaddress");
                        phone = mSPUtils.getString("phone");
                        reciver = mSPUtils.getString("reciver");
                        LogUtils.d(reciver);
                        LogUtils.d(phone);
                        mSelectAddress.setText(address);
                    } else {
                        mSelectAddress.setText("");

                    }
                }

                mProductInventory.setText(mProducts.getProductInventory() + "");
                mSelectCount.setText("1  件");
                mProductName.setText(mProducts.getProductName());
                mProductEnglishName.setText(mProducts.getProductSubname());
                initBanner();
                break;
        }
    }

    @OnClick({R.id.rl_back, R.id.ShopCar, R.id.contact_Customer_service, R.id.Ordering, R.id.order_rightaway, R.id.SelectCount, R.id.rl_SelectToWhere})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ShopCar:
                if (mSPUtils.getBoolean("hasLogin")) {
                    startActivity(new Intent(mContext, ShopCarActivity.class));
                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("您尚未登录不能进行相关操作")
                            .setPositiveButtonColor("#FD4A2E")
                            .setPositiveButton("去登录", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mSPUtils.putString("state", "1");

                                    startActivity(new Intent(mContext, LoginActivity.class));
                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }

                break;
            case R.id.Ordering:
                if(mProducts.getProductInventory()!=0){


                if (mSPUtils.getBoolean("hasLogin")) {
                    //第一个false 则为立即订购，否则为预定
                    String address = mSelectAddress.getText().toString();
                    if (!StringUtils.isEmpty(address)) {


                        new MyActionSheetDialog(mContext, mProducts, true, false, obj, addressid, address, reciver, phone).builder().setCanceledOnTouchOutside(false).show();
                    } else {
                        new AlertDialog(mContext).builder().setTitle("提示")
                                .setMsg("没有地址，是否去新建？")
                                .setPositiveButtonColor("#FD4A2E")
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mSPUtils.putString("state", "1");
                                        Intent intent = new Intent(mContext, AddressActivity.class);

                                        startActivity(intent);


                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                    }
                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("您尚未登录不能进行相关操作")
                            .setPositiveButtonColor("#FD4A2E")
                            .setPositiveButton("去登录", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mSPUtils.putString("state", "1");
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }
                }else{
                    Toast.makeText(mContext, "库存不足", Toast.LENGTH_SHORT).show();
                }

                break;
            //跳转填写订单
            case R.id.order_rightaway:
                if(mProducts.getProductInventory()!=0) {

                    if (mSPUtils.getBoolean("hasLogin")) {
                        String address = mSelectAddress.getText().toString();
                        if (!StringUtils.isEmpty(address)) {

                            //第一个false 则为立即订购，否则为预定，第二个为是否是选择数量
                            new MyActionSheetDialog(mContext, mProducts, false, true, obj, addressid, address, reciver, phone).builder().setCanceledOnTouchOutside(false).show();
                        } else {
                            new AlertDialog(mContext).builder().setTitle("提示")
                                    .setMsg("没有地址，是否去新建？")
                                    .setPositiveButtonColor("#FD4A2E")
                                    .setPositiveButton("确定", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            mSPUtils.putString("state", "1");
                                            Intent intent = new Intent(mContext, AddressActivity.class);

                                            startActivity(intent);

                                        }
                                    }).setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();

                        }

                    } else {
                        new AlertDialog(mContext).builder().setTitle("提示")
                                .setMsg("您尚未登录不能进行相关操作")
                                .setPositiveButtonColor("#FD4A2E")
                                .setPositiveButton("去登录", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mSPUtils.putString("state", "1");
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                    }
                }else{
                    Toast.makeText(mContext, "库存不足", Toast.LENGTH_SHORT).show();

                }

//                if (mSPUtils.getBoolean("hasLogin")) {
//                    mProductList.clear();
//                    Intent commitorder = new Intent(mContext, CommitOrderActivity.class);
//                    mProductList.add(mProductOrder);
//                    commitorder.putExtra("mProductList", (Serializable) mProductList);
//                    startActivity(commitorder);
//                } else {
//                    new AlertDialog(mContext).builder().setTitle("提示")
//                            .setMsg("您尚未登录不能进行相关操作")
//                            .setPositiveButtonColor("#FD4A2E")
//                            .setPositiveButton("去登录", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    startActivity(new Intent(mContext, LoginActivity.class));
//                                }
//                            }).setNegativeButton("取消", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                        }
//                    }).show();
//                }

                break;
            case R.id.SelectCount:
                if(mProducts.getProductInventory()!=0) {

                    if (mSPUtils.getBoolean("hasLogin")) {

                        //第一个false 则为立即订购，否则为预定，第二个为是否是选择数量
                        new MyActionSheetDialog(mContext, mProducts, false, true, obj, addressid, address, reciver, phone).builder().setCanceledOnTouchOutside(false).show();
                    } else {
                        new AlertDialog(mContext).builder().setTitle("提示")
                                .setMsg("您尚未登录不能进行相关操作")
                                .setPositiveButtonColor("#FD4A2E")
                                .setPositiveButton("去登录", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mSPUtils.putString("state", "1");
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                    }
                                }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                    }
                }else{
                    Toast.makeText(mContext, "库存不足", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.rl_SelectToWhere:
                if (mSPUtils.getBoolean("hasLogin")) {
                    Intent intent = new Intent(this, AddressActivity.class);

                    startActivityForResult(intent, mRequestCode);
                } else {
                    new AlertDialog(mContext).builder().setTitle("提示")
                            .setMsg("您尚未登录不能进行相关操作")
                            .setPositiveButtonColor("#FD4A2E")
                            .setPositiveButton("去登录", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mSPUtils.putString("state", "1");
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                }
                            }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }

                break;
            case R.id.contact_Customer_service:
                CallPhoneUtils.callphone(mContext);

                break;
        }
    }


    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        mRelativeLayout.setVisibility(View.GONE);
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        if (mCircleProgressView.isSpinning()) {
            mCircleProgressView.stopSpinning();
        }
        mRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getProductsId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    public void onSuccess() {
        hideProgress();
        EventBus.getDefault().post(new MyEvent(ProductDetail_CODE));
    }

    @Override
    public void onFailure() {
        hideProgress();
        mRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void onError() {
        hideProgress();
        mRelativeLayout.setVisibility(View.GONE);
    }


}
