package com.mvpankao.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/14
 */

public class ProductBean {

    /**
     * code : 200
     * result : 查询成功
     * object : {"list":[{"categoryFirstName":"电缆输电系统","productCategoryIdfk2":1,"productCategoryIdfk1":4,"productNote":"太太太太太","productStockSize":200,"productAddDate":1482892496000,"product_operativet_norm":"","productIcon":"zhengsuPlatform/fileResource/imageUpload/909691fe63594435bd75601068412075.jpg","id":1,"productName":"36KV 600mm² 连接件","productSubname":"","categorySecondName":"连接件"},{"productCategoryIdfk2":0,"productCategoryIdfk1":0,"productNote":" 32131","productStockSize":3213,"productAddDate":1483949180000,"product_operativet_norm":" 312313","productIcon":"zhengsuPlatform/fileResource/imageUpload/a32b3368cd5b491aa3b013d352b58308.jpg;zhengsuPlatform/fileResource/imageUpload/ac5ed3cdbcdc43d3852b51bd000d5abd.jpg","id":8,"productName":"3123131","productSubname":"1231"},{"categoryFirstName":"电缆输电系统","productCategoryIdfk2":1,"productCategoryIdfk1":4,"productNote":" 1133213","productStockSize":80,"productAddDate":1484272957000,"product_operativet_norm":" 132313","productIcon":"zhengsuPlatform/fileResource/imageUpload/1c3c145209df40b9a5388f3d929d3acd.jpg","id":9,"productName":"110KV 变压器","productSubname":"变压器","categorySecondName":"连接件"}],"total":3,"limit":10,"pages":1,"pageNumber":1,"isFirstPage":true,"isLastPage":false,"navigatePageNumbers":[1],"limitStart":0}
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
         * list : [{"categoryFirstName":"电缆输电系统","productCategoryIdfk2":1,"productCategoryIdfk1":4,"productNote":"太太太太太","productStockSize":200,"productAddDate":1482892496000,"product_operativet_norm":"","productIcon":"zhengsuPlatform/fileResource/imageUpload/909691fe63594435bd75601068412075.jpg","id":1,"productName":"36KV 600mm² 连接件","productSubname":"","categorySecondName":"连接件"},{"productCategoryIdfk2":0,"productCategoryIdfk1":0,"productNote":" 32131","productStockSize":3213,"productAddDate":1483949180000,"product_operativet_norm":" 312313","productIcon":"zhengsuPlatform/fileResource/imageUpload/a32b3368cd5b491aa3b013d352b58308.jpg;zhengsuPlatform/fileResource/imageUpload/ac5ed3cdbcdc43d3852b51bd000d5abd.jpg","id":8,"productName":"3123131","productSubname":"1231"},{"categoryFirstName":"电缆输电系统","productCategoryIdfk2":1,"productCategoryIdfk1":4,"productNote":" 1133213","productStockSize":80,"productAddDate":1484272957000,"product_operativet_norm":" 132313","productIcon":"zhengsuPlatform/fileResource/imageUpload/1c3c145209df40b9a5388f3d929d3acd.jpg","id":9,"productName":"110KV 变压器","productSubname":"变压器","categorySecondName":"连接件"}]
         * total : 3
         * limit : 10
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
             * categoryFirstName : 电缆输电系统
             * productCategoryIdfk2 : 1
             * productCategoryIdfk1 : 4
             * productNote : 太太太太太
             * productStockSize : 200
             * productAddDate : 1482892496000
             * product_operativet_norm :
             * productIcon : zhengsuPlatform/fileResource/imageUpload/909691fe63594435bd75601068412075.jpg
             * id : 1
             * productName : 36KV 600mm² 连接件
             * productSubname :
             * categorySecondName : 连接件
             */

            private String categoryFirstName;
            private String productCategoryIdfk2;
            private String productCategoryIdfk1;
            private String productNote;
            @SerializedName("productStockSize")
            private int ProductInventory;
            private long productAddDate;
            private String product_operativet_norm;
            @SerializedName("productIcon")
            private String Image;
            @SerializedName("id")
            private String ProductId;
            @SerializedName("productName")
            private String ProductName;
            private String productSubname;
            private String categorySecondName;

            public String getCategoryFirstName() {
                return categoryFirstName;
            }

            public void setCategoryFirstName(String categoryFirstName) {
                this.categoryFirstName = categoryFirstName;
            }

            public String getProductCategoryIdfk2() {
                return productCategoryIdfk2;
            }

            public void setProductCategoryIdfk2(String productCategoryIdfk2) {
                this.productCategoryIdfk2 = productCategoryIdfk2;
            }

            public String getProductCategoryIdfk1() {
                return productCategoryIdfk1;
            }

            public void setProductCategoryIdfk1(String productCategoryIdfk1) {
                this.productCategoryIdfk1 = productCategoryIdfk1;
            }

            public String getProductNote() {
                return productNote;
            }

            public void setProductNote(String productNote) {
                this.productNote = productNote;
            }

            public int getProductInventory() {
                return ProductInventory;
            }

            public void setProductInventory(int ProductInventory) {
                this.ProductInventory = ProductInventory;
            }

            public long getProductAddDate() {
                return productAddDate;
            }

            public void setProductAddDate(long productAddDate) {
                this.productAddDate = productAddDate;
            }

            public String getProduct_operativet_norm() {
                return product_operativet_norm;
            }

            public void setProduct_operativet_norm(String product_operativet_norm) {
                this.product_operativet_norm = product_operativet_norm;
            }

            public String getImage() {
                return Image;
            }

            public void setImage(String Image) {
                this.Image = Image;
            }

            public String getProductId() {
                return ProductId;
            }

            public void setProductId(String ProductId) {
                this.ProductId = ProductId;
            }

            public String getProductName() {
                return ProductName;
            }

            public void setProductName(String ProductName) {
                this.ProductName = ProductName;
            }

            public String getProductSubname() {
                return productSubname;
            }

            public void setProductSubname(String productSubname) {
                this.productSubname = productSubname;
            }

            public String getCategorySecondName() {
                return categorySecondName;
            }

            public void setCategorySecondName(String categorySecondName) {
                this.categorySecondName = categorySecondName;
            }
        }
    }
}
