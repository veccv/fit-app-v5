package com.github.veccvs.fitappv5.user.day;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/day")
@RequiredArgsConstructor
public class UserDayController {
  private final UserDayService userDayService;

  @PostMapping
  public ResponseEntity<UserDay> createUserDay(
      @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return ResponseEntity.ok(userDayService.createUserDay(date));
  }
  
  @PutMapping
  public ResponseEntity<UserDay> addProductsToDay() {
    return ResponseEntity.ok(userDayService.addProductsToDay());
  }

  @GetMapping
  public ResponseEntity<UserDay> getDay(@RequestParam Integer id) {
    return ResponseEntity.ok(userDayService.getDayById(id));
  }

  @GetMapping("/date")
  public ResponseEntity<UserDay> getDay(@RequestParam LocalDate date) {
    return ResponseEntity.ok(userDayService.getDayByDate(date));
  }
}
