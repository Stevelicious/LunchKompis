package com.awa;

import java.util.List;

/**
 * Created by Steven Hu on 2016-10-19.
 */
public interface Repository {
	
	long createUser(User user);
	
	long createGroup(UserGroup group);
	
	long createLunch(Lunch lunch);
	
	User getUser(long userID);
	
	List<User> getUsers();
	
	UserGroup getGroup(long groupID);
	
	List<User> getUsersInGroup(long groupID);
	
	Lunch getLunch(long lunchID);
	
	List<UserGroup> getGroupList(long userID);
	
	List<Lunch> getLunchList(long userID);
	
	List<Lunch> getPublicLunchList();
	
	long updateUser(long userID, User user);
	
	long updateGroup(long groupID, UserGroup group);
	
	long addUserToGroup(long groupID, long userID);
	
	long removeUserFromGroup(long groupID, long userID);
	
	long updateLunch(long lunchID, Lunch lunch);
	
	long addUserToLunch(long lunchID, long userID);
	
	long removeUserFromLunch(long lunchID, long userID);
	
	long addGroupToLunch(long lunchID, long groupID);
	
	
}
