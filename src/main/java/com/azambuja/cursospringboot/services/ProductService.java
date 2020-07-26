package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.domain.Client;
import com.azambuja.cursospringboot.domain.Product;
import com.azambuja.cursospringboot.repository.CategoryRepository;
import com.azambuja.cursospringboot.repository.ProductRepository;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  @Autowired
  CategoryRepository categoryRepository;

  public Page<Product> findPage(
    String name,
    List<Integer> ids,
    Integer page,
    Integer linesPerPage,
    String orderBy,
    String direction
  ) {
    PageRequest pageRequest = PageRequest.of(
      page,
      linesPerPage,
      Sort.Direction.valueOf(direction),
      orderBy
    );
    List<Category> categories = categoryRepository.findAllById(ids);
    return productRepository.findDistinctFirstByNameContainingAndCategoriesIn(
      name,
      categories,
      pageRequest
    );
  }

  public Product find(Integer id) {
    return productRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ObjectNotFoundException(
            "Object " + Client.class.getName() + " id: " + id + " not found"
          )
      );
  }
}
