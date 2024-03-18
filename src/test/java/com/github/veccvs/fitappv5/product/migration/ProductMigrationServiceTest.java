package com.github.veccvs.fitappv5.product.migration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.veccvs.fitappv5.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

class ProductMigrationServiceTest {

  @Mock private ProductRepository productRepository;

  @Mock private RestTemplate restTemplate;

  private ProductMigrationService productMigrationService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    productMigrationService = new ProductMigrationService(productRepository);
  }

  @Test
  void testGetFitatuData() {
    // Act
    var products = productMigrationService.getFitatuGlobalData();

    // Assert
    assertNotNull(products);
  }
}
