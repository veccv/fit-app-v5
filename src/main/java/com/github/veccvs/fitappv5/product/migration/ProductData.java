package com.github.veccvs.fitappv5.product.migration;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductData {
  private List<ProductModel> data;
  private int dataCount;
}
