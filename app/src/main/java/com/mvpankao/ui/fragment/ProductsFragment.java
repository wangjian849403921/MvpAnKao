package com.mvpankao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mvpankao.R;
import com.mvpankao.base.BaseFragment;
import com.mvpankao.model.bean.TypeID;
import com.mvpankao.presenter.ProductsPresenter;
import com.mvpankao.view.ProductSelectView;
import com.mvpankao.widget.popupwindow.FilterPopupWindow;
import com.mvpankao.widget.switchbar.SegmentControl;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 产品中心
 * Created by wangjian
 * On  2016/9/7
 */
public class ProductsFragment extends BaseFragment implements ProductSelectView {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.Layout)
    LinearLayout mLayout;
    @BindView(R.id.dowmarrow)
    ImageView mDowmarrow;
    @BindView(R.id.Rl_Select)
    RelativeLayout mRlSelect;
    View view;
    @BindView(R.id.header)
    RelativeLayout mHeader;

    @BindView(R.id.segment_control)
    SegmentControl mSegmentControl;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    /**
     * 所有产品
     */
    ProductAllFragment mAllProductFrament;
    /**
     * 电缆输电系统
     */
    ProductCableFragment mProductCableFragment;
    /**
     * GIL输电系统
     */
    ProductGILFragment mProductGILFragment;

    private FilterPopupWindow popupWindow;
    List<TypeID.ObjectBean.DataBean> list = new ArrayList<>();
    int index = 0;
    JSONArray obj;
    private ProductsPresenter mProductsPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductsPresenter=new ProductsPresenter(this);
    }

    @Override
    protected void initView() {

        mRlBack.setVisibility(View.INVISIBLE);
        mTitle.setText("产品中心");
        mSegmentControl.setText("所有产品", "电缆输电系统", "GIL输电系统");

        loadFragment(0);
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                loadFragment(index);
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_product;
    }

    @Override
    protected void initEventAndData() {


    }

    private void loadFragment(int type) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        hideFragments(transaction);

        switch (type) {
            case 0:
                if (mAllProductFrament == null) {
                    // 如果mAllProductFrament为空，则创建一个并添加到界面上
                    mAllProductFrament = new ProductAllFragment();
                    transaction.add(R.id.fl_content, mAllProductFrament);
                    index = type;
                } else {
                    // 如果mAllProductFrament不为空，则直接将它显示出来
                    transaction.show(mAllProductFrament);
                }

                break;

            case 1:
                if (mProductCableFragment == null) {
                    // 如果mProductCableFragment为空，则创建一个并添加到界面上
                    mProductCableFragment = new ProductCableFragment();
                    transaction.add(R.id.fl_content, mProductCableFragment);
                } else {
                    // 如果mProductCableFragment不为空，则直接将它显示出来
                    transaction.show(mProductCableFragment);
                }
                index = type;

                break;

            case 2:
                if (mProductGILFragment == null) {
                    // 如果mProductGILFragment为空，则创建一个并添加到界面上
                    mProductGILFragment = new ProductGILFragment();
                    transaction.add(R.id.fl_content, mProductGILFragment);
                } else {
                    // 如果mProductGILFragment不为空，则直接将它显示出来
                    transaction.show(mProductGILFragment);
                }

                index = type;

                break;
        }


        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (mAllProductFrament != null) {
            transaction.hide(mAllProductFrament);
        }
        if (mProductCableFragment != null) {
            transaction.hide(mProductCableFragment);
        }
        if (mProductGILFragment != null) {
            transaction.hide(mProductGILFragment);
        }

    }

    @OnClick(R.id.Rl_Select)
    public void onClick() {
        initSelectData();
    }

    private void initSelectData() {
       mProductsPresenter.initSelectData(getActivity(),mMyOkhttp);
    }


    @Override
    public void setJsonArray(JSONArray jsonArray) {
        obj=jsonArray;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess() {
        popupWindow = new FilterPopupWindow(getActivity(), mDowmarrow, obj, false, true, 1,index);
        popupWindow.showFilterPopup(mHeader);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onError() {

    }
}
