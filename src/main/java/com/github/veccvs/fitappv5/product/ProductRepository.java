package com.github.veccvs.fitappv5.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findAllPaged(Pageable pageable);

  boolean existsByName(String name);
}
