package com.WatchlistAndTracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="shows")
public class Show {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(name="name")
	private String showName;
	
	@Column(name="current_episode")
	private int currentEpisode;
	
	@Column(name="total_episode")
	private int totalEpisodes;
	
	@Column(name="completion_percentage")
	private float percentageCompleted;
	
	public Show() {
		super();
	}
	
	public Show(int id, String showName, int currentEpisode, int totalEpisodes, float percentageCompleted) {
		super();
		Id = id;
		this.showName = showName;
		this.currentEpisode = currentEpisode;
		this.totalEpisodes = totalEpisodes;
		this.percentageCompleted = percentageCompleted;
	}

	public int getId() {
		return Id;
	}
	
	
	public void setId(Integer id) {
		Id = id;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getCurrentEpisode() {
		return currentEpisode;
	}

	public void setCurrentEpisode(Integer currentEpisode) {
		this.currentEpisode = currentEpisode;
	}

	public int getTotalEpisodes() {
		return totalEpisodes;
	}

	public void setTotalEpisodes(Integer totalEpisodes) {
		this.totalEpisodes = totalEpisodes;
	}

	public float getPercentageCompleted() {
		return percentageCompleted;
	}

	public void setPercentageCompleted(Float percentageCompleted) {
		this.percentageCompleted = percentageCompleted;
	}


}
