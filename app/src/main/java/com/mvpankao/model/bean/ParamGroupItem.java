/**   
* @Title: ItemBean.java 
* @Package com.example.test 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @date 2014-6-25 ����9:45:29    
*/
package com.mvpankao.model.bean;

import java.util.ArrayList;

public class ParamGroupItem {

	private String name;
	private ArrayList<ParamChildrenItem> childrenItems;


	public ParamGroupItem(String name) {
		this.name = name;
		childrenItems=new ArrayList<>();
	}
	public ParamGroupItem(String name, ArrayList<ParamChildrenItem> list) {
		this.name = name;
		childrenItems=list;
}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addChildrenItem(ParamChildrenItem child) {
		childrenItems.add(child);
	}

	public int getChildrenCount() {
		return childrenItems.size();
	}

	public ParamChildrenItem getChildItem(int index) {
		return childrenItems.get(index);
	}

	public ArrayList<ParamChildrenItem> getChildrenItems() {
		return childrenItems;
	}


	public void setChildrenItems(ArrayList<ParamChildrenItem> childrenItems) {
		this.childrenItems = childrenItems;
	}

	
	
	
}
