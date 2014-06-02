package com.excilys.formation.java.projet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.java.projet.modele.User;
import com.excilys.formation.java.projet.service.UserService;

@Controller
@RequestMapping("/Login")
public class Login {

	@Autowired
	private UserService us;

	static final Logger LOG = LoggerFactory.getLogger(Dashboard.class);

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(
			@Valid @ModelAttribute("user") User user,
			BindingResult result) {

		ModelAndView mav = null;

		if (result.hasErrors()) {
			mav = new ModelAndView("login");
		} else {
			mav = new ModelAndView("redirect:Dashboard");
		}
		return mav;
	}

}