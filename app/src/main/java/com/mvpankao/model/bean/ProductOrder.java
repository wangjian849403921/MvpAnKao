package com.mvpankao.model.bean;

import java.io.Serializable;

/**
 * Created by wangjian
 * On  2016/12/1
 */

public class ProductOrder implements Serializable {
    //图片
    String Image;
    //产品名
    String ProductName;
    //库存
    String ProductInventory;    //数量
    int ProductCount;
//规格
    String productParam;
    //产品ID
    String ProductId;
    //产品ID
    int productStockDetailIdfk;
    int productStockDetailSize;
    //shopcarid
    String ShopCarid;
    //下单人姓名
    String UserName;
    //手机号
    String PhoneNum;
    //详细地址
    String AddressDetail;
    //省市区
    String ProvinceCityArea;
    //是否默认地址
    public boolean checked = false;
    private boolean isAllSelect;
    private int allcount;

    public String getProductParam() {
        return productParam;
    }

    public void setProductParam(String productParam) {
        this.productParam = productParam;
    }

    public String getShopCarid() {
        return ShopCarid;
    }

    public void setShopCarid(String shopCarid) {
        ShopCarid = shopCarid;
    }

    public int getProductStockDetailIdfk() {
        return productStockDetailIdfk;
    }

    public void setProductStockDetailIdfk(int productStockDetailIdfk) {
        this.productStockDetailIdfk = productStockDetailIdfk;
    }

    public int getProductStockDetailSize() {
        return productStockDetailSize;
    }

    public void setProductStockDetailSize(int productStockDetailSize) {
        this.productStockDetailSize = productStockDetailSize;
    }

    public int getAllcount() {
        return allcount;
    }

    public void setAllcount(int allcount) {
        this.allcount = allcount;
    }

    public boolean isAllSelect() {
        return isAllSelect;
    }

    public void setAllSelect(boolean allSelect) {
        isAllSelect = allSelect;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductInventory() {
        return ProductInventory;
    }

    public void setProductInventory(String productInventory) {
        ProductInventory = productInventory;
    }

    public int getProductCount() {
        return ProductCount;
    }

    public void setProductCount(int productCount) {
        ProductCount = productCount;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getProvinceCityArea() {
        return ProvinceCityArea;
    }

    public void setProvinceCityArea(String provinceCityArea) {
        ProvinceCityArea = provinceCityArea;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getAddressDetail() {
        return AddressDetail;
    }

    public void setAddressDetail(String address) {
        AddressDetail = address;
    }
}
