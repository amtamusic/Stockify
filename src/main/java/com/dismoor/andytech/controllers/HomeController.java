package com.dismoor.andytech.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dismoor.andytech.models.Portfolio;
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
		return userService.addUser(new User(username, bcrypt.encode(password), email, new ArrayList<>()));
	}

	@PostMapping("/portfolio/add")
	public Portfolio add(HttpServletRequest request, @RequestBody(required = true) Portfolio p) {
		System.out.println("Adding Portfolio: " + p.toString());
		userService.getUser(request.getUserPrincipal().getName());
		return userService.addPortfolio(request.getUserPrincipal().getName(), p);
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login.jsp");
	}

	@GetMapping("/portfolio/get")
	public List<Portfolio> getPortfolios(HttpServletRequest request) {
		System.out.println("Getting portfolios");
//		ArrayList<Portfolio> ports = new ArrayList<>();
//		Portfolio p = new Portfolio();
//		p.setName("Test Port 1");
//		p.setTotal(100.00);
//		p.setAvailable(50.00);
//		p.setInvested(50.00);
//		ports.add(p);
//		p.setName("Test Port 2");
//		ports.add(p);
//		return ports;
		return userService.getUser(request.getUserPrincipal().getName()).getPortfolios();
	}

	@GetMapping("/portfolio/get/{portfolio}")
	public Portfolio getPortfolio(HttpServletRequest request, @PathVariable(name = "portfolio") String portfolio) {
		System.out.println("Getting portfolio:" + portfolio);
		List<Portfolio> portfolios = userService.getUser(request.getUserPrincipal().getName()).getPortfolios();
		for (Portfolio p : portfolios) {
			if (p.getName().equals(portfolio)) {
				return p;
			}
		}
		return null;
	}

	@DeleteMapping("/portfolio/delete/{portfolio}")
	public List<Portfolio> deletePortfolios(HttpServletRequest request,
			@PathVariable(name = "portfolio") String portfolio) {
		System.out.println("Deleting portfolio:" + portfolio);
		List<Portfolio> portfolios = userService.getUser(request.getUserPrincipal().getName()).getPortfolios();
		for (int i = 0; i < portfolios.size(); i++) {
			if (portfolios.get(i).getName().equals(portfolio)) {
				portfolios.remove(i);
			}
		}
		User u = userService.getUser(request.getUserPrincipal().getName());
		u.setPortfolios(portfolios);
		userService.addUser(u);
		return u.getPortfolios();
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
