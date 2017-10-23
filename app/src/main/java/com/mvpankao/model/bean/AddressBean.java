package com.mvpankao.model.bean;

/**
 * Created by wangjian
 * On  2016/11/30
 */

public class AddressBean {
    String UserName;

    String PhoneNum;

    String  AddressDetail;

    String  Province;

    String City;

    String Area;

    boolean defaultAddress=false;

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getAddressDetail() {
        return AddressDetail;
    }

    public void setAddressDetail(String address) {
        AddressDetail = address;
    }


}
