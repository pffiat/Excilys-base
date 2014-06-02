package com.excilys.formation.java.projet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.java.projet.common.PageWrapper;
import com.excilys.formation.java.projet.dto.*;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.service.ComputerService;

@Controller
@RequestMapping("/Dashboard")
public class Dashboard {

	@Autowired
	private ComputerService cpts;
	private String search;
	private PageWrapper pageWrapper = new PageWrapper();

	static final Logger LOG = LoggerFactory.getLogger(Dashboard.class);

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request) {
		LOG.debug("Coucou");
		ModelAndView mav = new ModelAndView("dashboard");
		pageWrapper.setRequest(request);
		search = pageWrapper.getSearch();
		ComputerService cs = cpts;
		pageWrapper.setTotalCount(cs.getNumberWithCriteria(search));

		List<ComputerDto> comp = null;
		comp = ComputerMapper.toDTOList(cs.getCriteria(search,
				pageWrapper.getSort(),
				pageWrapper.getCurrentPage() * pageWrapper.getPageLimit()
						- pageWrapper.getPageLimit(),
				pageWrapper.getPageLimit()));
		pageWrapper.setList(comp);
		pageWrapper.setAttribute();
		mav.addObject("pageWrapper", pageWrapper);
		return mav;
	}

}