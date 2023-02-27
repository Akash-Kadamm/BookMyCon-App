package com.bookmycon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookmycon.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUserId(int id);
}
