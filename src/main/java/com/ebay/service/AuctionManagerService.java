package com.ebay.service;

import com.ebay.model.*;
import com.ebay.repository.AuctionManagerRepo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface AuctionManagerService {

	public void save(AuctionManager auctionManager);
	List<AuctionManager> findByAuctionId(long id);
	//void updateBidder(long id, User bidder, double price);
}
