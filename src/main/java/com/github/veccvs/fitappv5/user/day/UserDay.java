package com.github.veccvs.fitappv5.user.day;

import com.github.veccvs.fitappv5.product.CustomProduct;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_day")
public class UserDay implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  private LocalDate date;

  @OneToMany(mappedBy = "userDayId", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CustomProduct> products = new ArrayList<>();

  @Column(name = "user_id")
  private UUID userId;
}
