package com.dismoor.andytech.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dismoor.andytech.models.CurrentUser;
import com.dismoor.andytech.models.User;
import com.dismoor.andytech.repos.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repo.findById(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Invalid Credentials");
		} else {
			System.out.println("User Found");
			return new CurrentUser(user.get());
		}
	}

}
