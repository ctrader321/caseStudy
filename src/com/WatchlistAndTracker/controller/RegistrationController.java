package com.WatchlistAndTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.services.UserServices;

@Controller
public class RegistrationController {

	@Autowired
	UserServices us;
	
	@RequestMapping("/register")
	public ModelAndView registerHandler() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}
	
	@RequestMapping("registerSubmit")
	public ModelAndView registerUserHandler(@ModelAttribute User user) {
		ModelAndView mav = new ModelAndView("register");
		if(user.getUserPassword().equals(user.getVerifyPassword())) {
			us.addUser(user);
			mav.setViewName("login");
		}
		return mav;
	}
	
	
}
