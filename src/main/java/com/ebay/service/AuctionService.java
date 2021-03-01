package com.ebay.service;

import com.ebay.model.*;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface AuctionService {

	public void save(Auction auction);
	public List<Auction> findAll();
	public Auction findById(long id);
	public List<Auction> findBySeller(long id);
	public List<Auction> findByBidder(long id);
	public List<Auction> findAllBySeller(String username);
	public List<Auction> findAllByCategory(String category);
	void updatePriceBidder(long id, User bidder, double bid_price);
	void updateLastBidPrice(long id, double bid_price);
}
