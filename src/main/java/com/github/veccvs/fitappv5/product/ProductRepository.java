package com.github.veccvs.fitappv5.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @NonNull
  Page<Product> findAll(@NonNull Pageable pageable);

  @NonNull
  Page<Product> findAllByNameContainingIgnoreCase(@NonNull String name, @NonNull Pageable pageable);

  boolean existsByName(String name);
}
