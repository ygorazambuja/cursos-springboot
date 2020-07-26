package com.azambuja.cursospringboot.resources;

import com.azambuja.cursospringboot.domain.Product;
import com.azambuja.cursospringboot.dto.ProductDTO;
import com.azambuja.cursospringboot.resources.utils.URL;
import com.azambuja.cursospringboot.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
  @Autowired
  private ProductService productService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Product> findById(@PathVariable Integer id) {
    Product product = productService.find(id);
    return ResponseEntity.ok().body(product);
  }

  @GetMapping
  public ResponseEntity<Page<ProductDTO>> getByPage(
    @RequestParam(value = "name", defaultValue = "") String name,
    @RequestParam(value = "categories", defaultValue = "0") String categories,
    @RequestParam(value = "page", defaultValue = "0") Integer page,
    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
    @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
    @RequestParam(value = "direction", defaultValue = "ASC") String direction
  ) {
    String decodedName = URL.stringToDecodedString(name);
    List<Integer> ids = URL.stringToIntegerArray(categories);

    Page<Product> productServicePage = productService.findPage(
      decodedName,
      ids,
      page,
      linesPerPage,
      orderBy,
      direction
    );
    Page<ProductDTO> productDTOS = productServicePage.map(ProductDTO::new);
    return ResponseEntity.ok().body(productDTOS);
  }
}
