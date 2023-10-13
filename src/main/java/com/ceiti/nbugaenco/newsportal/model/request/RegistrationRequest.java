package com.ceiti.nbugaenco.newsportal.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegistrationRequest {

  @NotBlank(message = "Please provide a username")
  @Length(max = 32, message = "Username is too long")
  private String username;

  @NotBlank(message = "Please provide a password")
  @Length(min = 8, max = 64, message = "Password must be at least 8 characters")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$", message = "Password must have at least 1 number and 1 letter")
  private String password;

}
