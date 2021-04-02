package com.WatchlistAndTracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WatchlistAndTracker.entities.Show;
import com.WatchlistAndTracker.repo.ShowRepository;

@Service
public class ShowServices {
	
	@Autowired
	ShowRepository sr;
	
	public void addShow(Show show) {
		sr.save(show);
	}
	
	
}
