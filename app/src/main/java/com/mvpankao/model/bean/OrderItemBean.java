package com.mvpankao.model.bean;

import java.util.ArrayList;

public class OrderItemBean {

    //这个是订单列表的一个条目bean
    private int orderState;
    private int totalCount;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private ArrayList<ProductOrder> productslist;

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<ProductOrder> getList() {
        return productslist;
    }

    public void setList(ArrayList<ProductOrder> list) {
        this.productslist = list;
    }

}
