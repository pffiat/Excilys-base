package com.excilys.formation.java.projet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.projet.DTO.ComputerDTO;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.Computer;
import com.excilys.formation.java.projet.service.ComputerService;

/**
 * Servlet implementation class indexServlet
 */
@WebServlet("/Dashboard")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCourante;
	int nbDisplayed = 20;		
	int currentPageInt = 1;
	int nbOfPc;
	int nbOfBouton;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexServlet() {
        super();
        pageCourante = 1;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ComputerService cs = new ComputerService();
		List<ComputerDTO> listeAll = ComputerMapper.toDTOList(cs.getAll());
		currentPageInt = 1;
		nbOfPc = listeAll.size();
		
		String currentLigne = request.getParameter("lignes");
		if( currentLigne != null) {
			nbDisplayed = Integer.parseInt(currentLigne);
			nbDisplayed = nbDisplayed * 5;
		}		
		String currentPage = request.getParameter("page");
		if( currentPage != null) {
			currentPageInt = Integer.parseInt(currentPage);
		}
		String order = request.getParameter("order");
		System.out.println(order);
		String orderBy = "";
		if(order!=null && !"".equals(order)) orderBy = " ORDER BY " + request.getParameter("column") + " " + order;
		String searchComputer = " WHERE computer.name='" + request.getParameter("search") + "' OR company.name='" + request.getParameter("search") + "'";
		
		String criteria =  ((request.getParameter("search")!=null && !("".equals(request.getParameter("search"))) ) ? searchComputer : "")
				+ orderBy
				+ " LIMIT " + (currentPageInt*nbDisplayed - nbDisplayed) + "," + nbDisplayed ;
		
		nbOfBouton = (int)(nbOfPc / nbDisplayed) + 1;
		List<ComputerDTO> liste = ComputerMapper.toDTOList(cs.getCriteria(criteria));
		request.setAttribute("computers", liste);
		request.setAttribute("nbOfPc", nbOfPc);
		request.setAttribute("nbOfBouton", nbOfBouton);
		request.setAttribute("nbDisplayed", nbDisplayed);
		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request , response);
		//request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request , response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
