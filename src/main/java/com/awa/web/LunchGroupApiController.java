package com.awa.web;

import com.awa.InMemoryRepository;
import com.awa.SqlServerRepository;
import com.awa.tables.Lunch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by Administrator on 2016-10-18.
 */
@RestController
public class LunchGroupApiController {

    @Autowired
    InMemoryRepository repo;
    @Autowired
    SqlServerRepository sqlRepo;

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/api/groups")
    public
    @ResponseBody
    List<Lunch> getLunchGroups() throws JsonProcessingException {

        return repo.getPublicLunchList();
    }


    @PutMapping("/api/groups/{id}")
    public ModelAndView addUserToGroup(HttpSession session, @PathVariable("id") Long lunchid) {

        System.out.println("userid = " + session.getAttribute("userid") + ", groupid = " + lunchid);
        repo.addUserToLunch(lunchid, Long.parseLong(session.getAttribute("userid").toString()));
        return new ModelAndView("user/index");
    }

    @PostMapping("/api/groups")
    public String createLunchInDb()

    {
        Lunch lunch = new Lunch();
        lunch.setHost(1);
        lunch.setTitle("Lunch User");
        lunch.setDate(LocalDate.now());
        lunch.setTime(LocalTime.now().plusHours(5));
        lunch.setPlace("vegabaren");
        lunch.setPublic(true);

        System.out.println(sqlRepo.createLunch(lunch));
        return "Sucess";
    }

    @GetMapping("/api/groups/{id}")

    @DeleteMapping("/api/groups/{id}")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}