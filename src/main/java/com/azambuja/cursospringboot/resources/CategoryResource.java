package com.azambuja.cursospringboot.resources;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoriaService categoriaService;


    @RequestMapping( method = RequestMethod.GET)
    public List<Category> list() {

        Category category1 = new Category(1, "Informatica");
        Category category2 = new Category(2, "Escritório");

        List<Category> list = new ArrayList<>();
        list.add(category1);
        list.add(category2);
        return list;
    }

    @RequestMapping( value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Category category = categoriaService.getById(id);
        return ResponseEntity.ok().body(category);
    }
}
