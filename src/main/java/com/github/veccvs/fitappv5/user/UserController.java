package com.github.veccvs.fitappv5.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public ResponseEntity<User> getUserInfo() {
    return ResponseEntity.ok(userService.getUserInfo());
  }
}
