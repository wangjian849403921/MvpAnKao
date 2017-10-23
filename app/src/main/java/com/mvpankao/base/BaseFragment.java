package com.mvpankao.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.SPUtils;
import com.mvpankao.R;
import com.mvpankao.utils.MyEvent;
import com.tsy.sdk.myokhttp.MyOkHttp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

/**
 * 基类fragment
 * Created by wangjian
 * On  2016/9/23
 */
public  abstract class BaseFragment extends Fragment {
    protected volatile View mView;
    protected Activity mActivity;
    protected static Context mContext;
    protected SPUtils mSPUtils;
    protected MyOkHttp mMyOkhttp;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        mMyOkhttp = new MyOkHttp(okHttpClient);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (this.mView == null) {
            this.mView = inflater.inflate(getLayoutId(), null);
            ButterKnife.bind(this, mView);
            mSPUtils = new SPUtils(mContext, "USER_INFO");
            initView();
            initEventAndData();
        }

        if (this.mView.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.mView.getParent();
            parent.removeView(this.mView);
        }

//        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
//        ViewGroup parent = (ViewGroup) mView.getParent();
//        if (parent != null) {
//            parent.removeView(mView);
//        }
        return mView;
//        return  attachToSwipeBack(mView);
    }
     @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
         public void MyEvent(MyEvent event) {
           switch (event.getMsg()){

           }
         }


    @Override
    public void startActivity(Intent intent) {

        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.anim_in_right_left, R.anim.anim_out_right_left);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMyOkhttp.cancel(getActivity());
        EventBus.getDefault().unregister(this);
    }

    protected abstract void initView();
    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
}
