package com.example.springExsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			//can block the Iframe 
			.headers().frameOptions().disable()
			.and()
			// url 
				.authorizeHttpRequests()
//				 permitAll(): antMatchers set resource can access
				.antMatchers("/","/member/join","/member/joinForm","/member/login","/member/login-fail").permitAll()
//				 the other resource require certficated
				.anyRequest().authenticated()
			.and()	
//			//using Customized Login
				.formLogin()
				.usernameParameter("id")
				.loginPage("/member/login")
//				//Successed to login then URL 
				.defaultSuccessUrl("/member/login-success")
//				// handling the login process
				.loginProcessingUrl("/member/login")
//				// Failed to login then URl
				.failureUrl("/member/login-fail")
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				;
			return http.build();
	}	
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
	}
}
