package com.excilys.formation.java.projet.modele;

import javax.persistence.*;

@Entity
@Table(name="company") 
public class Company {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name = "";
	
	
	public Company() {	}
	public Company(int idd) {	
		this.id = idd;
	}
	public Company(String namme) {	
		this.name = namme;
	}
	public Company(int idd, String namme) {	
		this.id = idd;
		this.name = namme;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String namee) {
		this.name = namee;
	}
	
	public String toString() {
		return "NAME OF THE COMPANY :" + this.name;
	}
	
}