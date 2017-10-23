package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/14
 */

public class Address {


    /**
     * code : 200
     * result : 查询成功
     * object : [{"id":2,"userId":2,"receiveName":"汪建","receiveAddress":"","receivePhone":"15274757686","receiveAreaCode":"","isdefault":0,"province":"江苏省","citys":"南京市","areas":"江宁区"},{"id":3,"userId":2,"receiveName":"汪建","receiveAddress":"","receivePhone":"15274757686","receiveAreaCode":"","isdefault":0,"province":"江苏省","citys":"南京市","areas":"江宁区"},{"id":5,"userId":2,"receiveName":"汪建","receiveAddress":"","receivePhone":"15274757686","receiveAreaCode":"","isdefault":1,"province":"江苏省","citys":"南京市","areas":"江宁区"}]
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
         * id : 2
         * userId : 2
         * receiveName : 汪建
         * receiveAddress :
         * receivePhone : 15274757686
         * receiveAreaCode :
         * isdefault : 0
         * province : 江苏省
         * citys : 南京市
         * areas : 江宁区
         */

        private String id;
        private String userId;
        private String receiveName;
        private String receiveAddress;
        private String receivePhone;
        private String receiveAreaCode;
        private int isdefault;
        private String province;
        private String citys;
        private String areas;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public String getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
        }

        public String getReceivePhone() {
            return receivePhone;
        }

        public void setReceivePhone(String receivePhone) {
            this.receivePhone = receivePhone;
        }

        public String getReceiveAreaCode() {
            return receiveAreaCode;
        }

        public void setReceiveAreaCode(String receiveAreaCode) {
            this.receiveAreaCode = receiveAreaCode;
        }

        public int getIsdefault() {
            return isdefault;
        }

        public void setIsdefault(int isdefault) {
            this.isdefault = isdefault;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCitys() {
            return citys;
        }

        public void setCitys(String citys) {
            this.citys = citys;
        }

        public String getAreas() {
            return areas;
        }

        public void setAreas(String areas) {
            this.areas = areas;
        }
    }
}
