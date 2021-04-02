package com.WatchlistAndTracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WatchlistAndTracker.entities.Show;
import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.repo.UserRepository;

@Service
public class UserServices{

	@Autowired
	UserRepository ur;
	
	public User addUser(User user) {
		return ur.save(user);
	}		
	
	public User getUser(String username) {
		return ur.getUserByUsername(username);
	}
	
	public void updateUserBacklog(String username, Show showName) {
		User foundUser = ur.getUserByUsername(username);
		foundUser.getBacklogShowList().add(showName);
		ur.save(foundUser);	
				
	}
	
	public void updateUserCurrent(String username, Show showName) {
		User foundUser = ur.getUserByUsername(username);
		foundUser.getCurrentShowList().add(showName);
		ur.save(foundUser);
		
	}
	
}
