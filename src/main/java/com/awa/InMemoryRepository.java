package com.awa;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Hu on 2016-10-18.
 */
public class InMemoryRepository implements Repository {
	
	InMemoryDB db = new InMemoryDB();
	
	@Override
	public long createUser(User user) {
		db.users.add(user);
		return db.users.get(db.users.indexOf(user)).getUserid();
	}
	
	@Override
	public long createGroup(UserGroup group) {
		db.groupes.add(group);
		return db.groupes.get(db.groupes.indexOf(group)).getUsergroupid();
	}
	
	@Override
	public long createLunch(Lunch lunch) {
		db.lunches.add(lunch);
		return db.lunches.get(db.lunches.indexOf(lunch)).getLunchid();
	}
	
	@Override
	public User getUser(long userID) {
		User user = null;
		for (User u :
				db.users) {
			if (u.getUserid() == userID) {
				user = u;
				break;
			}
		}
		return user;
	}
	@Override
	public List<User> getUsers(){
		return db.users;
	}
	
	@Override
	public UserGroup getGroup(long groupID) {
		for (UserGroup group :
				db.groupes) {
			if (group.getUsergroupid() == groupID) {
				return group;
			}
		}
		return null;
	}
	
	@Override
	public List<User> getUsersInGroup(long groupID) {
		return getGroup(groupID).getUsers();
	}
	
	
	@Override
	public Lunch getLunch(long lunchID) {
		for (Lunch lunch :
				db.lunches) {
			if (lunch.getLunchid() == lunchID) {
				return lunch;
			}
		}
		return null;
	}
	
	@Override
	public List<UserGroup> getGroupList(long userID) {
		List<UserGroup> groups = new ArrayList<>();
		User user = getUser(userID);
		
		for (UserGroup group :
				db.groupes) {
			if (group.users.contains(user)) {
				groups.add(group);
			}
		}
		return groups;
	}
	
	@Override
	public List<Lunch> getLunchList(long userID) {
		List<Lunch> lunches = new ArrayList<>();
		User user = getUser(userID);
		
		for (Lunch lunch:
				db.lunches) {
			if (lunch.users.containsKey(user)) {
				lunches.add(lunch);
			}
		}
		return lunches;
	}
	
	@Override
	public List<Lunch> getPublicLunchList() {
		List<Lunch> lunches = new ArrayList<>();
		
		for (Lunch lunch:
				db.lunches) {
			if (lunch.isPublic) {
				lunches.add(lunch);
			}
		}
		return lunches;
	}
	
	@Override
	public long updateUser(long userID, User user) {
		return 0;
	}
	
	@Override
	public long updateGroup(long groupID, UserGroup group) {
		return 0;
	}
	
	@Override
	public long addUserToGroup(long groupID, long userID) {
		UserGroup group = getGroup(groupID);
		User user = getUser(userID);
		
		if(!group.getUsers().contains(user)){
			group.getUsers().add(user);
			return 1;
		}
		return 0;
	}
	
	@Override
	public long removeUserFromGroup(long groupID, long userID) {
		UserGroup group = getGroup(groupID);
		User user = getUser(userID);
		
		if(group.getUsers().contains(user)){
			group.getUsers().remove(user);
			return 1;
		}
		return 0;
	}
	
	@Override
	public long updateLunch(long lunchID, Lunch lunch) {
		return 0;
	}
	
	@Override
	public long addUserToLunch(long lunchID, long userID) {
		Lunch lunch = getLunch(lunchID);
		User user = getUser(userID);
		
		if(!lunch.users.containsKey(user)){
			lunch.setUsers(user,"Attending");
			return 1;
		}
		return 0;
	}
	
	@Override
	public long removeUserFromLunch(long lunchID, long userID) {
		Lunch lunch = getLunch(lunchID);
		User user = getUser(userID);
		
		if(lunch.users.containsKey(user)){
			lunch.users.remove(user);
			return 1;
		}
		return 0;
	}
	
	@Override
	public long addGroupToLunch(long lunchID, long groupID) {
		Lunch lunch = getLunch(lunchID);
		List<User> users = getUsersInGroup(groupID);
		
		for (User user: users) {
			if (!lunch.getUsers().keySet().contains(user))
			lunch.setUsers(user,"Attending");
		}
		return 1;
	}
}
