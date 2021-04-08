package com.watchlist.and.tracker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.watchlist.and.tracker.entities.User;
import com.watchlist.and.tracker.services.UserServices;

@Controller
public class RegistrationController {

	@Autowired
	UserServices us;
	
	@RequestMapping("/register")
	public ModelAndView registerHandler() {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}
	
	@RequestMapping(value="registerSubmit", method=RequestMethod.POST)
	public ModelAndView registerUserHandler(@ModelAttribute User user, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("register");
		if(user.getUserPassword().equals(user.getVerifyPassword()) && user.getUserPassword().length() > 6 && us.getUser(user.getUsername()) == null) {
			us.addUser(user);
			request.setAttribute("registrationSuccessful", "You've succesfully registered! You can now login.");
			mav.setViewName("login");
		}
		if(user.getUsername().length() < 4) {
			request.setAttribute("usernameShort", "Username is too short! please use at least 4 characters");
			request.getRequestDispatcher("/register");
		}
		if(us.getUser(user.getUsername()) != null) {
			request.setAttribute("usernameMessage", "Username already taken, please use another.");
			request.getRequestDispatcher("/register");
		}
		if (!user.getUserPassword().equals(user.getVerifyPassword())){
			request.setAttribute("message", "\"Password\" field must match \"Verify Password\" field. Please try again.");
			request.getRequestDispatcher("/register");
		}
		if(user.getUserPassword().length() < 6) {
				request.setAttribute("passwordMessage", "\"Password\" must be greater than 6 characters.");
				request.getRequestDispatcher("/register");
		}
		
		return mav;
	}
	
	
}
