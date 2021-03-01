package com.ebay.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.ebay.model.Auction;
import com.ebay.model.Item;
import com.ebay.repository.ItemRepo;
import com.ebay.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepo repo;

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return (List<Item>) repo.findAll();
		//return null;
		
	}
}
