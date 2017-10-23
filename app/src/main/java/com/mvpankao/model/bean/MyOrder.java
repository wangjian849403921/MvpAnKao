package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/12/29
 */

public class MyOrder {


    /**
     * code : 200
     * result : 查询成功
     * object : {"list":[{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486373565000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":2,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486373568000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":3,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486373570000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":4,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486377176000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":5,"productName":"110-500kV 瓷套终端"},{"productParmv1":"1000mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":4,"productOrderStatus":0,"productOrderCreatetime":1486379532000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":6,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":6,"productOrderStatus":0,"productOrderCreatetime":1486379535000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":7,"productName":"110-500kV 瓷套终端"},{"productParmv1":"1000mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":4,"productOrderStatus":0,"productOrderCreatetime":1486379490000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":8,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486446588000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":9,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"188****6666","receiveName":"421421","productParmv0":"36KV","receiveAddress":"北京顺义区旺泉街道5215","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486452151000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":10,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18855556666","receiveName":"421421","productParmv0":"36KV","receiveAddress":"北京顺义区旺泉街道5215","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486452393000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":11,"productName":"110-500kV 瓷套终端"}],"total":10,"limit":20,"pages":1,"pageNumber":1,"isFirstPage":true,"isLastPage":false,"navigatePageNumbers":[1],"limitStart":0}
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
         * list : [{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486373565000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":2,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486373568000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":3,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486373570000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":4,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486377176000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":5,"productName":"110-500kV 瓷套终端"},{"productParmv1":"1000mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":4,"productOrderStatus":0,"productOrderCreatetime":1486379532000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":6,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":6,"productOrderStatus":0,"productOrderCreatetime":1486379535000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":7,"productName":"110-500kV 瓷套终端"},{"productParmv1":"1000mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":4,"productOrderStatus":0,"productOrderCreatetime":1486379490000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":8,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18551718588","receiveName":"汪健","productParmv0":"36KV","receiveAddress":"江苏南京","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486446588000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":9,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"188****6666","receiveName":"421421","productParmv0":"36KV","receiveAddress":"北京顺义区旺泉街道5215","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486452151000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":10,"productName":"110-500kV 瓷套终端"},{"productParmv1":"600mm² ","receivePhone":"18855556666","receiveName":"421421","productParmv0":"36KV","receiveAddress":"北京顺义区旺泉街道5215","productOrderNum":1,"productOrderStatus":0,"productOrderCreatetime":1486452393000,"productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","id":11,"productName":"110-500kV 瓷套终端"}]
         * total : 10
         * limit : 20
         * pages : 1
         * pageNumber : 1
         * isFirstPage : true
         * isLastPage : false
         * navigatePageNumbers : [1]
         * limitStart : 0
         */

        private int total;
        private int limit;
        private int pages;
        private int pageNumber;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int limitStart;
        private List<ListBean> list;
        private List<Integer> navigatePageNumbers;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getLimitStart() {
            return limitStart;
        }

        public void setLimitStart(int limitStart) {
            this.limitStart = limitStart;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatePageNumbers() {
            return navigatePageNumbers;
        }

        public void setNavigatePageNumbers(List<Integer> navigatePageNumbers) {
            this.navigatePageNumbers = navigatePageNumbers;
        }

        public static class ListBean {
            /**
             * productParmv1 : 600mm²
             * receivePhone : 18551718588
             * receiveName : 汪健
             * productParmv0 : 36KV
             * receiveAddress : 江苏南京
             * productOrderNum : 1
             * productOrderStatus : 0
             * productOrderCreatetime : 1486373565000
             * productIcon : fileResource/imageUpload/67789f5a4665408f912130348398379f.png
             * id : 2
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

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }
        }
    }
}
