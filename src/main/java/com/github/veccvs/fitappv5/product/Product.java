package com.github.veccvs.fitappv5.product;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
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
}
