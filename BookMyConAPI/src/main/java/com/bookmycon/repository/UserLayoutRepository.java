package com.bookmycon.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmycon.model.UserLayout;


@Repository
public interface UserLayoutRepository extends JpaRepository<UserLayout, String> {

}
