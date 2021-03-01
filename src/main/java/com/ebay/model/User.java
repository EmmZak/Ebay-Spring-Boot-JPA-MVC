package com.ebay.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="User")
public class User extends UserAbs {
	/*
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
    */
	
	@Transient
	@Autowired
	@OneToMany(targetEntity = Sale.class, mappedBy="seller", cascade = CascadeType.ALL) 
	private Collection<Sale> sales = new ArrayList<Sale>();
	
	
	public User() {}
	
	public User(String name, String password) {
		super(name, password);
		//this.sale = new ArrayList<>();
		//this.sales = new ArrayList<Sale>();
	}
	/*
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	*/
	/*
	public Collection<Sale> getSales() {
		return sales;
	}

	public void setSales(Collection<Sale> sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "User [roles=" + roles + ", sales=" + sales + ", id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
	
	@Override
	public String toString() {
		return "User [roles=" + roles + ", id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
	*/
	@Override
	public String toString() {
		return "User [ id=" + id + ", username=" + username + ", password="
				+ password + "]";
	}
	
}
