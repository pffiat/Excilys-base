package com.excilys.formation.java.projet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.java.projet.modele.Computer;
import com.excilys.formation.java.projet.service.ComputerService;

@Controller
@RequestMapping("/DeleteComputer")
public class DeleteComputer {
	
	@Autowired
	private ComputerService cs;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request) throws ServletException, IOException {
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView("redirect:Dashboard");
		if(id != null) {
			int idInt = Integer.parseInt(id);
			Computer cpt = new Computer(idInt);
			cs.deleteComputer(cpt);
		}
		return mav;
	}
}
