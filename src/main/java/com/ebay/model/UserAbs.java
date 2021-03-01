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
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@MappedSuperclass
//@Table(name="user_abs")
//@Inheritance(strategy=InheritanceType.JOINED)
@Component
public abstract class UserAbs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	protected String username;
	protected String password;
	
	
	public UserAbs() {}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserAbs(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		//this.sales = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String mail) {
		this.password = mail;
	}
	/*
	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}
	
	@Override
	public String toString() {
		return "UserAbs [id=" + id + ", name=" + name + ", mail=" + mail + ", sales=" + sales + "]";
	}
	*/
	@Override
	public String toString() {
		return "UserAbs [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
