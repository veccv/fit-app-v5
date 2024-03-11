package com.github.veccvs.fitappv5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class FitAppV5Application {
  public static void main(String[] args) {
    SpringApplication.run(FitAppV5Application.class, args);
  }
}
