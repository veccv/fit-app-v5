package com.github.veccvs.fitappv5.product;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class CustomProduct {
  private String name;
  private String protein;
  private String carbs;
  private String fat;
  private String sugar;
}
