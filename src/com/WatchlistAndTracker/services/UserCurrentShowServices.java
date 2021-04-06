package com.WatchlistAndTracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WatchlistAndTracker.entities.Show;
import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.entities.UserCurrentShow;
import com.WatchlistAndTracker.repo.UserCurrentShowRepository;

@Service
public class UserCurrentShowServices {

	@Autowired
	UserCurrentShowRepository ucsr;
	
	@Autowired
	UserServices us;
	
	@Autowired
	ShowServices shs;
		
	public UserCurrentShow getUserCurrentShow(String username, String showName) {
		return ucsr.getUserCurrentShowByUsernameAndShowName(username, showName);
	}
	
	public void linkUserCurrentShowToUser(String username, String showName, int totalEpisodes, int currentEpisode) {
		UserCurrentShow userCurrentShow = new UserCurrentShow(username, showName, totalEpisodes, currentEpisode);
		ucsr.save(userCurrentShow);
	}
	
	public void updateShowCurrentEpisode(String username, String showName, int currentEpisode) {
		UserCurrentShow showToUpdate = ucsr.getUserCurrentShowByUsernameAndShowName(username, showName);
		showToUpdate.setCurrentEpisode(currentEpisode);
		ucsr.save(showToUpdate);
	}
	
	public List<UserCurrentShow> getAllUserCurrentShowByUsername(String username){
		return ucsr.findAllByUsername(username);
		
	}
}
