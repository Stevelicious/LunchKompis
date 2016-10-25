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
package com.awa.config;

import com.awa.InMemoryDB;
import com.awa.InMemoryRepository;
import com.awa.User;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Joe Grandja
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	InMemoryRepository repo;
	
	@Autowired
	DataSource dataSource;
	
	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				// .antMatchers("/**").permitAll()	// ingen säkerhet
				.antMatchers("/css/**", "/").permitAll() //utkomenterad vid utveckling
				.antMatchers("/user/**").hasAuthority("USER")
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/user/")
				.failureUrl("/login-error");
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	// @formatter:on
	
	// @formatter:off
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user").password("password").authorities("USER");
		
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select username,password, status from users where username=?")
				.authoritiesByUsernameQuery(
						"select users.username, userRoles.role from userRoles inner join users on users.userid=userRoles.user_id where users.username=?");
	}
	// @formatter:on
}