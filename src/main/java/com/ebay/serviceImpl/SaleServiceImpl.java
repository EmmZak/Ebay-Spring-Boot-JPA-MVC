package com.ebay.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebay.model.Auction;
import com.ebay.model.Sale;
import com.ebay.repository.SaleRepo;
import com.ebay.service.SaleService;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Service
public class SaleServiceImpl implements SaleService {
	///////////////////////////////////////////////////////////////////
	@Autowired
	private SaleRepo repo;

	@Override
	public List<Sale> findAll() {
		return (List<Sale>) repo.findAll();
		
	}

	@Override
	public List<Sale> findAllBySeller(long id) {
		List<Sale> sales = repo.findBySeller(id);
		return sales;
	}
	
	@Override
	public List<Sale> findAllBySeller(String username) {
		List<Sale> sales = repo.findBySeller(username);
		return sales;
	}
	
	@Override
	public void save(Sale s) {
		repo.save(s);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}

	@Override
	public Sale find(long id) {
		return repo.findById(id);
	}

	@Override
	public void updateSale(Sale sale) {
		repo.editSale(sale);
		
	}

	@Override
	public List<Sale> findAllByCategory(String category) {
		return repo.findByCategory(category);
	}

	
}
