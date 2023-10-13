package com.ceiti.nbugaenco.newsportal.service;

import com.ceiti.nbugaenco.newsportal.exception.NewsPortalException;
import com.ceiti.nbugaenco.newsportal.model.Authority;
import com.ceiti.nbugaenco.newsportal.model.User;
import com.ceiti.nbugaenco.newsportal.model.request.RegistrationRequest;
import com.ceiti.nbugaenco.newsportal.service.helper.RegistrationServiceHelper;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

  private final UserService userService;
  private final RegistrationServiceHelper registrationServiceHelper;

  public User register(RegistrationRequest registrationRequest) {
    return Optional.ofNullable(registrationRequest)
        .map(this::toUser)
        .map(userService::save)
        .orElseThrow(
            () -> new NewsPortalException("Registration failed", HttpStatus.INTERNAL_SERVER_ERROR));
  }

  private User toUser(RegistrationRequest registrationRequest) {
    return new User()
        .withId(UUID.randomUUID())
        .withUsername(registrationServiceHelper.toUsername(registrationRequest))
        .withPassword(registrationServiceHelper.toPassword(registrationRequest))
        .withAuthority(Collections.singleton(Authority.USER))
        .withEnabled(Boolean.TRUE);
  }
}
