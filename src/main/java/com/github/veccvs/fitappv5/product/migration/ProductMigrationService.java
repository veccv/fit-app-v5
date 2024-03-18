package com.github.veccvs.fitappv5.product.migration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.veccvs.fitappv5.product.ProductRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductMigrationService {
  private final ProductRepository productRepository;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final Logger logger = LoggerFactory.getLogger(ProductMigrationService.class);

  public List<ProductInformation> getAllProductsInformation() {
    var productsInformation = new ArrayList<ProductInformation>();

    getFitatuGlobalData()
        .forEach(
            product -> {
              try {
                ResponseEntity<String> response = getFitatuProductResponse(product.getId());
                if (response.getStatusCode() == HttpStatus.OK) {
                  productsInformation.add(processProductInformationResponse(response.getBody()));
                  logger.info("Got data for product id: {}", product.getId());
                }
              } catch (HttpClientErrorException e) {
                logger.error("Error while getting response from Fitatu API");
              }
            });

    return productsInformation;
  }

  private ProductInformation processProductInformationResponse(String body) {
    try {
      return mapper.readValue(body, ProductInformation.class);
    } catch (JsonProcessingException e) {
      logger.error("Error while processing response to ProductInformation");
      return null;
    }
  }

  public List<ProductModel> getFitatuGlobalData() {
    var products = new ArrayList<ProductModel>();

    for (var i = 17; i < 102; i++) {
      logger.info("Getting data for category: {}", i);
      products.addAll(getProductsForCategory(i));
    }

    return products;
  }

  private List<ProductModel> getProductsForCategory(int categoryId) {
    var products = new ArrayList<ProductModel>();

    for (var j = 0; j < 12; j++) {
      products.addAll(getProductData(categoryId, j));
    }

    return products;
  }

  private List<ProductModel> getProductData(int categoryId, int page) {
    var products = new ArrayList<ProductModel>();

    try {
      ResponseEntity<String> response = getFitatuGlobalResponse(categoryId, page);
      if (response.getStatusCode() == HttpStatus.OK) {
        products.addAll(processResponse(response.getBody(), categoryId, page));
      }
    } catch (HttpClientErrorException e) {
      logger.error("Error while getting response from Fitatu API");
    }

    return products;
  }

  private List<ProductModel> processResponse(String responseBody, int categoryId, int page) {
    var products = new ArrayList<ProductModel>();

    try {
      var productData = mapper.readValue(responseBody, ProductData.class);
      products.addAll(productData.getData());
      logger.info("Got data for category: {} page: {}", categoryId, page);
    } catch (JsonMappingException e) {
      logger.error("Error while mapping response to ProductModel");
    } catch (JsonProcessingException e) {
      logger.error("Error while processing response to ProductModel");
    }

    return products;
  }

  private ResponseEntity<String> getFitatuGlobalResponse(Integer categoryId, Integer page) {
    String url =
        "https://pl-pl.fitatu.com/api/public/resources/categories/"
            + categoryId
            + "/products?limit=100&page="
            + page;

    return getStringResponseEntity(url);
  }

  private ResponseEntity<String> getFitatuProductResponse(String productId) {
    String url = "https://pl-pl.fitatu.com/api/public/resources/products/" + productId;

    return getStringResponseEntity(url);
  }

  private ResponseEntity<String> getStringResponseEntity(String url) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.set("authority", "pl-pl.fitatu.com");
    headers.set("accept-language", "de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7,pl;q=0.6");
    headers.set("api-key", "FITATU-MOBILE-APP");
    headers.set("api-secret", "PYRXtfs88UDJMuCCrNpLV");
    headers.set("app-os", "FITATU-WEB");
    headers.set("content-type", "application/json");
    headers.set("origin", "https://www.fitatu.com");
    headers.set("referer", "https://www.fitatu.com/");
    headers.set(
        "sec-ch-ua",
        "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Google Chrome\";v=\"122\"");
    headers.set("sec-ch-ua-mobile", "?0");
    headers.set("sec-ch-ua-platform", "\"macOS\"");
    headers.set("sec-fetch-dest", "empty");
    headers.set("sec-fetch-mode", "cors");
    headers.set("sec-fetch-site", "same-site");
    headers.set(
        "user-agent",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko)"
            + " Chrome/122.0.0.0 Safari/537.36");
    headers.set("x-auth-token", "1tY95KNZFN");

    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
  }
}
