package com.WatchlistAndTracker.entities;

import java.util.List;

import javax.persistence.*;



@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(nullable=false, length=30)
	private String username;
	
	@Column(nullable=false, length=30)
	private String userPassword;

	@Transient //doesn't persist;
	private String passwordConfirm;
	
	@JoinTable(name="user_current")
	@OneToMany(targetEntity = Show.class, fetch= FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Show> currentShowList;
	
	@JoinTable(name="user_backlog")
	@OneToMany(targetEntity = Show.class, fetch= FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Show> backlogShowList;
	
	
	public User() {
		super();
	}
	public User(Integer id, String username, String userPassword) {
		super();
		Id = id;
		this.username = username;
		this.userPassword = userPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}
	
	public List<Show> getBacklogShowList() {
		return backlogShowList;
	}
	
	public void setBacklogShowList(List<Show> backlogShowList) {
		this.backlogShowList = backlogShowList;
	}
	
	public List<Show> getCurrentShowList() {
		return currentShowList;
	}
	
	public void setCurrentShowList(List<Show> currentShowList) {
		this.currentShowList = currentShowList;
	}
}
