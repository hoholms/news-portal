package com.ceiti.nbugaenco.newsportal.exception;

import com.ceiti.nbugaenco.newsportal.util.CommonUtils;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> globalExceptionHandling(Exception e) {
    log.error(e.getMessage());
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationErrors(
      MethodArgumentNotValidException e) {
    return new ResponseEntity<>(CommonUtils.getErrors(e.getBindingResult()),
        HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handlerHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body(new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage()));
  }

  @ExceptionHandler(NewsPortalException.class)
  public ResponseEntity<ErrorResponse> handlerNewsPortalException(NewsPortalException e) {
    log.error(e.getMessage());
    return ResponseEntity.status(e.getStatus())
        .body(new ErrorResponse(e.getStatus().value(), e.getMessage()));
  }
}
