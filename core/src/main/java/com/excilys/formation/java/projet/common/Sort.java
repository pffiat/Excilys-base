package com.excilys.formation.java.projet.common;

public class Sort {
	
	private int column;
	private String order = "";
	
	public Sort(int column, String order) {
		this.column = column;
		this.order = order;
	}
	
	public Sort(int column) {
		this.column = column;
	}
	
	public Sort() {
	}

	public int getColumn() {
		return column;
	}
	public String getOrder() {
		return order;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public void setOrder(String order) {
		if ("DESC".equals(order)) {
			this.order = order;
		} else {
			this.order = "ASC";
		}
		
	}
	
	public String toString() {
		return new String(column + " " + order);
	}

}
