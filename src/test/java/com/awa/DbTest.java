package com.awa;

/**
 * Created by Steven Hu on 2016-10-19.
 */
public class DbTest {
	public static void main(String[] args) {
		Repository repo = new InMemoryRepository();
		System.out.println(repo.getLunch(3));
		
		User user = repo.getUser(1);
		repo.addUserToLunch(3,1);
		System.out.println(repo.getLunch(3));

	
	}
}
