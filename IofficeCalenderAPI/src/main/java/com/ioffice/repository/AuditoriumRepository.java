package com.ioffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ioffice.model.Auditoriums;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditoriums, Integer> {

}
