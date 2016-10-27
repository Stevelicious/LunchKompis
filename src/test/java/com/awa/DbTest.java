package com.awa;

import com.awa.tables.Lunch;
import com.awa.tables.User;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Steven Hu on 2016-10-19.
 */
public class DbTest {
	public static void main(String[] args) {
		
		Repository repo = new InMemoryRepository();
		System.out.println(repo.getLunch(3));
		
		
		User user = repo.getUser(1);
		repo.addUserToLunch(3,1); //Add an user to a lunch
		System.out.println(repo.getLunch(3));
		
		System.out.println(repo.getGroup(2));
		repo.addUserToGroup(2,5); //Add an user to a group
		System.out.println(repo.getGroup(2));
		
		
		Lunch lunch = new Lunch();
		lunch.setTitle("Lunch"+99);
		lunch.setDate(LocalDate.now().toString());
		lunch.setTime(LocalTime.now().plusHours(99).toString()
		);
		lunch.setPlace("Place"+99);
		lunch.setPublic(true);
		
		long status = repo.createLunch(lunch);
		System.out.println(repo.getLunch(status));
		repo.addGroupToLunch(status, 2);
		System.out.println(repo.getLunch(status));
		
		System.out.println(repo.getLunch(3));
		repo.addGroupToLunch(3, 2);
		System.out.println(repo.getLunch(3));
		
		
	}
}
