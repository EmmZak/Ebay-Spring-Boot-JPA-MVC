package com.ebay.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.ebay.model.Sale;
import com.ebay.repositoryCustom.SaleRepoCustom;

public class SaleRepoImpl implements SaleRepoCustom {

	@PersistenceContext
	EntityManager em;
	/*
	@Transactional
	public void changePrice(long id, double price) {
		// TODO Auto-generated method stub
		
		Sale sale = em.find(Sale.class, id);
		sale.setPrice(price);
		em.merge(sale);
	}
	*/
	@Transactional
	public void editSale(Sale sale) {
		Sale s = em.find(Sale.class, sale.getId());
		s.setItem(sale.getItem());
		s.setPrice(sale.getPrice());
		s.setSeller(sale.getSeller());
		s.setTitle(sale.getTitle());
		em.merge(s);
	}

}
