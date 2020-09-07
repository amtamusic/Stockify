package com.dismoor.andytech.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dismoor.andytech.models.User;
import com.dismoor.andytech.services.UserService;

//@Controller
@RestController
@CrossOrigin
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("index.html");
	}

	// @RequestMapping(value = "/add", method = RequestMethod.GET)
	// @ResponseBody
	@PostMapping("/register/submit")
	public String add(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, @RequestParam(name = "email") String email) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		return userService.addUser(new User(username, bcrypt.encode(password), email));
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login.jsp");
	}

	@GetMapping("/details")
	public User getUserDetails(HttpServletRequest request) {
		return userService.getUser(request.getUserPrincipal().getName());
	}

	@GetMapping("/register")
	public ModelAndView registerPage(HttpServletRequest request) {
		return new ModelAndView("register.jsp");
	}

	@GetMapping("/logout-successful")
	public ModelAndView logoutPage() {
		return new ModelAndView("logout.jsp");
	}
}
