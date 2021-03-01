package com.ebay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebay.model.AuctionManager;
import com.ebay.repositoryCustom.AuctionManagerRepoCustom;

@Repository
public interface AuctionManagerRepo extends JpaRepository<AuctionManager, Long>, AuctionManagerRepoCustom{

	@Query("SELECT a from AuctionManager a WHERE a.auction.id = ?1")
	List<AuctionManager> findByAuctionId(long id);
	
}
