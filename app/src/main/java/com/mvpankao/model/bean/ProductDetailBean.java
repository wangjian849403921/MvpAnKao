package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/23
 */

public class ProductDetailBean {

    /**
     * code : 200
     * result : 查询成功
     * object : {"product":{"id":1,"productName":"110-500kV 瓷套终端","productSubname":"Ankura-YJZWC4","productStockSize":100,"productAddDate":1485070706000,"productCategoryIdfk1":1,"productCategoryIdfk2":3,"productNote":"hahfha","productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","productOperativetNorm":" "},"iconlist":["fileResource/imageUpload/67789f5a4665408f912130348398379f.png"]}
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
         * product : {"id":1,"productName":"110-500kV 瓷套终端","productSubname":"Ankura-YJZWC4","productStockSize":100,"productAddDate":1485070706000,"productCategoryIdfk1":1,"productCategoryIdfk2":3,"productNote":"hahfha","productIcon":"fileResource/imageUpload/67789f5a4665408f912130348398379f.png","productOperativetNorm":" "}
         * iconlist : ["fileResource/imageUpload/67789f5a4665408f912130348398379f.png"]
         */

        private ProductBean product;
        private List<String> iconlist;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public List<String> getIconlist() {
            return iconlist;
        }

        public void setIconlist(List<String> iconlist) {
            this.iconlist = iconlist;
        }

        public static class ProductBean {
            /**
             * id : 1
             * productName : 110-500kV 瓷套终端
             * productSubname : Ankura-YJZWC4
             * productStockSize : 100
             * productAddDate : 1485070706000
             * productCategoryIdfk1 : 1
             * productCategoryIdfk2 : 3
             * productNote : hahfha
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
}
