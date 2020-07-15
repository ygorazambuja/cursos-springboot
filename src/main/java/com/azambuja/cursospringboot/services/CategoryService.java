package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.dto.CategoryDTO;
import com.azambuja.cursospringboot.repository.CategoryRepository;
import com.azambuja.cursospringboot.services.exceptions.DataIntegrityException;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
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

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public Page<Category> findPage(
    Integer page,
    Integer linesPerPage,
    String orderBy,
    String direction
  ) {
    PageRequest pageRequest = PageRequest.of(
      page,
      linesPerPage,
      Direction.valueOf(direction),
      orderBy
    );
    return categoryRepository.findAll(pageRequest);
  }

  public Category fromDTO(CategoryDTO categoryDTO) {
    return new Category(categoryDTO.getId(), categoryDTO.getName());
  }
}
