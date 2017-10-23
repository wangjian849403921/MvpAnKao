package com.mvpankao.model.bean;

/**
 * Created by wangjian
 * On  2016/11/25
 */

public class ProductDescribe {
    //图片
    String Image;
    //产品名
    String ProductName;
    //库存
    String ProductInventory;
    //产品ID
    String ProductId;

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
}
