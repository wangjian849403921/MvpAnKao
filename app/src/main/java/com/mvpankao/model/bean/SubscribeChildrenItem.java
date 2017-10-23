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
public class SubscribeChildrenItem {

	private String time;
	private String endtime;
	private String name;
	private String statue;
	String id;

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

	public SubscribeChildrenItem() {
	}


	public SubscribeChildrenItem(String id,String time,String endtime ,String name,String statue) {
		this.id = id;
		this.time = time;
		this.endtime=endtime;
		this.name = name;
		this.statue = statue;
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

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}
}
