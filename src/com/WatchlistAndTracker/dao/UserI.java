package com.WatchlistAndTracker.dao;

import java.util.List;

import com.WatchlistAndTracker.entities.Show;
import com.WatchlistAndTracker.entities.User;

public interface UserI{
	
	public boolean addUser(User user);
	public User getUserByUsername(String username);
	public int updateUserById(Integer id, User currentUser);
	public User deleteUserById(Integer id);
	public List<Show> getBacklogUserShowList(String username);
	public List<Show> getCurrentUserShowList(String username);

}
