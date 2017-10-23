package com.mvpankao.model.bean;

import java.util.List;

/**
 * description：
 *
 * @auth wangjian
 * @time 2017/4/18 14:23
 */

public class WorkFlowDetailBean2 {

    /**
     * code : 200
     * result : 查询完成
     * object : [{"id":309,"workflowProIdFk":19,"workflowStepName":"产品存储状态","workflowStepOrder":1,"workflowStepStatus":2,"workflowStepCreateDate":1487303467000,"workflowStepUpdateDate":1487731830000,"workflowStepEndDate":1487731830000}]
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
         * id : 309
         * workflowProIdFk : 19
         * workflowStepName : 产品存储状态
         * workflowStepOrder : 1
         * workflowStepStatus : 2
         * workflowStepCreateDate : 1487303467000
         * workflowStepUpdateDate : 1487731830000
         * workflowStepEndDate : 1487731830000
         */

        private int id;
        private int workflowProIdFk;
        private String workflowStepName;
        private int workflowStepOrder;
        private int workflowStepStatus;
        private long workflowStepCreateDate;
        private long workflowStepUpdateDate;
        private long workflowStepEndDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWorkflowProIdFk() {
            return workflowProIdFk;
        }

        public void setWorkflowProIdFk(int workflowProIdFk) {
            this.workflowProIdFk = workflowProIdFk;
        }

        public String getWorkflowStepName() {
            return workflowStepName;
        }

        public void setWorkflowStepName(String workflowStepName) {
            this.workflowStepName = workflowStepName;
        }

        public int getWorkflowStepOrder() {
            return workflowStepOrder;
        }

        public void setWorkflowStepOrder(int workflowStepOrder) {
            this.workflowStepOrder = workflowStepOrder;
        }

        public int getWorkflowStepStatus() {
            return workflowStepStatus;
        }

        public void setWorkflowStepStatus(int workflowStepStatus) {
            this.workflowStepStatus = workflowStepStatus;
        }

        public long getWorkflowStepCreateDate() {
            return workflowStepCreateDate;
        }

        public void setWorkflowStepCreateDate(long workflowStepCreateDate) {
            this.workflowStepCreateDate = workflowStepCreateDate;
        }

        public long getWorkflowStepUpdateDate() {
            return workflowStepUpdateDate;
        }

        public void setWorkflowStepUpdateDate(long workflowStepUpdateDate) {
            this.workflowStepUpdateDate = workflowStepUpdateDate;
        }

        public long getWorkflowStepEndDate() {
            return workflowStepEndDate;
        }

        public void setWorkflowStepEndDate(long workflowStepEndDate) {
            this.workflowStepEndDate = workflowStepEndDate;
        }
    }
}
