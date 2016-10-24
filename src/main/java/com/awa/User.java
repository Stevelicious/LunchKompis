package com.awa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//@Entity
//@Table
public class User {
	
	private static int count = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    private String firstname;
    private String lastname;
    private String email;
    private String nickname;
    private String password;
    private int state;
    private Date creation_date;

    public User() {
	    userid = ++count;
    }

    public User(String firstname, String lastname, String email, String nickname, String password) {
	    userid = ++count;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public long getUserid() {
        return userid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        User.count = count;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
//
//    @Override
//    public String toString() {
////
////        ObjectMapper mapper = new ObjectMapper();
////        Map<String, Object> map = new HashMap<>();
////        map.put("userid", userid);
////        map.put("firstname", firstname);
////        map.put("lastname", lastname);
////        map.put("email", email);
////        map.put("nickname", nickname);
////        map.put("password", password);
////        String json = null;
////        try {
////            json = mapper.writeValueAsString(map);
////        } catch (JsonProcessingException e) {
////            e.printStackTrace();
////        }
////        System.out.println(json);
////        return json;
//
//    }

    @Override
    public String toString() {
        return "User{" +
                "userid " + userid +
                "firstname" + firstname +
                "lastname" + lastname+
                "email" + email+
                "nickname" + nickname+
                "password" + password+
                "state" + state +
                "creation_date" + creation_date +
                '}';
    }
}
