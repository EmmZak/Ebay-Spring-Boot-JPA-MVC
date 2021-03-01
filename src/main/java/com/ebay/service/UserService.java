package com.ebay.service;

import com.ebay.model.*;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface UserService {

	void save(User user);
	//void update(User user);
	
	User findByUsername(String name);
}
