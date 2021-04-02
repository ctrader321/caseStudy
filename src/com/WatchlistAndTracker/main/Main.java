package com.WatchlistAndTracker.main;


import org.springframework.beans.factory.annotation.Autowired;

import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.services.UserServices;

public class Main {
	@Autowired
	static UserServices us;
	
	public static void main(String[] args) {
		
		
		User user = new User();
		user.setUsername("Ct12345");
		user.setUserPassword("password");		
		
		
		us.addUser(user);
	
	
	}
}
