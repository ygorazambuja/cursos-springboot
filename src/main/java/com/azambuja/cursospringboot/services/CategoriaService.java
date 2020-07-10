package com.azambuja.cursospringboot.services;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.repository.CategoryRepository;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Object " + Category.class.getName() + " id: " + id + " not found"));
    }


}
