package com.ebay.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="OrderManager")
public class OrderManager {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Autowired
	@OneToOne
	private Sale sale;
	
	@Autowired
	@OneToOne
	private User buyer;

	public OrderManager() {}
	
	public OrderManager(long id, Sale sale, User buyer) {
		super();
		this.id = id;
		this.sale = sale;
		this.buyer = buyer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return "SaleManager [id=" + id + ", sale=" + sale + ", buyer=" + buyer + "]";
	}
	
	
	
	
	
}
