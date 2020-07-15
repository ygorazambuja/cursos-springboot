package com.azambuja.cursospringboot.resources;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.dto.CategoryDTO;
import com.azambuja.cursospringboot.services.CategoriaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAll() {
    List<Category> categories = categoriaService.getAll();
    // category -> new CategoryDTO(category))
    List<CategoryDTO> categoryDTOS = categories
      .stream()
      .map(CategoryDTO::new)
      .collect(Collectors.toList());
    return ResponseEntity.ok().body(categoryDTOS);
  }

  @GetMapping(value = "/page")
  public ResponseEntity<Page<CategoryDTO>> getByPage(
    @RequestParam(value = "page", defaultValue = "0") Integer page,
    @RequestParam(value = "linesPerPage/", defaultValue = "24") Integer linesPerPage,
    @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
    @RequestParam(value = "direction", defaultValue = "ASC") String direction
  ) {
    Page<Category> categoriaServicePage = categoriaService.findPage(
      page,
      linesPerPage,
      orderBy,
      direction
    );
    Page<CategoryDTO> categoryDTOS = categoriaServicePage.map(CategoryDTO::new);
    return ResponseEntity.ok().body(categoryDTOS);
  }
}
