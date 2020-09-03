package com.dismoor.andytech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dismoor.andytech.models.User;
import com.dismoor.andytech.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public String add(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		return userService.addUser(new User(username, password));
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "login.jsp";
	}

	@RequestMapping("/logout-successful")
	public String logoutPage() {
		return "logout.jsp";
	}
}
