package com.mvpankao.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/17
 */

public class RepairDetailBean {


    /**
     * code : 200
     * result : 查询成功
     * object : {"repair":{"repairPhone":"13057552806","repair_type_name":"电路故障变化","repairStatus":1,"repairActualEndTime":1484634097000,"repairPlanEndDate":1484634092000,"repairCircuit":"2333","repairNote":"测试","repairCompany":"没公司","repairGpsAddress":"南京市江宁区","repairCreateDate":1484634080000,"repairPlanBeginDate":1484634090000,"id":16,"username":"wangjian","repairAddress":"东城区"},"repairExecutors":["wangjian1","zuo","Mars","wangjian"],"repailogList":[{"id":2,"repairIdFk":16,"repairLogTime":1483605953000,"repairLogNote":"修理中"},{"id":1,"repairIdFk":16,"repairLogTime":1483605442000,"repairLogNote":"修理前"}]}
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
         * repair : {"repairPhone":"13057552806","repair_type_name":"电路故障变化","repairStatus":1,"repairActualEndTime":1484634097000,"repairPlanEndDate":1484634092000,"repairCircuit":"2333","repairNote":"测试","repairCompany":"没公司","repairGpsAddress":"南京市江宁区","repairCreateDate":1484634080000,"repairPlanBeginDate":1484634090000,"id":16,"username":"wangjian","repairAddress":"东城区"}
         * repairExecutors : ["wangjian1","zuo","Mars","wangjian"]
         * repailogList : [{"id":2,"repairIdFk":16,"repairLogTime":1483605953000,"repairLogNote":"修理中"},{"id":1,"repairIdFk":16,"repairLogTime":1483605442000,"repairLogNote":"修理前"}]
         */

        private RepairBean repair;
        private List<String> repairExecutors;
        private List<RepailogListBean> repailogList;

        public RepairBean getRepair() {
            return repair;
        }

        public void setRepair(RepairBean repair) {
            this.repair = repair;
        }

        public List<String> getRepairExecutors() {
            return repairExecutors;
        }

        public void setRepairExecutors(List<String> repairExecutors) {
            this.repairExecutors = repairExecutors;
        }

        public List<RepailogListBean> getRepailogList() {
            return repailogList;
        }

        public void setRepailogList(List<RepailogListBean> repailogList) {
            this.repailogList = repailogList;
        }

        public static class RepairBean {
            /**
             * repairPhone : 13057552806
             * repair_type_name : 电路故障变化
             * repairStatus : 1
             * repairActualEndTime : 1484634097000
             * repairPlanEndDate : 1484634092000
             * repairCircuit : 2333
             * repairNote : 测试
             * repairCompany : 没公司
             * repairGpsAddress : 南京市江宁区
             * repairCreateDate : 1484634080000
             * repairPlanBeginDate : 1484634090000
             * id : 16
             * username : wangjian
             * repairAddress : 东城区
             */

            @SerializedName("repairPhone")
            private String phoneNum;
            private String repair_type_name;
            @SerializedName("repairStatus")
            private int statue;
            private long repairActualEndTime;
            private long repairPlanEndDate;
            private String repairCircuit;
            @SerializedName("repairNote")
            private String remarks;
            @SerializedName("repairCompany")
            private String company;
            @SerializedName("repairGpsAddress")
            private String area;
            private long repairCreateDate;
            private long repairPlanBeginDate;
            private int id;
            private String username;
            @SerializedName("repairAddress")
            private String detailaddress;

            public String getPhoneNum() {
                return phoneNum;
            }

            public void setPhoneNum(String phoneNum) {
                this.phoneNum = phoneNum;
            }

            public String getRepair_type_name() {
                return repair_type_name;
            }

            public void setRepair_type_name(String repair_type_name) {
                this.repair_type_name = repair_type_name;
            }

            public int getStatue() {
                return statue;
            }

            public void setStatue(int statue) {
                this.statue = statue;
            }

            public long getRepairActualEndTime() {
                return repairActualEndTime;
            }

            public void setRepairActualEndTime(long repairActualEndTime) {
                this.repairActualEndTime = repairActualEndTime;
            }

            public long getRepairPlanEndDate() {
                return repairPlanEndDate;
            }

            public void setRepairPlanEndDate(long repairPlanEndDate) {
                this.repairPlanEndDate = repairPlanEndDate;
            }

            public String getRepairCircuit() {
                return repairCircuit;
            }

            public void setRepairCircuit(String repairCircuit) {
                this.repairCircuit = repairCircuit;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public long getRepairCreateDate() {
                return repairCreateDate;
            }

            public void setRepairCreateDate(long repairCreateDate) {
                this.repairCreateDate = repairCreateDate;
            }

            public long getRepairPlanBeginDate() {
                return repairPlanBeginDate;
            }

            public void setRepairPlanBeginDate(long repairPlanBeginDate) {
                this.repairPlanBeginDate = repairPlanBeginDate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getDetailaddress() {
                return detailaddress;
            }

            public void setDetailaddress(String detailaddress) {
                this.detailaddress = detailaddress;
            }
        }

        public static class RepailogListBean {
            /**
             * id : 2
             * repairIdFk : 16
             * repairLogTime : 1483605953000
             * repairLogNote : 修理中
             */

            private int id;
            private int repairIdFk;
            @SerializedName("repairLogTime")
            private long time;
            @SerializedName("repairLogNote")
            private String log;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRepairIdFk() {
                return repairIdFk;
            }

            public void setRepairIdFk(int repairIdFk) {
                this.repairIdFk = repairIdFk;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getLog() {
                return log;
            }

            public void setLog(String log) {
                this.log = log;
            }
        }
    }
}
