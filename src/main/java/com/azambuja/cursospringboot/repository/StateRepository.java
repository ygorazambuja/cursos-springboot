package com.azambuja.cursospringboot.repository;

import com.azambuja.cursospringboot.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {}
