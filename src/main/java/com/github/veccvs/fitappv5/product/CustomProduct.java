package com.github.veccvs.fitappv5.product;

import com.github.veccvs.fitappv5.user.day.DayTime;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class CustomProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String protein;
  private String carbs;
  private String fat;
  private String sugar;
  private DayTime dayTime;

  @JoinColumn(name = "userday_id")
  private Integer userDayId;
}
