package com.excilys.formation.java.projet.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.excilys.formation.java.projet.dto.ComputerDTO;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.service.ComputerService;

public class PageWrapper {
	
	private List<ComputerDTO> list;
	private int totalCount;
	private int currentPage = 1;
	private int pageLimit = 20;
	private int nbOfBouton;
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
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
		ComputerService cs = new ComputerService();
		String currentLigne = request.getParameter("lignes");
		if (currentLigne != null) {
			pageLimit = Integer.parseInt(currentLigne);
			pageLimit = pageLimit * 5;
		}
		String currentPageString = request.getParameter("page");
		if (currentPageString != null) {
			currentPage = Integer.parseInt(currentPageString);
		}
		String search = request.getParameter("search");

		if (currentPageString != null || currentLigne != null) {
			search = previousSearch;
		}
		previousSearch = search;

		String searchComputer = " WHERE computer.name LIKE '%" + search
				+ "%' OR company.name LIKE '%" + search + "%'";

		String searchQuery = ((search != null && !("".equals(search))) ? searchComputer
				: "");

		totalCount = cs.getNumberWithCriteria(searchQuery);
		if (totalCount != 0) {
			System.out.println(sort);
			sort.setColumn(request.getParameter("column"));
			sort.setOrder(request.getParameter("order"));	
			String criteria = searchQuery + sort.getOrder() + " LIMIT "
					+ (currentPage * pageLimit - pageLimit) + ","
					+ pageLimit;
			System.out.println("criteria: "+criteria);
			System.out.println(list);
			list = ComputerMapper.toDTOList(cs.getCriteria(criteria));
		}
		
		nbOfBouton = (int) (totalCount / pageLimit) + 1;
		setList(list);
		setTotalCount(totalCount);
		setNbOfBouton(nbOfBouton);
		setCurrentPage(currentPage);
		setPageLimit((list!=null) ? list.size() : 0);
		
	}

	public PageWrapper() {
	}

}
