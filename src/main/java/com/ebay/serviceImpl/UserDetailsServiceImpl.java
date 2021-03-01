package com.ebay.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ebay.model.MyUserDetails;
import com.ebay.model.User;
import com.ebay.model.UserPrincipal;
import com.ebay.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// my own user, maybe we should use spring's one ???
		User user = userRepo.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find use");
		}
		
		return new UserPrincipal(user);
	}

}
