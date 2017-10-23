/**
 * @Title: ItemBean.java
 * @Package com.example.test
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @date 2014-6-25 ����9:45:29
 */
package com.mvpankao.model.bean;

/**
 * 我的预约 展开的item
 */
public class ParamChildrenItem {

    private String name;

    private String data;

    public ParamChildrenItem() {
    }


    public ParamChildrenItem(String name, String data) {
        this.data = data;
        this.name = name;

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
