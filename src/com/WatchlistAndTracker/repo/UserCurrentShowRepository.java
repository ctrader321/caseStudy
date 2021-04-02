package com.WatchlistAndTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WatchlistAndTracker.entities.UserCurrentShow;

public interface UserCurrentShowRepository extends JpaRepository<UserCurrentShow, String>{

	UserCurrentShow getUserCurrentShowByUsernameAndShowName(String username, String showName);
	
}
