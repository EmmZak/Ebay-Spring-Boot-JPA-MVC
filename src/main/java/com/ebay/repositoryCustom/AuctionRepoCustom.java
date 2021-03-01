package com.ebay.repositoryCustom;

import com.ebay.model.User;

public interface AuctionRepoCustom {

	void changePriceBidder(long id, User bidder, double bid_price);
	
	void updateLastBidPrice(long id, double bid_price);
}
