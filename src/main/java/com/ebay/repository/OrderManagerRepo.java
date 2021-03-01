package com.ebay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebay.model.AuctionManager;
import com.ebay.model.OrderManager;

@Repository
public interface OrderManagerRepo extends JpaRepository<OrderManager, Long>{

	@Query("SELECT o from OrderManager o WHERE o.id = ?1")
	List<OrderManager> findBySaleId(long id);
	
	@Query("SELECT o from OrderManager o WHERE o.buyer.username = ?1")
	List<OrderManager> findBySaleBuyerUsername(String username);
}
