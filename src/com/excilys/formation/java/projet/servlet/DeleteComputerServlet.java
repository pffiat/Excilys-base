package com.excilys.formation.java.projet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.java.projet.modele.Computer;
import com.excilys.formation.java.projet.service.ComputerService;


@WebServlet("/DeleteComputerServlet")
public class DeleteComputerServlet extends HttpServlet {
	
	@Autowired
	private ComputerService cs;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("id: " + id);
		if(id != null) {
			int idInt = Integer.parseInt(id);
			Computer cpt = new Computer(idInt);
			cs.deleteComputer(cpt);
		}
		response.sendRedirect("");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
