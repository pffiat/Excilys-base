package com.excilys.formation.java.projet.controller;

import java.util.List;

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
import com.excilys.formation.java.projet.validator.ComputerDTOValidator;

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
	protected ModelAndView doPost( @ModelAttribute("computerdto") ComputerDto dto, BindingResult result ){
		
  		ComputerDTOValidator v = new ComputerDTOValidator();
  		ModelAndView mav = null;
  		int codeError = v.testComputerDTO(dto);
		if(codeError != 0) {
			Computer cpn = ComputerMapper.fromDTO(dto);
			cs.insertComputer(cpn);
			mav = new ModelAndView("");
		}else{
			mav = new ModelAndView("addComputer");
			mav.addObject("name", dto.getName());
			mav.addObject("introduced", dto.getIntroduced());
			mav.addObject("discontinued", dto.getDiscontinued());
			mav.addObject("company_id", dto.getCompany_id());
			CompanyService cp = new CompanyService();
			List<Company> liste = cp.getAll();
			mav.addObject("company_name", liste.get(dto.getCompany_id()).getName());
			liste.remove(dto.getCompany_id());
			mav.addObject("companies", liste);
			
		}
		return mav;
	}
	

}
