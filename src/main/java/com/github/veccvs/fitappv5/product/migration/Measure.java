package com.github.veccvs.fitappv5.product.migration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Measure {
  private String name;

  @JsonProperty("energyPerUnit")
  private Double energyPerUnit;

  @JsonProperty("weightPerUnit")
  private Double weightPerUnit;

  private Double energy;
  private Double weight;
  private Double portion;
  private Double capacity;
}
