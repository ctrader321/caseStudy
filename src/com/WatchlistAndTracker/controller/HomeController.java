package com.WatchlistAndTracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.WatchlistAndTracker.services.UserServices;

@Controller
public class HomeController {

	@Autowired
	UserServices us;
	
	
	@RequestMapping("/")
	public ModelAndView loginHandler() {
		ModelAndView mav = new ModelAndView("login");	
		return mav;
	}
		
	@RequestMapping("/index")
	public ModelAndView indexHandler() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

}
