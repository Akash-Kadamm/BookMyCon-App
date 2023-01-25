package com.ioffice.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ioffice.model.UserLayout;


@Repository
public interface UserLayoutRepository extends JpaRepository<UserLayout, String> {

}
