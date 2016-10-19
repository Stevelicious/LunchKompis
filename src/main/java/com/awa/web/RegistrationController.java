package com.awa.web;


import com.awa.validation.UserDto;
import com.awa.validation.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Administrator on 2016-10-18.
 */
@Controller
public class RegistrationController {

    @RequestMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup")
                .addObject("userDto", new UserDto()); }

    @RequestMapping("addUser")
    public ModelAndView addUser(@Valid UserDto userDto, BindingResult result){

        UserValidator userValidator = new UserValidator();
        userValidator.validate(userDto, result);

        if(result.hasErrors()){
            return new ModelAndView("signup")
                    .addObject("userDto", userDto);
        }
        if(!result.hasErrors()){
//            repo.save(userDto.createUser());
            System.out.println("Saving to db:");
            System.out.println(userDto.createUser().toString());
        }

        return new ModelAndView("index");
    }
}
