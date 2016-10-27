package com.awa.web;

import com.awa.InMemoryRepository;
import com.awa.SqlServerRepository;
import com.awa.tables.Lunch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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

        return sqlRepo.getPublicLunchList();
    }


    @PostMapping("/api/groups/{id}")
    public ModelAndView addUserToGroup(HttpSession session, @PathVariable("id") Long lunchid) {

        System.out.println("userid = " + session.getAttribute("userid") + ", groupid = " + lunchid);
        sqlRepo.addUserToLunch(lunchid, Long.parseLong(session.getAttribute("userid").toString()));
        return new ModelAndView("user/index");
    }

    @PostMapping("/api/groups/new")
    public String createLunchInDb(@RequestBody String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Lunch lunch = mapper.readValue(data, Lunch.class);
        System.out.println(lunch);
        System.out.println(data);
        return "1";
    }

//    {
//        Lunch lunch = new Lunch();
//        lunch.setHost(1);
//        lunch.setTitle("Lunch User");
//        lunch.setDate(LocalDate.now());
//        lunch.setTime(LocalTime.now().plusHours(5));
//        lunch.setPlace("vegabaren");
//        lunch.setPublic(true);
//        lunch.setOsm_id(369976808);
//        lunch.setOsm_type("N");
//
//        System.out.println(sqlRepo.createLunch(lunch));
//        return "Sucess";
//    }

    @GetMapping("/api/groups/{id}")

    @DeleteMapping("/api/groups/{id}")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}