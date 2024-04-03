package com.github.veccvs.fitappv5.product;

import com.github.veccvs.fitappv5.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public Page<Product> getAllProducts(Pageable page) {
    return productRepository.findAll(page);
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

  public Page<Product> searchProducts(String query, Pageable page) {
    return productRepository.findAllByNameContainingIgnoreCase(query, page);
  }

  public void deleteProduct(Long id) {
    if (!productRepository.existsById(id)) {
      throw new ResourceNotFoundException("Product with id: [%s] not found".formatted(id));
    }
    productRepository.deleteById(id);
  }
}
