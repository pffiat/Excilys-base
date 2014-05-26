package com.excilys.formation.java.projet.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;


@Entity
@Table(name="log") 
public class Log {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;
	
	public Log() {
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