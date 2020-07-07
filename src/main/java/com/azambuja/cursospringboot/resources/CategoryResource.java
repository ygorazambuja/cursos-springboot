package com.azambuja.cursospringboot.resources;

import com.azambuja.cursospringboot.domain.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    @RequestMapping( method = RequestMethod.GET)
    public List<Category> list() {

        Category category1 = new Category(1, "Informatica");
        Category category2 = new Category(2, "Escritório");

        List<Category> list = new ArrayList<>();
        list.add(category1);
        list.add(category2);


        return list;
    }
}
