package com.mvpankao.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.StringUtils;
import com.mvpankao.dialog.AlertDialog;
import com.mvpankao.model.OnRepairListener;
import com.mvpankao.model.RepairModel;
import com.mvpankao.model.bean.RepairBean;
import com.mvpankao.model.bean.RepairDetailBean;
import com.mvpankao.model.impl.RepairModelImpl;
import com.mvpankao.view.FaultRepairView;
import com.mvpankao.view.RepairDetailView;
import com.mvpankao.view.RepairView;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.List;

import static net.lemonsoft.lemonhello.LemonHelloGlobal.content;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/3/7 16:20
 */

public class RepairPresenter implements OnRepairListener {
    private RepairModel mModel;
    private RepairView mView;
    private RepairDetailView mDetailView;
    private FaultRepairView mFaultRepairView;
    public RepairPresenter(RepairView mView) {
        this.mView = mView;
        mModel = new RepairModelImpl();
    }

    public RepairPresenter(FaultRepairView faultRepairView) {
        mFaultRepairView = faultRepairView;
        mModel=new RepairModelImpl();
    }

    public RepairPresenter(RepairDetailView detailView) {
        mDetailView = detailView;
        mModel = new RepairModelImpl();
    }

    public void initData(Context context, MyOkHttp myOkHttp, List<RepairBean.ObjectBean.DataBean.ListBean> list) {
        int page = mView.getPage();
        String userid= mView.getUserId();
        String role=mView.getRole();
            if(NetworkUtils.isConnected(context)){
                mModel.initData(page, this,context,myOkHttp,list,userid,role);
            }else{
                mView.showToast("网络异常");
            }


    }
    public void initDetail(Context context, MyOkHttp myOkHttp, List<RepairDetailBean.ObjectBean.RepailogListBean> list) {


        String repairId=mDetailView.getRepairId();
        if(NetworkUtils.isConnected(context)){
            mModel.initDetailData(this,context,myOkHttp,list,repairId);
        }else{
            mDetailView.showToast("网络异常");
        }


    }
    public void commit(Context context,MyOkHttp MyOkHttp){
        String type=mFaultRepairView.getType();
        String userId=mFaultRepairView.getUserId();
        String username=mFaultRepairView.getUserName();
        String phone=mFaultRepairView.getPhone();
        String company=mFaultRepairView.getCompany();
        String area=mFaultRepairView.getArea();
        String detailaddress=mFaultRepairView.getDetailAddress();
        String linename=mFaultRepairView.getLineName();
        String statue=mFaultRepairView.getStatue();
        String remark=mFaultRepairView.getRemark();
        String repairId=mFaultRepairView.getRepairId();
        if (!StringUtils.isEmpty(type) & !StringUtils.isEmpty(linename) & !StringUtils.isEmpty(content)
                & !StringUtils.isEmpty(detailaddress) & !StringUtils.isEmpty(area)
                & !StringUtils.isEmpty(username)
                & !StringUtils.isEmpty(phone)) {
            if (NetworkUtils.isConnected(context)) {

              mModel.commit(this,context,MyOkHttp,userId,repairId,type,phone,username,area,detailaddress,company,linename,statue,remark);

            } else {
                Toast.makeText(context, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
            }
        } else {
            new AlertDialog(context).builder().setTitle("提示")
                    .setMsg("请将内容填写详细")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }
    }
    @Override
    public void onError() {
        if (mView!=null){
            mView.onError();
        }
        if (mDetailView!=null){
            mDetailView.onError();
        }
        if (mFaultRepairView!=null){
            mFaultRepairView.onError();
            mFaultRepairView.showToast("提交失败");
        }
    }



    @Override
    public void onSuccess() {

        if (mView!=null){
            mView.onSuccess();
        }
        if (mFaultRepairView!=null){
            mFaultRepairView.onSuccess();
        }
    }

    @Override
    public void onSuccess(RepairDetailBean repairDetailBean) {
        if (mDetailView!=null){
            mDetailView.onSuccess(repairDetailBean );
        }
    }


    @Override
    public void onFailure() {
        if (mView!=null){
            mView.onFailure();
        }
        if (mDetailView!=null){
            mDetailView.onFailure();
        }
        if (mFaultRepairView!=null){
            mFaultRepairView.onFailure();
        }
    }

    @Override
    public void getItemSize(int size) {
        if (mView!=null){
            mView.getItemSize(size);
        }

    }
}
