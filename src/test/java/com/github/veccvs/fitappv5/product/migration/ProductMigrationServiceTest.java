package com.github.veccvs.fitappv5.product.migration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.veccvs.fitappv5.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductMigrationServiceTest {

  @Mock private ProductService productService;
  
  private ProductMigrationService productMigrationService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    productMigrationService = new ProductMigrationService(productService);
  }

  @Test
  void testGetFitatuData() {
    // Act
    var products = productMigrationService.getFitatuGlobalData();

    // Assert
    assertNotNull(products);
  }

  @Test
  void testGetAllProductsInformation() {
    // Act
    var productsInformation = productMigrationService.getAllProductsInformation();

    // Assert
    assertNotNull(productsInformation);
  }

  @Test
  void testMigrateProducts() {
    // Act
    var result = productMigrationService.migrateProducts();

    // Assert
    assertTrue(result);
  }
}
