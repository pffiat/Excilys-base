package com.excilys.formation.java.projet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/EditComputer")
public class EditComputer {
	
	@Autowired
	private CompanyService cs;
	@Autowired
	private ComputerService cpts;
    private int idPrivate;

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request) throws ServletException, IOException {
		
		ModelAndView mav = new ModelAndView("editComputer");
        ComputerDto cpt = new ComputerDto();
		List<Company> liste = cs.getAll();
		String id = request.getParameter("id");
		if(id != null) {
			idPrivate = Integer.parseInt(id);
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
	protected ModelAndView doPost(HttpServletRequest request) throws ServletException, IOException {

		ModelAndView mav = null;
        ComputerDto dto = new ComputerDto();
        dto.setId(idPrivate);
		dto.setName(request.getParameter("name"));	
		dto.setIntroduced(request.getParameter("introduced"));
		dto.setDiscontinued(request.getParameter("discontinued"));
		if(request.getParameter("company") != null) {
			dto.setCompany_id(new Integer(request.getParameter("company")));
		} else {
			dto.setCompany_id(0);
		}
		
  		ComputerDTOValidator v = new ComputerDTOValidator();
  		int codeError = v.testComputerDTO(dto);
		if(codeError != 0) {
			Computer cpn = ComputerMapper.fromDTO(dto);
			cpts.updateComputer(cpn);
			mav = new ModelAndView("dashboard");
		}else{
			mav = new ModelAndView("editComputer");
			mav.addObject(dto);
			List<Company> liste = cs.getAll();
			liste.remove(dto.getCompany_id());
			mav.addObject("companies", liste);
		}
		return mav;
	}

}
