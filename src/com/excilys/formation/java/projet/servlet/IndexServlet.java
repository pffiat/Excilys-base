package com.excilys.formation.java.projet.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.java.projet.common.PageWrapper;
@WebServlet("/Dashboard")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCourante;
	private PageWrapper pageWrapper = new PageWrapper();

	public IndexServlet() {
		super();
		pageCourante = 1;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		pageWrapper.setRequest(request);
		request.setAttribute("pageWrapper", pageWrapper);
		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}