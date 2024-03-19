package com.github.veccvs.fitappv5.product.migration;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/migrate")
@RequiredArgsConstructor
public class ProductMigrationController {
  private final ProductMigrationService productMigrationService;

  @PutMapping
  public void migrateProducts() {
    productMigrationService.migrateProducts();
  }
}
