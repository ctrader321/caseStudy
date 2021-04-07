package com.WatchlistAndTracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WatchlistAndTracker.entities.UserCurrentShow;

public interface UserCurrentShowRepository extends JpaRepository<UserCurrentShow, String>{

	UserCurrentShow getUserCurrentShowByUsernameAndShowName(String username, String showName);
	
	UserCurrentShow deleteUserCurrentShowByUsernameAndShowName(String username, String showName);
	
	List<UserCurrentShow> findAllByUsername(String username);
}
