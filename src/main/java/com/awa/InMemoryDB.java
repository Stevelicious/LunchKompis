package com.awa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Hu on 2016-10-18.
 */
public class InMemoryDB {
	public List<User> users = new ArrayList<>();
	public List<Lunch> lunches = new ArrayList<>();
	public List<UserGroup> groupes= new ArrayList<>();
	
	public InMemoryDB() {
		
		
		for (int i = 0; i < 7; i++) {
			User user = new User();
			user.setFirstname("Firstname" + i);
			user.setLastname("Lastname" + i);
			user.setNickname("Nickname" + i);
			user.setPassword("User" + i);
			user.setEmail("Email" + i);
			users.add(user);
		}
		
		for (int i = 0; i < 5; i++) {
			Lunch lunch = new Lunch();
			lunch.setTitle("Lunch"+i);
			lunch.setDate(LocalDate.now());
			lunch.setTime(LocalTime.now().plusHours(i));
			lunch.setPlace("Place"+i);
			lunch.setPublic(true);
			for (int j = 1; j < i + 2; j++) {
				lunch.setUsers(users.get(j), "Attending");
			}
			
			lunches.add(lunch);
		}
		
		for (int i = 0; i < 2; i++) {
			UserGroup group = new UserGroup();
			group.setTitle("Group"+i);
			group.setCreationdate(LocalDateTime.now());
			group.setPerm(true);
			group.setUsers(users.subList(i,i+3));
			groupes.add(group);
		}
	}
}
