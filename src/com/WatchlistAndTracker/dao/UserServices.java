package com.WatchlistAndTracker.dao;

import java.util.ArrayList;
import java.util.List;

import com.WatchlistAndTracker.entities.Show;
import com.WatchlistAndTracker.entities.User;

public class UserServices extends MariaDbDAO implements UserI{

	@Override
	public boolean addUser(User user) {
		boolean res = false;
		connect();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			res = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = false;
		} finally {
			dispose();
		}
		
		return res;
	}

	@Override
	public User getUserByUsername(String username) {
		User result;
		connect();
		try {
			result = em.find(User.class, username);
		} catch (Exception e) {
			result = null;
		} finally {
			dispose();
		}
		
		
		return result;
	}

	@Override
	public int updateUserById(Integer id, User currentUser) {
		int row = 0;
		connect();
		try {
			em.getTransaction().begin();
			User user = em.find(User.class, id);
			user.setUsername(currentUser.getUsername());
			user.setUserPassword(currentUser.getUserPassword());
			em.getTransaction().commit();
			row = 1;
		} catch(Exception e) {
			row = 0;
		} finally {
			dispose();
		}
		return row;
	}

	@Override
	public User deleteUserById(Integer id) {
		connect();
		User result = null;
		try {
			result = em.find(User.class, id);
			em.getTransaction().begin();
			em.remove(result);
			em.getTransaction().commit();
		} catch(Exception e) {
			result = null;
		}finally {
			dispose();
		}
		return result;
	}

	@Override
	public List<Show> getBacklogUserShowList(String username) {
		
		List<Show> backlogUserShows = new ArrayList<Show>();
		User foundUser = null;
		connect();
		try {
			foundUser = em.find(User.class, foundUser);
			backlogUserShows = foundUser.getBacklogShowList();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			dispose();
		}
		return backlogUserShows;
	}

	@Override
	public List<Show> getCurrentUserShowList(String username) {
		List<Show> currentUserShows = null;
		User foundUser = null;
		connect();
		try {
			foundUser = em.find(User.class, foundUser);
			currentUserShows = foundUser.getCurrentShowList();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			dispose();
		}
		return currentUserShows;
	}
	
}
