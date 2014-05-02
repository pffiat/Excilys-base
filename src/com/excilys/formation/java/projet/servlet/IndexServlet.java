package com.excilys.formation.java.projet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.projet.dto.ComputerDTO;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.service.ComputerService;

/**
 * Servlet implementation class indexServlet
 */
@WebServlet("/Dashboard")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCourante;
	private int nbDisplayed = 20;
	private int currentPageInt = 1;
	private int nbOfPc;
	private int nbOfBouton;
	private String previousSearch = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		pageCourante = 1;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ComputerService cs = new ComputerService();
		currentPageInt = 1;
		String currentLigne = request.getParameter("lignes");
		if (currentLigne != null) {
			nbDisplayed = Integer.parseInt(currentLigne);
			nbDisplayed = nbDisplayed * 5;
		}
		String currentPage = request.getParameter("page");
		if (currentPage != null) {
			currentPageInt = Integer.parseInt(currentPage);
		}
		String search = request.getParameter("search");
		
		if(currentPage!=null || currentLigne!=null) {
			search = previousSearch;
			System.out.println("ici");
		}
		previousSearch = search;
		
		String searchComputer = " WHERE computer.name LIKE '%"
				+ search + "%' OR company.name LIKE '%"
				+ search + "%'";
		
		String searchQuery = ((search != null && !("".equals(search))) ? searchComputer
				: "");
		List<ComputerDTO> listeAll = ComputerMapper.toDTOList(cs
				.getCriteria(searchQuery));
		nbOfPc = listeAll.size();
		/*
		 * si la requete sur le nombre de pc indique 0 pas besoin de continuer
		 * sur les critères ORDER et LIMIT
		 */
		List<ComputerDTO> liste = null;
		if (nbOfPc != 0) {
			String order = request.getParameter("order");
			String orderBy = "";
			if (order != null && !"".equals(order)) {
				orderBy = " ORDER BY " + request.getParameter("column") + " "
						+ order;
			}
			String criteria = searchQuery + orderBy + " LIMIT "
					+ (currentPageInt * nbDisplayed - nbDisplayed) + ","
					+ nbDisplayed;
			liste = ComputerMapper.toDTOList(cs.getCriteria(criteria));
		}
		nbOfBouton = (int) (nbOfPc / nbDisplayed) + 1;
		request.setAttribute("computers", liste);
		request.setAttribute("nbOfPc", nbOfPc);
		request.setAttribute("nbOfBouton", nbOfBouton);
		request.setAttribute("currentPageInt", currentPageInt);
		request.setAttribute("nbDisplayed", ((nbDisplayed==liste.size()) ? nbDisplayed : liste.size() ));
		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}