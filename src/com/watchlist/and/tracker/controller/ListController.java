package com.watchlist.and.tracker.controller;

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

import com.watchlist.and.tracker.entities.Show;
import com.watchlist.and.tracker.entities.User;
import com.watchlist.and.tracker.entities.UserCurrentShow;
import com.watchlist.and.tracker.services.ShowServices;
import com.watchlist.and.tracker.services.UserCurrentShowServices;
import com.watchlist.and.tracker.services.UserServices;

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
			User z = (User)request.getSession().getAttribute("user");
			User u = us.getUser(z.getUsername());
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
	public ModelAndView userAddShowToCurrent(@ModelAttribute("showAdd") Show show,@RequestParam("episodeToSet") Integer currentEpisode, HttpServletRequest request){
		User z = (User)request.getSession().getAttribute("user");
		User u = us.getUser(z.getUsername());
		if(u.getCurrentShowList().contains(shs.getShow(show.getShowName()))) {
			request.setAttribute("alreadyInList", "You've already added that to your Current Watchlist! Try updating your current episode below!");
			return currentHandler(request);
		}else if(u.getBacklogShowList().contains(shs.getShow(show.getShowName()))) {
			u.getBacklogShowList().remove(shs.getShow(show.getShowName()));
			us.addUser(u);
		} else if(!u.getCurrentShowList().contains(shs.getShow(show.getShowName()))){
			u.getCurrentShowList().add(shs.getShow(show.getShowName()));
			us.addUser(u);
			UserCurrentShow updatedShow = ucss.linkUserCurrentShowToUser(u.getUsername(), shs.getShow(show.getShowName()).getShowName(), shs.getShow(show.getShowName()).getTotalEpisodes(), currentEpisode);	
			ucss.saveUserCurrentShow(updatedShow);
		}
				
		return currentHandler(request);
	}
	
	@RequestMapping(value="editInCurrent", method = RequestMethod.GET)
	public ModelAndView userEditShowInCurrent(@ModelAttribute("showEdit") Show show,@RequestParam("episodeNumberToSet") Integer currentEpisode, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("currentWatchlist");
		if((User)request.getSession().getAttribute("user") != null) {
			User z = (User)request.getSession().getAttribute("user");
			User u = us.getUser(z.getUsername());
			UserCurrentShow foundShow = ucss.getUserCurrentShow(u.getUsername(), show.getShowName());
			foundShow.setCurrentEpisode(currentEpisode);
			foundShow.setCompletionPercentage(currentEpisode);
			ucss.saveUserCurrentShow(foundShow);
		} else {
			mav.setViewName("login");
			request.setAttribute("loginAgainMessage", "Please sign in again!");
			return mav;
		}
		return currentHandler(request);
	}
	
	@RequestMapping(value="removeFromCurrent", method = RequestMethod.GET)
	public ModelAndView userRemoveShowInCurrent(@ModelAttribute("showRemove") Show show, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("currentWatchlist");
		if((User)request.getSession().getAttribute("user") != null) {
			User z = (User)request.getSession().getAttribute("user");
			User u = us.getUser(z.getUsername());
			ucss.removeUserCurrentShowByUser(u.getUsername(), show.getShowName());
			us.addUser(u);
		} else {
			mav.setViewName("login");
			request.setAttribute("loginAgainMessage", "Please sign in again!");
			return mav;
		}
		return currentHandler(request);
	}
	
	@RequestMapping(value="/backlogWatchlist")
	public ModelAndView backlogHandler(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("backlogWatchlist");
		if ((User)request.getSession().getAttribute("user") != null) {
			User z = (User)request.getSession().getAttribute("user");
			User u = us.getUser(z.getUsername());
			mav.addObject("showList", shs.getAllShows());
			mav.addObject("backlogList", u.getBacklogShowList());
		}else {
			mav.setViewName("login");
			request.setAttribute("loginAgainMessage", "Please sign in again!");
		}
		return mav;
	}
	
	@RequestMapping(value="addToBacklog", method = RequestMethod.POST)
	public ModelAndView userAddShowToBacklog(@ModelAttribute("showAdd") Show show, HttpServletRequest request){
		if((User)request.getSession().getAttribute("user") != null) {
			User z = (User)request.getSession().getAttribute("user");
			User u = us.getUser(z.getUsername());
			if(u.getBacklogShowList().contains(shs.getShow(show.getShowName()))){
				request.setAttribute("backlogAlreadyAdded", "You've already added that to your Backlog Watchlist!");
				return backlogHandler(request);
			}else if (u.getCurrentShowList().contains(shs.getShow(show.getShowName()))){
				request.setAttribute("alreadyInCurrent", "That show is already in your current watchlist! Head over to your Current Watchlist if you wish to see.");
				return backlogHandler(request);
			}else {
				u.getBacklogShowList().add(shs.getShow(show.getShowName()));
				us.addUser(u);

			}
		}
		return backlogHandler(request);	
	}
	
	@RequestMapping(value="removeFromBacklog", method = RequestMethod.POST)
	public ModelAndView userRemoveShowFromBacklog(@ModelAttribute("showRemove") Show show, HttpServletRequest request){
		User z = (User)request.getSession().getAttribute("user");
		User u = us.getUser(z.getUsername());
		u.getBacklogShowList().remove(shs.getShow(show.getShowName()));
		us.addUser(u);
		return backlogHandler(request);
	}
	
	@RequestMapping(value="moveFromBackToCurrent", method = RequestMethod.POST)
	public ModelAndView userMoveFromBacklogToCurrent(@ModelAttribute("showMove") Show show, HttpServletRequest request, HttpServletResponse response) {
		User z = (User)request.getSession().getAttribute("user");
		User u = us.getUser(z.getUsername());
		if(!u.getCurrentShowList().contains(shs.getShow(show.getShowName()))) {
			u.getBacklogShowList().remove(shs.getShow(show.getShowName()));
			u.getCurrentShowList().add(shs.getShow(show.getShowName()));
			ucss.linkUserCurrentShowToUser(u.getUsername(), shs.getShow(show.getShowName()).getShowName(), shs.getShow(show.getShowName()).getTotalEpisodes(), 0);	
			us.addUser(u);
		} else {
			
		}
		return backlogHandler(request);
		
	}
	
}
