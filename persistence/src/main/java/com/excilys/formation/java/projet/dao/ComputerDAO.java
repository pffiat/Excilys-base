package com.excilys.formation.java.projet.dao;

import java.util.List;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.modele.*;

public interface ComputerDAO {

	public void insert(Computer comp);

	public void delete(Computer comp);

	public void update(Computer comp);

	public List<Computer> getAll();

	public List<Computer> getCriteria(String search, Sort sort, int offset, int scope);

	public int getNumberWithCriteria(String search);

}
