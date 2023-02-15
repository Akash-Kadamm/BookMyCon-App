package com.ioffice.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ioffice.model.Areas;
import com.ioffice.model.UserLayout;


@Repository
public interface AreasRepository extends JpaRepository<Areas, Integer> {

	
	List<Areas> findByUserLayout(UserLayout userLayout);
	
	
}