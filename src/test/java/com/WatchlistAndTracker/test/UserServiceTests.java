package test.java.com.WatchlistAndTracker.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.WatchlistAndTracker.repo.UserRepository;
import com.WatchlistAndTracker.services.UserServices;


public class UserServiceTests {

	@Autowired
	private UserServices us;
	
	@Bean
	private UserRepository ur;
}
