package com.awa;

/**
 * Created by Administrator on 2016-10-20.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Controller
public class DbTestController {
    @Autowired
    private SqlServerRepository repo;


    @RequestMapping(value = "/dbtest", produces = "text/plain")
    @ResponseBody
    public String testDb() {


/*        User user = new User();
        UserGroup group = new UserGroup();
        group.setTitle("grouptitle");
        group.setPerm(true);


        user.setFirstname("asd");
        user.setLastname("asdasd");
        user.setEmail("sd@asdasd.com");
        user.setPassword("sdasdas");
        user.setState(2);
        user.setNickname("mynick");
        repo.createUser(user);
        repo.createGroup(group);*/

/*        Lunch lunch = new Lunch();
        lunch.setTitle("Mylunch");
        lunch.setDate(LocalDate.now());
        lunch.setTime(LocalTime.now());
        lunch.setPlace("galleria");
        lunch.setPublic(true);
        repo.createLunch(lunch);*/

        User user = repo.getUser(1);
        System.out.println(user.getNickname());




        return "Database connectivity seems ";

    }

}
