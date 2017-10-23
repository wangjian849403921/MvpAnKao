package com.mvpankao.widget.popupwindow;

import java.io.Serializable;

public class SelectValue implements Serializable {

	@Override
	public String toString() {
		return "SelectValue [goods=" + goods + ", value=" + value
				+ ", goodsAndValId=" + goodsAndValId + "]";
	}

	private String goods;
	private String value;
	private String goodsAndValId;
	private boolean isChecked;
	private String statue;
	private String assertName;
	private String level;
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	public String getAssertName() {
		return assertName;
	}



	public void setAssertName(String assertName) {
		this.assertName = assertName;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getGoodsAndValId() {
		return goodsAndValId;
	}

	public void setGoodsAndValId(String goodsAndValId) {
		this.goodsAndValId = goodsAndValId;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
