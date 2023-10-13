package com.ceiti.nbugaenco.newsportal.controller;

import com.ceiti.nbugaenco.newsportal.model.User;
import com.ceiti.nbugaenco.newsportal.model.request.LoginRequest;
import com.ceiti.nbugaenco.newsportal.model.request.RegistrationRequest;
import com.ceiti.nbugaenco.newsportal.service.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class LoginController {

  private final AuthenticationManager authenticationManager;
  private final RegistrationService registrationService;

  @GetMapping("/principal")
  public ResponseEntity<Principal> getPrincipal(Principal principal) {
    return new ResponseEntity<>(principal, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<Authentication> loginUser(@Valid @RequestBody LoginRequest loginRequest,
      HttpServletRequest request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getUsername(),
              loginRequest.getPassword()
          ));

      SecurityContext securityContext = SecurityContextHolder.getContext();
      securityContext.setAuthentication(authentication);

      HttpSession session = request.getSession(true);
      session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

      log.info("User {} logged in", loginRequest.getUsername());

      return new ResponseEntity<>(authentication, HttpStatus.OK);
    } catch (BadCredentialsException e) {
      log.warn("Failed login attempt for user: {}", loginRequest.getUsername());
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(
      @Valid @RequestBody RegistrationRequest registrationRequest) {
    return ResponseEntity.ok(registrationService.register(registrationRequest));
  }

  @PostMapping("/logout")
  @ResponseStatus(HttpStatus.OK)
  public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
      log.info("User {} logged out", auth.getName());
    }
  }
}