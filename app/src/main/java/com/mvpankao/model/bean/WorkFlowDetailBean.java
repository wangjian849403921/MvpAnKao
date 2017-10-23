package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/17
 */

public class WorkFlowDetailBean {

    /**
     * code : 200
     * result : 查询完成
     * object : {"workflowProcesslist":[{"id":1032,"workflowProIdFk":46,"workflowStepName":"产品存储状态","workflowStepOrder":1,"workflowStepStatus":0,"workflowStepCreateDate":1488449480000,"workflowStepUpdateDate":1488449480000,"workflowStepEndDate":1488449480000}],"workflowWorkerlist":[{"sex":1,"nickname":"","isManager":1,"id":114,"userId":8,"username":"晏雷"},{"sex":1,"nickname":"","isManager":0,"id":115,"userId":24,"username":"狄荣宽"},{"sex":1,"nickname":"","isManager":0,"id":116,"userId":27,"username":"陈怀保"},{"sex":1,"nickname":"","isManager":0,"id":117,"userId":28,"username":"吴梦德"}]}
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
        private List<WorkflowProcesslistBean> workflowProcesslist;
        private List<WorkflowWorkerlistBean> workflowWorkerlist;

        public List<WorkflowProcesslistBean> getWorkflowProcesslist() {
            return workflowProcesslist;
        }

        public void setWorkflowProcesslist(List<WorkflowProcesslistBean> workflowProcesslist) {
            this.workflowProcesslist = workflowProcesslist;
        }

        public List<WorkflowWorkerlistBean> getWorkflowWorkerlist() {
            return workflowWorkerlist;
        }

        public void setWorkflowWorkerlist(List<WorkflowWorkerlistBean> workflowWorkerlist) {
            this.workflowWorkerlist = workflowWorkerlist;
        }

        public static class WorkflowProcesslistBean {
            /**
             * id : 1032
             * workflowProIdFk : 46
             * workflowStepName : 产品存储状态
             * workflowStepOrder : 1
             * workflowStepStatus : 0
             * workflowStepCreateDate : 1488449480000
             * workflowStepUpdateDate : 1488449480000
             * workflowStepEndDate : 1488449480000
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

        public static class WorkflowWorkerlistBean {
            /**
             * sex : 1
             * nickname :
             * isManager : 1
             * id : 114
             * userId : 8
             * username : 晏雷
             */

            private int sex;
            private String nickname;
            private int isManager;
            private int id;
            private int userId;
            private String username;

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getIsManager() {
                return isManager;
            }

            public void setIsManager(int isManager) {
                this.isManager = isManager;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
