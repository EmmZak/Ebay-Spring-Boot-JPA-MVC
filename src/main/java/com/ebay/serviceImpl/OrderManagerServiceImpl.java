package com.ebay.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebay.model.AuctionManager;
import com.ebay.model.OrderManager;
import com.ebay.repository.OrderManagerRepo;
import com.ebay.service.OrderManagerService;

import java.util.List;

@Service
public class OrderManagerServiceImpl implements OrderManagerService {

	@Autowired
	private OrderManagerRepo repo;

	public void delete(long id) {
		repo.deleteById(id);
	}

	@Override
	public void save(OrderManager orderManager) {
		repo.save(orderManager);
	}

	@Override
	public List<OrderManager> findBySaleId(long id) {
		return repo.findBySaleId(id);
	}

	@Override
	public List<OrderManager> findBySaleBuyerUsername(String username) {
		return repo.findBySaleBuyerUsername(username);
	}

	@Override
	public List<OrderManager> findAll() {
		return repo.findAll();
	}
	
	
}
