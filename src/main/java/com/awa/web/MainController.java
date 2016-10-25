/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.awa.web;

import com.awa.InMemoryDB;
import com.awa.InMemoryRepository;
import com.awa.SqlServerRepository;
import com.awa.User;
import com.awa.validation.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {
	@Autowired
	SqlServerRepository repo;

/*	@RequestMapping("/")
	public String root() {
		return "index";
	}*/

/*	@RequestMapping("/")
	public String index() {
		return "index";
	}*/

	@RequestMapping("/user/")
	public String userIndex(HttpSession session) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		for (User user : repo.getUsers()) {
			if(user.getNickname() == name){
				session.setAttribute("userid", user.getUserid());
			}
		}
//		session.setAttribute("userid", 12);
		return "user/index";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		System.out.println("Login fail");
		model.addAttribute("loginError", true);
		model.addAttribute("userDto", new UserDto());
		return "index";
	}

}