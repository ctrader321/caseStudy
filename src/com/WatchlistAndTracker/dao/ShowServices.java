package com.WatchlistAndTracker.dao;

import com.WatchlistAndTracker.entities.Show;

public class ShowServices extends MariaDbDAO implements ShowI {

	@Override
	public boolean addShow(Show show) {
		boolean res = false;
		connect();
		try {
			em.getTransaction().begin();
			em.persist(show);
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

}
