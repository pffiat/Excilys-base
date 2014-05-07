package com.excilys.formation.java.projet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.java.projet.common.PageWrapper;
import com.excilys.formation.java.projet.dto.*;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.service.ComputerService;

@WebServlet("/Dashboard")
public class IndexServlet extends HttpServlet {
	
	@Autowired
	private ComputerService cpts;
	
	private static final long serialVersionUID = 1L;
	private String search;
	private PageWrapper pageWrapper = new PageWrapper();
	
	

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request);
		pageWrapper.setRequest(request);
		search = pageWrapper.getSearch();
		ComputerService cs = cpts;
		pageWrapper.setTotalCount(cs.getNumberWithCriteria(search));

		List<ComputerDTO> comp = null;
		comp = ComputerMapper.toDTOList(cs.getCriteria(search, pageWrapper.getSort(), (pageWrapper.getCurrentPage() * pageWrapper.getPageLimit() - pageWrapper.getPageLimit()), pageWrapper.getPageLimit()));
		pageWrapper.setList(comp);
		pageWrapper.setAttribute();
		request.setAttribute("pageWrapper", pageWrapper);
		getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}