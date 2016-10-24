package com.awa;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Steven Hu on 2016-10-18.
 */

//@Entity
//@Table
public class Lunch {
	private static int count = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long lunchid;
	String title;
	LocalDate date;
	LocalTime time;
	boolean isPublic;
	String place;
//	Map<User, String> users = new HashMap<>();
//	List<User> users = new ArrayList<>();
	Map<String, ArrayList<User>> users = new HashMap<>();
	
	public Lunch() {
		lunchid = ++count;
		
	}
	
	public long getLunchid() {
		return lunchid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public boolean isPublic() {
		return isPublic;
	}
	
	public void setPublic(boolean aPublic) {
		isPublic = aPublic;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public Map<String, ArrayList<User>> getUsers() {
		return users;
	}
	
	public void setUsers(User user, String status) {
		ArrayList<User> userArrayList = new ArrayList<>();

		if(users.containsKey(status)){
			userArrayList = users.get(status);
		}
		if(userArrayList.isEmpty()){
			userArrayList.add(0, user);
		}else{
			userArrayList.add(userArrayList.size(), user);
		}
		users.put(status, userArrayList);
		for (String list: users.keySet()) {
			System.out.println(list + " contains:");
			for (User user1: users.get(list)) {
				System.out.println(user1);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ID = %d, Title = %s%n", lunchid, title));
		for (Map.Entry e : users.entrySet()) {
			User user = (User) e.getKey();
			sb.append(String.format("\tUserID = %d; %s%n", user.getUserid(), e.getValue()));
		}
		
		
		return sb.toString();
	}
}
