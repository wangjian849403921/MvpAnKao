/**   
* @Title: ItemBean.java 
* @Package com.example.test 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @date 2014-6-25 ����9:45:29    
*/
package com.mvpankao.model.bean;

import java.util.ArrayList;

public class SubscribeGroupItem {

	private String id;
	private String month;
	private ArrayList<SubscribeChildrenItem> childrenItems;


	public SubscribeGroupItem(String month) {
		childrenItems=new ArrayList<>();
		this.month = month;
	}

	public SubscribeGroupItem(String id, String month, ArrayList<SubscribeChildrenItem> list) {
		this.id = id;
		this.month = month;
		childrenItems=list;
}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void addChildrenItem(SubscribeChildrenItem child) {
		childrenItems.add(child);
	}

	public int getChildrenCount() {
		return childrenItems.size();
	}

	public SubscribeChildrenItem getChildItem(int index) {
		return childrenItems.get(index);
	}

	public ArrayList<SubscribeChildrenItem> getChildrenItems() {
		return childrenItems;
	}


	public void setChildrenItems(ArrayList<SubscribeChildrenItem> childrenItems) {
		this.childrenItems = childrenItems;
	}

	
	
	
}
