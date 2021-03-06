package com.cognizant.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.authorization.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	
	User findByUserName(String userName);

}



