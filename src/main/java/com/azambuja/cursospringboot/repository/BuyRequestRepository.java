package com.azambuja.cursospringboot.repository;

import com.azambuja.cursospringboot.domain.BuyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRequestRepository extends JpaRepository<BuyRequest, Integer> {
}
