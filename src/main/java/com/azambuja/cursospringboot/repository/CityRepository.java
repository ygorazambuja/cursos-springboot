package com.azambuja.cursospringboot.repository;

import com.azambuja.cursospringboot.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
