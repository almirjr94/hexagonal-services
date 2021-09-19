package com.myorg.car.adapters.inbound.controllers.handler;

import com.myorg.car.application.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class StandardExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(
      ObjectNotFoundException e, HttpServletRequest request) {

    StandardError err =
        new StandardError(
            HttpStatus.NOT_FOUND.value(),
            e.getMessage(),
            request.getRequestURI(),
            System.currentTimeMillis());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<StandardError> badRequest(
          Exception e, HttpServletRequest request) {

    StandardError err =
            new StandardError(
                    HttpStatus.BAD_REQUEST.value(),
                    e.getMessage(),
                    request.getRequestURI(),
                    System.currentTimeMillis());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }
}
