package com.github.veccvs.fitappv5.product;

import com.github.veccvs.fitappv5.user.day.DayTime;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class CustomProductDao {
  private String name;
  private String protein;
  private String carbs;
  private String fat;
  private String sugar;
  private DayTime dayTime;
  private Integer userDayId;
}
