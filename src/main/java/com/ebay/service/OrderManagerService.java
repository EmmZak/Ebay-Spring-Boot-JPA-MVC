package com.ebay.service;

import com.ebay.model.*;
import com.ebay.repository.AuctionManagerRepo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface OrderManagerService {

	public void save(OrderManager orderManager);
	List<OrderManager> findAll();
	List<OrderManager> findBySaleId(long id);
	List<OrderManager> findBySaleBuyerUsername(String username);
}
