package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/2/17
 */

public class WorkOrderLogBean {


    /**
     * code : 200
     * result : 获取列表成功
     * object : [{"imgUrlList":["zhengsuPlatform/fileResource/imageUpload/de17375eb9654f87aaca3e00478fe0df.jpg","123456.jpg"],"logCreateTime":1484227924000,"engineeringIdfk":1,"logComment":"测试","id":3,"logAttachmentUrl":"zhengsuPlatform/fileResource/imageUpload/de17375eb9654f87aaca3e00478fe0df.jpg;123456.jpg","username":"王超"}]
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
         * imgUrlList : ["zhengsuPlatform/fileResource/imageUpload/de17375eb9654f87aaca3e00478fe0df.jpg","123456.jpg"]
         * logCreateTime : 1484227924000
         * engineeringIdfk : 1
         * logComment : 测试
         * id : 3
         * logAttachmentUrl : zhengsuPlatform/fileResource/imageUpload/de17375eb9654f87aaca3e00478fe0df.jpg;123456.jpg
         * username : 王超
         */

        private long logCreateTime;
        private String engineeringIdfk;
        private String logComment;
        private String id;
        private String logAttachmentUrl;
        private String username;
        private List<String> imgUrlList;

        public long getLogCreateTime() {
            return logCreateTime;
        }

        public void setLogCreateTime(long logCreateTime) {
            this.logCreateTime = logCreateTime;
        }

        public String getEngineeringIdfk() {
            return engineeringIdfk;
        }

        public void setEngineeringIdfk(String engineeringIdfk) {
            this.engineeringIdfk = engineeringIdfk;
        }

        public String getLogComment() {
            return logComment;
        }

        public void setLogComment(String logComment) {
            this.logComment = logComment;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogAttachmentUrl() {
            return logAttachmentUrl;
        }

        public void setLogAttachmentUrl(String logAttachmentUrl) {
            this.logAttachmentUrl = logAttachmentUrl;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getImgUrlList() {
            return imgUrlList;
        }

        public void setImgUrlList(List<String> imgUrlList) {
            this.imgUrlList = imgUrlList;
        }
    }
}
