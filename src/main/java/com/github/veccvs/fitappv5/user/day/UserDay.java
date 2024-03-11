package com.github.veccvs.fitappv5.user.day;

import com.github.veccvs.fitappv5.product.CustomProduct;
import com.github.veccvs.fitappv5.user.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_day")
public class UserDay {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  private LocalDate date;

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "userday_id"))
  private List<CustomProduct> breakfastProducts = new ArrayList<>();

  @ElementCollection
  @CollectionTable(joinColumns = @JoinColumn(name = "userday_id"))
  private List<CustomProduct> lunchProducts = new ArrayList<>();
}
