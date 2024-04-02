package com.github.veccvs.fitappv5.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping("/all")
  public ResponseEntity<Page<Product>> getAllProducts(Pageable page) {
    return ResponseEntity.ok(productService.getAllProducts(page));
  }

  @GetMapping
  public ResponseEntity<Product> getProductById(@RequestParam Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  @GetMapping("/search")
  public ResponseEntity<Page<Product>> searchProducts(@RequestParam String query, Pageable page) {
    return ResponseEntity.ok(productService.searchProducts(query, page));
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productService.createProduct(product));
  }

  @PutMapping
  public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productService.createProduct(product));
  }
}
