package com.ebay.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebay.model.Auction;
import com.ebay.model.User;
import com.ebay.repository.AuctionRepo;
import com.ebay.service.AuctionService;

import java.util.List;


@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	private AuctionRepo repo;

	@Override
	public List<Auction> findAll() {
		return (List<Auction>) repo.findAll();
	}

	@Override
	public List<Auction> findBySeller(long id) {
		List<Auction> auctions = repo.findBySeller(id);
		return auctions;
	}

	@Override
	public List<Auction> findByBidder(long id) {
		List<Auction> auctions = repo.findBybidder(id);
		return auctions;
	}

	@Override
	public void updatePriceBidder(long id, User bidder, double bid_price) {
		repo.changePriceBidder(id, bidder, bid_price);
	}

	@Override
	public List<Auction> findAllBySeller(String username) {
		return repo.findBySeller(username);
	}

	@Override
	public List<Auction> findAllByCategory(String category) {
		return repo.findByCategory(category);
	}

	@Override
	public Auction findById(long id) {
		return repo.findById(id);
	}

	@Override
	public void save(Auction auction) {
		repo.save(auction);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}

	@Override
	public void updateLastBidPrice(long id, double bid_price) {
		repo.updateLastBidPrice(id, bid_price);
	}
}
