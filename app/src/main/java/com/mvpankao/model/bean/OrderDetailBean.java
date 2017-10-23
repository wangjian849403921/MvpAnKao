package com.mvpankao.model.bean;

/**
 * Created by wangjian
 * On  2017/2/7
 */

public class OrderDetailBean {

    /**
     * code : 200
     * result : 查询成功
     * object : {"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486373570000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":4,"customerRequirement":"","productName":"110-500kV 瓷套终端"}
     */

    private int code;
    private String result;
    private ObjectBean object;

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

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * productParmv1 : 600mm²
         * receivePhone : 18551718588
         * receiveName : 汪健
         * productParmv0 : 36KV
         * receiveAddress : 江苏南京
         * productOrderNum : 1
         * productOrderStatus : 0
         * productOrderCreatetime : 1486373570000
         * productIcon : fileResource/imageUpload/67789f5a4665408f912130348398379f.png
         * id : 4
         * customerRequirement :
         * productName : 110-500kV 瓷套终端
         */

        private String productParmv1;
        private String receivePhone;
        private String receiveName;
        private String productParmv0;
        private String receiveAddress;
        private String productOrderNum;
        private String productOrderStatus;
        private long productOrderCreatetime;
        private String productIcon;
        private String id;
        private String customerRequirement;
        private String productName;

        public String getProductParmv1() {
            return productParmv1;
        }

        public void setProductParmv1(String productParmv1) {
            this.productParmv1 = productParmv1;
        }

        public String getReceivePhone() {
            return receivePhone;
        }

        public void setReceivePhone(String receivePhone) {
            this.receivePhone = receivePhone;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public String getProductParmv0() {
            return productParmv0;
        }

        public void setProductParmv0(String productParmv0) {
            this.productParmv0 = productParmv0;
        }

        public String getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
        }

        public String getProductOrderNum() {
            return productOrderNum;
        }

        public void setProductOrderNum(String productOrderNum) {
            this.productOrderNum = productOrderNum;
        }

        public String getProductOrderStatus() {
            return productOrderStatus;
        }

        public void setProductOrderStatus(String productOrderStatus) {
            this.productOrderStatus = productOrderStatus;
        }

        public long getProductOrderCreatetime() {
            return productOrderCreatetime;
        }

        public void setProductOrderCreatetime(long productOrderCreatetime) {
            this.productOrderCreatetime = productOrderCreatetime;
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

        public String getCustomerRequirement() {
            return customerRequirement;
        }

        public void setCustomerRequirement(String customerRequirement) {
            this.customerRequirement = customerRequirement;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
