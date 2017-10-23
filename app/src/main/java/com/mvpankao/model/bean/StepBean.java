package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/2/8
 */

public class StepBean {

    /**
     * code : 200
     * result : 提交成功
     * object : [{"id":1,"workflowStepIdFk":11,"workflowStepOperationStatus":2,"workflowStepCommentCreateDate":1486534478000,"workflowStepVerifyUpdateDate":1486537841000,"workflowStepOperationName":"王超","workflowStepComment":"测试","workflowStepIcon":"fileResource/workflow/a8b1b2c8da1849ca84d9d2293e027ea8.jpg;fileResource/workflow/a8b1b2c8da1849ca84d9d2293e027ea8.jpg","workflowStepVerifyComment":" 安装的不对","iconList":["fileResource/workflow/a8b1b2c8da1849ca84d9d2293e027ea8.jpg","fileResource/workflow/a8b1b2c8da1849ca84d9d2293e027ea8.jpg"]},{"id":2,"workflowStepIdFk":11,"workflowStepOperationStatus":2,"workflowStepCommentCreateDate":1486538482000,"workflowStepVerifyUpdateDate":null,"workflowStepOperationName":"王继伟","workflowStepComment":"测试2","workflowStepIcon":null,"workflowStepVerifyComment":"2","iconList":[]}]
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
         * id : 1
         * workflowStepIdFk : 11
         * workflowStepOperationStatus : 2
         * workflowStepCommentCreateDate : 1486534478000
         * workflowStepVerifyUpdateDate : 1486537841000
         * workflowStepOperationName : 王超
         * workflowStepComment : 测试
         * workflowStepIcon : fileResource/workflow/a8b1b2c8da1849ca84d9d2293e027ea8.jpg;fileResource/workflow/a8b1b2c8da1849ca84d9d2293e027ea8.jpg
         * workflowStepVerifyComment :  安装的不对
         * iconList : ["fileResource/workflow/a8b1b2c8da1849ca84d9d2293e027ea8.jpg","fileResource/workflow/a8b1b2c8da1849ca84d9d2293e027ea8.jpg"]
         */

        private String id;
        private String workflowStepIdFk;
        private String workflowStepOperationStatus;
        private long workflowStepCommentCreateDate;
        private long workflowStepVerifyUpdateDate;
        private String workflowStepOperationName;
        private String workflowStepComment;
        private String workflowStepIcon;
        private String workflowStepVerifyComment;
        private List<String> iconList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWorkflowStepIdFk() {
            return workflowStepIdFk;
        }

        public void setWorkflowStepIdFk(String workflowStepIdFk) {
            this.workflowStepIdFk = workflowStepIdFk;
        }

        public String getWorkflowStepOperationStatus() {
            return workflowStepOperationStatus;
        }

        public void setWorkflowStepOperationStatus(String workflowStepOperationStatus) {
            this.workflowStepOperationStatus = workflowStepOperationStatus;
        }

        public long getWorkflowStepCommentCreateDate() {
            return workflowStepCommentCreateDate;
        }

        public void setWorkflowStepCommentCreateDate(long workflowStepCommentCreateDate) {
            this.workflowStepCommentCreateDate = workflowStepCommentCreateDate;
        }

        public long getWorkflowStepVerifyUpdateDate() {
            return workflowStepVerifyUpdateDate;
        }

        public void setWorkflowStepVerifyUpdateDate(long workflowStepVerifyUpdateDate) {
            this.workflowStepVerifyUpdateDate = workflowStepVerifyUpdateDate;
        }

        public String getWorkflowStepOperationName() {
            return workflowStepOperationName;
        }

        public void setWorkflowStepOperationName(String workflowStepOperationName) {
            this.workflowStepOperationName = workflowStepOperationName;
        }

        public String getWorkflowStepComment() {
            return workflowStepComment;
        }

        public void setWorkflowStepComment(String workflowStepComment) {
            this.workflowStepComment = workflowStepComment;
        }

        public String getWorkflowStepIcon() {
            return workflowStepIcon;
        }

        public void setWorkflowStepIcon(String workflowStepIcon) {
            this.workflowStepIcon = workflowStepIcon;
        }

        public String getWorkflowStepVerifyComment() {
            return workflowStepVerifyComment;
        }

        public void setWorkflowStepVerifyComment(String workflowStepVerifyComment) {
            this.workflowStepVerifyComment = workflowStepVerifyComment;
        }

        public List<String> getIconList() {
            return iconList;
        }

        public void setIconList(List<String> iconList) {
            this.iconList = iconList;
        }
    }
}
