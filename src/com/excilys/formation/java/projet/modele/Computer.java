package com.excilys.formation.java.projet.modele;

import java.util.Calendar;

public class Computer {

	private int id;
	private String name;
	private Calendar introduced;
	private Calendar discontinued;
	private Company company;
	
	
	public Computer() {	}
	public Computer(int idd) {
		this.id = idd;
	}
	public Computer(String namee) {
		this.name = namee;
	}
	public Computer(int idd, String namee) {
		this.id = idd;
		this.name = namee;
	}
	public Computer(int idd, String namee, Calendar intro) {
		this.id = idd;
		this.name = namee;
		this.introduced = intro;
	}
	public Computer(int idd, String namee, Calendar intro, Calendar disc) {
		this.id = idd;
		this.name = namee;
		this.introduced = intro;
		this.discontinued = disc;
	}
	public Computer(int idd, String namee, Calendar intro, Calendar disc, Company comp) {
		this.id = idd;
		this.name = namee;
		this.introduced = intro;
		this.discontinued = disc;
		this.company = comp;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Calendar getIntroduced() {
		return introduced;
	}
	
	public Calendar getDiscontinued() {
		return discontinued;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String namee) {
		this.name = namee;
	}
	
	public void setIntroduced(Calendar date) {
			this.introduced = date;
	}
	
	public void setDiscontinued(Calendar date) {
		
 	discontinued = date;
		
	}
	
	public void setCompany(Company cpn) {
		company=cpn;		
	}
	
	public String toString() {
		return "NAME OF THE COMPUTER :" + this.name + " introduced : " + introduced + " disc : " + discontinued + " id: " + company.getId();
	}
	
}
