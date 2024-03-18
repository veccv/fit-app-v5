package com.github.veccvs.fitappv5.product.migration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInformation {
  private String name;
  private double energy;
  private double protein;

  @JsonProperty("vegetableProtein")
  private Double vegetableProtein;

  @JsonProperty("animalProtein")
  private Double animalProtein;

  private double fat;

  @JsonProperty("saturatedFat")
  private double saturatedFat;

  @JsonProperty("polyunsaturatedFat")
  private Double polyunsaturatedFat;

  @JsonProperty("monounsaturatedFat")
  private Double monounsaturatedFat;

  private double carbohydrate;
  private Double cholesterol;
  private Double fiber;
  private double sugars;

  @JsonProperty("categoryId")
  private int categoryId;

  private String brand;
  private String manufacturer;
  private List<Measure> measures;

  @JsonProperty("simpleMeasures")
  private List<Measure> simpleMeasures;

  private List<String> redirect;
}
