package com.github.veccvs.fitappv5.product.migration;

import com.github.veccvs.fitappv5.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMigrationService {
  private final ProductRepository productRepository;
}
