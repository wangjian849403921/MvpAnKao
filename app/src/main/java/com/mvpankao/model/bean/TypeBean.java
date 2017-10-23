package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/19
 */

public class TypeBean {

    /**
     * code : 1
     * result : 查询成功
     * object : {"repairTypeList":[{"id":1,"repairTypeName":"电缆故障","repairtTypeCreateDate":1483069547000},{"id":2,"repairTypeName":"电缆附件故障","repairtTypeCreateDate":1483532434000},{"id":3,"repairTypeName":"DTS光纤测温系统故障","repairtTypeCreateDate":1484708738000},{"id":4,"repairTypeName":"接地电流监测系统故障","repairtTypeCreateDate":1484708763000},{"id":5,"repairTypeName":"局部放电监测系统故障","repairtTypeCreateDate":1484708784000},{"id":6,"repairTypeName":"其他类故障","repairtTypeCreateDate":1484708886000}]}
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
        private List<RepairTypeListBean> repairTypeList;

        public List<RepairTypeListBean> getRepairTypeList() {
            return repairTypeList;
        }

        public void setRepairTypeList(List<RepairTypeListBean> repairTypeList) {
            this.repairTypeList = repairTypeList;
        }

        public static class RepairTypeListBean {
            /**
             * id : 1
             * repairTypeName : 电缆故障
             * repairtTypeCreateDate : 1483069547000
             */

            private int id;
            private String repairTypeName;
            private long repairtTypeCreateDate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRepairTypeName() {
                return repairTypeName;
            }

            public void setRepairTypeName(String repairTypeName) {
                this.repairTypeName = repairTypeName;
            }

            public long getRepairtTypeCreateDate() {
                return repairtTypeCreateDate;
            }

            public void setRepairtTypeCreateDate(long repairtTypeCreateDate) {
                this.repairtTypeCreateDate = repairtTypeCreateDate;
            }
        }
    }
}
