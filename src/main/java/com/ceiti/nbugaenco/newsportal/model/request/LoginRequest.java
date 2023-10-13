package com.ceiti.nbugaenco.newsportal.model.request;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Data;

@Data
public class LoginRequest implements Serializable {

  @NotBlank(message = "Please provide a username")
  private final String username;

  @NotBlank(message = "Please provide a password")
  private final String password;
}