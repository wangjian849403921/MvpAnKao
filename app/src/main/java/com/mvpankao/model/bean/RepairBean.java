package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/11
 */

public class RepairBean {

    /**
     * code : 200
     * result : 查询成功
     * object : {"data":{"list":[{"repairCircuit":"ewqeq","repairStatus":0,"repairTypeName":"电路故障变化","repairCreateDate":1482576267000,"id":1}],"total":1,"limit":10,"pages":1,"pageNumber":1,"isFirstPage":true,"isLastPage":false,"navigatePageNumbers":[1],"limitStart":0}}
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
         * data : {"list":[{"repairCircuit":"ewqeq","repairStatus":0,"repairTypeName":"电路故障变化","repairCreateDate":1482576267000,"id":1}],"total":1,"limit":10,"pages":1,"pageNumber":1,"isFirstPage":true,"isLastPage":false,"navigatePageNumbers":[1],"limitStart":0}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * list : [{"repairCircuit":"ewqeq","repairStatus":0,"repairTypeName":"电路故障变化","repairCreateDate":1482576267000,"id":1}]
             * total : 1
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
                 * repairCircuit : ewqeq
                 * repairStatus : 0
                 * repairTypeName : 电路故障变化
                 * repairCreateDate : 1482576267000
                 * id : 1
                 */

                private String repairCircuit;
                private int repairStatus;
                private String repairTypeName;
                private long repairCreateDate;
                private String id;

                public String getRepairCircuit() {
                    return repairCircuit;
                }

                public void setRepairCircuit(String repairCircuit) {
                    this.repairCircuit = repairCircuit;
                }

                public int getRepairStatus() {
                    return repairStatus;
                }

                public void setRepairStatus(int repairStatus) {
                    this.repairStatus = repairStatus;
                }

                public String getRepairTypeName() {
                    return repairTypeName;
                }

                public void setRepairTypeName(String repairTypeName) {
                    this.repairTypeName = repairTypeName;
                }

                public long getRepairCreateDate() {
                    return repairCreateDate;
                }

                public void setRepairCreateDate(long repairCreateDate) {
                    this.repairCreateDate = repairCreateDate;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}
