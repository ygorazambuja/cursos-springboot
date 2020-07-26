package com.azambuja.cursospringboot.repository;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.domain.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  // Query is Optional because the method name
  @Transactional(readOnly = true)
  @Query(
    "SELECT DISTINCT obj FROM Product obj " +
    "INNER JOIN obj.categories cat " +
    "WHERE obj.name LIKE %:name% AND cat IN :categories"
  )
  Page<Product> findDistinctFirstByNameContainingAndCategoriesIn(
    @Param("name") String nome,
    @Param("categories") List<Category> categories,
    Pageable pageable
  );
}
