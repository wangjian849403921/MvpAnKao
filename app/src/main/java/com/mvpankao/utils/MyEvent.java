package com.mvpankao.utils;

/**
 * eventbus通知bean
 */
public class MyEvent {

    Integer mMsg;

    String mData;

    Object mObject;

    public MyEvent(Integer msg, String data, Object obj) {
        mMsg = msg;
        mData = data;
        mObject=obj;
    }

    public MyEvent(Integer msg, String data) {
        mMsg = msg;
        mData = data;
    }
    public MyEvent(Integer msg) {
        mMsg = msg;
    }

    public MyEvent(String data) {
        mData = data;
    }

    public Integer getMsg() {
        return mMsg;
    }

    public String getData() {
        return mData;

    }

    public Object getObject() {
        return mObject;
    }

}
