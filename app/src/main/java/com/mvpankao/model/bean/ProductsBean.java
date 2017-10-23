package com.mvpankao.model.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/19
 */

public class ProductsBean {

    /**
     * code : 200
     * result : 查询成功
     * object : {"list":[{"id":1,"productName":"36KV 600mm² 连接件","productSubname":"","productStockSize":200,"productAddDate":1482892496000,"productCategoryIdfk1":1,"productCategoryIdfk2":1,"productNote":"太太太太太","productIcon":"zhengsuPlatform/fileResource/imageUpload/909691fe63594435bd75601068412075.jpg","productOperativetNorm":""}],"total":1,"limit":20,"pages":1,"pageNumber":1,"isFirstPage":true,"isLastPage":false,"navigatePageNumbers":[1],"limitStart":0}
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
         * list : [{"id":1,"productName":"36KV 600mm² 连接件","productSubname":"","productStockSize":200,"productAddDate":1482892496000,"productCategoryIdfk1":1,"productCategoryIdfk2":1,"productNote":"太太太太太","productIcon":"zhengsuPlatform/fileResource/imageUpload/909691fe63594435bd75601068412075.jpg","productOperativetNorm":""}]
         * total : 1
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

        public static class ListBean implements Serializable {
            /**
             * id : 1
             * productName : 36KV 600mm² 连接件
             * productSubname :
             * productStockSize : 200
             * productAddDate : 1482892496000
             * productCategoryIdfk1 : 1
             * productCategoryIdfk2 : 1
             * productNote : 太太太太太
             * productIcon : zhengsuPlatform/fileResource/imageUpload/909691fe63594435bd75601068412075.jpg
             * productOperativetNorm :
             */
            //shopcarid
            String ShopCarid;
            String productParam;

            private int productStockDetailIdfk;

            @SerializedName("id")
            private String productId;
            private String productName;
            private String productSubname;
            @SerializedName("productStockSize")
            private int productInventory;
            private long productAddDate;
            private int productCategoryIdfk1;
            private int productCategoryIdfk2;
            private String productNote;
            @SerializedName("productIcon")
            private String Image;
            private String productOperativetNorm;
            private int productCount;

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

            public int getProductCount() {
                return productCount;
            }

            public void setProductCount(int productCount) {
                this.productCount = productCount;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
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

            public int getProductInventory() {
                return productInventory;
            }

            public void setProductInventory(int productInventory) {
                this.productInventory = productInventory;
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

            public String getImage() {
                return Image;
            }

            public void setImage(String Image) {
                this.Image = Image;
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
