package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/11
 */

public class WorkFlowBean {


    /**
     * code : 200
     * result : 查询完成
     * object : {"list":[{"id":6,"workflowFkName":"冰冻大象工程","workflowProName":"南京电厂110KV高压设备安装","workflowProUserId":4,"workflowProIcon":"zhengsuPlatform/fileResource/imageUpload/de17375eb9654f87aaca3e00478fe0df.jpg","workflowProBeginDate":1483787198000,"workflowProEndDate":1485256000000,"workflowProStatus":0}],"total":1,"limit":20,"pages":1,"pageNumber":1,"isFirstPage":true,"isLastPage":false,"navigatePageNumbers":[1],"limitStart":0}
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
         * list : [{"id":6,"workflowFkName":"冰冻大象工程","workflowProName":"南京电厂110KV高压设备安装","workflowProUserId":4,"workflowProIcon":"zhengsuPlatform/fileResource/imageUpload/de17375eb9654f87aaca3e00478fe0df.jpg","workflowProBeginDate":1483787198000,"workflowProEndDate":1485256000000,"workflowProStatus":0}]
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
             * id : 6
             * workflowFkName : 冰冻大象工程
             * workflowProName : 南京电厂110KV高压设备安装
             * workflowProUserId : 4
             * workflowProIcon : zhengsuPlatform/fileResource/imageUpload/de17375eb9654f87aaca3e00478fe0df.jpg
             * workflowProBeginDate : 1483787198000
             * workflowProEndDate : 1485256000000
             * workflowProStatus : 0
             */

            private String id;
            private String workflowFkName;
            private String workflowProName;
            private String workflowProUserId;
            private String workflowProIcon;
            private long workflowProBeginDate;
            private long workflowProEndDate;
            private int workflowProStatus;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWorkflowFkName() {
                return workflowFkName;
            }

            public void setWorkflowFkName(String workflowFkName) {
                this.workflowFkName = workflowFkName;
            }

            public String getWorkflowProName() {
                return workflowProName;
            }

            public void setWorkflowProName(String workflowProName) {
                this.workflowProName = workflowProName;
            }

            public String getWorkflowProUserId() {
                return workflowProUserId;
            }

            public void setWorkflowProUserId(String workflowProUserId) {
                this.workflowProUserId = workflowProUserId;
            }

            public String getWorkflowProIcon() {
                return workflowProIcon;
            }

            public void setWorkflowProIcon(String workflowProIcon) {
                this.workflowProIcon = workflowProIcon;
            }

            public long getWorkflowProBeginDate() {
                return workflowProBeginDate;
            }

            public void setWorkflowProBeginDate(long workflowProBeginDate) {
                this.workflowProBeginDate = workflowProBeginDate;
            }

            public long getWorkflowProEndDate() {
                return workflowProEndDate;
            }

            public void setWorkflowProEndDate(long workflowProEndDate) {
                this.workflowProEndDate = workflowProEndDate;
            }

            public int getWorkflowProStatus() {
                return workflowProStatus;
            }

            public void setWorkflowProStatus(int workflowProStatus) {
                this.workflowProStatus = workflowProStatus;
            }
        }
    }
}
