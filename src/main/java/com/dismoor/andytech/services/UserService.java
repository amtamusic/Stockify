package com.dismoor.andytech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dismoor.andytech.models.User;
import com.dismoor.andytech.repos.UserRepo;

@Service
public class UserService {

	private UserRepo userRepo;

	@Autowired
	public UserService(UserRepo userRepo) {
		// TODO Auto-generated constructor stub
		this.userRepo = userRepo;
	}

	public String addUser(User user) {
		System.out.println(user);
		userRepo.save(user);
		return "was created successfully.";
	}
}
