package com.WatchlistAndTracker.controller;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.WatchlistAndTracker.entities.Show;
import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.entities.UserCurrentShow;
import com.WatchlistAndTracker.services.ShowServices;
import com.WatchlistAndTracker.services.UserCurrentShowServices;
import com.WatchlistAndTracker.services.UserServices;

@Controller
public class ListController {
	
	@Autowired
	ShowServices shs;
	
	@Autowired
	UserServices us;
	
	@Autowired
	UserCurrentShowServices ucss;
	
	@RequestMapping(value="/currentWatchlist", method=RequestMethod.GET)
	public ModelAndView currentHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("currentWatchlist");
		if((User)request.getSession().getAttribute("user")!= null) {
			User u = (User)request.getSession().getAttribute("user");
			List<UserCurrentShow> userCurrentShowByUsername = ucss.getAllUserCurrentShowByUsername(u.getUsername());
			mav.addObject("userList", userCurrentShowByUsername);
			mav.addObject("showList", shs.getAllShows());
			mav.addObject("currentShows", u.getCurrentShowList());
		} else {
			mav.setViewName("login");
			request.setAttribute("loginAgainMessage", "Please sign in again!");
		}
		return mav;
	}
	
	@RequestMapping(value="addToCurrent", method = RequestMethod.GET)
	public ModelAndView userAddShowToCurrent(@ModelAttribute("show") Show show, HttpServletRequest request){
		User u = (User)request.getSession().getAttribute("user");
		if(u.getCurrentShowList().contains(shs.getShow(show.getShowName()))) {
			request.setAttribute("alreadyInList", "You've already added that to your Current Watchlist!");
			return currentHandler(request);
		}else if(u.getBacklogShowList().contains(shs.getShow(show.getShowName()))) {
			u.getBacklogShowList().remove(shs.getShow(show.getShowName()));
			us.addUser(u);
		} else if(!u.getCurrentShowList().contains(shs.getShow(show.getShowName()))){
			u.getCurrentShowList().add(shs.getShow(show.getShowName()));
			us.addUser(u);
			UserCurrentShow updatedShow = ucss.linkUserCurrentShowToUser(u.getUsername(), shs.getShow(show.getShowName()).getShowName(), shs.getShow(show.getShowName()).getTotalEpisodes(), 0);	
			ucss.saveUserCurrentShow(updatedShow);
		}
				
		return currentHandler(request);
	}
	
	@RequestMapping(value="editInCurrent", method = RequestMethod.GET)
	public ModelAndView userEditShowInCurrent(@ModelAttribute("show") Show show,@RequestParam("episodeNumberToSet") String currentEpisode, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("currentWatchlist");
		if((User)request.getSession().getAttribute("user") != null) {
			User u = (User)request.getSession().getAttribute("user");
			UserCurrentShow foundShow = ucss.getUserCurrentShow(u.getUsername(), show.getShowName());
			foundShow.setCurrentEpisode(Integer.parseInt(currentEpisode));
			foundShow.setCompletionPercentage(Integer.parseInt(currentEpisode));
			ucss.saveUserCurrentShow(foundShow);
		} else {
			mav.setViewName("login");
			request.setAttribute("loginAgainMessage", "Please sign in again!");
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/backlogWatchlist", method = RequestMethod.GET)
	public ModelAndView backlogHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("backlogWatchlist");
		if ((User)request.getSession().getAttribute("user") != null) {
			User u = (User)request.getSession().getAttribute("user");
			List<Show> userBacklog = u.getBacklogShowList();
			List<Show> allShows = shs.getAllShows();
			mav.addObject("showList", allShows);
			mav.addObject("backlogList", userBacklog);
			
		}else {
			mav.setViewName("login");
			request.setAttribute("loginAgainMessage", "Please sign in again!");
		}
		return mav;
	}
	
	@RequestMapping(value="addToBacklog", method = RequestMethod.POST)
	public ModelAndView userAddShowToBacklog(@ModelAttribute("show") Show show, HttpServletRequest request){
		User u = (User)request.getSession().getAttribute("user");
		u.getBacklogShowList().add(shs.getShow(show.getShowName()));
		us.addUser(u);
		return backlogHandler(request);
	}
	
	@RequestMapping(value="removeFromBacklog", method = RequestMethod.POST)
	public ModelAndView userRemoveShowFromBacklog(@ModelAttribute("show") Show show, HttpServletRequest request){
		User u = (User)request.getSession().getAttribute("user");
		u.getBacklogShowList().remove(shs.getShow(show.getShowName()));
		us.addUser(u);
		return backlogHandler(request);
	}
	
	@RequestMapping(value="moveFromBackToCurrent", method = RequestMethod.POST)
	public ModelAndView userMoveFromBacklogToCurrent(@ModelAttribute("show") Show show, HttpServletRequest request, HttpServletResponse response) {
		User u = (User)request.getSession().getAttribute("user");
		u.getBacklogShowList().remove(shs.getShow(show.getShowName()));
		u.getCurrentShowList().add(shs.getShow(show.getShowName()));
		ucss.linkUserCurrentShowToUser(u.getUsername(), shs.getShow(show.getShowName()).getShowName(), shs.getShow(show.getShowName()).getTotalEpisodes(), 0);	
		us.addUser(u);
		return backlogHandler(request);
		
	}
	
}
