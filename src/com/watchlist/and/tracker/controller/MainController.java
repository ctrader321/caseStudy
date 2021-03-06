package com.watchlist.and.tracker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.watchlist.and.tracker.entities.Show;
import com.watchlist.and.tracker.entities.User;
import com.watchlist.and.tracker.services.ShowServices;
import com.watchlist.and.tracker.services.UserCurrentShowServices;
import com.watchlist.and.tracker.services.UserServices;

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

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("index");
		User u = (User)request.getAttribute("user");
		mav.addObject("user", u);
		mav.addObject("allShowsInDb", shs.getAllShows());
		return mav;
	}

	@RequestMapping(value = "loginProcess", method = RequestMethod.POST)
	public String loginHandlerTesting(@RequestParam("username") String username, @RequestParam("userPassword") String userPassword, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (us.validateUser(username, userPassword) != null) {
			User user = us.getUser(username);
			request.getSession().setAttribute("user", user);
			mav.addObject("user", user);
			return "redirect:/index";
		} else {
			request.setAttribute("message", "Unknown username/password. Please try again. Click Create an Account below if this is your first visit :");
			request.getRequestDispatcher("/");
		}

		return "/login";
	}
	
	@RequestMapping(value="addShowToDb", method = RequestMethod.POST)
	public ModelAndView addNewShowToTable(@ModelAttribute("Show") Show show, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if((User)request.getSession().getAttribute("user") !=null ) {
			
			if(shs.getShow(show.getShowName()) != null) {
				request.setAttribute("showAlreadyInDb", "We already have a record of that show! Please try adding something else!");
				return indexHandler(request);
			} else {
				shs.addShow(show);
			}
			
		} else {
			mav.setViewName("login");
			request.setAttribute("loginAgainMessage", "Please sign in again!");
			return mav;
		}
		return indexHandler(request);
	}
	
}

