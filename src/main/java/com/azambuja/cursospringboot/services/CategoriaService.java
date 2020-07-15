package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.repository.CategoryRepository;
import com.azambuja.cursospringboot.services.exceptions.DataIntegrityException;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
  @Autowired
  private CategoryRepository categoryRepository;

  public Category getById(Integer id) {
    return categoryRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ObjectNotFoundException(
            "Object " + Category.class.getName() + " id: " + id + " not found"
          )
      );
  }

  public Category insert(Category category) {
    category.setId(null);
    return categoryRepository.save(category);
  }

  public Category update(Category category) {
    Category newObj = getById(category.getId());
    newObj.setName(category.getName());
    return categoryRepository.save(newObj);
  }

  public void delete(Integer id) {
    getById(id);
    try {
      categoryRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Category have Dependents", e.getCause());
    }
  }
}
