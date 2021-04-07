package com.WatchlistAndTracker.controller;

import javax.servlet.http.HttpServletRequest;

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
	public ModelAndView registerUserHandler(@ModelAttribute User user, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("register");
		if(user.getUserPassword().equals(user.getVerifyPassword()) && user.getUserPassword().length() >= 6) {
			if(us.getUser(user.getUsername()) != null) {
				request.setAttribute("usernameMessage", "Username already taken, please use another.");
				request.getRequestDispatcher("/register");
			} else {
				us.addUser(user);
				mav.setViewName("login");
			}
		} else {
			request.setAttribute("message", "\"Password\" field must match \"Verify Password\" field. Please try again.");
			if(user.getUserPassword().length() <= 6) {
				request.setAttribute("passwordMessage", "\"Password\" must be greater than 6 characters.");
				request.getRequestDispatcher("/register");
			}
		}

		return mav;
	}
	
	
}
