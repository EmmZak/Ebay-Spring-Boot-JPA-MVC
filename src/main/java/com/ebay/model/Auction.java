package com.ebay.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Table(name="Auction")
@Component
public class Auction extends SaleAbs {
	
	//@Transient
	//@Column(name="bid_prices", columnDefinition="double[]")
	//private List<Double> bid_prices;
	private double last_bid_price;
	
	@Autowired
	@OneToOne
	private User last_bidder;
	/*
	@Autowired
	@Transient
	@Qualifier("auction_price_user")
	// key - user id, value - bid - price
	private Map<Integer, Double> bids;
	*/
	
	public Auction() {}
	
	public Auction(Item item, User seller, String title, Double price) {
		super(item, seller, title, price);
		//this.title = title;
		this.last_bid_price = 0;
		this.last_bidder = null;
		//this.bids = new HashMap<>();
	}
	
	public Auction(Item item, User seller, String title, Double price, User last_bidder, double last_bid_price) {
		super(item, seller, title, price);
		//this.title = title;
		this.last_bidder = last_bidder;
		this.last_bid_price = last_bid_price;
		this.last_bidder = null;
		//this.bids = new HashMap<>();
	}

	public Double getPrices() {
		return last_bid_price;
	}

	public void setPrices(Double last_bid_price) {
		this.last_bid_price = last_bid_price;
	}

	public double getLast_bid_price() {
		return last_bid_price;
	}

	public void setLast_bid_price(double last_bid_price) {
		this.last_bid_price = last_bid_price;
	}

	public User getLast_bidder() {
		return last_bidder;
	}

	public void setLast_bidder(User last_bidder) {
		this.last_bidder = last_bidder;
	}
	/*
	public Map<Integer, Double> getBids() {
		return bids;
	}

	public void setBids(HashMap<Integer, Double> bids) {
		this.bids = bids;
	}
	
	@Override
	public String toString() {
		return "Auction [last_bid_price=" + last_bid_price + ", bids=" + bids + ", id=" + id + ", item=" + item + ", seller=" + seller
				+ ", title=" + title + ", price=" + price + "]";
	}
	*/
	@Override
	public String toString() {
		return "Auction [last_bid_price=" + last_bid_price + ", id=" + id + ", item=" + item + ", seller=" + seller
				+ ", title=" + title + ", price=" + price + "]";
	}
	
}
