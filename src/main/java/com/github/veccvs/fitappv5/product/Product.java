package com.github.veccvs.fitappv5.product;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "protein")
  private String protein;

  @Column(name = "carbs")
  private String carbs;

  @Column(name = "fat")
  private String fat;

  @Column(name = "sugar")
  private String sugar;

  private String fitatuId;

  private String calories;

  private String weight;

  public Product(
      String name,
      double protein,
      double carbohydrate,
      double fat,
      double sugars,
      double energy,
      double weight) {
    this.name = name;
    this.protein = String.valueOf(protein);
    this.carbs = String.valueOf(carbohydrate);
    this.fat = String.valueOf(fat);
    this.sugar = String.valueOf(sugars);
    this.calories = String.valueOf(energy);
    this.weight = String.valueOf(weight);
  }
}
