package com.awa.tables;


import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Steven Hu on 2016-10-18.
 */

@Entity
@Table
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
	long host;


	@Transient
	Map<String, ArrayList<User>> users = new HashMap<>();

	String osm_type;
	int osm_id;

	public Lunch() {

	}

	public void setLunchid(long lunchid) {
		this.lunchid = lunchid;
	}

	public String getOsm_type() {
		return osm_type;
	}

	public void setOsm_type(String osm_type) {
		if (osm_type!=null){
			this.osm_type = osm_type.toUpperCase().charAt(0) + "";
		}
	}

	public int getOsm_id() {
		return osm_id;
	}

	public void setOsm_id(String osm_id) {
		this.osm_id = Integer.parseInt(osm_id);
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
	
	public void setDate(String date) {
		String[] split = date.split("T");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd YYYY");
		LocalDate localDate = LocalDate.parse(split[0]);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date parsed = null;
//		try {
//			parsed = format.parse(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		LocalDate localDate = parsed.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.date = localDate;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public void setTime(String time) {
		LocalTime localTime = LocalTime.parse(time);
		this.time = localTime;
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
	
	public long getHost() {
		return host;
	}
	
	public void setHost(long host) {
		this.host = host;
	}

	public Map<String, ArrayList<User>> getUsers() {
		return users;
	}
	
	public void setUsers(Map<String, ArrayList<User>> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ID = %d, Title = %s%n", lunchid, title));
//		for (Map.Entry e : users.entrySet()) {
//			sb.append(String.format("%n%s :",  e.getKey()));
//			ArrayList<User> list = (ArrayList<User>) e.getValue();
//			sb.append(list.toString());
//		}
		return sb.toString();
	}
}
