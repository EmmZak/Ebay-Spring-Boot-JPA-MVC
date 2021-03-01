package com.ebay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebay.model.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer>{

}
