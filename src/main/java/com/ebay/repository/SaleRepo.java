package com.ebay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebay.model.Sale;
import com.ebay.repositoryCustom.SaleRepoCustom;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long>, SaleRepoCustom {
	
	//@Query("select s from Sale s where s.seller.id= ?1")
	//List<Sale> findByAll();
	
	@Query("select s from Sale s where s.id= ?1")
	Sale findById(long id);
	
	@Query("select s from Sale s where s.seller.id= ?1")
	List<Sale> findBySeller(long id);
	
	@Query("select s from Sale s where s.seller.username= ?1")
	List<Sale> findBySeller(String username);
	
	@Query("select s from Sale s where s.item.category= ?1")
	List<Sale> findByCategory(String category);
	
}
