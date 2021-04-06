package com.WatchlistAndTracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WatchlistAndTracker.entities.Show;
import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.repo.UserRepository;

@Service
public class UserServices{

	@Autowired
	UserRepository ur;
	
	@Autowired
	UserCurrentShowServices ucss;
	
	public User addUser(User user) {
		return ur.save(user);
	}		
	
	public User getUser(String username) {
		return ur.getUserByUsername(username);
	}
	
	public List<User> getAllUsers(){
		return ur.findAll();
	}
	
	public void addToUserBacklog(String username, Show showName) {
		User foundUser = ur.getUserByUsername(username);
		foundUser.getBacklogShowList().add(showName);
		ur.save(foundUser);	
				
	}
	
	public void addToUserCurrent(String username, Show showName) {
		User foundUser = ur.getUserByUsername(username);
		foundUser.getCurrentShowList().add(showName);
		ur.save(foundUser);
		
	}
	
	public void deleteFromUserBacklog(String username, Show showname) {
		User foundUser = ur.getUserByUsername(username);
		foundUser.getBacklogShowList().remove(showname);
		ur.save(foundUser);
	}
	
	public void deleteFromUserCurrent(String username, Show showname) {
		User foundUser = ur.getUserByUsername(username);
		foundUser.getCurrentShowList().remove(showname);
		ur.save(foundUser);
	}
	
	public User validateUser(String username, String userPassword) {
		User user = ur.getUserByUsername(username);
		if (user!= null) {
			if(user.getUserPassword().equals(userPassword)) {
				return user;
			}
		}
		return null;
	}
	
	
	
}
