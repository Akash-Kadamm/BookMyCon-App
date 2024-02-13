package com.bookmycon.repository;

import com.bookmycon.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FloorRepository extends JpaRepository<Floor, Integer> {
    Optional<Floor> findByFid (int fid);

}
