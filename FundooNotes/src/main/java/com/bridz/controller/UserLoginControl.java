package com.bridz.controller;
import com.bridz.model.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserLoginControl {
	
	
	@GetMapping("/userLogin")
	public ModelAndView userLogin(@RequestParam("userName") String userName, @RequestParam("password") String password) {

		ModelAndView mv = new ModelAndView("Home.jsp");
		mv.addObject("userName", userName);
		mv.addObject("password", password);
		return mv;
	}

	@GetMapping("/userRegistration")
	public ModelAndView userRegistration(UserDetails userData) {

		ModelAndView mv = new ModelAndView("Home.jsp");
		mv.addObject("userData", userData);
		return mv;
	}

} 
