package com.awa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Steven Hu on 2016-10-18.
 */
public class Lunch {
	long lunchid;
	String title;
	LocalDate date;
	LocalTime time;
	boolean isPublic;
	String place;
	List<User> users;
	
	public long getLunchid() {
		return lunchid;
	}
	
	public void setLunchid(long lunchid) {
		this.lunchid = lunchid;
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
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
