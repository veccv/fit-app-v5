package com.github.veccvs.fitappv5.auth;

import com.github.veccvs.fitappv5.config.JwtService;
import com.github.veccvs.fitappv5.exception.ResourceFoundException;
import com.github.veccvs.fitappv5.exception.ResourceNotFoundException;
import com.github.veccvs.fitappv5.user.Role;
import com.github.veccvs.fitappv5.user.User;
import com.github.veccvs.fitappv5.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user =
        new User(
            request.getLastname(),
            request.getFirstname(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword()),
            Role.USER);

    if (!userRepository.findAllByEmail(user.getEmail()).isEmpty()
        && userRepository.findByEmail(user.getEmail()).isPresent())
      throw new ResourceFoundException(
          "User with email [%s] already exist.".formatted(user.getEmail()));

    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse refreshToken() {
    var userLogin = getUserLogin();
    var user = userRepository.findByEmail(userLogin).orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public String getUserLogin() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      return authentication.getName();
    } else {
      throw new ResourceNotFoundException("Logged user login not found");
    }
  }
}
