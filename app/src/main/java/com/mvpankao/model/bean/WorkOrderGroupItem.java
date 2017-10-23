/**   
* @Title: ItemBean.java 
* @Package com.example.test 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @date 2014-6-25 ����9:45:29    
*/
package com.mvpankao.model.bean;

import java.util.ArrayList;

public class WorkOrderGroupItem {

	private String statue;
	private ArrayList<WorkOrderChildrenItem> childrenItems;


	public WorkOrderGroupItem(String statue) {
		this.statue = statue;
		childrenItems=new ArrayList<>();
	}
	public WorkOrderGroupItem(String statue, ArrayList<WorkOrderChildrenItem> list) {
		this.statue = statue;
		childrenItems=list;
}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public void addChildrenItem(WorkOrderChildrenItem child) {
		childrenItems.add(child);
	}

	public int getChildrenCount() {
		return childrenItems.size();
	}

	public WorkOrderChildrenItem getChildItem(int index) {
		return childrenItems.get(index);
	}

	public ArrayList<WorkOrderChildrenItem> getChildrenItems() {
		return childrenItems;
	}


	public void setChildrenItems(ArrayList<WorkOrderChildrenItem> childrenItems) {
		this.childrenItems = childrenItems;
	}

	
	
	
}
