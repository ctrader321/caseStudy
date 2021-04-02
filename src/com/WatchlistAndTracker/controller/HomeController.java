package com.WatchlistAndTracker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.services.UserServices;

@Controller
public class HomeController {

	@Autowired
	UserServices us;
	
	
	@RequestMapping("/")
	public ModelAndView loginHandler1() {
		ModelAndView mav = new ModelAndView("login");	
		return mav;
	}
		
	@RequestMapping("/index")
	public ModelAndView indexHandler() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	
	@RequestMapping("loginProcess")
	public ModelAndView loginHandler(@ModelAttribute User user) {
		ModelAndView mav = new ModelAndView();
		if(us.validateUser(user.getUsername(), user.getUserPassword()) != null) {
			mav.setViewName("index");
			mav.addObject(user);
		} else mav.setViewName("login");
		
		return mav;
	}
}
