package com.github.veccvs.fitappv5.user.day;

import com.github.veccvs.fitappv5.product.CustomProduct;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/day")
@RequiredArgsConstructor
public class UserDayController {
  private final UserDayService userDayService;

  @PostMapping
  public ResponseEntity<UserDay> createUserDay(@RequestBody LocalDate date) {
    return ResponseEntity.ok(userDayService.createUserDay(date));
  }

  @PutMapping
  public ResponseEntity<UserDay> addProductToDay(
      @RequestParam Integer userDayId,
      @RequestParam DayTime dayTime,
      @RequestBody CustomProduct customProduct) {
    return ResponseEntity.ok(userDayService.addProductToDay(userDayId, dayTime, customProduct));
  }

  @GetMapping
  public ResponseEntity<UserDay> getDay(@RequestParam Integer id) {
    return ResponseEntity.ok(userDayService.getDayById(id));
  }
}
