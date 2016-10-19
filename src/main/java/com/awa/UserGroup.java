package com.awa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Hu on 2016-10-18.
 */

@Entity
@Table
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long usergroupid;
	String title;
	List<User> users = new ArrayList<>();
	LocalDateTime creationdate;
	boolean isPerm;
	
	public long getUsergroupid() {
		return usergroupid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public LocalDateTime getCreationdate() {
		return creationdate;
	}
	
	public void setCreationdate(LocalDateTime creationdate) {
		this.creationdate = creationdate;
	}
	
	public boolean isPerm() {
		return isPerm;
	}
	
	public void setPerm(boolean perm) {
		isPerm = perm;
	}
}
