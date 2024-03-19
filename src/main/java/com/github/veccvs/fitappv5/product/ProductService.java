package com.github.veccvs.fitappv5.product;

import com.github.veccvs.fitappv5.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException("Product with id: [%s] not found".formatted(id)));
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public boolean productExists(String name) {
    return productRepository.existsByName(name);
  }
}
