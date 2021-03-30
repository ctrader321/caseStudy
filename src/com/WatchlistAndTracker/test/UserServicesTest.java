package com.WatchlistAndTracker.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.WatchlistAndTracker.dao.MariaDbDAO;
import com.WatchlistAndTracker.dao.UserServices;
import com.WatchlistAndTracker.entities.User;

class UserServicesTest extends MariaDbDAO {

	static UserServices us = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		us = new UserServices();
		startJDBC("root", "Annaisawesome123!");
		dropDatabase();
		createDatabase();
		createTables();
		runSQLFile("testingUserInserts.sql");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		stopJDBC();
	}

	@Test
	void testAddUser() {
		us = new UserServices();
		User newUser = new User(10, "testUsername","testPassword");
		assertTrue(us.addUser(newUser));
	}

	@Test
	void testGetUserByUsername() {
		us.getUserByUsername("ct123");
		
	}

	@Test
	void testUpdateUserById() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteUserById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBacklogUserShowList() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCurrentUserShowList() {
		fail("Not yet implemented");
	}

}
