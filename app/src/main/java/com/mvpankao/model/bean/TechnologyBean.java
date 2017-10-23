package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/12
 */

public class TechnologyBean {

    /**
     * code : 200
     * result : 查询成功
     * object : {"list":[{"id":4,"docTitle":"我打我的","docUrl":"zhengsuPlatform/fileResource/docment/344a6e7a3852489b9623bddd60fd7490.xlsx","docCreateTime":1483689969000}],"total":1,"limit":20,"pages":1,"pageNumber":1,"isFirstPage":true,"isLastPage":false,"navigatePageNumbers":[1],"limitStart":0}
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
         * list : [{"id":4,"docTitle":"我打我的","docUrl":"zhengsuPlatform/fileResource/docment/344a6e7a3852489b9623bddd60fd7490.xlsx","docCreateTime":1483689969000}]
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

        public static class ListBean {
            /**
             * id : 4
             * docTitle : 我打我的
             * docUrl : zhengsuPlatform/fileResource/docment/344a6e7a3852489b9623bddd60fd7490.xlsx
             * docCreateTime : 1483689969000
             */

            private String id;
            private String docTitle;
            private String docUrl;
            private long docCreateTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDocTitle() {
                return docTitle;
            }

            public void setDocTitle(String docTitle) {
                this.docTitle = docTitle;
            }

            public String getDocUrl() {
                return docUrl;
            }

            public void setDocUrl(String docUrl) {
                this.docUrl = docUrl;
            }

            public long getDocCreateTime() {
                return docCreateTime;
            }

            public void setDocCreateTime(long docCreateTime) {
                this.docCreateTime = docCreateTime;
            }
        }
    }
}
