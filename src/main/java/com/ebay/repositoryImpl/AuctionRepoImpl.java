package com.ebay.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.ebay.model.Auction;
import com.ebay.model.User;
import com.ebay.repositoryCustom.AuctionRepoCustom;

public class AuctionRepoImpl implements AuctionRepoCustom {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void changePriceBidder(long id, User bidder, double bid_price) {
		
		Auction auction = em.find(Auction.class, id);
		auction.setLast_bid_price(bid_price);
		auction.setLast_bidder(bidder);
		em.merge(auction);
	}

	@Transactional
	@Override
	public void updateLastBidPrice(long id, double bid_price) {
		Auction auction = em.find(Auction.class, id);
		auction.setLast_bid_price(bid_price);
		em.merge(auction);
	}
}
