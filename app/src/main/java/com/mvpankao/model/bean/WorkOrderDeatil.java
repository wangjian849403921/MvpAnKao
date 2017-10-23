package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/11
 */

public class WorkOrderDeatil {

    /**
     * code : 200
     * result : 获取数据成功
     * object : {"create_user_id":4,"engineering_type_idfk":2,"executor":[],"executorPlanBeginDate":1487174400000,"engineering_status":0,"createUserName":"wangjian","executorPlanEndDate":1487433600000,"engineeringLogCount":0,"engineeringTypeName":"报警工单","engineeringNote":"时间:2016-12-28 18:28:11\n位置:住院楼\n级别:高级\n变相说明:设备在工作过程中，因某种原因\u201c丧失规定功能\u201d或危害安全现象","engineeringId":17,"engineering_asset_position":"住院楼"}
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
         * create_user_id : 4
         * engineering_type_idfk : 2
         * executor : []
         * executorPlanBeginDate : 1487174400000
         * engineering_status : 0
         * createUserName : wangjian
         * executorPlanEndDate : 1487433600000
         * engineeringLogCount : 0
         * engineeringTypeName : 报警工单
         * engineeringNote : 时间:2016-12-28 18:28:11
         位置:住院楼
         级别:高级
         变相说明:设备在工作过程中，因某种原因“丧失规定功能”或危害安全现象
         * engineeringId : 17
         * engineering_asset_position : 住院楼
         */

        private String create_user_id;
        private String engineering_type_idfk;
        private long executorPlanBeginDate;
        private int engineering_status;
        private String createUserName;
        private long executorPlanEndDate;
        private String engineeringLogCount;
        private String engineeringTypeName;
        private String engineeringNote;
        private String engineeringId;
        private String engineering_asset_position;
        private String engineering_asset_extent;
        private String engineeringIcon;
        private List<String> executor;
        private long engineeringCreateDate;
        private long executorActualEndDate;

        public long getEngineeringCreateDate() {
            return engineeringCreateDate;
        }

        public void setEngineeringCreateDate(long engineeringCreateDate) {
            this.engineeringCreateDate = engineeringCreateDate;
        }

        public long getExecutorActualEndDate() {
            return executorActualEndDate;
        }

        public void setExecutorActualEndDate(long executorActualEndDate) {
            this.executorActualEndDate = executorActualEndDate;
        }

        public String getEngineeringIcon() {
            return engineeringIcon;
        }

        public void setEngineeringIcon(String engineeringIcon) {
            this.engineeringIcon = engineeringIcon;
        }

        public String getEngineering_asset_extent() {
            return engineering_asset_extent;
        }

        public void setEngineering_asset_extent(String engineering_asset_extent) {
            this.engineering_asset_extent = engineering_asset_extent;
        }

        public String getCreate_user_id() {
            return create_user_id;
        }

        public void setCreate_user_id(String create_user_id) {
            this.create_user_id = create_user_id;
        }

        public String getEngineering_type_idfk() {
            return engineering_type_idfk;
        }

        public void setEngineering_type_idfk(String engineering_type_idfk) {
            this.engineering_type_idfk = engineering_type_idfk;
        }

        public long getExecutorPlanBeginDate() {
            return executorPlanBeginDate;
        }

        public void setExecutorPlanBeginDate(long executorPlanBeginDate) {
            this.executorPlanBeginDate = executorPlanBeginDate;
        }

        public int getEngineering_status() {
            return engineering_status;
        }

        public void setEngineering_status(int engineering_status) {
            this.engineering_status = engineering_status;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public long getExecutorPlanEndDate() {
            return executorPlanEndDate;
        }

        public void setExecutorPlanEndDate(long executorPlanEndDate) {
            this.executorPlanEndDate = executorPlanEndDate;
        }

        public String getEngineeringLogCount() {
            return engineeringLogCount;
        }

        public void setEngineeringLogCount(String engineeringLogCount) {
            this.engineeringLogCount = engineeringLogCount;
        }

        public String getEngineeringTypeName() {
            return engineeringTypeName;
        }

        public void setEngineeringTypeName(String engineeringTypeName) {
            this.engineeringTypeName = engineeringTypeName;
        }

        public String getEngineeringNote() {
            return engineeringNote;
        }

        public void setEngineeringNote(String engineeringNote) {
            this.engineeringNote = engineeringNote;
        }

        public String getEngineeringId() {
            return engineeringId;
        }

        public void setEngineeringId(String engineeringId) {
            this.engineeringId = engineeringId;
        }

        public String getEngineering_asset_position() {
            return engineering_asset_position;
        }

        public void setEngineering_asset_position(String engineering_asset_position) {
            this.engineering_asset_position = engineering_asset_position;
        }

        public List<String> getExecutor() {
            return executor;
        }

        public void setExecutor(List<String> executor) {
            this.executor = executor;
        }
    }
}
