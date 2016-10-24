package com.awa.web;

import com.awa.InMemoryRepository;
import com.awa.Lunch;
import com.awa.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

/**
 * Created by Administrator on 2016-10-18.
 */
@RestController
public class LunchGroupApiController {

    InMemoryRepository repo = new InMemoryRepository();
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/api/groups")
    public @ResponseBody List<Lunch> getLunchGroups() throws JsonProcessingException {
        
        return repo.getPublicLunchList();
    }

    @PostMapping("/api/groups")

    @GetMapping("/api/groups/{id}")

    @DeleteMapping("/api/groups/{id}")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
