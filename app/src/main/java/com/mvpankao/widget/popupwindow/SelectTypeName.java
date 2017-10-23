package com.mvpankao.widget.popupwindow;

import java.io.Serializable;
import java.util.List;

public class SelectTypeName implements Serializable{

	private String name;
	private String nameId;
	private List<SelectValue> saleVo;
	private String saleAttr;
	private boolean nameIsChecked;
	private String id;
	private String ProductInventory;

	public String getProductInventory() {
		return ProductInventory;
	}

	public void setProductInventory(String productInventory) {
		ProductInventory = productInventory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public List<SelectValue> getSaleVo() {
		return saleVo;
	}


	public void setSaleVo(List<SelectValue> saleVo) {
		this.saleVo = saleVo;
	}

	public String getSaleAttr() {
		return saleAttr;
	}

	public void setSaleAttr(String saleAttr) {
		this.saleAttr = saleAttr;
	}

	public boolean isNameIsChecked() {
		return nameIsChecked;
	}

	public void setNameIsChecked(boolean nameIsChecked) {
		this.nameIsChecked = nameIsChecked;
	}

}
