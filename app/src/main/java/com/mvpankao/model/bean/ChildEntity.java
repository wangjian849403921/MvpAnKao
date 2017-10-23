package com.mvpankao.model.bean;

import java.util.ArrayList;

/**
 * 
 * @author wangjian���
 * 
 *         ��������ʵ��
 * 
 * */

public class ChildEntity {

	private int groupColor;

	private String groupName;

	private String groupId;
	private String groupIcon;
	private String groupLevel;
	private ArrayList<String> childNames;
	private ArrayList<String> childIds;
	private ArrayList<String> childLevels;
	private ArrayList<String> childIcons;
	/* ==========================================================
	 * ======================= get method =======================
	 * ========================================================== */

	public ArrayList<String> getChildIcons() {
		return childIcons;
	}

	public void setChildIcons(ArrayList<String> childIcons) {
		this.childIcons = childIcons;
	}

	public String getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(String groupLevel) {
		this.groupLevel = groupLevel;
	}

	public ArrayList<String> getChildLevels() {
		return childLevels;
	}

	public void setChildLevels(ArrayList<String> childLevels) {
		this.childLevels = childLevels;
	}

	public String getGroupIcon() {
		return groupIcon;
	}

	public void setGroupIcon(String groupIcon) {
		this.groupIcon = groupIcon;
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

	public ArrayList<String> getChildNames() {
		return childNames;
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

	public void setChildNames(ArrayList<String> childNames) {
		this.childNames = childNames;
	}

	public ArrayList<String> getChildIds() {
		return childIds;
	}

	public void setChildIds(ArrayList<String> childIds) {
		this.childIds = childIds;
	}
}
