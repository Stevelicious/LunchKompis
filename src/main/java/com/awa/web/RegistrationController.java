package com.awa.web;


import com.awa.InMemoryRepository;
import com.awa.validation.UserDto;
import com.awa.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

/**
 * Created by Administrator on 2016-10-18.
 */
@Controller
public class RegistrationController {
	
	@Autowired
	InMemoryRepository repo;
	
	@RequestMapping("/")
	public ModelAndView start(@ModelAttribute("userDto") UserDto userDto) {
		return new ModelAndView("index")
				.addObject("userDto", userDto);
	}
	
	@RequestMapping("addUser")
	public RedirectView addUser(@Valid UserDto userDto, BindingResult result,RedirectAttributes redirectAttributes) {
		
		UserValidator userValidator = new UserValidator();
		userValidator.validate(userDto, result);
		
		if (!result.hasErrors()) {
			repo.createUser(userDto.createUser());
			System.out.println(repo.getUsers());
			redirectAttributes.addFlashAttribute("userDto",new UserDto());
			
			return new RedirectView("/",true);
		}
		redirectAttributes.addFlashAttribute("userDto",userDto);
		return new RedirectView("/#tf-register",true);
	}
}
