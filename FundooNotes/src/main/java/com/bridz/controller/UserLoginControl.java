package com.bridz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserLoginControl {

	@PostMapping("/userLogin") 
	ModelAndView userLogin() {
	 
 		ModelAndView mv = new ModelAndView("Login.jsp");
 		mv.addObject("name","prathamesh");
		return mv; 
	}
	
	@PostMapping("/userRegistration") 
	ModelAndView userRegistration() {
	 
 		ModelAndView mv = new ModelAndView("UserRegistration.jsp");
 		mv.addObject("name","prathamesh");
		return mv; 
	}
}
