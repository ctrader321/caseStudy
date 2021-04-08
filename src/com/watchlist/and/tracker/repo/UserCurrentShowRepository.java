package com.watchlist.and.tracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watchlist.and.tracker.entities.UserCurrentShow;

public interface UserCurrentShowRepository extends JpaRepository<UserCurrentShow, String>{

	UserCurrentShow getUserCurrentShowByUsernameAndShowName(String username, String showName);
	
	void deleteUserCurrentShowByUsernameAndShowName(String username, String showName);
	
	List<UserCurrentShow> findAllByUsernameOrderByCompletionPercentageDesc(String username);
	
}
