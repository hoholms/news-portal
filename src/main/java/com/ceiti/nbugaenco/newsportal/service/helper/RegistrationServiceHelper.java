package com.ceiti.nbugaenco.newsportal.service.helper;

import static org.springframework.data.util.Predicates.negate;

import com.ceiti.nbugaenco.newsportal.exception.NewsPortalException;
import com.ceiti.nbugaenco.newsportal.model.request.RegistrationRequest;
import com.ceiti.nbugaenco.newsportal.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceHelper {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public String toUsername(RegistrationRequest registrationRequest) {
    return Optional.ofNullable(registrationRequest)
        .map(RegistrationRequest::getUsername)
        .filter(negate(userService::existsByUsername))
        .orElseThrow(
            () -> new NewsPortalException("This user already exists", HttpStatus.CONFLICT));
  }

  public String toPassword(RegistrationRequest registrationRequest) {
    return Optional.ofNullable(registrationRequest)
        .map(RegistrationRequest::getPassword)
        .map(passwordEncoder::encode)
        .orElseThrow(() -> new NewsPortalException("Failed password encoding",
            HttpStatus.INTERNAL_SERVER_ERROR));
  }
}
