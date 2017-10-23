package com.mvpankao.model.bean;

import java.util.ArrayList;

/**
 * @author wangjian����
 *         <p>
 *         ��������ʵ��
 */

public class ParentEntity {

    private int groupColor;

    private String groupName;

    private String groupId;

    private String icon;
    private String  level;

    private ArrayList<ChildEntity> childs;

	
	/* ==========================================================
     * ======================= get method =======================
	 * ========================================================== */

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIcon() {

        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getGroupColor() {
        return groupColor;
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<ChildEntity> getChilds() {
        return childs;
    }
	
	/* ==========================================================
	 * ======================= set method =======================
	 * ========================================================== */

    public void setGroupColor(int groupColor) {
        this.groupColor = groupColor;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setChilds(ArrayList<ChildEntity> childs) {
        this.childs = childs;
    }

}
