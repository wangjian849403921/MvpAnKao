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
public class WorkOrderChildrenItem {
    private String id;
    private String time;
    private String endtime;
    private String name;
    private String workOrderContent;
    private String overdue;

    public WorkOrderChildrenItem() {
    }


    public WorkOrderChildrenItem(String name, String time, String workOrderContent, String overdue) {
        this.time = time;
        this.name = name;
        this.workOrderContent = workOrderContent;
        this.overdue = overdue;
    }

    public WorkOrderChildrenItem(String id, String name, String time, String endtime, String workOrderContent, String overdue) {
        this.id = id;
        this.time = time;
        this.endtime = endtime;
        this.name = name;
        this.workOrderContent = workOrderContent;
        this.overdue = overdue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkOrderContent() {

        return workOrderContent;
    }

    public void setWorkOrderContent(String workOrderContent) {
        this.workOrderContent = workOrderContent;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }
}
