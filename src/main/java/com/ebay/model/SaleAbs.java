package com.ebay.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//@Entity
//@Table(name="sale_abs")
//@Inheritance(strategy=InheritanceType.JOINED)
@Component
@MappedSuperclass
public abstract class SaleAbs {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
	//private User seller;
	@Autowired
	@OneToOne(cascade=CascadeType.ALL)
	protected Item item;	
	
	@Autowired 
	@ManyToOne()
	//@JoinColumn(name="seller_username")
	protected User seller;
	
	protected String title;
	protected Double price;
	
	protected boolean sold;
	
	public SaleAbs(){}
	
	public SaleAbs(Item item, User seller, String title, Double price) {
		this.item = item;
		this.seller = seller;
		this.title = title;
		this.price = price;
		this.sold = false;
		//this.sales = new ArrayList<>();
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SaleAbs [id=" + id + ", item=" + item + ", seller=" + seller + ", title=" + title + ", price=" + price
				+ ", sold=" + sold + "]";
	}
	
}
