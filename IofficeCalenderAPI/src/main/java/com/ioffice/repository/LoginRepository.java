package com.ioffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ioffice.model.User;


@Repository
public interface LoginRepository extends JpaRepository<User, Integer> {


	@Query(value="select * from users where user_email=?1",nativeQuery = true)
	public User findByUserEmail(String email);
	
	
	
	@Query(
			  value = "select * from users where user_email=?1 and user_password=?2", 
			  nativeQuery = true)
	public User findByUserEmailAndUserPassword(String email, String password)  ;
	
	
}