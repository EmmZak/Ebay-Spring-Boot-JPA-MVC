package com.ebay.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.ebay.model.AuctionManager;
import com.ebay.model.User;
import com.ebay.repositoryCustom.AuctionManagerRepoCustom;

public class AuctionManagerImpl implements AuctionManagerRepoCustom {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void updateBid(long id, User bidder, double price) {
		AuctionManager am = em.find(AuctionManager.class, id);
		am.setBid_price(price);
		am.setBidder(bidder);
		em.merge(am);
	}

	


}
