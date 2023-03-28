package com.micro.housekeepingservice.repository;

import com.micro.housekeepingservice.entity.Housekeeping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousekeepingRepository extends JpaRepository<Housekeeping, Long> {
}
