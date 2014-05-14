package com.excilys.formation.java.projet.controller;

import java.util.List;

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
@RequestMapping("/AddComputer")
public class AddComputer {
	
    @Autowired
    private CompanyService cpts;
    @Autowired
    private ComputerService cs;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet() {
		ModelAndView mav = new ModelAndView("addComputer");
		List<Company> liste = cpts.getAll();
		mav.addObject("companies", liste);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(@Valid @ModelAttribute("computerdto") ComputerDto dto, BindingResult result){
		
  		ModelAndView mav = null;

		if( ! result.hasErrors() ) {
			mav = new ModelAndView("redirect:Dashboard");
			Computer cpn = ComputerMapper.fromDTO(dto);
			cs.insertComputer(cpn);
			System.out.println("dashboard");
		}else{
			System.out.println("addComputer");
			mav = new ModelAndView("addComputer");
			mav.addObject("dto", dto);
			List<Company> liste = cpts.getAll();
			mav.addObject("company_name", liste.get(dto.getCompany_id()).getName());
			liste.remove(dto.getCompany_id());
			mav.addObject("companies", liste);
			System.out.println("no insertComputer");			
		}
		return mav;
	}
}
