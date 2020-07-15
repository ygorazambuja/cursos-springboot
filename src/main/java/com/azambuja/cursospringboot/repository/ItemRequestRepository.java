package com.azambuja.cursospringboot.repository;

import com.azambuja.cursospringboot.domain.ItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Integer> {}
