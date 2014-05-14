package com.excilys.formation.java.projet.modele;

import org.joda.time.DateTime;

import com.excilys.formation.java.projet.dto.ComputerDto;

public class Computer {

	private int id;
	private String name;
	private DateTime introduced;
	private DateTime discontinued;
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
	public Computer(int idd, String namee, DateTime intro) {
		this.id = idd;
		this.name = namee;
		this.introduced = intro;
	}
	public Computer(int idd, String namee, DateTime intro, DateTime disc) {
		this.id = idd;
		this.name = namee;
		this.introduced = intro;
		this.discontinued = disc;
	}
	public Computer(int idd, String namee, DateTime intro, DateTime disc, Company comp) {
		this.id = idd;
		this.name = namee;
		this.introduced = intro;
		this.discontinued = disc;
		this.company = comp;
	}

	public Computer(ComputerDto dto) {
		Company c = new Company(dto.getCompany_id(), dto.getCompany());
		this.id = dto.getId();
		this.name = dto.getName();
		if(dto.getIntroduced() != null && !("".equals(dto.getIntroduced()))) {
			this.introduced = new DateTime(dto.getIntroduced());
		}
		if(dto.getDiscontinued() != null && !("".equals(dto.getDiscontinued()))) {
			this.discontinued = new DateTime(dto.getDiscontinued());
		}
		this.company = c;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public DateTime getIntroduced() {
		return introduced;
	}

	public DateTime getDiscontinued() {
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

	public void setIntroduced(DateTime date) {
		this.introduced = date;
	}

	public void setDiscontinued(DateTime date) {		
		discontinued = date;
	}

	public void setCompany(Company cpn) {
		company=cpn;		
	}

	public String toString() {
		return "NAME OF THE COMPUTER :" + this.name + " introduced : " + introduced + " disc : " + discontinued + " id: " + company.getId();
	}

}
