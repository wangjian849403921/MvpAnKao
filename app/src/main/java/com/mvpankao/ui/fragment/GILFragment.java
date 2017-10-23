package com.mvpankao.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvpankao.R;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.http.NetUrl;
import com.mvpankao.model.bean.HomePageProduct;
import com.mvpankao.presenter.HomeProductPresenter;
import com.mvpankao.ui.activity.ProductDetailActivity;
import com.mvpankao.ui.activity.ProductsOrderActivity;
import com.mvpankao.utils.Constants;
import com.mvpankao.utils.MyEvent;
import com.mvpankao.view.HomeProductView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * GIL输电系统
 * Created by wangjian
 * On  2016/11/18
 */

public class GILFragment extends BaseFragment implements HomeProductView {
    @BindView(R.id.to_more)
    TextView mToMore;
    List<HomePageProduct.ObjectBean> list = new ArrayList<>();
    @BindView(R.id.product_image)
    ImageView mProductImage;
    @BindView(R.id.product_name)
    TextView mProductName;
    @BindView(R.id.to_detail)
    TextView mToDetail;
    @BindView(R.id.product_name2)
    TextView mProductName2;
    @BindView(R.id.to_detail2)
    TextView mToDetail2;
    @BindView(R.id.product_image2)
    ImageView mProductImage2;
    @BindView(R.id.product_name3)
    TextView mProductName3;
    @BindView(R.id.to_detail3)
    TextView mToDetail3;
    @BindView(R.id.product_image3)
    ImageView mProductImage3;
    @BindView(R.id.ParentLayout)
    LinearLayout mLayout;
    @BindView(R.id.View1)
    View mView1;
    @BindView(R.id.View2)
    View mView2;
    @BindView(R.id.View3)
    View mView3;
    HomeProductPresenter HomeProductPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeProductPresenter=new HomeProductPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_electricabble;
    }

    @Override
    protected void initEventAndData() {
        if(mActivity.isDestroyed()){
            return;
        }
        setGone();
        HomeProductPresenter.initData(getActivity(),mMyOkhttp,list,"2");
    }

    private void setGone() {
        mProductName.setVisibility(View.GONE);
        mProductImage.setVisibility(View.GONE);
        mToDetail.setVisibility(View.GONE);
        mView1.setVisibility(View.GONE);
        mToMore.setVisibility(View.GONE);

        mProductName2.setVisibility(View.GONE);
        mProductImage2.setVisibility(View.GONE);
        mToDetail2.setVisibility(View.GONE);
        mView2.setVisibility(View.GONE);

        mProductName3.setVisibility(View.GONE);
        mProductImage3.setVisibility(View.GONE);
        mToDetail3.setVisibility(View.GONE);
        mView3.setVisibility(View.GONE);
    }

    private void setVisible() {
        mProductName.setVisibility(View.VISIBLE);
        mProductImage.setVisibility(View.VISIBLE);
        mToDetail.setVisibility(View.VISIBLE);
        mView1.setVisibility(View.VISIBLE);
        mToMore.setVisibility(View.VISIBLE);

        mProductName2.setVisibility(View.VISIBLE);
        mProductImage2.setVisibility(View.VISIBLE);
        mToDetail2.setVisibility(View.VISIBLE);
        mView2.setVisibility(View.VISIBLE);

        mProductName3.setVisibility(View.VISIBLE);
        mProductImage3.setVisibility(View.VISIBLE);
        mToDetail3.setVisibility(View.VISIBLE);
        mView3.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.to_more)
    public void onClick() {
        Intent intent = new Intent(getActivity(), ProductsOrderActivity.class);
        intent.putExtra("type", "2");
        startActivity(intent);

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void MyEvent(MyEvent event) {
        switch (event.getMsg()) {
            case Constants.HomePageProduct_CODE:
                RequestOptions options = new RequestOptions();
                options.error(R.drawable.product1);
                if (!StringUtils.isEmpty(list.get(0).getProductIcon())) {
                    Glide.with(getActivity()).load(NetUrl.DOCHeader + list.get(0).getProductIcon()).apply(options).into(mProductImage);
                }
                if (!StringUtils.isEmpty(list.get(1).getProductIcon())) {
                    Glide.with(getActivity()).load(NetUrl.DOCHeader + list.get(1).getProductIcon()).into(mProductImage2);
                }
                if (!StringUtils.isEmpty(list.get(2).getProductIcon())) {
                    Glide.with(getActivity()).load(NetUrl.DOCHeader + list.get(2).getProductIcon()).into(mProductImage3);
                }
                if (!StringUtils.isEmpty(list.get(0).getProductName())) {
                    mProductName.setText(list.get(0).getProductName());
                }
                if (!StringUtils.isEmpty(list.get(1).getProductName())) {
                    mProductName2.setText(list.get(1).getProductName());
                }
                if (!StringUtils.isEmpty(list.get(2).getProductName())) {
                    mProductName3.setText(list.get(2).getProductName());
                }
                break;
        }
    }


    @OnClick({R.id.to_detail, R.id.to_detail2, R.id.to_detail3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_detail:
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("id", list.get(0).getId());
                startActivity(intent);
                break;
            case R.id.to_detail2:
                Intent intent2 = new Intent(getActivity(), ProductDetailActivity.class);
                intent2.putExtra("id", list.get(1).getId());
                startActivity(intent2);
                break;
            case R.id.to_detail3:
                Intent intent3 = new Intent(getActivity(), ProductDetailActivity.class);
                intent3.putExtra("id", list.get(2).getId());
                startActivity(intent3);
                break;
        }
    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onSuccess() {
        mLayout.setVisibility(View.VISIBLE);
        setVisible();
        EventBus.getDefault().post(new MyEvent(Constants.HomePageProduct_CODE));
    }

    @Override
    public void onFailure() {
        mLayout.setVisibility(View.GONE);
        setGone();
    }

    @Override
    public void onError() {
        mLayout.setVisibility(View.GONE);
        setGone();
    }
}
