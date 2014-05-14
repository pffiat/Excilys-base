package com.excilys.formation.java.projet.modele;

import org.joda.time.DateTime;


public class Log {

	private int id;
	private String name;
	private DateTime date;
	
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
	
	public Log(int id, String name, DateTime date) {
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

	public DateTime getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Log [id=" + id + ", name=" + name + ", date=" + date + "]";
	}

}
