package com.excilys.formation.java.projet.common;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.excilys.formation.java.projet.dto.ComputerDto;

public class PageWrapper {
	
	private List<ComputerDto> list;
	private String language = "en";
	private int totalCount;
	private int currentPage = 1;
	private int pageLimit = 20;
	private int nbOfLines;
	private int nbOfBouton;
	private String search;
	private HttpServletRequest request;
	private String previousSearch;
	private Sort sort = new Sort();

	
	public List<ComputerDto> getList() {
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

	public void setList(List<ComputerDto> list) {
		this.list = list;		
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
		String currentLigne = request.getParameter("lignes");
		if (currentLigne != null) {
			pageLimit = Integer.parseInt(currentLigne);
			pageLimit = pageLimit * 5;
		}
		String currentPageString = request.getParameter("page");
		if (currentPageString != null) {
			currentPage = Integer.parseInt(currentPageString);
		}
		search = request.getParameter("search");

		if (currentPageString != null || currentLigne != null) {
			search = previousSearch;
		}
		String languageCurrent = request.getParameter("language");
		if (languageCurrent != null && !("".equals(languageCurrent))) {
			language = languageCurrent;
			System.out.println("\npar ici \n");
		}
		previousSearch = search;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	private void initializeList(String search){
		if (totalCount != 0) {
			if(request.getParameter("column")!=null){
				sort.setColumn(new Integer(request.getParameter("column")));
			} else {
				sort.setColumn(1);
			}
			sort.setOrder(request.getParameter("order"));
			System.out.println("sort : " + sort.getColumn()+sort.getOrder());
		}
		
	}

	public PageWrapper() {
	}

}
