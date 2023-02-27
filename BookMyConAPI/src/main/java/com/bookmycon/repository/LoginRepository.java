package com.bookmycon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmycon.model.User;


@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {

	public User findByUserEmail(String email);
	public User findByUserEmailAndUserPassword(String email, String password)  ;
	
	
}