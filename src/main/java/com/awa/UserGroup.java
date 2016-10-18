package com.awa;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Steven Hu on 2016-10-18.
 */
public class UserGroup {
	long usergroupid;
	String title;
	List<User> users;
	LocalDateTime creationdate;
	boolean isPerm;
	
	
}
