package com.ebay.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ebay.model.Auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="AuctionManager")
public class AuctionManager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Autowired
	@OneToOne
	private Auction auction;
	
	@Autowired
	@OneToOne
	private User bidder;
	
	private Double bid_price;
	
	public AuctionManager() {}
	
	public AuctionManager(Auction auction, User bidder, Double bid_price) {
		this.auction = auction;
		this.bidder = bidder;
		this.bid_price = bid_price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public User getBidder() {
		return bidder;
	}

	public void setBidder(User bidder) {
		this.bidder = bidder;
	}

	public Double getBid_price() {
		return bid_price;
	}

	public void setBid_price(Double bid_price) {
		this.bid_price = bid_price;
	}

	@Override
	public String toString() {
		return "AuctionManager [id=" + id + ", auction=" + auction + ", bidder=" + bidder + ", bid_price=" + bid_price
				+ "]";
	}

}
