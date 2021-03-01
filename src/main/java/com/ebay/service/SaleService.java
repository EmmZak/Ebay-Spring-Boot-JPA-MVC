package com.ebay.service;

import com.ebay.model.*;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface SaleService {
	
	public void save(Sale s);
	public List<Sale> findAll();
	public Sale find(long id);
	public List<Sale> findAllBySeller(long id);
	public List<Sale> findAllBySeller(String username);
	public List<Sale> findAllByCategory(String category);
	public void updateSale(Sale sale);
}
