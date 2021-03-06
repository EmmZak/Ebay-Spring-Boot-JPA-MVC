package com.ebay.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ebay.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

	//@Query("SELECT u FROM User u WHERE u.username = :username")
	//User findByUsername(@Param("username") String username);
	User findByUsername(String username);
}
