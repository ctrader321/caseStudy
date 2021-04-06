package com.WatchlistAndTracker.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "redirect:/";
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
	    if (((HttpServletRequest) request).getSession().getAttribute("user") == null) {
	        ((HttpServletResponse) response).sendRedirect("login"); // Not logged in, redirect to login page.
	    } else {
	        chain.doFilter(request, response); // Logged in, just continue request.
	    }
	}
}
