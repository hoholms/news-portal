package com.ceiti.nbugaenco.newsportal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NewsPortalException extends RuntimeException {

  private final HttpStatus status;

  public NewsPortalException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
