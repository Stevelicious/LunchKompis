package com.awa;

/**
 * Created by Administrator on 2016-10-20.
 */


import com.awa.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
    @RequestMapping(value="/lout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }


}
