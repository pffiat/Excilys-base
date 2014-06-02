package com.excilys.formation.java.projet.modele;

import javax.persistence.*;

@Entity
@Table(name="people") 
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	
	
	public User() {	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getPassword() {
		return password;
	}


	public String getRole() {
		return role;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
}