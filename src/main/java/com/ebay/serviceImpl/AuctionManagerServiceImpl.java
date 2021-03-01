package com.ebay.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebay.model.Auction;
import com.ebay.model.AuctionManager;
import com.ebay.model.User;
import com.ebay.repository.AuctionManagerRepo;
import com.ebay.repository.AuctionRepo;
import com.ebay.service.AuctionManagerService;
import com.ebay.service.AuctionService;

import java.util.List;

@Service
public class AuctionManagerServiceImpl implements AuctionManagerService {

	@Autowired
	private AuctionManagerRepo repo;

	@Override
	public void save(AuctionManager auctionManager) {
		repo.save(auctionManager);
	}

	@Override
	public List<AuctionManager> findByAuctionId(long id) {
		return repo.findByAuctionId(id);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}

	
	
}
