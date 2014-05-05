package com.excilys.formation.java.projet.common;

public class Sort {
	
	private String column;
	private String order = "";
	
	public Sort(String column, String order) {
		this.column = column;
		this.order = order;
	}
	
	public Sort(String column) {
		this.column = column;
	}
	
	public Sort() {
	}

	public String getColumn() {
		return column;
	}
	public String getOrder() {
		return order;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public void setOrder(String order) {
		this.order = "";
		if (order != null && !"".equals(order)) {
			this.order = order;
		}
		
	}
	
	public boolean needOrder(){
		boolean bool = true;
		if("".equals(this.order)) bool = false;
		return bool;
	}

}
