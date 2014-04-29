package com.excilys.formation.java.projet.modele;

import java.util.Calendar;

public class Log {

	private int id;
	private String name;
	private Calendar date;
	
	public Log() {
		this.id = 0;
		this.name = "";
		this.date = null;
	}
	
	public Log(int id) {
		this.id = id;
	}
	
	public Log(String name) {
		this.name = name;
	}
	
	public Log(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Log(int id, String name, Calendar date) {
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Calendar getDate() {
		if(date == null ) return Calendar.getInstance();
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Log [id=" + id + ", name=" + name + ", date=" + date + "]";
	}

}
