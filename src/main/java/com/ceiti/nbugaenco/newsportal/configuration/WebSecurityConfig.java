package com.ceiti.nbugaenco.newsportal.configuration;

import com.ceiti.nbugaenco.newsportal.exception.AccessDeniedHandlerImpl;
import com.ceiti.nbugaenco.newsportal.exception.AuthenticationEntryPointImpl;
import com.ceiti.nbugaenco.newsportal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

  public static final String NEWS_API = "/news/**";
  public static final String ADMIN = "ADMIN";

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .exceptionHandling()
        .accessDeniedHandler(new AccessDeniedHandlerImpl())
        .authenticationEntryPoint(new AuthenticationEntryPointImpl())
        .and()
        .authorizeHttpRequests(requests -> requests
            .requestMatchers("/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
            .permitAll()
            .requestMatchers(HttpMethod.GET, NEWS_API).permitAll()
            .requestMatchers(HttpMethod.POST, NEWS_API).authenticated()
            .requestMatchers(HttpMethod.DELETE, NEWS_API).hasAuthority(ADMIN)
            .anyRequest().authenticated()
        )
        .rememberMe()
        .and()
        .logout()
        .logoutUrl("/login?logout")
        .logoutSuccessUrl("/login")
        .and();

    return http.build();
  }

  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService)
        .passwordEncoder(passwordEncoder);
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .build();
  }

  static class PWEncoder {

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
  }
}
