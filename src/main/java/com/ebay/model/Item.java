package com.ebay.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/* data.sql
insert into items values (0, 'shoes');
insert into items values (1, 'books');
insert into items values (2, 'nothing');
*/

@Entity
@Table(name="Item")
@Component
public class Item {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String category;
	
	public Item() {}
	
	public Item(String cat) {
		this.category = cat;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", category=" + category + "]";
	}
	
	
}
