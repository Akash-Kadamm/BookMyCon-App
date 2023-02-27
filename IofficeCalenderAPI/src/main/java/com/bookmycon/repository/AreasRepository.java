package com.bookmycon.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmycon.model.Areas;
import com.bookmycon.model.UserLayout;


@Repository
public interface AreasRepository extends JpaRepository<Areas, Integer> {

	
	List<Areas> findByUserLayout(UserLayout userLayout);

	List<Areas> findByCoords(int[]  coords);
}