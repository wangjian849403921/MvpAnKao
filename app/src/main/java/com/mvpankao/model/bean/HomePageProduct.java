package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/20
 */

public class HomePageProduct {


    /**
     * code : 200
     * result : 查询成功
     * object : [{"id":1,"productName":"110-500kV 瓷套终端","productSubname":"Ankura-YJZWC4","productStockSize":100,"productAddDate":1485070706000,"productCategoryIdfk1":1,"productCategoryIdfk2":3,"productNote":"\n","productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","productOperativetNorm":" "},{"id":2,"productName":"110-500kV 复合套终端","productSubname":"Ankura-YJZWCF4","productStockSize":100,"productAddDate":1485071751000,"productCategoryIdfk1":1,"productCategoryIdfk2":3,"productNote":" ","productIcon":"fileResource/imageUpload/ab8cb2f7125347038253362b7c4bd7c6.jpg","productOperativetNorm":" "},{"id":3,"productName":"110-500kV GIS终端","productSubname":"Ankura-YJZGG","productStockSize":100,"productAddDate":1485071901000,"productCategoryIdfk1":1,"productCategoryIdfk2":3,"productNote":" ","productIcon":"fileResource/imageUpload/2aa6b8184bcc4325860e0113f93ab306.jpg","productOperativetNorm":" "}]
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
         * id : 1
         * productName : 110-500kV 瓷套终端
         * productSubname : Ankura-YJZWC4
         * productStockSize : 100
         * productAddDate : 1485070706000
         * productCategoryIdfk1 : 1
         * productCategoryIdfk2 : 3
         * productNote :

         * productIcon : fileResource/imageUpload/67789f5a4665408f912130348398379f.png
         * productOperativetNorm :
         */

        private String id;
        private String productName;
        private String productSubname;
        private int productStockSize;
        private long productAddDate;
        private int productCategoryIdfk1;
        private int productCategoryIdfk2;
        private String productNote;
        private String productIcon;
        private String productOperativetNorm;

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

        public String getProductSubname() {
            return productSubname;
        }

        public void setProductSubname(String productSubname) {
            this.productSubname = productSubname;
        }

        public int getProductStockSize() {
            return productStockSize;
        }

        public void setProductStockSize(int productStockSize) {
            this.productStockSize = productStockSize;
        }

        public long getProductAddDate() {
            return productAddDate;
        }

        public void setProductAddDate(long productAddDate) {
            this.productAddDate = productAddDate;
        }

        public int getProductCategoryIdfk1() {
            return productCategoryIdfk1;
        }

        public void setProductCategoryIdfk1(int productCategoryIdfk1) {
            this.productCategoryIdfk1 = productCategoryIdfk1;
        }

        public int getProductCategoryIdfk2() {
            return productCategoryIdfk2;
        }

        public void setProductCategoryIdfk2(int productCategoryIdfk2) {
            this.productCategoryIdfk2 = productCategoryIdfk2;
        }

        public String getProductNote() {
            return productNote;
        }

        public void setProductNote(String productNote) {
            this.productNote = productNote;
        }

        public String getProductIcon() {
            return productIcon;
        }

        public void setProductIcon(String productIcon) {
            this.productIcon = productIcon;
        }

        public String getProductOperativetNorm() {
            return productOperativetNorm;
        }

        public void setProductOperativetNorm(String productOperativetNorm) {
            this.productOperativetNorm = productOperativetNorm;
        }
    }
}
