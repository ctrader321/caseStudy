package com.watchlist.and.tracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watchlist.and.tracker.entities.Show;
import com.watchlist.and.tracker.repo.ShowRepository;

@Service
public class ShowServices {
	
	@Autowired
	ShowRepository sr;
	
	public void addShow(Show show) {
		sr.save(show);
	}
	
	public Show getShow(String showname) {
		return sr.getShowByShowName(showname);
	}
	
	public List<Show> getAllShows(){
		return sr.findAll();
	}
}
