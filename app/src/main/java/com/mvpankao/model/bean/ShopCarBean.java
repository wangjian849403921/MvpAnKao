package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/2/7
 */

public class ShopCarBean {


    /**
     * code : 200
     * result : 查询成功
     * object : [{"productParmv1":"36KV","productParmv0":"36KV","productChooseCreatetime":1486523261000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":42,"productStockDetailIdfk":2,"productChooseNum":3,"userId":4,"productIdfk":1,"productStockDetailSize":292,"productName":"110-500kV 瓷套终端"},{"productParmv1":"36KV","productParmv0":"36KV","productChooseCreatetime":1486523247000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":41,"productStockDetailIdfk":1,"productChooseNum":2,"userId":4,"productIdfk":1,"productStockDetailSize":151,"productName":"110-500kV 瓷套终端"},{"productParmv1":"36KV","productParmv0":"36KV","productChooseCreatetime":1486523227000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":40,"productStockDetailIdfk":2,"productChooseNum":5,"userId":4,"productIdfk":1,"productStockDetailSize":292,"productName":"110-500kV 瓷套终端"},{"productParmv1":"36KV","productParmv0":"36KV","productChooseCreatetime":1486523180000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":39,"productStockDetailIdfk":2,"productChooseNum":5,"userId":4,"productIdfk":1,"productStockDetailSize":292,"productName":"110-500kV 瓷套终端"}]
     */

    private int code;
    private String result;
    private List<ObjectBean> object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * productParmv1 : 36KV
         * productParmv0 : 36KV
         * productChooseCreatetime : 1486523261000
         * productIcon : fileResource/imageUpload/67789f5a4665408f912130348398379f.png
         * id : 42
         * productStockDetailIdfk : 2
         * productChooseNum : 3
         * userId : 4
         * productIdfk : 1
         * productStockDetailSize : 292
         * productName : 110-500kV 瓷套终端
         */

        private String productParmv1;
        private String productParmv0;
        private long productChooseCreatetime;
        private String productIcon;
        private String id;
        private int productStockDetailIdfk;
        private int productChooseNum;
        private String userId;
        private String productIdfk;
        private int productStockDetailSize;
        private String productName;

        public String getProductParmv1() {
            return productParmv1;
        }

        public void setProductParmv1(String productParmv1) {
            this.productParmv1 = productParmv1;
        }

        public String getProductParmv0() {
            return productParmv0;
        }

        public void setProductParmv0(String productParmv0) {
            this.productParmv0 = productParmv0;
        }

        public long getProductChooseCreatetime() {
            return productChooseCreatetime;
        }

        public void setProductChooseCreatetime(long productChooseCreatetime) {
            this.productChooseCreatetime = productChooseCreatetime;
        }

        public String getProductIcon() {
            return productIcon;
        }

        public void setProductIcon(String productIcon) {
            this.productIcon = productIcon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getProductStockDetailIdfk() {
            return productStockDetailIdfk;
        }

        public void setProductStockDetailIdfk(int productStockDetailIdfk) {
            this.productStockDetailIdfk = productStockDetailIdfk;
        }

        public int getProductChooseNum() {
            return productChooseNum;
        }

        public void setProductChooseNum(int productChooseNum) {
            this.productChooseNum = productChooseNum;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getProductIdfk() {
            return productIdfk;
        }

        public void setProductIdfk(String productIdfk) {
            this.productIdfk = productIdfk;
        }

        public int getProductStockDetailSize() {
            return productStockDetailSize;
        }

        public void setProductStockDetailSize(int productStockDetailSize) {
            this.productStockDetailSize = productStockDetailSize;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
