package com.excilys.formation.java.projet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.java.projet.dto.ComputerDto;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.Company;
import com.excilys.formation.java.projet.modele.Computer;
import com.excilys.formation.java.projet.service.CompanyService;
import com.excilys.formation.java.projet.service.ComputerService;

@Controller
@RequestMapping("/EditComputer")
public class EditComputer {

	@Autowired
	private CompanyService cs;

	@Autowired
	private ComputerService cpts;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request) throws ServletException, IOException {

		ModelAndView mav = new ModelAndView("editComputer");
		ComputerDto cpt = new ComputerDto();
		List<Company> liste = cs.getAll();
		String id = request.getParameter("id");
		if(id != null) {
			Integer.parseInt(id);
		}
		cpt.setName(request.getParameter("name"));	
		cpt.setIntroduced(request.getParameter("introduced"));
		cpt.setDiscontinued(request.getParameter("discontinued"));
		Company cpn = new Company();

		if(request.getParameter("comp_id") != null) {
			cpn.setName(request.getParameter("comp_name"));
			cpn.setId(new Integer(request.getParameter("comp_id")));
		} else {
			cpn.setId(0);
		}

		mav.addObject("computer", cpt);
		mav.addObject("cpn", cpn);
		liste.remove(cpn);
		mav.addObject("companies", liste);
		return mav;

	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(@Valid @ModelAttribute("computerdto") ComputerDto dto, BindingResult result ) {


		ModelAndView mav = null;
		if(!result.hasErrors() ) {
			mav = new ModelAndView("redirect:Dashboard");
			Computer cpn = ComputerMapper.fromDTO(dto);
			cpts.updateComputer(cpn);
		}else{
			mav = new ModelAndView("editComputer");
			mav.addObject("dto", dto);
			List<Company> liste = cs.getAll();
			mav.addObject("company_name", liste.get(dto.getCompany_id()).getName());
			liste.remove(dto.getCompany_id());
			mav.addObject("companies", liste);

		}
		return mav;
	}

}