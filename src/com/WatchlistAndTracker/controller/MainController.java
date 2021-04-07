package com.WatchlistAndTracker.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.services.ShowServices;
import com.WatchlistAndTracker.services.UserCurrentShowServices;
import com.WatchlistAndTracker.services.UserServices;

@Controller
public class MainController {
	
	@Autowired
	ShowServices shs;
	
	@Autowired
	UserServices us;
	
	@Autowired
	UserCurrentShowServices ucss;
	
	@RequestMapping("/")
	public ModelAndView loginHandler1() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView loginHandler2() {
		return new ModelAndView("login");
	}
		
	@RequestMapping("/index")
	public ModelAndView indexHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		User u = (User)request.getAttribute("user");
		mav.addObject("user", u);
		mav.addObject("allShowsInDb", shs.getAllShows());
		return mav;
	}
		
	@RequestMapping(value = "loginProcess", method = RequestMethod.GET)
	public ModelAndView loginHandlerTesting(@RequestParam("username") String username, @RequestParam("userPassword") String userPassword, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(us.validateUser(username, userPassword) != null) {
			User user = us.getUser(username);
			request.getSession().setAttribute("user", user);
			mav.addObject("user",user);
			return indexHandler(request);
		} else {
			request.setAttribute("message", "Unknown username/password. Please try again. Click Create an Account below if this is your first visit :)");
			request.getRequestDispatcher("/");
			mav.setViewName("login");
		}
		
		return mav;
	}

}
