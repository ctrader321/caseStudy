package com.watchlist.and.tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.watchlist.and.tracker.entities.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {

	Show getShowByShowName(String showname);
}
