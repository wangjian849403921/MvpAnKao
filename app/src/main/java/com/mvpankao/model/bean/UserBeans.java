package com.mvpankao.model.bean;

import java.util.List;

/**
 * Created by wangjian
 * On  2017/1/13
 */

public class UserBeans {


    /**
     * code : 200
     * result : 登陆成功
     * object : {"receiptAddress":[{"id":11,"userId":4,"receiveName":"施文","receiveAddress":"12358","receivePhone":"13776614680","receiveAreaCode":null,"isdefault":1,"province":"新疆","citys":"克孜勒苏州","areas":"阿合奇县阿合奇镇"}],"user":{"id":4,"username":"wangjian","passwd":"","nickname":"望京","sex":1,"telephone":"13585125792","icon":"fileResource/imageUpload/005f32eb63474b749e8dc04f18c74a1c.jpg","email":"","isValid":1,"loginTime":1486373786000,"createDate":1484297627000,"updateDate":null,"inType":null,"isBind":null,"channel":null,"role":1},"token":"ad154f0def5c4d1498b0dca4a19a13e5"}
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
         * receiptAddress : [{"id":11,"userId":4,"receiveName":"施文","receiveAddress":"12358","receivePhone":"13776614680","receiveAreaCode":null,"isdefault":1,"province":"新疆","citys":"克孜勒苏州","areas":"阿合奇县阿合奇镇"}]
         * user : {"id":4,"username":"wangjian","passwd":"","nickname":"望京","sex":1,"telephone":"13585125792","icon":"fileResource/imageUpload/005f32eb63474b749e8dc04f18c74a1c.jpg","email":"","isValid":1,"loginTime":1486373786000,"createDate":1484297627000,"updateDate":null,"inType":null,"isBind":null,"channel":null,"role":1}
         * token : ad154f0def5c4d1498b0dca4a19a13e5
         */

        private UserBean user;
        private String token;
        private List<ReceiptAddressBean> receiptAddress;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<ReceiptAddressBean> getReceiptAddress() {
            return receiptAddress;
        }

        public void setReceiptAddress(List<ReceiptAddressBean> receiptAddress) {
            this.receiptAddress = receiptAddress;
        }

        public static class UserBean {
            /**
             * id : 4
             * username : wangjian
             * passwd :
             * nickname : 望京
             * sex : 1
             * telephone : 13585125792
             * icon : fileResource/imageUpload/005f32eb63474b749e8dc04f18c74a1c.jpg
             * email :
             * isValid : 1
             * loginTime : 1486373786000
             * createDate : 1484297627000
             * updateDate : null
             * inType : null
             * isBind : null
             * channel : null
             * role : 1
             */

            private String id;
            private String username;
            private String passwd;
            private String nickname;
            private int sex;
            private String telephone;
            private String icon;
            private String email;
            private int isValid;
            private long loginTime;
            private long createDate;
            private Object updateDate;
            private Object inType;
            private Object isBind;
            private Object channel;
            private String role;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPasswd() {
                return passwd;
            }

            public void setPasswd(String passwd) {
                this.passwd = passwd;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getIsValid() {
                return isValid;
            }

            public void setIsValid(int isValid) {
                this.isValid = isValid;
            }

            public long getLoginTime() {
                return loginTime;
            }

            public void setLoginTime(long loginTime) {
                this.loginTime = loginTime;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public Object getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(Object updateDate) {
                this.updateDate = updateDate;
            }

            public Object getInType() {
                return inType;
            }

            public void setInType(Object inType) {
                this.inType = inType;
            }

            public Object getIsBind() {
                return isBind;
            }

            public void setIsBind(Object isBind) {
                this.isBind = isBind;
            }

            public Object getChannel() {
                return channel;
            }

            public void setChannel(Object channel) {
                this.channel = channel;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }

        public static class ReceiptAddressBean {
            /**
             * id : 11
             * userId : 4
             * receiveName : 施文
             * receiveAddress : 12358
             * receivePhone : 13776614680
             * receiveAreaCode : null
             * isdefault : 1
             * province : 新疆
             * citys : 克孜勒苏州
             * areas : 阿合奇县阿合奇镇
             */

            private String id;
            private int userId;
            private String receiveName;
            private String receiveAddress;
            private String receivePhone;
            private Object receiveAreaCode;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
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

            public Object getReceiveAreaCode() {
                return receiveAreaCode;
            }

            public void setReceiveAreaCode(Object receiveAreaCode) {
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
}
