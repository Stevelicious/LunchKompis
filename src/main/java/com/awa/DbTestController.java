package com.awa;

/**
 * Created by Administrator on 2016-10-20.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DbTestController {
    @Autowired
    private SqlServerRepository repo;


    @RequestMapping(value = "/dbtest", produces = "text/plain")
    @ResponseBody
    public String testDb() {


        User user = new User();
        user.setFirstname("asd");
        user.setLastname("asdasd");
        user.setEmail("sd@asdasd.com");
        user.setPassword("sdasdas");
        user.setState(2);
        user.setNickname("mynick");
        repo.createUser(user);
        System.out.println(repo.getUsers());

        return "Database connectivity seems ";

    }

}
