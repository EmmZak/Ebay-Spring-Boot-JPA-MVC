package com.ebay.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebay.model.Auction;
import com.ebay.model.User;
import com.ebay.repository.UserRepo;
import com.ebay.service.UserService;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo repo;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Override
	public void save(User user) {
		User u = new User();
		u.setUsername(user.getUsername());
		System.out.println("setting new users password");
		String encryptedPassword = encoder.encode(user.getPassword());
		u.setPassword(encryptedPassword);
		repo.save(u);
	}
	/*
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}
	*/
	@Override
	public User findByUsername(String username) {
		return repo.findByUsername(username);
	}


	
	

}
