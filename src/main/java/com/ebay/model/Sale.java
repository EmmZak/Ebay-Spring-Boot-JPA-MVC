package com.ebay.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

//import javax.persistence.Entity;
//import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Sale")
//@Inheritance(strategy=InheritanceType.JOINED)
@Component
public class Sale extends SaleAbs {

	//private String title;
	
	public Sale() {}
	
	public Sale(Item item, User seller, String title, Double price) {
		super(item, seller, title, price);
		//this.title = title;
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

	@Override
	public String toString() {
		return "Sale [id=" + id + ", item=" + item + ", seller=" + seller + ", title=" + title + ", price=" + price
				+ "]";
	}
	
}
