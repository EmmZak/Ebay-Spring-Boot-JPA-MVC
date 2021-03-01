package com.ebay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ebay.model.Auction;
import com.ebay.model.Sale;
import com.ebay.repositoryCustom.AuctionRepoCustom;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long>, AuctionRepoCustom {
//public interface AuctionRepo extends CrudRepository<Auction, Long>, AuctionRepoCustom {

	@Query("select a from Auction a where a.id = ?1")
	Auction findById(long id);
	
	@Query("select a from Auction a where a.seller.id = ?1")
	List<Auction> findBySeller(long id);
	
	@Query("select a from Auction a where a.last_bidder.id = ?1")
	List<Auction> findBybidder(long id);
	
	@Query("select a from Auction a where a.seller.username= ?1")
	List<Auction> findBySeller(String username);
	
	@Query("select a from Auction a where a.item.category= ?1")
	List<Auction> findByCategory(String category);
}
