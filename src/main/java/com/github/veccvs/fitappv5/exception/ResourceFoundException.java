package com.github.veccvs.fitappv5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ResourceFoundException extends RuntimeException {
  public ResourceFoundException(String message) {
    super(message);
  }
}
