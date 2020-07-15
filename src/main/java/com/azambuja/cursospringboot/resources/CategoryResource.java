package com.azambuja.cursospringboot.resources;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.services.CategoriaService;
import java.net.URI;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
  @Autowired
  private CategoriaService categoriaService;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Category> getById(@PathVariable Integer id) {
    Category category = categoriaService.getById(id);
    return ResponseEntity.ok().body(category);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@RequestBody Category newCategory) {
    Category category = categoriaService.insert(newCategory);
    URI uri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(category.getId())
      .toUri();
    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Void> update(
    @RequestBody Category updatedCategory,
    @PathVariable Integer id
  ) {
    updatedCategory.setId(id);
    categoriaService.update(updatedCategory);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    categoriaService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
