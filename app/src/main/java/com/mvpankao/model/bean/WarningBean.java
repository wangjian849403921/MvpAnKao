package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2016/12/30
 */

public class WarningBean {


    /**
     * code : 200
     * result : 获取列表成功
     * object : {"list":[{"alarm_name":"接地电流B1组A相电流","alarm_begindate":1482920891000,"alarm_status":"已解除","alarm_level":"高","id":1,"alarm_parm_name":"光纤测温","alarm_assetsname":"电力部杭州机械设计研究所"},{"alarm_name":"电流大暴走","alarm_begindate":1482921785000,"alarm_status":"未解除","alarm_level":"中","id":2,"alarm_parm_name":"接地电流","alarm_assetsname":"帝国大厦"},{"alarm_name":"室外避雷针电流负载过大","alarm_begindate":1484040096000,"alarm_status":"已解除","alarm_level":"低","id":3,"alarm_parm_name":"光纤测温","alarm_assetsname":"出门左拐小卖部"},{"alarm_name":"黑子袭击","alarm_begindate":1484040099000,"alarm_status":"未解除","alarm_level":"高","id":4,"alarm_parm_name":"接地电流","alarm_assetsname":"铁心桥AC米兰花厂"}],"total":4,"limit":10,"pages":1,"pageNumber":1,"isFirstPage":true,"isLastPage":false,"navigatePageNumbers":[1],"limitStart":0}
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
         * list : [{"alarm_name":"接地电流B1组A相电流","alarm_begindate":1482920891000,"alarm_status":"已解除","alarm_level":"高","id":1,"alarm_parm_name":"光纤测温","alarm_assetsname":"电力部杭州机械设计研究所"},{"alarm_name":"电流大暴走","alarm_begindate":1482921785000,"alarm_status":"未解除","alarm_level":"中","id":2,"alarm_parm_name":"接地电流","alarm_assetsname":"帝国大厦"},{"alarm_name":"室外避雷针电流负载过大","alarm_begindate":1484040096000,"alarm_status":"已解除","alarm_level":"低","id":3,"alarm_parm_name":"光纤测温","alarm_assetsname":"出门左拐小卖部"},{"alarm_name":"黑子袭击","alarm_begindate":1484040099000,"alarm_status":"未解除","alarm_level":"高","id":4,"alarm_parm_name":"接地电流","alarm_assetsname":"铁心桥AC米兰花厂"}]
         * total : 4
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
             * alarm_name : 接地电流B1组A相电流
             * alarm_begindate : 1482920891000
             * alarm_status : 已解除
             * alarm_level : 高
             * id : 1
             * alarm_parm_name : 光纤测温
             * alarm_assetsname : 电力部杭州机械设计研究所
             */

            private String alarm_name;
            private long alarm_begindate;
            private String alarm_status;
            private String alarm_level;
            private String id;
            private String alarm_parm_name;
            private String alarm_assetsname;

            public String getAlarm_name() {
                return alarm_name;
            }

            public void setAlarm_name(String alarm_name) {
                this.alarm_name = alarm_name;
            }

            public long getAlarm_begindate() {
                return alarm_begindate;
            }

            public void setAlarm_begindate(long alarm_begindate) {
                this.alarm_begindate = alarm_begindate;
            }

            public String getAlarm_status() {
                return alarm_status;
            }

            public void setAlarm_status(String alarm_status) {
                this.alarm_status = alarm_status;
            }

            public String getAlarm_level() {
                return alarm_level;
            }

            public void setAlarm_level(String alarm_level) {
                this.alarm_level = alarm_level;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAlarm_parm_name() {
                return alarm_parm_name;
            }

            public void setAlarm_parm_name(String alarm_parm_name) {
                this.alarm_parm_name = alarm_parm_name;
            }

            public String getAlarm_assetsname() {
                return alarm_assetsname;
            }

            public void setAlarm_assetsname(String alarm_assetsname) {
                this.alarm_assetsname = alarm_assetsname;
            }
        }
    }
}
