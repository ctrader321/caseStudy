package com.WatchlistAndTracker.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.WatchlistAndTracker.dao.ShowServices;
import com.WatchlistAndTracker.dao.UserServices;
import com.WatchlistAndTracker.entities.Show;
import com.WatchlistAndTracker.entities.User;




public class Main {
	static UserServices us = new UserServices();
	static ShowServices showservice = new ShowServices();
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("Ct12345");
		user.setUserPassword("password");
		List<Show> currentlyWatching = new ArrayList<>();
		Show show1 = new Show();
		show1.setId(1);
		show1.setShowName("Naruto");
		show1.setCurrentEpisode(220);
		show1.setTotalEpisodes(220);
		show1.setPercentageCompleted(100.00f);
		
		
		currentlyWatching.add(show1);
		
		
		user.setCurrentShowList(currentlyWatching);
		boolean result = us.addUser(user);
		System.out.println(result);

		
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.jikan.moe/v3/top/anime/1/tv"))
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//			.thenApply(HttpResponse::body)
//			.thenAccept(System.out::println)
//			.join();
		
	
		
		
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://jikan1.p.rapidapi.com/search/anime?q=Attack%20on%20Titan"))
//				.header("x-rapidapi-key", "7469e1a7f4msh295b92b70f3d0cfp12a79fjsnb7a0832e2b79")
//				.header("x-rapidapi-host", "jikan1.p.rapidapi.com")
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println(response.body());
	}
	
	public static String parse(String responseBody) {
		
		JSONArray shows = new JSONArray(responseBody);
		for(int i =0; i<shows.length(); i++) {
			JSONObject show = shows.getJSONObject(i);
			int id = show.getInt("mal_id");
			String title = show.getString("title");
			String startDate = show.getString("start_date");
			System.out.println(id + " " + title + " " + startDate);
			
		}
		return null;
	}
	
	
	
}
