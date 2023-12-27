package com.github.veccvs.fitappv5.user;

import com.github.veccvs.fitappv5.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final AuthenticationService authenticationService;

  public User getUserInfo() {
    return userRepository.findByEmail(authenticationService.getUserLogin()).orElseThrow();
  }
}
