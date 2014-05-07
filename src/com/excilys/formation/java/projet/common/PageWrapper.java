package com.excilys.formation.java.projet.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.java.projet.dto.ComputerDTO;
import com.excilys.formation.java.projet.service.ComputerService;

public class PageWrapper {
	
	@Autowired
	private ComputerService cs;
	
	private List<ComputerDTO> list;
	private int totalCount;
	private int currentPage = 1;
	private int pageLimit = 20;
	private int nbOfLines;
	private int nbOfBouton;
	private String search;
	private HttpServletRequest request;
	private String previousSearch;
	private Sort sort = new Sort();

	
	public List<ComputerDTO> getList() {
		return list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public Sort getSort() {
		return sort;
	}

	public void setList(List<ComputerDTO> list) {
		this.list = list;
		System.out.println("list pageWrapper beginning: "+this.list.get(0));
		
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		initializeList(search);
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public int getNbOfBouton() {
		return nbOfBouton;
	}

	public void setNbOfBouton(int nbOfBouton) {
		this.nbOfBouton = nbOfBouton;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
		System.out.println("pageLimit :" + pageLimit );
		String currentLigne = request.getParameter("lignes");
		if (currentLigne != null) {
			pageLimit = Integer.parseInt(currentLigne);
			pageLimit = pageLimit * 5;
			System.out.println("pageLimit currentLigne:" + pageLimit +" " + currentLigne);
		}
		String currentPageString = request.getParameter("page");
		if (currentPageString != null) {
			currentPage = Integer.parseInt(currentPageString);
		}
		search = request.getParameter("search");

		if (currentPageString != null || currentLigne != null) {
			search = previousSearch;
		}
		previousSearch = search;
		System.out.println("\nsearch: "+search);
		setSearch(search);
		
	}
	
	public void setAttribute(){		
		nbOfBouton = (int) (totalCount / pageLimit) + 1;
		setTotalCount(totalCount);
		setNbOfBouton(nbOfBouton);
		setCurrentPage(currentPage);
		setNbOfLines((list!=null) ? list.size() : 0);
	}
	
	public int getNbOfLines() {
		return nbOfLines;
	}

	public void setNbOfLines(int nbOfLines) {
		this.nbOfLines = nbOfLines;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	private void initializeList(String search){
		if (totalCount != 0) {
			sort.setColumn(request.getParameter("column"));
			sort.setOrder(request.getParameter("order"));
			System.out.println("currentPage * pageLimit - pageLimit ||| pageLimit: " + (currentPage * pageLimit - pageLimit) + " ||| " + pageLimit);
		}
		
	}

	public PageWrapper() {
	}

}
