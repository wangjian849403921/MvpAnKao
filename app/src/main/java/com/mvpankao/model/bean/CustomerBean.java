package com.mvpankao.model.bean;

/**
 * 客户bean
 * Created by wangjian
 * On  2017/1/3
 */

public class CustomerBean {
    String Image;
    String customerName;
    String id;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
