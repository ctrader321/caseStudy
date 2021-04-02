package main.java.com.WatchlistAndTracker.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.WatchlistAndTracker.entities.User;
import com.WatchlistAndTracker.services.UserServices;

class UserServicesTest {

	@Autowired
	UserServices us;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testAddUser() {
		User user = new User("testUser1", "testPass1");
		
		assertSame(user, us.addUser(user));
	}

	@Test
	void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateUserBacklog() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateUserCurrent() {
		fail("Not yet implemented");
	}

}
